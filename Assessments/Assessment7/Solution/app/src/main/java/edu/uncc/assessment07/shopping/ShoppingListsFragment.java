package edu.uncc.assessment07.shopping;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.uncc.assessment07.databinding.FragmentShoppingListsBinding;
import edu.uncc.assessment07.databinding.ShoppingListRowItemBinding;
import edu.uncc.assessment07.models.ShoppingList;
import edu.uncc.assessment07.models.ShoppingListItem;


public class ShoppingListsFragment extends Fragment {
    public ShoppingListsFragment() {
        // Required empty public constructor
    }

    FragmentShoppingListsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShoppingListsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<ShoppingList> shoppingLists = new ArrayList<>();
    ShopListsAdapter adapter;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListenerRegistration listenerRegistration;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Shopping Lists");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShopListsAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.buttonAddNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoCreateNewList();
            }
        });
        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.logout();
            }
        });

        //TODO: setup a snapshot listener to get the shopping lists
        listenerRegistration = db.collection("shopping_lists").whereEqualTo("ownerId", mAuth.getCurrentUser().getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {

                            shoppingLists.clear();
                            for (QueryDocumentSnapshot doc: value) {
                                ShoppingList shoppingList = doc.toObject(ShoppingList.class);
                                shoppingLists.add(shoppingList);
                            }
                            adapter.notifyDataSetChanged();

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

    class ShopListsAdapter extends RecyclerView.Adapter<ShopListsAdapter.ShopListsViewHolder>{
        @NonNull
        @Override
        public ShopListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ShoppingListRowItemBinding itemBinding = ShoppingListRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new ShopListsViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ShopListsViewHolder holder, int position) {
            ShoppingList shoppingList = shoppingLists.get(position);
            holder.setupUI(shoppingList);
        }

        @Override
        public int getItemCount() {
            return shoppingLists.size();
        }

        class ShopListsViewHolder extends RecyclerView.ViewHolder{
            ShoppingListRowItemBinding mBinding;
            ShoppingList mShoppingList;
            public ShopListsViewHolder(ShoppingListRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.mBinding = itemBinding;
            }

            public void deleteFirstItem(ArrayList<ShoppingListItem> items){
                if(items.size() > 0 ){
                    ShoppingListItem item = items.remove(0);
                    db.collection("shopping_lists").document(mShoppingList.getDocId()).collection("items")
                            .document(item.getDocId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    deleteFirstItem(items);
                                }
                            });
                } else {
                    db.collection("shopping_lists").document(mShoppingList.getDocId()).delete();
                }
            }

            public void setupUI(ShoppingList shoppingList){
                this.mShoppingList = shoppingList;
                mBinding.textViewListName.setText(shoppingList.getName());

                mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.gotoShoppingList(mShoppingList);
                    }
                });

                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: to delete the full shopping list.
                        //You will need to delete all the documents in the sub-collections then delete this document

                        db.collection("shopping_lists").document(mShoppingList.getDocId()).collection("items").get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            ArrayList<ShoppingListItem> items = new ArrayList<>();
                                            for (QueryDocumentSnapshot doc: task.getResult()) {
                                                ShoppingListItem item = doc.toObject(ShoppingListItem.class);
                                                items.add(item);
                                            }
                                            deleteFirstItem(items);
                                        } else {

                                        }
                                    }
                                });

                    }
                });

            }
        }
    }
    ShoppingListsFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ShoppingListsFragmentListener) context;
    }

    public interface ShoppingListsFragmentListener{
        void logout();
        void gotoCreateNewList();
        void gotoShoppingList(ShoppingList shoppingList);
    }

}