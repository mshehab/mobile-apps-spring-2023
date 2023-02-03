package com.example.inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inclass05.databinding.FragmentAppsListBinding;

import java.util.ArrayList;
import java.util.List;

public class AppsListFragment extends Fragment {
    private static final String ARG_PARAM_CATEGORY = "ARG_PARAM_CATEGORY";
    private String mCategory;
    private ArrayList<DataServices.App> appArrayList;
    public AppsListFragment() {
        // Required empty public constructor
    }
    public static AppsListFragment newInstance(String category) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM_CATEGORY);
        }
    }

    FragmentAppsListBinding binding;
    AppsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAppsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appArrayList = DataServices.getAppsByCategory(mCategory);

        adapter = new AppsAdapter(getActivity(), appArrayList);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataServices.App app = appArrayList.get(position);
                mListener.sendSelectedApp(app);
            }
        });

        getActivity().setTitle(mCategory);
    }


    class AppsAdapter extends ArrayAdapter<DataServices.App>{
        public AppsAdapter(@NonNull Context context, @NonNull List<DataServices.App> objects) {
            super(context, R.layout.app_list_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.app_list_item, parent, false);
            }

            TextView textViewAppName = convertView.findViewById(R.id.textViewAppName);
            TextView textViewArtistName = convertView.findViewById(R.id.textViewArtistName);
            TextView textViewReleaseDate = convertView.findViewById(R.id.textViewReleaseDate);

            DataServices.App app = getItem(position);

            textViewAppName.setText(app.getName());
            textViewArtistName.setText(app.getArtistName());
            textViewReleaseDate.setText(app.getReleaseDate());


            return convertView;
        }
    }

    AppsListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppsListListener) context;
    }

    interface AppsListListener{
        void sendSelectedApp(DataServices.App app);
    }

}