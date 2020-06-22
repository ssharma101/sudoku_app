package com.example.sudoku;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_screen);

//        ScreenView screenView = new ScreenView(this);
//        Canvas canvas = new Canvas();
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        screenView.draw(canvas, paint);
        Button easyButton = (Button) findViewById(R.id.easyButton);

    }


}
