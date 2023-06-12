package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.quizenglish.databinding.ActivityTheoryCourElementaryBinding;

public class TheoryCourElementaryActivity extends AppCompatActivity {

    ActivityTheoryCourElementaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityTheoryCourElementaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Передача данных категории
        final String courTitle = getIntent().getStringExtra("courTitle");
        binding.vTheoryTitle.setText(courTitle);
        final String courList = getIntent().getStringExtra("courList");

        Glide.with(TheoryCourElementaryActivity.this)
                .load(courList)
                .into(binding.vCourList);

    }

    // Намерение перейти из TheoryCourElementaryActivity в QuizCourActivity с передачей данных
    public void startQuiz(View view) {
        final String courTitle = getIntent().getStringExtra("courTitle");
        final String courId = getIntent().getStringExtra("courId");
        Intent intentQuiz = new Intent(TheoryCourElementaryActivity.this, QuizCourActivity.class);
        intentQuiz.putExtra("courTitle", courTitle);
        intentQuiz.putExtra("courId", courId);
        TheoryCourElementaryActivity.this.startActivity(intentQuiz);
    }

    // Намерение перейти из TheoryCourElementaryActivity в CourseElementaryActivity
    public void startCourse(View view) {
        startActivity(new Intent(TheoryCourElementaryActivity.this,CourseElementaryActivity.class));
    }
}