package com.icanerdogan.posedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class CanvasFigureDraw extends View {
    private Paint paint = new Paint();
    private float motionX = 50f;
    private float motionY = 50f;
    private Bitmap bitmap1;

    public CanvasFigureDraw(Context context) {
        super(context);

        paint.setColor(getResources().getColor(R.color.black));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(40f);
        paint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        motionX = event.getX();
        motionY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(motionX, motionY);
                break;
            case MotionEvent.ACTION_DOWN:
                touchStart(motionX, motionY);
                break;


        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_1);
        canvas.drawBitmap(bitmap1, motionX, motionY, paint);

    }


    private void touchStart(float xVal, float yVal) {
        motionX = xVal;
        motionY = yVal;
    }

    private void touchMove(float xVal, float yVal) {
        motionX = xVal;
        motionY = yVal;

        invalidate();
    }

    private void touchUp() {

    }
}
