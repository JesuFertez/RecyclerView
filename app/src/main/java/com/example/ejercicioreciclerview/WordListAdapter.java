package com.example.ejercicioreciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioreciclerview.databinding.WordListItemBinding;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter <WordListAdapter.WordViewHolder> {

    private List<String> mWordList;
    private PassElemetSelected listener ;


    public WordListAdapter(List<String> mWordList,PassElemetSelected listener) {
        this.mWordList = mWordList;
        this.listener =listener;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordListItemBinding binding =
                WordListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new WordViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
    String word = mWordList.get(position);
    holder.wordItemTv.setText(word);
    
    }

    @Override
    public int getItemCount() {

        return mWordList.size();
    }

    public class WordViewHolder extends
            RecyclerView.ViewHolder implements View

            .OnClickListener{
        public TextView wordItemTv;

        public WordViewHolder(WordListItemBinding binding) {
            super(binding.getRoot());
            wordItemTv = binding.tvWordItem;
            wordItemTv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String element = mWordList.get(position);
            mWordList.set(position, "Seleccionado " + element);
            notifyDataSetChanged();
            listener.passElement(element);

        }
    }
}
