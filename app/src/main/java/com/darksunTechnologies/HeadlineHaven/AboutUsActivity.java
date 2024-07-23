package com.darksunTechnologies.HeadlineHaven;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void workingOnIt(View view) {
        Toast.makeText(this, "We are working on our social media for now just use email", Toast.LENGTH_SHORT).show();
    }
}