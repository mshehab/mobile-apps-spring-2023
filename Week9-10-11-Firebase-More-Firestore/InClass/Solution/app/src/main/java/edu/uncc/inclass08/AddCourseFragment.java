package edu.uncc.inclass08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import edu.uncc.inclass08.databinding.FragmentAddCourseBinding;

public class AddCourseFragment extends Fragment {
    public AddCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddCourseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Course");
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseNumber = binding.editTextCourseNumber.getText().toString();
                String courseName = binding.editTextCourseName.getText().toString();
                double courseHours = Double.parseDouble(binding.editTextCourseHours.getText().toString());
                int selectedId = binding.radioGroupGrades.getCheckedRadioButtonId();

                if(courseName.isEmpty() || courseNumber.isEmpty() || binding.editTextCourseHours.getText().toString().isEmpty()) {
                   Toast.makeText(getContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else if(selectedId == -1){
                    Toast.makeText(getContext(), "Please select a letter grade !!", Toast.LENGTH_SHORT).show();
                } else {
                    String courseLetterGrade;
                    if(selectedId == R.id.radioButtonA) {
                        courseLetterGrade = "A";
                    } else if(selectedId == R.id.radioButtonB) {
                        courseLetterGrade = "B";
                    } else if(selectedId == R.id.radioButtonC) {
                        courseLetterGrade = "C";
                    } else if(selectedId == R.id.radioButtonD) {
                        courseLetterGrade = "D";
                    } else {
                        courseLetterGrade = "F";
                    }

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    DocumentReference docRef = db.collection("grades").document();
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("hours", courseHours);
                    data.put("letterGrade", courseLetterGrade);
                    data.put("name", courseName);
                    data.put("number", courseNumber);
                    data.put("owner_id", FirebaseAuth.getInstance().getUid());
                    data.put("docId", docRef.getId());

                    docRef.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mListener.doneAddCourse();
                            } else {
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelAddCourse();
            }
        });

    }

    AddCourseListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AddCourseListener) {
            mListener = (AddCourseListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AddCourseListener");
        }
    }

    interface AddCourseListener {
        void doneAddCourse();
        void cancelAddCourse();
    }
}