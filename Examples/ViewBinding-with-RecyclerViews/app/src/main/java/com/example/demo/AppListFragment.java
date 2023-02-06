package com.example.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.databinding.AppListItemBinding;
import com.example.demo.databinding.FragmentAppListBinding;

import java.util.ArrayList;


public class AppListFragment extends Fragment {
    public AppListFragment() {
        // Required empty public constructor
    }

    FragmentAppListBinding binding;
    ArrayList<DataServices.App> apps;
    AppsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAppListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Apps List");
        apps = DataServices.getDemoAppList();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AppsAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppViewHolder>{
        @NonNull
        @Override
        public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            AppListItemBinding mBinding = AppListItemBinding.inflate(getLayoutInflater(), parent, false);
            AppViewHolder holder = new AppViewHolder(mBinding);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
            DataServices.App app = apps.get(position);
            holder.setupUI(app);
        }

        @Override
        public int getItemCount() {
            return apps.size();
        }

        class AppViewHolder extends RecyclerView.ViewHolder{
            AppListItemBinding mBinding;
            public AppViewHolder(AppListItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            public void setupUI(DataServices.App app){
                mBinding.textViewAppName.setText(app.getName());
                mBinding.textViewArtistName.setText(app.getArtistName());
                mBinding.textViewReleaseDate.setText(app.getReleaseDate());
            }
        }
    }

}