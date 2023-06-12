package com.example.quizenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.quizenglish.databinding.ActivityQuizVocBinding;
import com.example.quizenglish.models.QuestionModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

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

        Random random = new Random();
        final int rand = random.nextInt(12);

        database.collection("vocabularies_set1")
                .document(vocId)
                .collection("questions")
                .whereGreaterThanOrEqualTo("index", rand)
                .orderBy("index")
                .limit(5).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.getDocuments().size() < 5) {
                            database.collection("vocabularies_set1")
                                    .document(vocId)
                                    .collection("questions")
                                    .whereLessThanOrEqualTo("index", rand)
                                    .orderBy("index")
                                    .limit(5).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                                QuestionModel question = snapshot.toObject(QuestionModel.class);
                                                questions.add(question);
                                            }
                                            setNextQuestion();
                                        }
                                    });
                        } else {
                            for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                QuestionModel question = snapshot.toObject(QuestionModel.class);
                                questions.add(question);
                            }
                            setNextQuestion();
                        }
                    }
                });

//        database.collection("vocabularies_set1")
//                .document(vocId)
//                .collection("questions")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        for(DocumentSnapshot snapshot : value.getDocuments()) {
//                            QuestionModel question = snapshot.toObject(QuestionModel.class);
//                            questions.add(question);
//                        }
//                        setNextQuestion();
//                    }
//                });
//
//        database.collection("vocabularies_set2")
//                .document(vocId)
//                .collection("questions")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        for(DocumentSnapshot snapshot : value.getDocuments()) {
//                            QuestionModel question = snapshot.toObject(QuestionModel.class);
//                            questions.add(question);
//                        }
//                        setNextQuestion();
//                    }
//                });
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
        if(question.getAnswer().equals(binding.vQuizOption1.getText().toString()))
            binding.vQuizOption1.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
        else if(question.getAnswer().equals(binding.vQuizOption2.getText().toString()))
            binding.vQuizOption2.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
        else if(question.getAnswer().equals(binding.vQuizOption3.getText().toString()))
            binding.vQuizOption3.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
        else if(question.getAnswer().equals(binding.vQuizOption4.getText().toString()))
            binding.vQuizOption4.setBackground(getResources().getDrawable(R.drawable.quiz_answer_correct));
    }
    // ОБНУЛЕНИЕ ФОНОВ
    void reset() {
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
                reset();
                // Если вопросы остались
                if(index <= questions.size()) {
                    // Переход к следующему вопросу
                    index++;
                    setNextQuestion();
                }
                // Если вопросов не осталось
                else {
                    // Переход к результату
                    Intent intent = new Intent(QuizVocActivity.this, ResultActivity.class);
                    // Передача результатов
                    final String vocId = getIntent().getStringExtra("vocId");
//                    intent.putExtra("title", vocId);
//                    intent.putExtra("correct", correctAnswers);
//                    intent.putExtra("wrong", wrongAnswers);
//                    intent.putExtra("total", questions.size());
                    startActivity(intent);
                }
                break;
        }
    }
}