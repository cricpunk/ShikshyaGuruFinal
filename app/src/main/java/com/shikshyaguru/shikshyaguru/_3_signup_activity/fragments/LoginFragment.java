package com.shikshyaguru.shikshyaguru._3_signup_activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.HomePageActivity;

/**
 * Created by cricpunk on 7/12/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class LoginFragment extends Fragment implements View.OnClickListener{
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static LoginFragment newInstance(int page, String title) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        loginFragment.setArguments(args);
        return loginFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 2);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._3_2_login_fragment, container, false);

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.login_full_layout);
        layout.setPadding(0,120,0,120);

        Button login = (Button) view.findViewById(R.id.loginButton);
        login.setOnClickListener(this);

//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginButton :
                startActivity(new Intent(getContext(), HomePageActivity.class));
                break;

            default:
                break;
        }
    }
}
