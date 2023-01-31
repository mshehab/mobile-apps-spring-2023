package com.example.inclass04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener,
        RegistrationFragment.RegistrationFragmentListener, DepartmentFragment.DepartmentFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

    @Override
    public void gotoRegistration() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegistrationFragment(), "registration-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoDepartment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new DepartmentFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoProfile(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendDepartment(String department) {
        //need to find the registration fragment from backstack ..
        //send the department to the registration fragment
        //pop the back stack ..

        RegistrationFragment fragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag("registration-fragment");
        if(fragment != null){
            fragment.setSelectedDepartment(department);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelDepartment() {
        getSupportFragmentManager().popBackStack();
    }
}