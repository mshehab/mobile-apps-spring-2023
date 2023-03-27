package edu.uncc.hw06;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import edu.uncc.hw06.databinding.CommentRowItemBinding;
import edu.uncc.hw06.databinding.FragmentForumBinding;


public class ForumFragment extends Fragment {
    private static final String ARG_PARAM_FORUM = "ARG_PARAM_FORUM";
    private Forum mForum;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Comment> mComments = new ArrayList<>();
    public ForumFragment() {
        // Required empty public constructor
    }

    public static ForumFragment newInstance(Forum forum) {
        ForumFragment fragment = new ForumFragment();
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

    FragmentForumBinding binding;
    CommentsAdapter adapter;
    ListenerRegistration listenerRegistration;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForumBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Forum");

        binding.textViewForumCreatedBy.setText(mForum.getOwnerName());
        binding.textViewForumTitle.setText(mForum.getTitle());
        binding.textViewForumText.setText(mForum.getDescription());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommentsAdapter();
        binding.recyclerView.setAdapter(adapter);

        listenerRegistration = db.collection("forums").document(mForum.getDocId()).collection("comments").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    error.printStackTrace();
                    return;
                }
                mComments.clear();
                for (QueryDocumentSnapshot doc: value) {
                    Comment comment = doc.toObject(Comment.class);
                    mComments.add(comment);
                }

                binding.textViewCommentsCount.setText(mComments.size() + " Comments");

                adapter.notifyDataSetChanged();
            }
        });

        binding.buttonSubmitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = binding.editTextComment.getText().toString();
                if(comment.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a comment !!", Toast.LENGTH_SHORT).show();
                } else {
                    DocumentReference docRef = db
                            .collection("forums")
                            .document(mForum.getDocId())
                            .collection("comments")
                            .document();

                    HashMap<String, Object> data = new HashMap<>();
                    data.put("ownerId", mAuth.getCurrentUser().getUid());
                    data.put("ownerName", mAuth.getCurrentUser().getDisplayName());
                    data.put("comment", comment);
                    data.put("createdAt", FieldValue.serverTimestamp());
                    data.put("docId", docRef.getId());
                    docRef.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                binding.editTextComment.setText("");
                                //notify the adapter !!
                            } else {
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(listenerRegistration != null){
            listenerRegistration.remove();
            listenerRegistration = null;
        }
    }

    class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>{

        @NonNull
        @Override
        public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CommentRowItemBinding itemBinding = CommentRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CommentViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
            Comment comment = mComments.get(position);
            holder.setupUI(comment);
        }

        @Override
        public int getItemCount() {
            return mComments.size();
        }

        class CommentViewHolder extends RecyclerView.ViewHolder{
            CommentRowItemBinding mBinding;
            Comment mComment;
            public CommentViewHolder(CommentRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }

            public void setupUI(Comment comment){
                this.mComment = comment;

                mBinding.textViewCommentCreatedBy.setText(comment.getOwnerName());
                mBinding.textViewCommentText.setText(comment.getComment());

                if(mComment.getCreatedAt() != null){
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                    mBinding.textViewCommentCreatedAt.setText(sdf.format(mComment.getCreatedAt().toDate()));
                } else {
                    mBinding.textViewCommentCreatedAt.setText("");
                }

                if(mComment.getOwnerId().equals(mAuth.getCurrentUser().getUid())){
                    mBinding.imageViewDelete.setVisibility(View.VISIBLE);
                    mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.collection("forums")
                                    .document(mForum.getDocId())
                                    .collection("comments")
                                    .document(mComment.getDocId()).delete();
                        }
                    });
                } else {
                    mBinding.imageViewDelete.setVisibility(View.INVISIBLE);
                }
            }
        }
    }





}