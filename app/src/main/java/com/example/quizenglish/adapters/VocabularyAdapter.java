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
import com.example.quizenglish.TheoryVocActivity;
import com.example.quizenglish.models.VocabularyModel;
import java.util.ArrayList;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>{
    Context context;
    ArrayList<VocabularyModel> vocabularyModels;

    public VocabularyAdapter(Context context, ArrayList<VocabularyModel> vocabularyModels) {
        this.context = context;
        this.vocabularyModels = vocabularyModels;
    }

    @NonNull
    @Override
    public VocabularyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vocabulary,null);
        return new VocabularyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyViewHolder holder, int position) {
        final VocabularyModel model = vocabularyModels.get(position);

        holder.vVocabularyTitle.setText(model.getVocabularyTitle());
        holder.vVocabularyWords.setText(model.getVocabularyWords());
        holder.vVocabularyLevel.setText(model.getVocabularyLevel());

        Glide.with(context)
                .load(model.getVocabularyImg())
                .into(holder.vVocabularyImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTheory = new Intent(context, TheoryVocActivity.class);
                intentTheory.putExtra("vocTitle", model.getVocabularyTitle());
                intentTheory.putExtra("vocId", model.getVocabularyId());
                context.startActivity(intentTheory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vocabularyModels.size();
    }

    public class VocabularyViewHolder extends RecyclerView.ViewHolder {
        ImageView vVocabularyImg;
        TextView vVocabularyTitle,vVocabularyWords,vVocabularyLevel;

        public VocabularyViewHolder(@NonNull View itemView) {
            super(itemView);
            vVocabularyTitle = itemView.findViewById(R.id.v_vocabulary_title);
            vVocabularyImg = itemView.findViewById(R.id.v_vocabulary_img);
            vVocabularyWords = itemView.findViewById(R.id.v_vocabulary_words);
            vVocabularyLevel = itemView.findViewById(R.id.v_vocabulary_level);
        }
    }
}
