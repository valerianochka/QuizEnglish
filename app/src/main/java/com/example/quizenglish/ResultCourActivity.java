package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.quizenglish.databinding.ActivityResultCourBinding;


public class ResultCourActivity extends AppCompatActivity {

    ActivityResultCourBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_cour);

        //  Подключение viewBinding
        binding = ActivityResultCourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String courTitle = getIntent().getStringExtra("courTitle");
        final int correct = getIntent().getIntExtra("correct",0);
        final int total = getIntent().getIntExtra("total",0);
        final int wrong = getIntent().getIntExtra("wrong",0);

        binding.vResTitle.setText("\"" + courTitle + "\"");
        binding.vResCorrect.setText(String.format("%d",correct));
        binding.vResTotal.setText(String.format("%d",total));
        binding.vResWrong.setText(String.format("%d",wrong));

        binding.btnBackVoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Намерение перейти из ResultCourActivity в CourseActivity
                startActivity(new Intent(ResultCourActivity.this,CourseActivity.class));
            }
        });
    }
}