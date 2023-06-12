package com.example.quizenglish.adapters;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.quizenglish.R;
import com.example.quizenglish.models.WordModel;
import java.io.IOException;
import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder>{
    Context context;
    ArrayList<WordModel> wordModels;

    public WordAdapter(Context context, ArrayList<WordModel> wordModels) {
        this.context = context;
        this.wordModels = wordModels;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_word,null);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        final WordModel model = wordModels.get(position);

        holder.vWordEn.setText(model.getWordEn());
        holder.vWordRu.setText(model.getWordRu());
        holder.vWordSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer;
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(model.getWordSound());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);

                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                lp.dimAmount = 0.6f; // уровень затемнения от 1.0 до 0.0
                dialog.getWindow().setAttributes(lp);
                dialog.setContentView(R.layout.item_word_dialog);
                dialog.setCancelable(true);

                TextView dialogWordEn = dialog.findViewById(R.id.v_word_dialog_en);
                TextView dialogWordRu = dialog.findViewById(R.id.v_word_dialog_ru);
                TextView dialogWordType = dialog.findViewById(R.id.v_word_dialog_type);
                TextView dialogWordDescriptionEn = dialog.findViewById(R.id.v_word_dialog_description_en);
                TextView dialogWordDescriptionRu = dialog.findViewById(R.id.v_word_dialog_description_ru);
                TextView dialogWordExampleEn = dialog.findViewById(R.id.v_word_dialog_example_en);
                TextView dialogWordExampleRu = dialog.findViewById(R.id.v_word_dialog_example_ru);
                ImageView dialogWordImg = dialog.findViewById(R.id.v_word_dialog_img);

                dialogWordEn.setText(model.getWordEn());
                dialogWordRu.setText(model.getWordRu());
                dialogWordType.setText(model.getWordType());
                dialogWordDescriptionEn.setText(model.getWordDescriptionEn());
                dialogWordDescriptionRu.setText(model.getWordDescriptionRu());
                dialogWordExampleEn.setText(model.getWordExampleEn());
                dialogWordExampleRu.setText(model.getWordExampleRu());
                Glide.with(context)
                        .load(model.getWordImg())
                        .into(dialogWordImg);

                CardView dialogWordSound = dialog.findViewById(R.id.v_word_dialog_sound);
                dialogWordSound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(model.getWordSound());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                        }
                    }
                });

                CardView btnClose = dialog.findViewById(R.id.v_word_dialog_close);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordModels.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView vWordEn,vWordRu;
        CardView vWordSound;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            vWordEn = itemView.findViewById(R.id.v_word_en);
            vWordRu = itemView.findViewById(R.id.v_word_ru);
            vWordSound = itemView.findViewById(R.id.v_word_sound);
        }
    }
}