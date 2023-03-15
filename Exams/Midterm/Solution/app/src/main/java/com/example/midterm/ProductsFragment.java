package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.midterm.databinding.FragmentProductsBinding;
import com.example.midterm.databinding.RowItemProductBinding;
import com.example.midterm.models.Product;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ProductsFragment extends Fragment {
    public ProductsFragment() {
        // Required empty public constructor
    }
    FragmentProductsBinding binding;
    ArrayList<Product> mProducts = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ProductsAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ProductsAdapter(getActivity(), R.layout.row_item_product, mProducts);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = mProducts.get(position);
                mListener.onProductSelected(product);
            }
        });

        getProducts();
    }


    OkHttpClient client = new OkHttpClient();
    private void getProducts(){
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/products")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String body = response.body().string();
                    try {
                        mProducts.clear();
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonArray = jsonObject.getJSONArray("products");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject productJsonObject = jsonArray.getJSONObject(i);
                            Product product = new Product(productJsonObject);
                            mProducts.add(product);
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

    class ProductsAdapter extends ArrayAdapter<Product>{

        public ProductsAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            RowItemProductBinding rowBinding;
            if(convertView == null){
                rowBinding = RowItemProductBinding.inflate(getLayoutInflater(), parent, false);
                convertView = rowBinding.getRoot();
                convertView.setTag(rowBinding);
            } else {
                rowBinding = (RowItemProductBinding) convertView.getTag();
            }

            Product product = getItem(position);
            rowBinding.textViewProductName.setText(product.getName());
            rowBinding.textViewProductDesc.setText(product.getDescription());
            rowBinding.textViewProductPrice.setText("$" + product.getPrice());
            rowBinding.textViewProductReviews.setText(product.getReview_count() + " Reviews");

            Picasso.get().load(product.getImg_url()).into(rowBinding.imageViewProductIcon);

            return convertView;
        }
    }


    ProductsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ProductsListener){
            mListener = (ProductsListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ProductsListener");
        }
    }

    interface ProductsListener{
        void onProductSelected(Product product);
    }







}