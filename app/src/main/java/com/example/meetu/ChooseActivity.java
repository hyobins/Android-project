package com.example.meetu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meetu.databinding.ActivityChooseBinding;

import find_art.FindArtActivity;
import find_person.FindPersonActivity;


public class ChooseActivity extends AppCompatActivity {
    ActivityChooseBinding choosing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        choosing = DataBindingUtil.setContentView(this,R.layout.activity_choose);
        choosing.setActivity(this);
    }

    public void find_art(View view){
        Intent intent = new Intent(getApplicationContext(), FindArtActivity.class);
        startActivity(intent);
    }

    public void find_person(View view){
        Intent intent = new Intent(getApplicationContext(), FindPersonActivity.class);
        startActivity(intent);
    }
}