package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {
    private Integer selectedButtonID;
    private Button selectedButton;

    private ArrayList<Integer> numButtons = new ArrayList<Integer>();
    private List<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> unsolvedNums = new ArrayList<Integer>();
    private ArrayList<Integer> checkNums = new ArrayList<Integer>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }



}
