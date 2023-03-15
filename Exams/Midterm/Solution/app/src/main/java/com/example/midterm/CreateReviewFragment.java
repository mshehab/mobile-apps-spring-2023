package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.midterm.databinding.FragmentCreateReviewBinding;
import com.example.midterm.models.Product;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateReviewFragment extends Fragment {
    private static final String ARG_PARAM_PRODUCT = "ARG_PARAM_PRODUCT";
    Product mProduct;

    public static CreateReviewFragment newInstance(Product product) {
        CreateReviewFragment fragment = new CreateReviewFragment();
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
    public CreateReviewFragment() {
        // Required empty public constructor
    }



    FragmentCreateReviewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateReviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    OkHttpClient client = new OkHttpClient();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewProductName.setText(mProduct.getName());
        binding.textViewProductPrice.setText("$" + mProduct.getPrice());
        Picasso.get().load(mProduct.getImg_url()).into(binding.imageViewProductIcon);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = binding.editTextReview.getText().toString();
                int selectedRating = binding.radioGroup.getCheckedRadioButtonId();
                int rating = 5;
                if (selectedRating == R.id.radioButton_level_1) {
                    rating = 1;
                } else if (selectedRating == R.id.radioButton_level_2) {
                    rating = 2;
                } else if (selectedRating == R.id.radioButton_level_3) {
                    rating = 3;
                } else if (selectedRating == R.id.radioButton_level_4) {
                    rating = 4;
                } else if (selectedRating == R.id.radioButton_level_5) {
                    rating = 5;
                }

                if(review.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a review !!", Toast.LENGTH_SHORT).show();
                } else {

                    RequestBody formBody = new FormBody.Builder()
                            .add("pid", mProduct.getPid())
                            .add("rating", String.valueOf(rating))
                            .add("review", review)
                            .build();
                    Request request = new Request.Builder()
                            .url("https://www.theappsdr.com/api/product/review")
                            .post(formBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if(response.isSuccessful()){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mListener.onReviewCreated();
                                    }
                                });

                            } else {

                            }
                        }
                    });

                }
            }
        });
   }

    CreateReviewListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CreateReviewListener) {
            mListener = (CreateReviewListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CreateReviewListener");
        }
    }

    interface CreateReviewListener {
       void onReviewCreated();
   }
}