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

    private List<ArrayList<Integer>> allCols = new ArrayList<ArrayList<Integer>>();
    private List<ArrayList<Integer>> allRows = new ArrayList<ArrayList<Integer>>();


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


    private boolean isValid(HashSet<Integer> col, HashSet<Integer> row, ArrayList<Integer> box, int num) {
        if (col.contains(num) || row.contains(num) || box.contains(num)) {
            return true;
        }
        return false;
    }

    private void setButtonListeners() {
        makeAnPuzzle();

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
                    checkNums.add(solution.get(i-1).get(j-1));
                } else {
                    currButton.setEnabled(false);
                    currButton.setTextColor(Color.BLACK);

                    currButton.setBackgroundColor(Color.TRANSPARENT);
                    currButton.setText(String.valueOf(solution.get(i-1).get(j-1)));
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


    public void makeAnPuzzle() {
        ArrayList<Integer> b1 =  new ArrayList<>(Arrays.asList(2, 1, 5, 9, 8, 6, 7, 3, 4));
        ArrayList<Integer> b2 =  new ArrayList<>(Arrays.asList(3, 7, 9, 1, 2, 4, 8, 5, 6));
        ArrayList<Integer> b3 =  new ArrayList<>(Arrays.asList(8, 6, 4, 3, 5, 7, 2, 1, 9));
        ArrayList<Integer> b4 =  new ArrayList<>(Arrays.asList(4, 5, 2, 8, 6, 9, 3, 7, 1));
        ArrayList<Integer> b5 =  new ArrayList<>(Arrays.asList(7, 8, 1, 5, 4, 3, 6, 9, 2));
        ArrayList<Integer> b6 =  new ArrayList<>(Arrays.asList(6, 9, 3, 1, 7, 2, 4, 8, 5));
        ArrayList<Integer> b7 =  new ArrayList<>(Arrays.asList(5, 2, 7, 6, 4, 8, 1, 9, 3));
        ArrayList<Integer> b8 =  new ArrayList<>(Arrays.asList(4, 1, 8, 9, 3, 7, 2, 6, 5));
        ArrayList<Integer> b9 =  new ArrayList<>(Arrays.asList(9, 3, 6, 5, 2, 1, 7, 4, 8));

        solution.add(b1);
        solution.add(b2);
        solution.add(b3);
        solution.add(b4);
        solution.add(b5);
        solution.add(b6);
        solution.add(b7);
        solution.add(b8);
        solution.add(b9);

        Random rand = new Random();
        initializeSets();
        addToRowCol();



        for (int i = 0; i < 3; i++) {
            int randomRow = rand.nextInt(3) + i*3;

            int randomRow2 = rand.nextInt(3) + i*3;
            while (randomRow == randomRow2) {
                randomRow = rand.nextInt(3) + i*3;
            }
            ArrayList<Integer> a = allRows.get(randomRow);
            ArrayList<Integer> b = allRows.get(randomRow2);
            allRows.set(randomRow, b);
            allRows.set(randomRow2, a);
        }
        rowToBox();
        boxToCol();
        for (int j = 0; j < 3; j++) {
            int randomCol = rand.nextInt(3) + j*3;

            int randomCol2 = rand.nextInt(3) + j*3;
            while (randomCol == randomCol2) {
                randomCol = rand.nextInt(3) + j*3;
            }
            ArrayList<Integer> a = allRows.get(randomCol);
            ArrayList<Integer> b = allRows.get(randomCol2);
            allRows.set(randomCol, b);
            allRows.set(randomCol2, a);
        }
//        colToBox();

    }


    private void rowToBox() {
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> curr = solution.get(i);
            for (int j = 0; j < 9; j++) {
                int rowI = (i/3) + (j/3) + (i/3)*2;
                ArrayList<Integer> currRow = allRows.get(rowI);
                int k = (j % 3) + 3*(i%3);
                curr.set(j, currRow.get(k));

            }
            solution.set(i, curr);
        }

    }

    private void boxToCol() {
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> curr = solution.get(i);

            for (int j = 0; j < 9; j++) {
                int colI = (i%3) + (j%3) + (i%3)*2;
                ArrayList<Integer> currCol = allCols.get(colI);
                int k = 3*(i / 3) + (j/3);
                currCol.set(k, curr.get(j));
                allCols.set(i, currCol);
//                System.out.println("Curr colI: "+ colI + " and k is " +currCol.get(k) +  " and j is: " + j);
            }
        }
