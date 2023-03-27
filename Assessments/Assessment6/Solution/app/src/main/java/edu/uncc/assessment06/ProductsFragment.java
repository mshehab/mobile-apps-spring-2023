package edu.uncc.assessment06;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

        binding.buttonGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoCart();
            }
        });

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.logout();
            }
        });

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

                mBinding.imageViewAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference docRef = db.collection("carts").document();

                        HashMap<String, Object> data = new HashMap<>();
                        data.put("icon_url", mProduct.getImg_url());
                        data.put("name", mProduct.getName());

                        try{
                            data.put("price", Double.valueOf(mProduct.getPrice()));
                        } catch (NumberFormatException ex){
                            data.put("price", 0.0);
                        }
                        data.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        data.put("docId", docRef.getId());

                        docRef.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getActivity(), "Added successfully!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                });

            }
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
        void logout();
        void gotoCart();
    }
}