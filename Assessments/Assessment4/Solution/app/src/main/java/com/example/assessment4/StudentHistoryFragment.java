package com.example.assessment4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.assessment4.Models.CourseHistory;
import com.example.assessment4.Models.Student;
import com.example.assessment4.databinding.FragmentStudentHistoryBinding;
import com.example.assessment4.databinding.HistoryRowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentHistoryFragment extends Fragment {
    private static final String ARG_PARAM_STUDENT = "ARG_PARAM_STUDENT";
    private Student mStudent;

    public StudentHistoryFragment() {
        // Required empty public constructor
    }

    public static StudentHistoryFragment newInstance(Student student) {
        StudentHistoryFragment fragment = new StudentHistoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_STUDENT, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStudent = (Student) getArguments().getSerializable(ARG_PARAM_STUDENT);
        }
    }

    FragmentStudentHistoryBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStudentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<CourseHistory> courseHistories;
    CourseHistoryAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Student History");
        binding.textViewStudentName.setText(mStudent.getName());

        courseHistories = mStudent.getCourses();

        adapter = new CourseHistoryAdapter(getActivity(), R.layout.history_row_item, courseHistories);
        binding.listView.setAdapter(adapter);
    }

    class CourseHistoryAdapter extends ArrayAdapter<CourseHistory>{
        public CourseHistoryAdapter(@NonNull Context context, int resource, @NonNull List<CourseHistory> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            HistoryRowItemBinding itemBinding;
            if(convertView == null){
                itemBinding = HistoryRowItemBinding.inflate(getLayoutInflater(), parent,false);
                convertView = itemBinding.getRoot();
                convertView.setTag(itemBinding);
            } else {
                itemBinding = (HistoryRowItemBinding) convertView.getTag();
            }

            CourseHistory courseHistory = getItem(position);


            itemBinding.textViewCourseName.setText(courseHistory.getName());
            itemBinding.textViewCourseNumber.setText(courseHistory.getNumber());
            itemBinding.textViewCourseLetterGrade.setText(courseHistory.getLetterGrade());
            itemBinding.textViewCourseHours.setText(String.valueOf(courseHistory.getHours()) + " Hours");

            return convertView;
        }
    }
}