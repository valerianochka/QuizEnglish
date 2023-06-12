package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizenglish.databinding.ActivityCourseBinding;

public class CourseActivity extends AppCompatActivity {

    ActivityCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    // Намерение перейти из CourseActivity в MainActivity
    public void startMain(View view) {
        startActivity(new Intent(CourseActivity.this,MainActivity.class));
    }

    // Намерение перейти из CourseActivity в CourseBeginnerActivity
    public void startBeginner(View view) {
        startActivity(new Intent(CourseActivity.this,CourseBeginnerActivity.class));
    }

    // Намерение перейти из CourseActivity в CourseElementaryActivity
    public void startElementary(View view) {
        startActivity(new Intent(CourseActivity.this,CourseElementaryActivity.class));
    }
}