package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer selectedButtonID;
    private Button selectedButton;

    private ArrayList<Integer> numButtons = new ArrayList<Integer>();
    private List<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> unsolvedNums = new ArrayList<Integer>();
    private ArrayList<Integer> checkNums = new ArrayList<Integer>();
    private int[] difficultyNums = {4, 6, 8};
    int difficultyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ScreenView screenView = new ScreenView(this);
//        Canvas canvas = new Canvas();
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        screenView.draw(canvas, paint);
        Bundle currBundle = getIntent().getExtras();
        Integer diffLevel = currBundle.getInt("difficulty");
        difficultyNum = difficultyNums[diffLevel];
        addNumButtons();
        setButtonListeners();

    }

    public List<ArrayList<Integer>> createPuzzle() {
        List<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(0);
            newList.add(0);
            newList.add(0);
            newList.add(0);
            newList.add(0);
            newList.add(0);
            newList.add(0);
            newList.add(0);
            newList.add(0);
            aList.add(newList);

        }
//        aList.add((ArrayList<Integer>) Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));


        solution = aList;
        System.out.println("Solution: " + solution.size());
        return aList;
    }

    private List<ArrayList<Integer>> makePuzzle() {
        List<ArrayList<Integer>> newPuzzle = new ArrayList<ArrayList<Integer>>();
        List<HashSet<Integer>> allColumns = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> row0 = new HashSet<Integer>();
        allColumns.add(row0);
        HashSet<Integer> row1 = new HashSet<Integer>();
        allColumns.add(row1);
        HashSet<Integer> row2 = new HashSet<Integer>();
        allColumns.add(row2);
        HashSet<Integer> row3 = new HashSet<Integer>();
        allColumns.add(row3);
        HashSet<Integer> row4 = new HashSet<Integer>();
        allColumns.add(row4);
        HashSet<Integer> row5 = new HashSet<Integer>();
        allColumns.add(row5);
        HashSet<Integer> row6 = new HashSet<Integer>();
        allColumns.add(row6);
        HashSet<Integer> row7 = new HashSet<Integer>();
        allColumns.add(row7);
        HashSet<Integer> row8 = new HashSet<Integer>();
        allColumns.add(row8);

        List<HashSet<Integer>> allBoxes = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> box0 = new HashSet<Integer>();
        allBoxes.add(box0);
        HashSet<Integer> box1 = new HashSet<Integer>();
        allBoxes.add(box1);
        HashSet<Integer> box2 = new HashSet<Integer>();
        allBoxes.add(box2);
        HashSet<Integer> box3 = new HashSet<Integer>();
        allBoxes.add(box3);
        HashSet<Integer> box4 = new HashSet<Integer>();
        allBoxes.add(box4);
        HashSet<Integer> box5 = new HashSet<Integer>();
        allBoxes.add(box5);
        HashSet<Integer> box6 = new HashSet<Integer>();
        allBoxes.add(box6);
        HashSet<Integer> box7 = new HashSet<Integer>();
        allBoxes.add(box7);
        HashSet<Integer> box8 = new HashSet<Integer>();
        allBoxes.add(box8);

        Random rand = new Random();
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> currList = new ArrayList<Integer>();
            HashSet<Integer> currNums = new HashSet<Integer>();
            for (int j = 0; j < 9; j++) {
                HashSet<Integer> currCol = allColumns.get(j);
                int newNum = rand.nextInt(9) + 1;
                while ((currNums.contains(newNum)) && (currCol.contains(newNum))) {
                    newNum = rand.nextInt(9) + 1;
                }
                currNums.add(newNum);
                currList.add(newNum);
                currCol.add(newNum);
            }
            newPuzzle.add(currList);
        }


        return newPuzzle;
    }

    private List<ArrayList<Integer>> makeNewPuzzle() {
        List<ArrayList<Integer>> newPuzzle = new ArrayList<ArrayList<Integer>>();
        List<HashSet<Integer>> allRows = new ArrayList<HashSet<Integer>>();

        HashSet<Integer> row0 = new HashSet<Integer>();
        allRows.add(row0);
        HashSet<Integer> row1 = new HashSet<Integer>();
        allRows.add(row1);
        HashSet<Integer> row2 = new HashSet<Integer>();
        allRows.add(row2);
        HashSet<Integer> row3 = new HashSet<Integer>();
        allRows.add(row3);
        HashSet<Integer> row4 = new HashSet<Integer>();
        allRows.add(row4);
        HashSet<Integer> row5 = new HashSet<Integer>();
        allRows.add(row5);
        HashSet<Integer> row6 = new HashSet<Integer>();
        allRows.add(row6);
        HashSet<Integer> row7 = new HashSet<Integer>();
        allRows.add(row7);
        HashSet<Integer> row8 = new HashSet<Integer>();
        allRows.add(row8);

        List<HashSet<Integer>> allCols = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> col0 = new HashSet<Integer>();
        allCols.add(col0);
        HashSet<Integer> col1 = new HashSet<Integer>();
        allCols.add(col1);
        HashSet<Integer> col2 = new HashSet<Integer>();
        allCols.add(col2);
        HashSet<Integer> col3 = new HashSet<Integer>();
        allCols.add(col3);
        HashSet<Integer> col4 = new HashSet<Integer>();
        allCols.add(col4);
        HashSet<Integer> col5 = new HashSet<Integer>();
        allCols.add(col5);
        HashSet<Integer> col6 = new HashSet<Integer>();
        allCols.add(col6);
        HashSet<Integer> col7 = new HashSet<Integer>();
        allCols.add(col7);
        HashSet<Integer> col8 = new HashSet<Integer>();
        allCols.add(col8);



        Random rand = new Random();
//        HashSet<Integer> temp = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> temp = new HashSet<Integer>();

            ArrayList<Integer> currList = new ArrayList<Integer>();
//            System.out.println();
//            System.out.println("i: " + i);
            for (int j = 0; j < 9; j++) {
                int rowI = (i/3) + (j/3) + (i/3)*2;
                HashSet<Integer> currRow = allRows.get(rowI);
//                System.out.println("rowI: " + rowI);
                int colI = (i%3) + (j%3) + (i%3)*2;
//                System.out.println("colI: "+ colI);
                HashSet<Integer> currCol = allCols.get(colI);

                int newNum = rand.nextInt(9) + 1;
                System.out.println("NewNum: " + newNum);

                if ((temp.contains(newNum)) || (currCol.contains(newNum))) {
                    newNum = 1;
                }
                while (isValid(currCol, currRow, currList, newNum)) {
                    newNum = (newNum + 1);
                }


//                System.out.println(newNum);
                currList.add(newNum);
                temp.add(newNum);
                currCol.add(newNum);
                currRow.add(newNum);
            }
//            System.out.println();
//            System.out.println();
            temp.clear();
            newPuzzle.add(currList);
        }


        solution = newPuzzle;

        return newPuzzle;
    }


    private List<ArrayList<Integer>> makeNewPuzzle2() {
        List<ArrayList<Integer>> newPuzzle = new ArrayList<ArrayList<Integer>>();
        List<HashSet<Integer>> allRows = new ArrayList<HashSet<Integer>>();

        HashSet<Integer> row0 = new HashSet<Integer>();
        allRows.add(row0);
        HashSet<Integer> row1 = new HashSet<Integer>();
        allRows.add(row1);
        HashSet<Integer> row2 = new HashSet<Integer>();
        allRows.add(row2);
        HashSet<Integer> row3 = new HashSet<Integer>();
        allRows.add(row3);
        HashSet<Integer> row4 = new HashSet<Integer>();
        allRows.add(row4);
        HashSet<Integer> row5 = new HashSet<Integer>();
        allRows.add(row5);
        HashSet<Integer> row6 = new HashSet<Integer>();
        allRows.add(row6);
        HashSet<Integer> row7 = new HashSet<Integer>();
        allRows.add(row7);
        HashSet<Integer> row8 = new HashSet<Integer>();
        allRows.add(row8);

        List<HashSet<Integer>> allCols = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> col0 = new HashSet<Integer>();
        allCols.add(col0);
        HashSet<Integer> col1 = new HashSet<Integer>();
        allCols.add(col1);
        HashSet<Integer> col2 = new HashSet<Integer>();
        allCols.add(col2);
        HashSet<Integer> col3 = new HashSet<Integer>();
        allCols.add(col3);
        HashSet<Integer> col4 = new HashSet<Integer>();
        allCols.add(col4);
        HashSet<Integer> col5 = new HashSet<Integer>();
        allCols.add(col5);
        HashSet<Integer> col6 = new HashSet<Integer>();
        allCols.add(col6);
        HashSet<Integer> col7 = new HashSet<Integer>();
        allCols.add(col7);
        HashSet<Integer> col8 = new HashSet<Integer>();
        allCols.add(col8);



        Random rand = new Random();
//        HashSet<Integer> temp = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> temp = new HashSet<Integer>();

            ArrayList<Integer> currList = new ArrayList<Integer>();
//            System.out.println();
//            System.out.println("i: " + i);
            for (int j = 0; j < 9; j++) {
                int rowI = (i/3) + (j/3) + (i/3)*2;
                HashSet<Integer> currRow = allRows.get(rowI);
//                System.out.println("rowI: " + rowI);
                int colI = (i%3) + (j%3) + (i%3)*2;
//                System.out.println("colI: "+ colI);
                HashSet<Integer> currCol = allCols.get(colI);

                int newNum = rand.nextInt(9) + 1;
                System.out.println("NewNum: " + newNum);

                if ((temp.contains(newNum)) || (currCol.contains(newNum))) {
                    newNum = 1;
                }
                while (isValid(currCol, currRow, currList, newNum)) {
                    newNum = (newNum + 1);
                }


//                System.out.println(newNum);
                currList.add(newNum);
                temp.add(newNum);
                currCol.add(newNum);
                currRow.add(newNum);
            }
//            System.out.println();
//            System.out.println();
            temp.clear();
            newPuzzle.add(currList);
        }


        solution = newPuzzle;

        return newPuzzle;
    }

    private boolean isValid(HashSet<Integer> col, HashSet<Integer> row, ArrayList<Integer> box, int num) {
        if (col.contains(num) || row.contains(num) || box.contains(num)) {
            return true;
        }
        return false;
    }

    private List<ArrayList<Integer>> makeDiagPuzzle() {
        List<ArrayList<Integer>> newPuzzle = new ArrayList<ArrayList<Integer>>();
        newPuzzle = createPuzzle();
        List<HashSet<Integer>> allRows = new ArrayList<HashSet<Integer>>();

        HashSet<Integer> row0 = new HashSet<Integer>();
        allRows.add(row0);
        HashSet<Integer> row1 = new HashSet<Integer>();
        allRows.add(row1);
        HashSet<Integer> row2 = new HashSet<Integer>();
        allRows.add(row2);
        HashSet<Integer> row3 = new HashSet<Integer>();
        allRows.add(row3);
        HashSet<Integer> row4 = new HashSet<Integer>();
        allRows.add(row4);
        HashSet<Integer> row5 = new HashSet<Integer>();
        allRows.add(row5);
        HashSet<Integer> row6 = new HashSet<Integer>();
        allRows.add(row6);
        HashSet<Integer> row7 = new HashSet<Integer>();
        allRows.add(row7);
        HashSet<Integer> row8 = new HashSet<Integer>();
        allRows.add(row8);

        List<HashSet<Integer>> allCols = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> col0 = new HashSet<Integer>();
        allCols.add(col0);
        HashSet<Integer> col1 = new HashSet<Integer>();
        allCols.add(col1);
        HashSet<Integer> col2 = new HashSet<Integer>();
        allCols.add(col2);
        HashSet<Integer> col3 = new HashSet<Integer>();
        allCols.add(col3);
        HashSet<Integer> col4 = new HashSet<Integer>();
        allCols.add(col4);
        HashSet<Integer> col5 = new HashSet<Integer>();
        allCols.add(col5);
        HashSet<Integer> col6 = new HashSet<Integer>();
        allCols.add(col6);
        HashSet<Integer> col7 = new HashSet<Integer>();
        allCols.add(col7);
        HashSet<Integer> col8 = new HashSet<Integer>();
        allCols.add(col8);



        Random rand = new Random();
//        HashSet<Integer> temp = new HashSet<Integer>();
        HashSet<Integer> temp = new HashSet<Integer>();
        ArrayList<Integer> currList = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            int rowI = (i / 3) + (0 / 3) + (i / 3) * 2;
            HashSet<Integer> currRow = allRows.get(rowI);
            int colI = (i % 3) + (0 % 3) + (i % 3) * 2;
            HashSet<Integer> currCol = allCols.get(colI);
            int newNum = rand.nextInt(9) + 1;
            if ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {
                newNum = 1;
            }
            while ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {

                newNum = (newNum + 1);

            }
            temp.add(newNum);
            currCol.add(newNum);
            currRow.add(newNum);
        }

        temp.clear();
        newPuzzle.add(0, currList);
        currList.clear();

        for (int i = 0; i < 9; i++) {
            int rowI = (i / 3) + (4 / 3) + (i / 3) * 2;
            HashSet<Integer> currRow = allRows.get(rowI);
            int colI = (i % 3) + (4 % 3) + (i % 3) * 2;
            HashSet<Integer> currCol = allCols.get(colI);
            int newNum = rand.nextInt(9) + 1;
            if ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {
                newNum = 1;
            }
            while ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {

                newNum = (newNum + 1);

            }
            temp.add(newNum);
            currCol.add(newNum);
            currRow.add(newNum);
        }
        temp.clear();
        newPuzzle.add(4, currList);
        currList.clear();

        for (int i = 0; i < 9; i++) {
            int rowI = (i / 3) + (8 / 3) + (i / 3) * 2;
            HashSet<Integer> currRow = allRows.get(rowI);
            int colI = (i % 3) + (8 % 3) + (i % 3) * 2;
            HashSet<Integer> currCol = allCols.get(colI);
            int newNum = rand.nextInt(9) + 1;
            if ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {
                newNum = 1;
            }
            while ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {

                newNum = (newNum + 1);

            }
            temp.add(newNum);
            currCol.add(newNum);
            currRow.add(newNum);
        }
        temp.clear();
        newPuzzle.add(8, currList);
        currList.clear();

        for (int i = 1; i < 8; i = i+1) {
            if (i == 4) {
                i = i + 1;
            } else {

//            System.out.println();
//            System.out.println("i: " + i);
                for (int j = 0; j < 9; j++) {
                    int rowI = (i / 3) + (j / 3) + (i / 3) * 2;
                    HashSet<Integer> currRow = allRows.get(rowI);
//                System.out.println("rowI: " + rowI);
                    int colI = (i % 3) + (j % 3) + (i % 3) * 2;
//                System.out.println("colI: "+ colI);
                    HashSet<Integer> currCol = allCols.get(colI);

                    int newNum = 1;
                    System.out.println("NewNum: " + newNum);


                    while ((temp.contains(newNum)) || (currRow.contains(newNum)) || (currCol.contains(newNum))) {
                        System.out.println(newNum);
                        if (temp.contains(newNum)) {
                            System.out.println("Temp contains!");
                        }
                        if (currRow.contains(newNum)) {
                            System.out.println("Curr row contains!");
                        }
                        if (currCol.contains(newNum)) {
                            System.out.println("Curr col contains!");
                        }
                        newNum = (newNum + 1);
                    }
//                System.out.println(newNum);
                    currList.add(newNum);
                    temp.add(newNum);
                    currCol.add(newNum);
                    currRow.add(newNum);
                }
//            System.out.println();
//            System.out.println();
                temp.clear();
                newPuzzle.add(i, currList);
            }
        }

        solution = newPuzzle;

        return newPuzzle;
    }

    private void setButtonListeners() {
        List<ArrayList<Integer>> currPuzzle = makeNewPuzzle();

        for (int i = 1; i <= 9; i++) {
            HashSet<Integer> randIndices = createRand();
            for (int j = 1; j <= 9; j++) {
                String currButtonName = "button_" + i + "_" + j;
                int currId = getResources().getIdentifier(currButtonName, "id", getPackageName());
                Button currButton = (Button) findViewById(currId);
                if (randIndices.contains(j - 1)) {
                    unsolvedNums.add(currId);
                    currButton.setText("");
                    currButton.setOnClickListener(this);
                    currButton.setBackgroundColor(Color.TRANSPARENT);
                    checkNums.add(currPuzzle.get(i-1).get(j-1));
                } else {
                    currButton.setEnabled(false);
                    currButton.setTextColor(Color.BLACK);

                    currButton.setBackgroundColor(Color.TRANSPARENT);
                    currButton.setText(String.valueOf(currPuzzle.get(i-1).get(j-1)));
                }
//                currButton.setOnClickListener(this);

            }
        }
        for (int i = 0; i < numButtons.size(); i++) {
            Button currButton = (Button) findViewById(numButtons.get(i));
            currButton.setOnClickListener(this);
        }
        Button finalButton = (Button) findViewById(R.id.finishButton);
        finalButton.setOnClickListener(this);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        Button clearOneButton = (Button) findViewById(R.id.clearOneButton);
        clearOneButton.setOnClickListener(this);
    }

    private HashSet<Integer> createRand() {
//        ArrayList<Integer> setNums = new ArrayList<Integer>();
        HashSet<Integer> currRandNums = new HashSet<Integer>();
        int getDifficultyNum;
        Random rand = new Random();
        int numRandNums = rand.nextInt(difficultyNum);
        while(numRandNums == 0) {
            numRandNums = rand.nextInt(difficultyNum);
        }
        int curr = 0;
        while (curr < numRandNums) {
            int currRand = rand.nextInt(10);
            while (currRandNums.contains(currRand)) {
                currRand = rand.nextInt(10);
            }
            currRandNums.add(currRand);
            curr++;
//            setNums.add(currRand);
        }


        return currRandNums;
    }

    private void addNumButtons() {
        numButtons.add(R.id.button1);
        numButtons.add(R.id.button2);
        numButtons.add(R.id.button3);
        numButtons.add(R.id.button4);
        numButtons.add(R.id.button5);
        numButtons.add(R.id.button6);
        numButtons.add(R.id.button7);
        numButtons.add(R.id.button8);
        numButtons.add(R.id.button9);

    }


    @Override
    public void onClick(View v) {

        int currID = v.getId();
        if (currID == R.id.finishButton) {
            checkSolution();
            Button finishButton = (Button) findViewById(currID);
            finishButton.setEnabled(false);
        } else if (currID == R.id.clearButton) {
            for (int i = 0; i < unsolvedNums.size(); i++) {
                System.out.println(unsolvedNums.get(i));
                Button currButton = (Button) findViewById(unsolvedNums.get(i));
                currButton.setText("");
                currButton.setBackgroundColor(Color.TRANSPARENT);
            }
            Button finishButton = (Button) findViewById(R.id.finishButton);
            finishButton.setEnabled(true);

        } else if (currID == R.id.clearOneButton) {
            if (selectedButtonID != null) {
                Button changeButton = (Button) findViewById(selectedButtonID);
                changeButton.setText("");
                changeButton.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (numButtons.contains(currID)) {
            if (selectedButtonID != null) {

                Button currSelect = (Button) findViewById(currID);
                Button changeButton = (Button) findViewById(selectedButtonID);

                String selectButtonName = getResources().getResourceName(currID);
//                String buttonName = getResources().getResourceName(selectedButtonID);

                int currSelectedNum = selectButtonName.charAt(28) - '0';
//                int a = buttonName.charAt(29) - '0';
//                int b = buttonName.charAt(31) - '0';
                changeButton.getBackground().clearColorFilter();

//                int solutionNum = solution.get((a - 1)).get(b - 1);
//                if (solutionNum == currSelectedNum) {
//                    changeButton.setBackgroundColor(Color.GREEN);
//                } else {
//                    changeButton.setBackgroundColor(Color.RED);
//                }

                changeButton.setText(String.valueOf(currSelect.getText()));
            }
        } else {
            selectedButtonID = currID;
            if (selectedButton != null) {
                if (selectedButton.getText() == "") {
                    selectedButton.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    selectedButton.setBackgroundColor(Color.LTGRAY);
                }
            }
            Button currButton = (Button) findViewById(selectedButtonID);
            selectedButton = currButton;
            currButton.setBackgroundColor(Color.YELLOW);

        }

    }

    private void checkSolution() {
        for (int i = 0; i < unsolvedNums.size(); i++) {
            int currId = unsolvedNums.get(i);
            Button currButton = (Button) findViewById(currId);
            String userNum = (String) currButton.getText();
            String actualNum = checkNums.get(i) + "";
            if (actualNum.equals(userNum)) {
                currButton.setBackgroundColor(Color.GREEN);
            } else {
                currButton.setText(actualNum);
                currButton.setBackgroundColor(Color.RED);
            }
        }

    }


}
