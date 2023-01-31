package com.example.inclass04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass04.databinding.FragmentDepartmentBinding;


public class DepartmentFragment extends Fragment {
    public DepartmentFragment() {
        // Required empty public constructor
    }

    FragmentDepartmentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDepartmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Department");

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelDepartment();
            }
        });

        binding.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = binding.radioGroup.getCheckedRadioButtonId();
                String department = "Computer Science";
                if(selectedId == R.id.radioButtonSIS){
                    department = "Software and Info Systems";
                } else if(selectedId == R.id.radioButtonBIO){
                    department = "Bio Informatics";
                } else if(selectedId == R.id.radioButtonDS){
                    department = "Data Science";
                }
                mListener.sendDepartment(department);
            }
        });

    }

    DepartmentFragmentListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DepartmentFragmentListener) context;
    }

    interface DepartmentFragmentListener{
        void sendDepartment(String department);
        void cancelDepartment();
    }

}