package com.shikshyaguru.shikshyaguru._1_splash_activity;

/*
 * Created by cricpunk on 7/9/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._2_welcome_activity.WelcomeSliderActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView orgLogo;
    private TextView orgName;
    private TextView orgSlogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._1_splash_screen);

        //RelativeLayout layout = findViewById(R.id.root_splash);
        //KenBurnsView background = findViewById(R.id.iv_splash_background);
        orgLogo = findViewById(R.id.iv_splash_org_logo);
        orgName =  findViewById(R.id.lbl_splash_org_name);
        orgSlogan = findViewById(R.id.lbl_splash_org_slogan);
        orgLogo.setVisibility(View.INVISIBLE);
        orgName.setVisibility(View.INVISIBLE);
        orgSlogan.setVisibility(View.INVISIBLE);

        animateLogo();
        animateText();
        startActivity();
    }

    private void animateLogo() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                orgLogo.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInDown)
                        .duration(3000)
                        .playOn(orgLogo);
            }
        }, 1500);
    }

    private void animateText() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                orgName.setVisibility(View.VISIBLE);
                orgSlogan.setVisibility(View.VISIBLE);

                YoYo.with(Techniques.FadeInDown)
                        .duration(4000)
                        .playOn(orgName);

                YoYo.with(Techniques.FadeInUp)
                        .duration(4000)
                        .playOn(orgSlogan);

            }
        }, 3500);
    }

    private void startActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeSliderActivity.class);
                startActivity(intent);
                finish();
            }
        }, 0000);
    }

}
