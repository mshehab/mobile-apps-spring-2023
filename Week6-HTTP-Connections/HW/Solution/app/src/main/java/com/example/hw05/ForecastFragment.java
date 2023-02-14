package com.example.hw05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hw05.databinding.ForecastListItemBinding;
import com.example.hw05.databinding.FragmentForecastBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForecastFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private DataService.City mCity;

    public ForecastFragment() {
        // Required empty public constructor
    }

    public static ForecastFragment newInstance(DataService.City city) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (DataService.City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    FragmentForecastBinding binding;
    ForecastAdapter adapter;
    ArrayList<Forecast> forecasts = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForecastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Weather Forecast");
        adapter = new ForecastAdapter(getActivity(), R.layout.forecast_list_item, forecasts);
        binding.listView.setAdapter(adapter);
        getForecast();
    }

    private final OkHttpClient client = new OkHttpClient();
    private void getForecast(){
        HttpUrl url = HttpUrl.parse("https://api.openweathermap.org/data/2.5/forecast").newBuilder()
                .addQueryParameter("appid", "PUT_YOUR_KEY_HERE")
                .addQueryParameter("units", "imperial")
                .addQueryParameter("lon", String.valueOf(mCity.getLon()))
                .addQueryParameter("lat", String.valueOf(mCity.getLat()))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonList = jsonObject.getJSONArray("list");

                        forecasts.clear();

                        for (int i = 0; i < jsonList.length() ; i++) {
                            JSONObject forecastJsonObject = jsonList.getJSONObject(i);
                            Forecast forecast = new Forecast(forecastJsonObject);
                            forecasts.add(forecast);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                } else {

                }
            }
        });


    }

    class ForecastAdapter extends ArrayAdapter<Forecast>{
        public ForecastAdapter(@NonNull Context context, int resource, @NonNull List<Forecast> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ForecastListItemBinding mBinding;
            if(convertView == null){
                mBinding = ForecastListItemBinding.inflate(getLayoutInflater(), parent, false);
                convertView = mBinding.getRoot();
                convertView.setTag(mBinding);
            } else {
                mBinding = (ForecastListItemBinding) convertView.getTag();
            }

            Forecast forecast = getItem(position);

            mBinding.textViewDateTime.setText(forecast.getDt_txt());
            mBinding.textViewTemp.setText(forecast.getTemp() + "F");
            mBinding.textViewTempMax.setText("Max:" + forecast.getTemp_max() + "F");
            mBinding.textViewTempMin.setText("Min:" + forecast.getTemp_min() + "F");
            mBinding.textViewHumidity.setText("Humidity: " + forecast.getHumidity() + "%");
            mBinding.textViewDesc.setText(forecast.getDescription());

            Picasso.get().load(forecast.getIconUrl()).into(mBinding.imageViewWeatherIcon);

            return convertView;
        }
    }


}