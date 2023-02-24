package com.example.assessment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.assessment5.models.Auth;
import com.example.assessment5.models.Forum;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener, RegisterFragment.RegisterListener,
        CreateForumFragment.CreateForumListener, ForumsFragment.ForumsFragmentListener {
    Auth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if(sharedPref.contains("auth")){
            String authStr = sharedPref.getString("auth", null);
            if(authStr == null){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.rootView, new LoginFragment())
                        .commit();
            } else {
                Gson gson = new Gson();
                mAuth = gson.fromJson(authStr, Auth.class);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.rootView, ForumsFragment.newInstance(mAuth))
                        .commit();
            }
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootView, new LoginFragment())
                    .commit();
        }
    }
    @Override
    public void gotoLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void authSuccessful(Auth auth) {
        this.mAuth = auth;

        //store the auth data into the shared preferences
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        editor.putString("auth", gson.toJson(auth));
        editor.apply();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ForumsFragment.newInstance(auth))
                .commit();
    }

    @Override
    public void gotoRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegisterFragment())
                .commit();
    }

    @Override
    public void cancelForumCreate() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void completedForumCreate() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void logout() {
        mAuth = null;

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("auth");
        editor.apply();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void gotoCreateForum() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, CreateForumFragment.newInstance(mAuth))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoForumMessages(Forum forum) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ForumMessagesFragment.newInstance(forum, mAuth))
                .addToBackStack(null)
                .commit();
    }
}