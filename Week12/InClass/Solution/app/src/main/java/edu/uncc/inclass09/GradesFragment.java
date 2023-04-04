package edu.uncc.inclass09;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

import edu.uncc.inclass09.databinding.FragmentGradesBinding;
import edu.uncc.inclass09.databinding.GradeRowItemBinding;

public class GradesFragment extends Fragment {
    public GradesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    FragmentGradesBinding binding;
    GradeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<Grade> mGrades = new ArrayList<>();
    AppDatabase db;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Grades");
        adapter = new GradeAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        setHasOptionsMenu(true);

        //get the grades ..

        db = Room.databaseBuilder(getActivity(), AppDatabase.class, "grades-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        loadAndDisplayData();

    }

    void loadAndDisplayData(){
        mGrades.clear();
        mGrades.addAll(db.gradeDao().getAll());
        adapter.notifyDataSetChanged();
        calculateGPA();
    }

    private void calculateGPA(){
        if(mGrades.size() == 0){
            binding.textViewGPA.setText("GPA: 4.0");
            binding.textViewHours.setText("Hours: 0.0");
        } else {
            double acc = 0.0;
            double hours = 0.0;
            for (Grade grade: mGrades) {
                double letterGrade = 0.0;
                if(grade.getLetterGrade().equals("A")){
                    letterGrade = 4.0;
                } else if(grade.getLetterGrade().equals("B")){
                    letterGrade = 3.0;
                } else if(grade.getLetterGrade().equals("C")){
                    letterGrade = 2.0;
                } else if(grade.getLetterGrade().equals("D")){
                    letterGrade = 1.0;
                }
                acc = acc + grade.getCourseHours() * letterGrade;
                hours = hours + grade.getCourseHours();
            }

            if(hours == 0) {
                binding.textViewGPA.setText("GPA: 4.0");
                binding.textViewHours.setText("Hours: 0.0");
            } else {
                acc = acc / hours;
                binding.textViewGPA.setText("GPA: " + String.format("%.2f", acc));
                binding.textViewHours.setText("Hours: " + String.format("%.2f", hours));
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            mListener.gotoAddCourse();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHolder>{
        @NonNull
        @Override
        public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            GradeRowItemBinding rowBinding = GradeRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new GradeViewHolder(rowBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
            Grade grade = mGrades.get(position);
            holder.setupUI(grade);
        }

        @Override
        public int getItemCount() {
            return mGrades.size();
        }

        class GradeViewHolder extends RecyclerView.ViewHolder{
            GradeRowItemBinding mBinding;
            Grade mGrade;
            public GradeViewHolder(@NonNull GradeRowItemBinding rowBinding) {
                super(rowBinding.getRoot());
                this.mBinding = rowBinding;
            }
            public void setupUI(Grade grade){
                this.mGrade = grade;
                mBinding.textViewCourseHours.setText(mGrade.getCourseHours() + "Credit Hours");
                mBinding.textViewCourseName.setText(mGrade.getCourseName());
                mBinding.textViewCourseLetterGrade.setText(mGrade.getLetterGrade());
                mBinding.textViewCourseNumber.setText(mGrade.getCourseNumber());
                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.gradeDao().delete(mGrade);
                        loadAndDisplayData();
                    }
                });
            }
        }
    }

    GradesListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof GradesListener) {
            mListener = (GradesListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GradesListener");
        }
    }

    interface GradesListener{
        void gotoAddCourse();
    }
}