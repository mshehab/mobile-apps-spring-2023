package com.example.inclass06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass06.databinding.FragmentDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    private static final String ARG_PARAM_CONTACT = "ARG_PARAM_CONTACT";

    private Contact mContact;


    public DetailsFragment() {
        // Required empty public constructor
    }


    public static DetailsFragment newInstance(Contact contact) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = (Contact) getArguments().getSerializable(ARG_PARAM_CONTACT);
        }
    }

    FragmentDetailsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Contact Details");

        binding.textViewName.setText(mContact.getName());
        binding.textViewEmail.setText(mContact.getEmail());
        binding.textViewPhone.setText(mContact.getPhone());
        binding.textViewPhoneType.setText(mContact.getPhoneType());

    }
}