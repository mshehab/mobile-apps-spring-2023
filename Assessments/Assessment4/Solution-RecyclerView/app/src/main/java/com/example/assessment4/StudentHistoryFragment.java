package com.example.assessment4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        adapter = new CourseHistoryAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }

    class CourseHistoryAdapter extends RecyclerView.Adapter<CourseHistoryAdapter.CourseHistoryViewHolder>{

        @NonNull
        @Override
        public CourseHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            HistoryRowItemBinding itemBinding = HistoryRowItemBinding.inflate(getLayoutInflater(), parent,false);
            return new CourseHistoryViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseHistoryViewHolder holder, int position) {
            CourseHistory courseHistory = courseHistories.get(position);
            holder.setupUI(courseHistory);
        }

        @Override
        public int getItemCount() {
            return courseHistories.size();
        }

        class CourseHistoryViewHolder extends RecyclerView.ViewHolder{
            HistoryRowItemBinding mBinding;
            public CourseHistoryViewHolder(HistoryRowItemBinding vhBinding) {
                super(vhBinding.getRoot());
                mBinding = vhBinding;
            }

            void setupUI(CourseHistory courseHistory){
                mBinding.textViewCourseName.setText(courseHistory.getName());
                mBinding.textViewCourseNumber.setText(courseHistory.getNumber());
                mBinding.textViewCourseLetterGrade.setText(courseHistory.getLetterGrade());
                mBinding.textViewCourseHours.setText(String.valueOf(courseHistory.getHours()) + " Hours");
            }
        }
    }
}