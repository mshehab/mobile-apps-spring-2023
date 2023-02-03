package com.example.inclass05;

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
import android.widget.TextView;

import com.example.inclass05.databinding.FragmentAppDetailsBinding;

import java.util.ArrayList;

public class AppDetailsFragment extends Fragment {
    private static final String ARG_PARAM_APP = "ARG_PARAM_APP";
    private DataServices.App mApp;
    public AppDetailsFragment() {
        // Required empty public constructor
    }

    public static AppDetailsFragment newInstance(DataServices.App app) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_APP, app);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mApp = (DataServices.App) getArguments().getSerializable(ARG_PARAM_APP);
        }
    }

    FragmentAppDetailsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAppDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<String> genres;
    GenresAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("App Details");

        binding.textViewAppName.setText(mApp.getName());
        binding.textViewArtistName.setText(mApp.getArtistName());
        binding.textViewReleaseDate.setText(mApp.getReleaseDate());

        genres = mApp.getGenres();

        adapter = new GenresAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

    }

    class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreViewHolder>{


        @NonNull
        @Override
        public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.simple_row_item, parent, false);
            return new GenreViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
            String genre = genres.get(position);
            holder.setupUI(genre);
        }

        @Override
        public int getItemCount() {
            return genres.size();
        }

        class GenreViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public GenreViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }

            public void setupUI(String genre){
                textView.setText(genre);
            }
        }
    }
}