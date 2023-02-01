package com.example.hw03;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw03.databinding.FragmentViewDrinksBinding;

import java.util.ArrayList;

public class ViewDrinksFragment extends Fragment {
    private static final String ARG_PARAM_DRINKS = "ARG_PARAM_DRINKS";
    private ArrayList<Drink> mDrinks;
    private int currentIndex = 0;

    public ViewDrinksFragment() {
        // Required empty public constructor
    }

    public static ViewDrinksFragment newInstance(ArrayList<Drink> drinks) {
        ViewDrinksFragment fragment = new ViewDrinksFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_DRINKS, drinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDrinks = (ArrayList<Drink>) getArguments().getSerializable(ARG_PARAM_DRINKS);
        }
    }

    FragmentViewDrinksBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("View Drinks");
        currentIndex = 0;
        displayDrink();



        binding.imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex == mDrinks.size() - 1){
                    currentIndex = 0;
                } else {
                    currentIndex++;
                }
                displayDrink();
            }
        });

        binding.imageViewPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrevious();
            }
        });

        binding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrinks.remove(currentIndex);
                if(mDrinks.size() == 0){
                    mListener.closeViewDrinks();
                } else {
                    displayPrevious();
                }
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeViewDrinks();
            }
        });
    }

    void displayPrevious(){
        if(currentIndex == 0){
            currentIndex = mDrinks.size() - 1;
        } else {
            currentIndex--;
        }
        displayDrink();
    }

    private void displayDrink(){
        Drink drink = mDrinks.get(currentIndex);
        binding.textViewDrinksCount.setText("Drink " + (currentIndex + 1) + " out of " + mDrinks.size());
        binding.textViewDrinkSize.setText(String.valueOf(drink.getSize()) + "oz");
        binding.textViewDrinkAlcoholPercent.setText(String.valueOf(drink.getAlcohol()) + "% Alcohol");
        binding.textViewDrinkAddedOn.setText("Added " + drink.getAddedOn().toString());
    }

    ViewDrinksFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ViewDrinksFragmentListener) context;
    }

    interface ViewDrinksFragmentListener{
        void closeViewDrinks();
    }

}