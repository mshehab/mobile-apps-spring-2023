package edu.uncc.inclass08;

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

import java.util.ArrayList;
import java.util.List;

import edu.uncc.inclass08.databinding.FragmentGradesBinding;
import edu.uncc.inclass08.databinding.GradeRowItemBinding;

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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Grades");
        adapter = new GradeAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

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