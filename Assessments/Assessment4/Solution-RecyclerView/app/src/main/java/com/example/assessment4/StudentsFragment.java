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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assessment4.Models.DataServices;
import com.example.assessment4.Models.Student;
import com.example.assessment4.databinding.FragmentStudentsBinding;

import java.util.ArrayList;

public class StudentsFragment extends Fragment {

    public StudentsFragment() {
        // Required empty public constructor
    }

    FragmentStudentsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    ArrayList<Student> students = new ArrayList<>();

    StudentsAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Students");

        students.clear();
        students.addAll(DataServices.getStudents());
        adapter = new StudentsAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }

    class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>{

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = students.get(position);
            holder.setupUI(student);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        class StudentViewHolder extends RecyclerView.ViewHolder{
            Student mStudent;
            TextView textViewStudentName;
            public StudentViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewStudentName = itemView.findViewById(android.R.id.text1);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.sendSelectedStudent(mStudent);
                    }
                });

            }

            public void setupUI(Student student){
                this.mStudent = student;
                textViewStudentName.setText(student.getName());
            }

        }

    }


    StudentsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (StudentsListener) context;
    }

    interface StudentsListener{
        void sendSelectedStudent(Student student);
    }

}


















