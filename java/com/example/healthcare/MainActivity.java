package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        private static int SPLASH_SCREEN=3000;
    Animation topAnim,bottomAnim;
    ImageView img;
    TextView logo,Slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_main);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView3);
        Slogan=findViewById(R.id.textView4);

        img.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        Slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(()->{

                Intent it = new Intent(MainActivity.this, Loginactivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(img, "logo_image");
                pairs[1] = new Pair<View, String>(logo, "logo_text");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(it, options.toBundle());
                }
            },SPLASH_SCREEN);
        }


}


