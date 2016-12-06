package com.example.hyr.startv.ui;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.hyr.startv.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageview);

        imageView.setImageResource(R.drawable.loading);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();

        animationDrawable.start();
    }
}
