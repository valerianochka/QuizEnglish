package com.example.quizenglish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizenglish.R;
import com.example.quizenglish.TheoryCourBeginnerActivity;
import com.example.quizenglish.models.CourseModel;
import java.util.ArrayList;

public class CourseBeginnerAdapter extends RecyclerView.Adapter<CourseBeginnerAdapter.CourseViewHolder>{
    Context context;
    ArrayList<CourseModel> courseModels;

    public CourseBeginnerAdapter(Context context, ArrayList<CourseModel> courseModels) {
        this.context = context;
        this.courseModels = courseModels;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course,null);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        final CourseModel model = courseModels.get(position);

        holder.vCourseNum.setText(model.getCourseNum());
        holder.vCourseTitle.setText(model.getCourseTitle());
        holder.vCourseDescription.setText(model.getCourseDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTheory = new Intent(context, TheoryCourBeginnerActivity.class);
                intentTheory.putExtra("courTitle", model.getCourseTitle());
                intentTheory.putExtra("courId", model.getCourseId());
                intentTheory.putExtra("courList", model.getCourseList());
                context.startActivity(intentTheory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModels.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView vCourseNum,vCourseTitle,vCourseDescription;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            vCourseNum = itemView.findViewById(R.id.v_course_index);
            vCourseTitle = itemView.findViewById(R.id.v_course_title);
            vCourseDescription = itemView.findViewById(R.id.v_course_description);
        }
    }
}

