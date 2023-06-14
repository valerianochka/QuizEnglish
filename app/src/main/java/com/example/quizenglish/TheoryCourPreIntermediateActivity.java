package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.quizenglish.databinding.ActivityTheoryCourPreIntermediateBinding;

public class TheoryCourPreIntermediateActivity extends AppCompatActivity {

    ActivityTheoryCourPreIntermediateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityTheoryCourPreIntermediateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Передача данных категории
        final String courTitle = getIntent().getStringExtra("courTitle");
        binding.vTheoryTitle.setText(courTitle);
        final String courList = getIntent().getStringExtra("courList");

        Glide.with(TheoryCourPreIntermediateActivity.this)
                .load(courList)
                .into(binding.vCourList);
    }

    // Намерение перейти из TheoryCourElementaryActivity в QuizCourActivity с передачей данных
    public void startQuiz(View view) {
        final String courTitle = getIntent().getStringExtra("courTitle");
        final String courId = getIntent().getStringExtra("courId");
        Intent intentQuiz = new Intent(TheoryCourPreIntermediateActivity.this, QuizCourActivity.class);
        intentQuiz.putExtra("courTitle", courTitle);
        intentQuiz.putExtra("courId", courId);
        TheoryCourPreIntermediateActivity.this.startActivity(intentQuiz);
    }

    // Намерение перейти из TheoryCourPreIntermediateActivity в CoursePreIntermediateActivity
    public void startCourse(View view) {
        startActivity(new Intent(TheoryCourPreIntermediateActivity.this,CoursePreIntermediateActivity.class));
    }
}