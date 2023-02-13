package com.example.assessment3;

<<<<<<< HEAD
import android.os.Bundle;

=======
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
>>>>>>> 9871bc85aa04e30eee1f81dd1131f6df5659fedb
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

=======
import android.widget.Toast;

import com.example.assessment3.databinding.FragmentRegistrationBinding;


public class RegistrationFragment extends Fragment {
>>>>>>> 9871bc85aa04e30eee1f81dd1131f6df5659fedb
    public RegistrationFragment() {
        // Required empty public constructor
    }

<<<<<<< HEAD
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }
=======
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

>>>>>>> 9871bc85aa04e30eee1f81dd1131f6df5659fedb
}