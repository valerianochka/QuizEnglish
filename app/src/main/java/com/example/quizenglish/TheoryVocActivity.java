package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.quizenglish.adapters.WordAdapter;
import com.example.quizenglish.databinding.ActivityTheoryVocBinding;
import com.example.quizenglish.models.VocabularyModel;
import com.example.quizenglish.models.WordModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class TheoryVocActivity extends AppCompatActivity {

    ActivityTheoryVocBinding binding;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityTheoryVocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Передача данных категории
        final String vocId = getIntent().getStringExtra("vocId");
        final String vocTitle = getIntent().getStringExtra("vocTitle");
        binding.vTheoryTitle.setText(vocTitle);

        database = FirebaseFirestore.getInstance();
        final ArrayList<WordModel> words1 = new ArrayList<>();
        final WordAdapter adapter1 = new WordAdapter(this, words1);
        database.collection("vocabularies_set1")
                .document(vocId)
                .collection("words")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        words1.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            WordModel model = snapshot.toObject(WordModel.class);
                            model.setWordId(snapshot.getId());
                            words1.add(model);
                        }
                        adapter1.notifyDataSetChanged();
                    }
                });
        binding.wordList1.setLayoutManager(new LinearLayoutManager(this));
        binding.wordList1.setAdapter(adapter1);

        final ArrayList<WordModel> words2 = new ArrayList<>();
        final WordAdapter adapter2 = new WordAdapter(this, words2);
        database.collection("vocabularies_set2")
                .document(vocId)
                .collection("words")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        words2.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            WordModel model = snapshot.toObject(WordModel.class);
                            model.setWordId(snapshot.getId());
                            words2.add(model);
                        }
                        adapter2.notifyDataSetChanged();
                    }
                });
        binding.wordList2.setLayoutManager(new LinearLayoutManager(this));
        binding.wordList2.setAdapter(adapter2);
    }

    public void startQuiz(View view) {

        final String vocId = getIntent().getStringExtra("vocId");
        final String vocTitle = getIntent().getStringExtra("vocTitle");

        Intent intentQuiz = new Intent(TheoryVocActivity.this, QuizVocActivity.class);
        intentQuiz.putExtra("vocId", vocId);
        intentQuiz.putExtra("vocTitle", vocTitle);
        TheoryVocActivity.this.startActivity(intentQuiz);
    }

    public void startVocabulary(View view) {
        startActivity(new Intent(TheoryVocActivity.this,VocabularyActivity.class));
    }
}