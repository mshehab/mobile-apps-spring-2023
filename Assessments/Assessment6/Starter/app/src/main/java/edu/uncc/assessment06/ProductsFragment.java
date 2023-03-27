package edu.uncc.assessment06;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.assessment06.databinding.FragmentProductsBinding;
import edu.uncc.assessment06.databinding.ProductRowItemBinding;
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
    ProductsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Products");
        adapter = new ProductsAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
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

    class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductViewHolder(ProductRowItemBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product product = mProducts.get(position);
            holder.setupUI(product);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }

        class ProductViewHolder extends RecyclerView.ViewHolder{
            ProductRowItemBinding mBinding;
            Product mProduct;
            public ProductViewHolder(ProductRowItemBinding rowItemBinding) {
                super(rowItemBinding.getRoot());
                mBinding = rowItemBinding;
            }

            void setupUI(Product product){
                this.mProduct = product;
                mBinding.textViewProductName.setText(product.getName());
                mBinding.textViewProductPrice.setText("$" + product.getPrice());
                Picasso.get().load(product.getImg_url()).into(mBinding.imageViewProductIcon);
            }
        }
    }
}