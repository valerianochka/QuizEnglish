package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.quizenglish.adapters.CoursePreIntermediateAdapter;
import com.example.quizenglish.databinding.ActivityCoursePreIntermediateBinding;
import com.example.quizenglish.models.CourseModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class CoursePreIntermediateActivity extends AppCompatActivity {

    ActivityCoursePreIntermediateBinding binding;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityCoursePreIntermediateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляр класса FirebaseFirestore
        database = FirebaseFirestore.getInstance();

        // Создание модели CourseModel и адаптера CoursePreIntermediateAdapter
        final ArrayList<CourseModel> courses = new ArrayList<>();
        final CoursePreIntermediateAdapter adapter = new CoursePreIntermediateAdapter(CoursePreIntermediateActivity.this, courses);

        database.collection("courses_pre_intermediate")
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
        binding.preIntermediateList1.setLayoutManager(new LinearLayoutManager(CoursePreIntermediateActivity.this));
        binding.preIntermediateList1.setAdapter(adapter);
    }

    // Намерение перейти из CoursePreIntermediateAdapter в CourseActivity
    public void startCourse(View view) {
        startActivity(new Intent(CoursePreIntermediateActivity.this,CourseActivity.class));
    }
}