//        printCols();
    }

    private void printCols() {
        for (int i = 0; i < allCols.size(); i++) {
            ArrayList<Integer> currCol = allCols.get(i);
            for (int j = 0; j < currCol.size(); j++) {
                System.out.print(currCol.get(j) + " ");
            }
            System.out.println();
        }
    }

    private void colToBox() {
//        printCols();
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> curr = solution.get(i);

            for (int j = 0; j < 9; j++) {
                int colI = (i%3) + (j%3) + (j%3)*2;
                ArrayList<Integer> currCol = allCols.get(colI);
                int k = 3*(i / 3) + (j/3);
                curr.set(j, currCol.get(k));
                System.out.println("Curr colI: "+ colI + " and k is " +(k) +  " and j is: " + j);
            }
            solution.set(i, curr);
        }

    }

    private void addToRowCol() {
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> curr = solution.get(i);

            for (int j = 0; j < 9; j++) {
                int rowI = (i/3) + (j/3) + (i/3)*2;
                ArrayList<Integer> currRow = allRows.get(rowI);
                int colI = (i%3) + (j%3) + (i%3)*2;
                ArrayList<Integer> currCol = allCols.get(colI);
                currRow.add(curr.get(j));
                currCol.add(curr.get(j));

            }
        }

    }



    private boolean isValid(HashSet<Integer> col, HashSet<Integer> row, List<Integer> box, int num) {
        if (col.contains(num) || row.contains(num) || box.contains(num)) {
            return true;
        }
        return false;
    }



    public void printArr() {
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> currList = solution.get(i);
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.println();
                }
                System.out.print(currList.get(j) + " ");
            }
            System.out.println();

        }
    }

    private void initializeSets() {

        List<ArrayList<Integer>> newPuzzle = new ArrayList<ArrayList<Integer>>();


        ArrayList<Integer> row0 = new ArrayList<Integer>();
        allRows.add(row0);
        ArrayList<Integer> row1 = new ArrayList<Integer>();
        allRows.add(row1);
        ArrayList<Integer> row2 = new ArrayList<Integer>();
        allRows.add(row2);
        ArrayList<Integer> row3 = new ArrayList<Integer>();
        allRows.add(row3);
        ArrayList<Integer> row4 = new ArrayList<Integer>();
        allRows.add(row4);
        ArrayList<Integer> row5 = new ArrayList<Integer>();
        allRows.add(row5);
        ArrayList<Integer> row6 = new ArrayList<Integer>();
        allRows.add(row6);
        ArrayList<Integer> row7 = new ArrayList<Integer>();
        allRows.add(row7);
        ArrayList<Integer> row8 = new ArrayList<Integer>();
        allRows.add(row8);

        ArrayList<Integer> col0 = new ArrayList<Integer>();
        allCols.add(col0);
        ArrayList<Integer> col1 = new ArrayList<Integer>();
        allCols.add(col1);
        ArrayList<Integer> col2 = new ArrayList<Integer>();
        allCols.add(col2);
        ArrayList<Integer> col3 = new ArrayList<Integer>();
        allCols.add(col3);
        ArrayList<Integer> col4 = new ArrayList<Integer>();
        allCols.add(col4);
        ArrayList<Integer> col5 = new ArrayList<Integer>();
        allCols.add(col5);
        ArrayList<Integer> col6 = new ArrayList<Integer>();
        allCols.add(col6);
        ArrayList<Integer> col7 = new ArrayList<Integer>();
        allCols.add(col7);
        ArrayList<Integer> col8 = new ArrayList<Integer>();
        allCols.add(col8);

    }


}
