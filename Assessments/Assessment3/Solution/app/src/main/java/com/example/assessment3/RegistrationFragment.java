package com.example.assessment3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assessment3.databinding.FragmentRegistrationBinding;


public class RegistrationFragment extends Fragment {
    public RegistrationFragment() {
        // Required empty public constructor
    }

    FragmentRegistrationBinding binding;
    String selectedGender;

    public void setSelectedGender(String gender){
        this.selectedGender = gender;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Registration");
        if(selectedGender == null){
            binding.textViewSelectedGender.setText("N/A");
        } else {
            binding.textViewSelectedGender.setText(selectedGender);
        }

        binding.buttonSetGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSetGender();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextName.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a name !!", Toast.LENGTH_SHORT).show();
                } else if(selectedGender == null){
                    Toast.makeText(getActivity(), "Select a gender!!", Toast.LENGTH_SHORT).show();
                } else {
                    Profile profile = new Profile(name, selectedGender);
                    mListener.gotoProfile(profile);
                }
            }
        });
    }
    RegistrationListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (RegistrationListener) context;
    }

    interface RegistrationListener{
        void gotoSetGender();
        void gotoProfile(Profile profile);
    }

}