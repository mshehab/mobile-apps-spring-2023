package com.example.hw04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hw04.databinding.FragmentSetProfileBinding;


public class SetProfileFragment extends Fragment {
    public SetProfileFragment() {
        // Required empty public constructor
    }

    FragmentSetProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSetProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Set Weight/Gender");
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelProfile();
            }
        });

        binding.buttonSetWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double weight = Double.valueOf(binding.editTextWeight.getText().toString());
                    if(weight > 0){
                        String gender = "Female";
                        if(binding.radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale){
                            gender = "Male";
                        }
                        Profile profile = new Profile(weight, gender);
                        mListener.sendProfile(profile);
                    } else {
                        Toast.makeText(getActivity(), "Enter valid weight!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException ex){
                    Toast.makeText(getActivity(), "Enter valid weight!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    SetProfileFragmentListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SetProfileFragmentListener) context;
    }

    interface SetProfileFragmentListener{
        void cancelProfile();
        void sendProfile(Profile profile);
    }
}