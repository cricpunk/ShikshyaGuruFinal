package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shikshyaguru.shikshyaguru.R;


/**
 * Created by cricpunk on 7/12/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class SignUpFragment extends Fragment implements View.OnFocusChangeListener, View.OnClickListener{

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._3_6_sign_up_fragment_new, container, false);

        EditText password = view.findViewById(R.id.password);
        EditText userName = view.findViewById(R.id.userName);
        EditText email = view.findViewById(R.id.email);

        password.setOnFocusChangeListener(this);
        userName.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(v,
                        centerX(v),
                        centerY(v),
                        0,
                        finalRadius(v)).start();
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    public int centerX(View v) {
        return (v.getLeft() + v.getRight()) / 2;
    }

    public int centerY(View v) {
        return (v.getTop() + v.getBottom()) / 2 ;
    }

    public float finalRadius(View v) {
        //Final radius for the clipping circle
        int distanceX = Math.max(centerX(v), v.getWidth() - centerX(v));
        int distanceY = Math.max(centerY(v), v.getHeight() - centerY(v));
        return  (float) Math.hypot(distanceX, distanceY);
    }
}
