package com.example.hw04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw04.databinding.DrinkRowItemBinding;
import com.example.hw04.databinding.FragmentViewDrinksBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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


    DrinksAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("View Drinks");

        binding.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeViewDrinks();
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DrinksAdapter();
        binding.recyclerView.setAdapter(adapter);

        binding.imageViewSortAscAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mDrinks, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink d1, Drink d2) {
                        return (int) (d1.getAlcohol() - d2.getAlcohol());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortDescAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mDrinks, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink d1, Drink d2) {
                        return (int) (-1 * (d1.getAlcohol() - d2.getAlcohol()));
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortAscDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mDrinks, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink d1, Drink d2) {
                        return d1.getAddedOn().compareTo(d2.getAddedOn());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortDescDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mDrinks, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink d1, Drink d2) {
                        return -1 * d1.getAddedOn().compareTo(d2.getAddedOn());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

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

    class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder>{
        @NonNull
        @Override
        public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            DrinkRowItemBinding binding = DrinkRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new DrinkViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
            Drink drink = mDrinks.get(position);
            holder.setupUI(drink);
        }

        @Override
        public int getItemCount() {
            return mDrinks.size();
        }

        class DrinkViewHolder extends RecyclerView.ViewHolder{
            DrinkRowItemBinding mBinding;
            Drink mDrink;
            public DrinkViewHolder(DrinkRowItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            public void setupUI(Drink drink){
                mBinding.textViewAlcoholPercentage.setText(String.valueOf(drink.getAlcohol()) + "% Alcohol");
                mBinding.textViewAlcoholSize.setText(String.valueOf(drink.getSize()) + "oz");
                mBinding.textViewDateAdded.setText("Added " + String.valueOf(drink.getAddedOn().toString()));

                mDrink = drink;

                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrinks.remove(mDrink);

                        if(mDrinks.size() == 0){
                            mListener.closeViewDrinks();
                        } else {
                            notifyDataSetChanged();
                        }
                    }
                });

            }
        }
    }

}