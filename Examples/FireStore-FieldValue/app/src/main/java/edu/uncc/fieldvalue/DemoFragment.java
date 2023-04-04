package edu.uncc.fieldvalue;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import edu.uncc.fieldvalue.databinding.FragmentDemoBinding;

public class DemoFragment extends Fragment {
    public DemoFragment() {
        // Required empty public constructor
    }

    FragmentDemoBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDemoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("count", FieldValue.increment(1));

                db.collection("posts").document("my-doc")
                        .update(data);
            }
        });

        binding.buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("count", FieldValue.increment(-1));

                db.collection("posts").document("my-doc")
                        .update(data);
            }
        });

        binding.buttonServertimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("createdAt", FieldValue.serverTimestamp());

                db.collection("posts").document("my-doc")
                        .update(data);
            }
        });

        binding.buttonUnionArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("likes", FieldValue.arrayUnion("Bob1", "Bob2", "Bob3"));

                db.collection("posts").document("my-doc")
                        .update(data);
            }
        });

        binding.buttonRemoveArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("likes", FieldValue.arrayRemove("Bob1", "Bob2", "Bob3"));

                db.collection("posts").document("my-doc")
                        .update(data);
            }
        });

    }
}