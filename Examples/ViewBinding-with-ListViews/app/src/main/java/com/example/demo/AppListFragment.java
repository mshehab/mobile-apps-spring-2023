package com.example.demo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demo.databinding.AppListItemBinding;
import com.example.demo.databinding.FragmentAppListBinding;

import java.util.ArrayList;
import java.util.List;


public class AppListFragment extends Fragment {
    public AppListFragment() {
        // Required empty public constructor
    }

    FragmentAppListBinding binding;
    AppsAdapter adapter;
    ListView listView;
    ArrayList<DataServices.App> apps;
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

        listView = view.findViewById(R.id.listView);
        adapter = new AppsAdapter(getActivity(), apps);
        listView.setAdapter(adapter);
    }

    class AppsAdapter extends ArrayAdapter<DataServices.App>{
        public AppsAdapter(@NonNull Context context, @NonNull List<DataServices.App> objects) {
            super(context, R.layout.app_list_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            AppListItemBinding mBinding;

            if(convertView == null){
                mBinding = AppListItemBinding.inflate(getLayoutInflater(), parent, false);
                convertView = mBinding.getRoot();
                convertView.setTag(mBinding);
            } else {
                mBinding = (AppListItemBinding) convertView.getTag();
            }

            DataServices.App app =  getItem(position);

            mBinding.textViewAppName.setText(app.getName());
            mBinding.textViewArtistName.setText(app.getArtistName());
            mBinding.textViewReleaseDate.setText(app.getReleaseDate());

            return convertView;
        }
    }
}