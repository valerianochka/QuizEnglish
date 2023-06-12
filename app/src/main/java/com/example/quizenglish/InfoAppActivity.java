package com.example.quizenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import com.example.quizenglish.databinding.ActivityInfoAppBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class InfoAppActivity extends AppCompatActivity {

    ActivityInfoAppBinding binding;
    FirebaseFirestore database;
    FirebaseAuth auth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityInfoAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляры классов FirebaseFirestore и FirebaseAuth
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        userId = auth.getCurrentUser().getUid();
        DocumentReference documentReference = database.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                binding.userName.setText(documentSnapshot.getString("name"));
                binding.userEmail.setText(documentSnapshot.getString("email"));
            }
        });
    }

    // Намерение перейти из InfoAppActivity в MainActivity
    public void startMain(View view) {
        startActivity(new Intent(InfoAppActivity.this,MainActivity.class));
    }

    // Анимация для cardview
    public void viewInfoUser(View view) {
        int v = (binding.infoUserDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(binding.infoUser,new AutoTransition());
        binding.infoUserDetails.setVisibility(v);
    }
    public void viewInfoAuthor(View view) {
        int v = (binding.infoAuthorDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(binding.infoAuthor,new AutoTransition());
        binding.infoAuthorDetails.setVisibility(v);
    }
}