package com.example.inclass05;

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

import com.example.inclass05.databinding.FragmentAppsListBinding;

import java.util.ArrayList;
import java.util.List;

public class AppsListFragment extends Fragment {
    private static final String ARG_PARAM_CATEGORY = "ARG_PARAM_CATEGORY";
    private String mCategory;
    private ArrayList<DataServices.App> appArrayList;
    public AppsListFragment() {
        // Required empty public constructor
    }
    public static AppsListFragment newInstance(String category) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM_CATEGORY);
        }
    }

    FragmentAppsListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAppsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    AppAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appArrayList = DataServices.getAppsByCategory(mCategory);
        adapter = new AppAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        getActivity().setTitle(mCategory);
    }

    AppsListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppsListListener) context;
    }

    class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder>{

        @NonNull
        @Override
        public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.app_list_item, parent, false);
            return new AppViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
            DataServices.App app = appArrayList.get(position);
            holder.setupUI(app);
        }

        @Override
        public int getItemCount() {
            return appArrayList.size();
        }

        class AppViewHolder extends RecyclerView.ViewHolder{
            TextView textViewAppName, textViewArtistName, textViewReleaseDate;
            DataServices.App mApp;
            public AppViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewAppName = itemView.findViewById(R.id.textViewAppName);
                textViewArtistName = itemView.findViewById(R.id.textViewArtistName);
                textViewReleaseDate = itemView.findViewById(R.id.textViewReleaseDate);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.sendSelectedApp(mApp);
                    }
                });

            }

            void setupUI(DataServices.App app){
                mApp = app;
                textViewAppName.setText(mApp.getName());
                textViewArtistName.setText(mApp.getArtistName());
                textViewReleaseDate.setText(mApp.getReleaseDate());
            }
        }
    }

    interface AppsListListener{
        void sendSelectedApp(DataServices.App app);
    }

}