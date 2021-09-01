package com.icanerdogan.posedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easystudio.rotateimageview.RotateZoomImageView;
import com.skydoves.colorpickerview.AlphaTileView;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.flag.BubbleFlag;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.sliders.AlphaSlideBar;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    ColorPickerView imageViewPose;
    RotateZoomImageView test;
    LinearLayout btnRefresh,container;
    TextView angleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageViewPose = findViewById(R.id.imageView2);
        angleTextView = findViewById(R.id.angleText);
        btnRefresh = findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(this);
        container = findViewById(R.id.container);

//        View view = new CanvasFigureDraw(this);
//        container.addView(view);

        Intent intent = getIntent();
        String text = intent.getStringExtra("Text");
        angleTextView.setText(text);

        Singleton singleton = Singleton.getInstance();

        Drawable drawable = new BitmapDrawable(getResources(), singleton.getMyImage());
        imageViewPose.setPaletteDrawable(drawable);
//        imageViewPose.setImageBitmap(singleton.getMyImage());


        BubbleFlag bubbleFlag = new BubbleFlag(this);

        imageViewPose.setFlagView(bubbleFlag);
        imageViewPose.setColorListener(
                (ColorEnvelopeListener)
                        (envelope, fromUser) -> {
                            Log.d("color: %s", envelope.getHexCode());
                            setLayoutColor(envelope);
                        });

        // attach alphaSlideBar
        final AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
        imageViewPose.attachAlphaSlider(alphaSlideBar);

        // attach brightnessSlideBar
        final BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessSlide);
        imageViewPose.attachBrightnessSlider(brightnessSlideBar);
        imageViewPose.setLifecycleOwner(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.refresh:
                backMain();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setLayoutColor(ColorEnvelope envelope) {
        TextView textView = findViewById(R.id.textView);
        textView.setText("#" + envelope.getHexCode());

        AlphaTileView alphaTileView = findViewById(R.id.alphaTileView);
        alphaTileView.setPaintColor(envelope.getColor());
    }

    private void backMain() {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }
}