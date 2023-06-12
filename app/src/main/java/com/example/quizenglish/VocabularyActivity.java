package com.example.quizenglish;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import com.example.quizenglish.adapters.VocabularyAdapter;
import com.example.quizenglish.databinding.ActivityVocabularyBinding;
import com.example.quizenglish.models.VocabularyModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class VocabularyActivity extends AppCompatActivity {

    ActivityVocabularyBinding binding;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityVocabularyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляр класса FirebaseFirestore
        database = FirebaseFirestore.getInstance();

        // Создание модели VocabularyModel и адаптера VocabularyAdapter
        final ArrayList<VocabularyModel> vocabularies1 = new ArrayList<>();
        final VocabularyAdapter adapter1 = new VocabularyAdapter(VocabularyActivity.this, vocabularies1);

        database.collection("vocabularies_set1")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        vocabularies1.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            VocabularyModel model = snapshot.toObject(VocabularyModel.class);
                            model.setVocabularyId(snapshot.getId());
                            vocabularies1.add(model);
                        }
                        adapter1.notifyDataSetChanged();
                    }
                });
        binding.vocabularyList1.setLayoutManager(new LinearLayoutManager(VocabularyActivity.this));
        binding.vocabularyList1.setAdapter(adapter1);

        final ArrayList<VocabularyModel> vocabularies2 = new ArrayList<>();
        final VocabularyAdapter adapter2 = new VocabularyAdapter(VocabularyActivity.this, vocabularies2);
        database.collection("vocabularies_set2")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        vocabularies2.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            VocabularyModel model = snapshot.toObject(VocabularyModel.class);
                            model.setVocabularyId(snapshot.getId());
                            vocabularies2.add(model);
                        }
                        adapter2.notifyDataSetChanged();
                    }
                });
        binding.vocabularyList2.setLayoutManager(new LinearLayoutManager(VocabularyActivity.this));
        binding.vocabularyList2.setAdapter(adapter2);


    }

    // Намерение перейти из VocabularyActivity в MainActivity
    public void startMain(View view) {
        startActivity(new Intent(VocabularyActivity.this,MainActivity.class));
    }
}