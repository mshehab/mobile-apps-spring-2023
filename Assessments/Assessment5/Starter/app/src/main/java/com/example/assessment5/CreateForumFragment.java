package com.example.assessment5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assessment5.databinding.FragmentCreateForumBinding;

public class CreateForumFragment extends Fragment {
    public CreateForumFragment() {
        // Required empty public constructor
    }

    FragmentCreateForumBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateForumBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Create Forum");
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancelForumCreate();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextForumTitle.getText().toString();
                if(title.isEmpty()) {
                    Toast.makeText(getContext(), "Title is required", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: Create forum using api
                }
            }
        });
    }

    CreateForumListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CreateForumListener) {
            mListener = (CreateForumListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CreateForumListener");
        }
    }

    interface CreateForumListener {
        void cancelForumCreate();
        void completedForumCreate();
    }
}