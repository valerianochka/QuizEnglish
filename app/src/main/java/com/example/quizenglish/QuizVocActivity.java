package com.example.quizenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quizenglish.databinding.ActivityQuizVocBinding;
import com.example.quizenglish.models.QuestionModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QuizVocActivity extends AppCompatActivity {

    ActivityQuizVocBinding binding;

    ArrayList<QuestionModel> questions;
    QuestionModel question;

    FirebaseFirestore database;

    int index = 0;
    int correctAnswers = 0;
    int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizVocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questions = new ArrayList<>();
        database = FirebaseFirestore.getInstance();

        // Передача данных категории
        final String vocId = getIntent().getStringExtra("vocId");
        final String vocTitle = getIntent().getStringExtra("vocTitle");
        binding.vQuizTitle.setText(vocTitle);

        database.collection("vocabularies_set1")
                .document(vocId)
                .collection("questions")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for(DocumentSnapshot snapshot : value.getDocuments()) {
                            QuestionModel question = snapshot.toObject(QuestionModel.class);
                            questions.add(question);
                        }
                        setNextQuestion();
                    }
                });

        database.collection("vocabularies_set2")
                .document(vocId)
                .collection("questions")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for(DocumentSnapshot snapshot : value.getDocuments()) {
                            QuestionModel question = snapshot.toObject(QuestionModel.class);
                            questions.add(question);
                        }
                        setNextQuestion();
                    }
                });
    }

    // ПЕРЕКЛЮЧЕНИЕ ВОПРОСОВ
    void setNextQuestion() {
        if(index < questions.size()) {
            binding.vQuizQuestionCount.setText(String.format("Вопрос %d из %d", (index+1), questions.size()));
            question = questions.get(index);
            binding.vQuizQuestion.setText(question.getQuestion());
            binding.vQuizOption1.setText(question.getOption1());
            binding.vQuizOption2.setText(question.getOption2());
            binding.vQuizOption3.setText(question.getOption3());
            binding.vQuizOption4.setText(question.getOption4());
        }
    }

    // ПРОВЕРКА ОТВЕТА
    void checkAnswer(TextView textView) {
        String selectedAnswer = textView.getText().toString();
        // Если ответ правильный
        if(selectedAnswer.equals(question.getAnswer())) {
            correctAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
        }
        // Если ответ неправильный
        else {
            showAnswer();
            wrongAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.quiz_answer_wrong));
        }
    }
    // ПОКАЗ ПРАВИЛЬНОГО ОТВЕТА
    void showAnswer() {
        if(question.getAnswer().equals(binding.vQuizOption1.getText().toString())){
            binding.vQuizOption1.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
            binding.vQuizOption2.setClickable(false);
            binding.vQuizOption3.setClickable(false);
            binding.vQuizOption4.setClickable(false);
        }else if(question.getAnswer().equals(binding.vQuizOption2.getText().toString())){
            binding.vQuizOption2.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
            binding.vQuizOption1.setClickable(false);
            binding.vQuizOption3.setClickable(false);
            binding.vQuizOption4.setClickable(false);
        } else if(question.getAnswer().equals(binding.vQuizOption3.getText().toString())){
            binding.vQuizOption3.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
            binding.vQuizOption1.setClickable(false);
            binding.vQuizOption2.setClickable(false);
            binding.vQuizOption4.setClickable(false);
        } else if(question.getAnswer().equals(binding.vQuizOption4.getText().toString())){
            binding.vQuizOption4.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
            binding.vQuizOption1.setClickable(false);
            binding.vQuizOption2.setClickable(false);
            binding.vQuizOption3.setClickable(false);
        }
    }
    // ОБНУЛЕНИЕ ФОНОВ И КНОПОК
    void reset() {
        binding.vQuizOption1.setClickable(true);
        binding.vQuizOption2.setClickable(true);
        binding.vQuizOption3.setClickable(true);
        binding.vQuizOption4.setClickable(true);
        binding.vQuizOption1.setBackground(getResources().getDrawable(R.drawable.quiz_answer));
        binding.vQuizOption2.setBackground(getResources().getDrawable(R.drawable.quiz_answer));
        binding.vQuizOption3.setBackground(getResources().getDrawable(R.drawable.quiz_answer));
        binding.vQuizOption4.setBackground(getResources().getDrawable(R.drawable.quiz_answer));
    }

    // Обработчик кнопок
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_quiz_option1:
            case R.id.v_quiz_option2:
            case R.id.v_quiz_option3:
            case R.id.v_quiz_option4:
                TextView selected = (TextView) view;
                checkAnswer(selected);
                break;
            case R.id.btn:
                // Если вопросы остались
                if(index < questions.size()) {
                    // Переход к следующему вопросу
                    index++;
                    if (index != questions.size()){
                        reset();
                        setNextQuestion();
                    }else {
                        // Если вопросов не осталось
                        Intent intent = new Intent(QuizVocActivity.this, ResultVocActivity.class);
                        // Передача результатов
                        final String vocId = getIntent().getStringExtra("vocId");
                        final String vocTitle = getIntent().getStringExtra("vocTitle");
                        intent.putExtra("vocId", vocId);
                        intent.putExtra("vocTitle", vocTitle);
                        intent.putExtra("correct", correctAnswers);
                        intent.putExtra("wrong", wrongAnswers);
                        intent.putExtra("total", questions.size());
                        startActivity(intent);
                    }
                    break;
                }
                break;
        }
    }
}