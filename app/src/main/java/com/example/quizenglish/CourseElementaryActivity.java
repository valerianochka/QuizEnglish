package com.example.quizenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.quizenglish.adapters.CourseElementaryAdapter;
import com.example.quizenglish.databinding.ActivityCourseElementaryBinding;
import com.example.quizenglish.models.CourseModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class CourseElementaryActivity extends AppCompatActivity {

    ActivityCourseElementaryBinding binding;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Подключение viewBinding
        binding = ActivityCourseElementaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получаем экземпляр класса FirebaseFirestore
        database = FirebaseFirestore.getInstance();

        // Создание модели CourseModel и адаптера CourseElementaryAdapter
        final ArrayList<CourseModel> courses = new ArrayList<>();
        final CourseElementaryAdapter adapter = new CourseElementaryAdapter(CourseElementaryActivity.this, courses);

        database.collection("courses_elementary")
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
        binding.elementaryList1.setLayoutManager(new LinearLayoutManager(CourseElementaryActivity.this));
        binding.elementaryList1.setAdapter(adapter);
    }

    // Намерение перейти из CourseElementaryActivity в CourseActivity
    public void startCourse(View view) {
        startActivity(new Intent(CourseElementaryActivity.this,CourseActivity.class));
    }
}