package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.quizenglish.databinding.ActivityResultVocBinding;

public class ResultVocActivity extends AppCompatActivity {

    ActivityResultVocBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityResultVocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String vocTitle = getIntent().getStringExtra("vocTitle");
        final int correct = getIntent().getIntExtra("correct",0);
        final int total = getIntent().getIntExtra("total",0);
        final int wrong = getIntent().getIntExtra("wrong",0);

        binding.vResTitle.setText("\"" + vocTitle + "\"");
        binding.vResCorrect.setText(String.format("%d",correct));
        binding.vResTotal.setText(String.format("%d",total));
        binding.vResWrong.setText(String.format("%d",wrong));

        binding.btnBackVoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Намерение перейти из ResultVocActivity в VocabularyActivity
                startActivity(new Intent(ResultVocActivity.this,VocabularyActivity.class));
            }
        });

    }
}