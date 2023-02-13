package com.example.assessment3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assessment3.databinding.FragmentSetGenderBinding;

public class SetGenderFragment extends Fragment {

    public SetGenderFragment() {
        // Required empty public constructor
    }

    FragmentSetGenderBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSetGenderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Set Gender");


        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelSetGender();
            }
        });

        binding.buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "Female";
                if(binding.radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMale){
                    gender = "Male";
                }
                mListener.sendSelectedGender(gender);
            }
        });
    }

    SetGenderListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SetGenderListener) context;
    }

    interface SetGenderListener{
        void sendSelectedGender(String gender);
        void cancelSetGender();
    }


}