package edu.uncc.inclass09;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GradesFragment.GradesListener, AddCourseFragment.AddCourseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new GradesFragment())
                .commit();
    }

    @Override
    public void gotoAddCourse() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddCourseFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void doneAddCourse() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelAddCourse() {
        getSupportFragmentManager().popBackStack();
    }
}