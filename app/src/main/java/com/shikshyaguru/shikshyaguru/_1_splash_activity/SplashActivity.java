package com.shikshyaguru.shikshyaguru._1_splash_activity;

/*
 * Created by cricpunk on 7/9/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.HomePageActivity;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;
import com.shikshyaguru.shikshyaguru.animation_collection.Animator;

//import com.shikshyaguru.shikshyaguru._4_home_page.views.HomePageActivity;

public class SplashActivity extends AppCompatActivity {

    Animator animator = new Animator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._1_splash_screen);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.splash_full_layout);

        //Fade in up Animation
        animator.fadeInUp(getApplicationContext(), layout);

        ImageView imageView = (ImageView) findViewById(R.id.orgLogo);
        Drawable drawable = imageView.getDrawable();

        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, InstitutionsHomePageActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
