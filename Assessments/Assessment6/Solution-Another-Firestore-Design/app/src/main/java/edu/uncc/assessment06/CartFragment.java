package edu.uncc.assessment06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uncc.assessment06.databinding.CartRowItemBinding;
import edu.uncc.assessment06.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {
    public CartFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<CartItem> mCartItems = new ArrayList<>();
    ListenerRegistration listenerRegistration = null;
    CartAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Cart");

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CartAdapter();
        binding.recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        listenerRegistration = db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("carts")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.w("demo", "Listen failed.", error);
                            return;
                        }

                        mCartItems.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            CartItem cartItem = doc.toObject(CartItem.class);
                            mCartItems.add(cartItem);
                        }
                        getAndDisplayTotal();
                        adapter.notifyDataSetChanged();
                        Log.d("demo", "onEvent: " + mCartItems);
                    }
                });
    }

    void getAndDisplayTotal(){
        double total = 0.0;
        for (CartItem item: mCartItems) {
            total = total + item.getPrice();
        }
        binding.textViewTotal.setText("Total: $" + String.valueOf(total));
    }

    class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CartRowItemBinding itemBinding = CartRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CartViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
            CartItem cartItem = mCartItems.get(position);
            holder.setupUI(cartItem);
        }

        @Override
        public int getItemCount() {
            return mCartItems.size();
        }

        class CartViewHolder extends RecyclerView.ViewHolder{
            CartRowItemBinding mBinding;
            CartItem mCartItem;
            public CartViewHolder(CartRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.mBinding = itemBinding;
            }

            void setupUI(CartItem cartItem){
                this.mCartItem = cartItem;
                mBinding.textViewProductName.setText(mCartItem.getName());
                mBinding.textViewProductPrice.setText("$" + mCartItem.getPrice());
                Picasso.get().load(mCartItem.getIcon_url()).into(mBinding.imageViewProductIcon);

                mBinding.imageViewDeleteFromCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                        db.collection("users")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("carts")
                                .document(mCartItem.getDocId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                });

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (listenerRegistration != null){
            listenerRegistration.remove();
        }
    }
}