package com.example.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class TopView extends View {
    Paint paint;
    Canvas canvas;

    public TopView(Context context) {
        super(context);
        canvas = new Canvas();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public TopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        canvas = new Canvas();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.GRAY);
//        for (int i = 0; i < 3; i++) {
//            int pxConv = dpToPx(20 + 38*i);
//            int pxConv2 = dpToPx(400);
//
//            canvas.drawLine(pxConv, 20, pxConv, pxConv2, paint);
//        }
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
//        int startX = dpToPx(10);
//        int startY = dpToPx(50);
//        int endX = dpToPx(400);
//        canvas.drawLine(startY, startX, startY, endX, paint);

        drawCols(canvas, paint);
        drawVertBorders(canvas, paint);
        drawRows(canvas, paint);
        drawHorizBorders(canvas, paint);
    }

    private void drawCols(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        for (int i = 50; i <= 330; i = i + 120) {
            drawVertLines(canvas, i,2);
        }


    }

    private void drawRows(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        for (int i = 50; i <= 330; i = i + 120) {
            drawHorizLines(canvas, i, 2);
        }

    }

    private void drawVertBorders(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
        for (int i = 130; i <= 285; i = i + 120) {
            drawVertLines(canvas, i, 1);
        }
    }

    private void drawHorizBorders(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
        for (int i = 130; i <= 285; i = i + 120) {
            drawHorizLines(canvas, i, 1);
        }
    }

    private void drawVertLines(Canvas canvas, int start, int end) {
        for (int i = 0; i < end; i++) {
            int pxConv = dpToPx(start + 40*i);
            int pxConv2 = dpToPx(370);
            int startNum = dpToPx(10);

            canvas.drawLine(pxConv, startNum, pxConv, pxConv2, paint);
        }
    }

    private void drawHorizLines(Canvas canvas, int start, int end) {
        for (int i = 0; i < end; i++) {
            int pxConv = dpToPx(start + 40*i);
            int pxConv2 = dpToPx(370);
            int startNum = dpToPx(10);
            canvas.drawLine(startNum, pxConv, pxConv2, pxConv, paint);
        }


    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
