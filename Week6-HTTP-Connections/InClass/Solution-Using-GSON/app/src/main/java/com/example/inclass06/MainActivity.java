package com.example.inclass06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ContactsFragment.ContactsListener, CreateContactFragment.CreateContactListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new ContactsFragment())
                .commit();
    }

    @Override
    public void gotoAddContact() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CreateContactFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoContactDetails(GetContactsResponse.Contact contact) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, DetailsFragment.newInstance(contact))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void cancelCreateContact() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void doneCreateContact() {
        getSupportFragmentManager().popBackStack();
    }
}