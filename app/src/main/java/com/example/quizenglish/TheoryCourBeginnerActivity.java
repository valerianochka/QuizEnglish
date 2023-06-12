package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.quizenglish.adapters.CourseBeginnerAdapter;
import com.example.quizenglish.databinding.ActivityTheoryCourBeginnerBinding;

public class TheoryCourBeginnerActivity extends AppCompatActivity {

    ActivityTheoryCourBeginnerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityTheoryCourBeginnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Передача данных
        final String courTitle = getIntent().getStringExtra("courTitle");
        binding.vTheoryTitle.setText(courTitle);
        final String courList = getIntent().getStringExtra("courList");

        Glide.with(TheoryCourBeginnerActivity.this)
            .load(courList)
            .into(binding.vCourList);
    }

    // Намерение перейти из TheoryCourBeginnerActivity в QuizCourActivity с передачей данных
    public void startQuiz(View view) {
        final String courTitle = getIntent().getStringExtra("courTitle");
        final String courId = getIntent().getStringExtra("courId");
        Intent intentQuiz = new Intent(TheoryCourBeginnerActivity.this, QuizCourActivity.class);
        intentQuiz.putExtra("courTitle", courTitle);
        intentQuiz.putExtra("courId", courId);
        TheoryCourBeginnerActivity.this.startActivity(intentQuiz);
    }

    // Намерение перейти из TheoryCourBeginnerActivity в CourseBeginnerActivity
    public void startCourse(View view) {
        startActivity(new Intent(TheoryCourBeginnerActivity.this,CourseBeginnerActivity.class));
    }
}