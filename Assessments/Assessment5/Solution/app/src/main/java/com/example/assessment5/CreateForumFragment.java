package com.example.assessment5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assessment5.databinding.FragmentCreateForumBinding;
import com.example.assessment5.models.Auth;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateForumFragment extends Fragment {
    public CreateForumFragment() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM_AUTH = "ARG_PARAM_AUTH";
    Auth mAuth;
    public static CreateForumFragment newInstance(Auth auth) {
        CreateForumFragment fragment = new CreateForumFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_AUTH, auth);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAuth = (Auth) getArguments().getSerializable(ARG_PARAM_AUTH);
        }
    }

    FragmentCreateForumBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateForumBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private final OkHttpClient client = new OkHttpClient();
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
                    RequestBody formBody = new FormBody.Builder()
                            .add("title", title)
                            .build();
                    Request request = new Request.Builder()
                            .url("https://www.theappsdr.com/api/thread/add")
                            .addHeader("Authorization", "BEARER "  + mAuth.getToken())
                            .post(formBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if(response.isSuccessful()){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mListener.completedForumCreate();
                                    }
                                });
                            } else {
                                Log.d("demo", "onResponse: error ");
                            }
                        }
                    });
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