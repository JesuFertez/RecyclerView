package com.example.ejercicioreciclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ejercicioreciclerview.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements PassElemetSelected{

    private FragmentFirstBinding binding;
    private List<String> mWordList;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        wordListAutoGenerate();

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setUpClickListener();
    }

    public void setUpClickListener() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addWordItem();
            }
        });

    }

    private void addWordItem() {
        mWordList.add("valor desde Java");
        binding.wordList.getAdapter().notifyItemInserted(mWordList.size());
        binding.wordList.smoothScrollToPosition(mWordList.size());
    }

    private List<String> wordListAutoGenerate() {
        mWordList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mWordList.add("Word " + i);
        }
        return mWordList;
    }

    private void setupRecyclerView() {

        WordListAdapter wordListAdapter = new WordListAdapter(mWordList, this);
        binding.wordList.setAdapter(wordListAdapter);
        binding.wordList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void passElement(String element) {
        Toast.makeText(getContext(), element, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}