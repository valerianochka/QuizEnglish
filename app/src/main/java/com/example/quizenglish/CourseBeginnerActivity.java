package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.quizenglish.adapters.CourseBeginnerAdapter;
import com.example.quizenglish.databinding.ActivityCourseBeginnerBinding;
import com.example.quizenglish.models.CourseModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class CourseBeginnerActivity extends AppCompatActivity {

    ActivityCourseBeginnerBinding binding;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityCourseBeginnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляр класса FirebaseFirestore
        database = FirebaseFirestore.getInstance();

        // Создание модели CourseModel и адаптера CourseBeginnerAdapter
        final ArrayList<CourseModel> courses = new ArrayList<>();
        final CourseBeginnerAdapter adapter = new CourseBeginnerAdapter(CourseBeginnerActivity.this, courses);

        database.collection("courses_beginner")
                .orderBy("courseNum")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        courses.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            CourseModel model = snapshot.toObject(CourseModel.class);
                            model.setCourseId(snapshot.getId());
                            courses.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        binding.beginnerList1.setLayoutManager(new LinearLayoutManager(CourseBeginnerActivity.this));
        binding.beginnerList1.setAdapter(adapter);
    }

    // Намерение перейти из CourseBeginnerActivity в CourseActivity
    public void startCourse(View view) {
        startActivity(new Intent(CourseBeginnerActivity.this,CourseActivity.class));
    }
}