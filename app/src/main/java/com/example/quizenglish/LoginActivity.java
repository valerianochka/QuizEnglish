package com.example.quizenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.quizenglish.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляр класса FirebaseAuth
        auth = FirebaseAuth.getInstance();

        // Сохранение данных при входе
        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // Обработчик кнопки "Войти в аккаунт"
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
                email = binding.editEmail.getText().toString();
                pass = binding.editPass.getText().toString();

                // Если пользователь не ввел данные
                if (binding.editEmail.length() == 0 || binding.editPass.length() == 0){
                    Toast.makeText(LoginActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                // В ином случае
                else {
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Если пользователь существует
                            if(task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                            // В ином случае
                            else {
                                Toast.makeText(LoginActivity.this, "Такого пользователя нет!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    // Намерение перейти из LoginActivity в RegistrationActivity
    public void startReg(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}