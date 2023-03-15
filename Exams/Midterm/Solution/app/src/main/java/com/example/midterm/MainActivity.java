package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.midterm.models.Product;

public class MainActivity extends AppCompatActivity implements ProductsFragment.ProductsListener, ReviewsFragment.ReviewsListener, CreateReviewFragment.CreateReviewListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new ProductsFragment())
                .commit();

    }

    @Override
    public void onProductSelected(Product product) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ReviewsFragment.newInstance(product))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void createReview(Product product) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, CreateReviewFragment.newInstance(product))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onReviewCreated() {
        getSupportFragmentManager().popBackStack();
    }
}