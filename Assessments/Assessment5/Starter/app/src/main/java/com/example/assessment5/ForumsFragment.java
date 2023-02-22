package com.example.assessment5;

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

import com.example.assessment5.databinding.ForumRowItemBinding;
import com.example.assessment5.databinding.FragmentForumsBinding;
import com.example.assessment5.models.Forum;

import java.util.ArrayList;

public class ForumsFragment extends Fragment {

    public ForumsFragment() {
        // Required empty public constructor
    }

    FragmentForumsBinding binding;
    ArrayList<Forum> forums = new ArrayList<>();
    ForumsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForumsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Forums");
        binding.buttonCreateForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoCreateForum();
            }
        });

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.logout();
            }
        });

        adapter = new ForumsAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        getForums();
    }

    void getForums() {
        //TODO: setup the api call and get the forums
    }

    class ForumsAdapter extends RecyclerView.Adapter<ForumsAdapter.ForumsViewHolder> {

        @NonNull
        @Override
        public ForumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ForumsViewHolder(ForumRowItemBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ForumsViewHolder holder, int position) {
            Forum forum = forums.get(position);
            holder.setupUI(forum);
        }

        @Override
        public int getItemCount() {
            return forums.size();
        }

        class ForumsViewHolder extends RecyclerView.ViewHolder {
            Forum mForum;
            ForumRowItemBinding mBinding;
            public ForumsViewHolder(ForumRowItemBinding mBinding) {
                super(mBinding.getRoot());
                this.mBinding = mBinding;
                mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.gotoForumMessages(mForum);
                    }
                });
            }

            void setupUI(Forum forum){
                this.mForum = forum;
                mBinding.textViewForumTitle.setText(mForum.getTitle());
                mBinding.textViewForumCreatedAt.setText(forum.getCreated_at());
                mBinding.textViewForumCreatorName.setText(forum.getCreatedByFname() + " " + forum.getCreatedByLname());
                //TODO: setup the rest of the UI the delete icon ..
            }
        }
    }

    ForumsFragmentListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ForumsFragmentListener) {
            mListener = (ForumsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ForumsFragmentListener");
        }
    }

    interface ForumsFragmentListener {
        void logout();
        void gotoCreateForum();
        void gotoForumMessages(Forum forum);
    }
}