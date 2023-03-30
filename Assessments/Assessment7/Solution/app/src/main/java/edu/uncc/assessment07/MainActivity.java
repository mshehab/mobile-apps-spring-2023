package edu.uncc.assessment07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import edu.uncc.assessment07.auth.LoginFragment;
import edu.uncc.assessment07.auth.SignUpFragment;
import edu.uncc.assessment07.models.ShoppingList;
import edu.uncc.assessment07.shopping.AddListItemFragment;
import edu.uncc.assessment07.shopping.CreateNewListFragment;
import edu.uncc.assessment07.shopping.ShoppingListFragment;
import edu.uncc.assessment07.shopping.ShoppingListsFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener, SignUpFragment.SignUpListener,
        ShoppingListsFragment.ShoppingListsFragmentListener, ShoppingListFragment.ShoppingListListener, AddListItemFragment.AddListItemListener, CreateNewListFragment.CreateNewListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootView, new LoginFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootView, new ShoppingListsFragment())
                    .commit();
        }
    }

    @Override
    public void createNewAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SignUpFragment())
                .commit();
    }

    @Override
    public void login() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void authSuccessful() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new ShoppingListsFragment())
                .commit();
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void gotoCreateNewList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CreateNewListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoShoppingList(ShoppingList shoppingList) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ShoppingListFragment.newInstance(shoppingList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoAddListItem(ShoppingList shoppingList) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, AddListItemFragment.newInstance(shoppingList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goBackToShoppingLists() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addListItemDone() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addListItemCancel() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void createNewListDone() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void createNewListCancel() {
        getSupportFragmentManager().popBackStack();
    }
}