package com.example.cryptonews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.cryptonews.Models.CryptoNewsApiResponse;
import com.example.cryptonews.Models.CryptoNewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Handler handler;
    private TextView txt_app_name;
    private Animation textViewAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        txt_app_name = findViewById(R.id.txt_app_name);
        textViewAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textview_animation);

        handler = new Handler();

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        };

        handler.postDelayed(runnable, 4000);

        txt_app_name.setAnimation(textViewAnimation);




    }


}
