package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Styles;


/**
 * Created by cricpunk on 7/12/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class SignUpFragment extends Fragment{

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._3_6_sign_up_fragment_new, container, false);


        EditText userName = view.findViewById(R.id.et_sign_up_user_name);
        EditText email = view.findViewById(R.id.et_sign_up_email);
        EditText password = view.findViewById(R.id.et_sign_up_password);
        EditText confirmPassword = view.findViewById(R.id.et_sign_up_confirm_password);

        userName.setOnFocusChangeListener(Styles.textViewAnimation);
        email.setOnFocusChangeListener(Styles.textViewAnimation);
        password.setOnFocusChangeListener(Styles.textViewAnimation);
        confirmPassword.setOnFocusChangeListener(Styles.textViewAnimation);

        return view;
    }

}
