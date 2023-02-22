package com.example.assessment5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assessment5.databinding.ForumRowItemBinding;
import com.example.assessment5.databinding.FragmentForumMessagesBinding;
import com.example.assessment5.databinding.FragmentForumsBinding;
import com.example.assessment5.databinding.MessageRowItemBinding;
import com.example.assessment5.models.Forum;
import com.example.assessment5.models.Message;

import java.util.ArrayList;

public class ForumMessagesFragment extends Fragment {
    private static final String ARG_PARAM_FORUM = "ARG_PARAM_FORUM";
    private Forum mForum;
    ArrayList<Message> messages = new ArrayList<>();

    public ForumMessagesFragment() {
        // Required empty public constructor
    }

    public static ForumMessagesFragment newInstance(Forum forum) {
        ForumMessagesFragment fragment = new ForumMessagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_FORUM, forum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mForum = (Forum) getArguments().getSerializable(ARG_PARAM_FORUM);
        }
    }

    FragmentForumMessagesBinding binding;
    MessagesAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        binding = FragmentForumMessagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Forum Messages");
        adapter = new MessagesAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        getMessages();


        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.editTextMessage.getText().toString();
                if(message.isEmpty()){
                    Toast.makeText(getContext(), "Message is required", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO : send the message to the API
                }
            }
        });
    }

    void getMessages(){
        //TODO: get the messages from the API
    }


    class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

        @NonNull
        @Override
        public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MessagesViewHolder(MessageRowItemBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
            Message message = messages.get(position);
            holder.setupUI(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        class MessagesViewHolder extends RecyclerView.ViewHolder {
            Message mMessage;
            MessageRowItemBinding mBinding;
            public MessagesViewHolder(MessageRowItemBinding mBinding) {
                super(mBinding.getRoot());
                this.mBinding = mBinding;
            }

            void setupUI(Message message){
                this.mMessage = message;
                mBinding.textViewMessage.setText(message.getMessage());
                mBinding.textViewMessageCreatedAt.setText(message.getCreated_at());
                mBinding.textViewMessageCreatorName.setText(message.getCreatedByFname() + " " + message.getCreatedByLname());

                //TODO: setup the rest of the UI the delete icon ..
            }
        }
    }

}