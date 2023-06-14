package com.example.quizenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizenglish.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore database;
    FirebaseAuth auth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляры классов FirebaseFirestore и FirebaseAuth
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        userId = auth.getCurrentUser().getUid();
        DocumentReference documentReference = database.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                binding.userName.setText(documentSnapshot.getString("name") + "!");
            }
        });




    }

    //  Намерение перейти из MainActivity в VocabularyActivity
    public void startVocabulary(View view) {
        startActivity(new Intent(MainActivity.this,VocabularyActivity.class));
    }

    //  Намерение перейти из MainActivity в CourseActivity
    public void startCourse(View view) {
        startActivity(new Intent(MainActivity.this, CourseActivity.class));
    }

    //  Намерение перейти из MainActivity в InfoAppActivity
    public void startSetting(View view) {
        startActivity(new Intent(MainActivity.this, InfoActivity.class));
    }

    // Выход из аккаунта
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}