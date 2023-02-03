package com.example.hw04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.hw04.databinding.FragmentAddDrinkBinding;

public class AddDrinkFragment extends Fragment {
    public AddDrinkFragment() {
        // Required empty public constructor
    }

    FragmentAddDrinkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddDrinkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Drink");


        binding.seekBarAlcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textViewAlcoholPercentage.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        binding.buttonAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double alcohol = binding.seekBarAlcohol.getProgress();
                double size = 1.0;
                if(binding.radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton5oz){
                    size = 5.0;
                } else if(binding.radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton12oz){
                    size = 12.0;
                }

                Drink drink = new Drink(alcohol, size);
                mListener.sendDrink(drink);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelAddDrink();
            }
        });

    }

    AddDrinkFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddDrinkFragmentListener) context;
    }

    interface AddDrinkFragmentListener{
        void sendDrink(Drink drink);
        void cancelAddDrink();
    }

}