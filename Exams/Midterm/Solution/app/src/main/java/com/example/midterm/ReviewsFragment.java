package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.midterm.databinding.FragmentReviewsBinding;
import com.example.midterm.databinding.RowItemReviewBinding;
import com.example.midterm.models.Product;
import com.example.midterm.models.Review;
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

public class ReviewsFragment extends Fragment {
    private static final String ARG_PARAM_PRODUCT = "ARG_PARAM_PRODUCT";
    Product mProduct;
    public ReviewsFragment() {
        // Required empty public constructor
    }
    public static ReviewsFragment newInstance(Product product) {
        ReviewsFragment fragment = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProduct = (Product) getArguments().getSerializable(ARG_PARAM_PRODUCT);
        }
    }

    FragmentReviewsBinding binding;
    ArrayList<Review> mReviews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ReviewAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textViewProductName.setText(mProduct.getName());
        binding.textViewProductPrice.setText("$" + mProduct.getPrice());
        Picasso.get().load(mProduct.getImg_url()).into(binding.imageViewProductIcon);

        adapter = new ReviewAdapter(getActivity(), R.layout.row_item_review, mReviews);
        binding.listView.setAdapter(adapter);
        getReviews();

        binding.buttonCreateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.createReview(mProduct);
            }
        });
    }

    OkHttpClient client = new OkHttpClient();
    private void getReviews(){
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/product/reviews/" + mProduct.getPid())
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
                        mReviews.clear();
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonArray = jsonObject.getJSONArray("reviews");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject reviewJsonObject = jsonArray.getJSONObject(i);
                            Review review = new Review(reviewJsonObject);
                            mReviews.add(review);
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

    class ReviewAdapter extends ArrayAdapter<Review>{

        public ReviewAdapter(@NonNull Context context, int resource, @NonNull List<Review> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            RowItemReviewBinding rowItemReviewBinding;

            if(convertView == null){
                rowItemReviewBinding = RowItemReviewBinding.inflate(getLayoutInflater(), parent, false);
                convertView = rowItemReviewBinding.getRoot();
                convertView.setTag(rowItemReviewBinding);
            } else {
                rowItemReviewBinding = (RowItemReviewBinding) convertView.getTag();
            }

            Review review = getItem(position);
            rowItemReviewBinding.textViewReview.setText(review.getReview());

            if(review.getRating().equals("1")){
                rowItemReviewBinding.imageViewReviewRating.setImageResource(R.drawable.stars_1);
            } else if(review.getRating().equals("2")){
                rowItemReviewBinding.imageViewReviewRating.setImageResource(R.drawable.stars_2);
            } else if(review.getRating().equals("3")){
                rowItemReviewBinding.imageViewReviewRating.setImageResource(R.drawable.stars_3);
            } else if(review.getRating().equals("4")){
                rowItemReviewBinding.imageViewReviewRating.setImageResource(R.drawable.stars_4);
            } else if(review.getRating().equals("5")){
                rowItemReviewBinding.imageViewReviewRating.setImageResource(R.drawable.stars_5);
            }

            rowItemReviewBinding.textViewReviewDate.setText(review.getCreated_at());

            return convertView;
        }
    }

    ReviewsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ReviewsListener){
            mListener = (ReviewsListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ReviewsListener");
        }
    }

    interface ReviewsListener{
        void createReview(Product product);
    }

}