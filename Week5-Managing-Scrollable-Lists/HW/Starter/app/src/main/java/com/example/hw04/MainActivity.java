package com.example.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BACFragment.BACFragmentListener,
        SetProfileFragment.SetProfileFragmentListener, AddDrinkFragment.AddDrinkFragmentListener,
        ViewDrinksFragment.ViewDrinksFragmentListener {
    ArrayList<Drink> drinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new BACFragment(), "bac-fragment")
                .commit();
    }

    @Override
    public void gotoSetProfile() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SetProfileFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void clearAllDrinks() {
        drinks.clear();
    }

    @Override
    public ArrayList<Drink> getAllDrinks() {
        return this.drinks;
    }

    @Override
    public void gotoAddDrink() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddDrinkFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoViewDrinks() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ViewDrinksFragment.newInstance(drinks))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void cancelProfile() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendProfile(Profile profile) {
        drinks.clear();
        //find the bac fragment
        //send it the profile
        //pop the back stack.
        BACFragment fragment = (BACFragment) getSupportFragmentManager().findFragmentByTag("bac-fragment");
        if(fragment != null){
            fragment.setProfile(profile);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendDrink(Drink drink) {
        drinks.add(drink);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelAddDrink() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeViewDrinks() {
        getSupportFragmentManager().popBackStack();
    }
}