package com.example.quizenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizenglish.databinding.ActivityRegistrationBinding;
import com.example.quizenglish.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляры классов FirebaseAuth и FirebaseFirestore
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        // Обработчик кнопки "Зарегистрироваться"
        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, pass;

                name = binding.editName.getText().toString();
                email = binding.editEmail.getText().toString();
                pass = binding.editPass.getText().toString();

                // Создание модели User
                final UserModel user = new UserModel(name, email, pass);

                // Если пользователь не ввел данные
                if (binding.editName.length() == 0 || binding.editEmail.length() == 0 || binding.editPass.length() == 0){
                    Toast.makeText(RegistrationActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                // В ином случае
                else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Если пользователь не существует
                            if(task.isSuccessful()) {
                                String uid = task.getResult().getUser().getUid();
                                database.collection("users")
                                        .document(uid)
                                        .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                // Если регистрация прошла успешно
                                                if(task.isSuccessful()) {
                                                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                                    finish();
                                                }
                                                // В ином случае
                                                else {
                                                    Toast.makeText(RegistrationActivity.this, "Аккаунт создан!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                            // В ином случае
                            else {
                                Toast.makeText(RegistrationActivity.this, "Такой пользователь уже есть!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    // Намерение перейти из RegistrationActivity в LoginActivity
    public void startLogin(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}