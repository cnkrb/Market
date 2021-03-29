package com.cenk.marketsmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.Activities.InfoActivity;
import com.cenk.marketsmi.Models.Categori;
import com.cenk.marketsmi.Utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenk.marketsmi.Utils.Utils.interfaces;

public class SplashActivity extends AppCompatActivity {
    ImageView upImageView;
    TextView downImageVie;
    Animation up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        up= AnimationUtils.loadAnimation(this,R.anim.up);
        down= AnimationUtils.loadAnimation(this,R.anim.down);
        upImageView=findViewById(R.id.up);
        downImageVie=findViewById(R.id.down);
        upImageView.setAnimation(up);
        downImageVie.setAnimation(down);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Animatoo.animateFade(SplashActivity.this);
                finish();
            }
        }, 1500);
    }

}