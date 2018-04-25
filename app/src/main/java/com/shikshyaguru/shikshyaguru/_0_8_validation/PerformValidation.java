package com.shikshyaguru.shikshyaguru._0_8_validation;

import android.app.Activity;
import android.widget.EditText;

import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;

import java.util.Objects;

/*
 * Created by Pankaj Koirala on 4/3/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class PerformValidation {

    private Activity activity;

    public PerformValidation(Activity activity) {
        this.activity = activity;
    }

    // Validate user name input field
    public boolean userName(EditText editText) {

        String userName = editText.getText().toString().trim();

        if (userName.equals("") || userName.length() <= 5) {
            PopupCollections.tooltipMessage(activity, editText, "User name cannot be empty and must be longer than five characters! ").show();
            return false;
        } else {
            return true;
        }

    }

    // Validate email input field
    public boolean emailId(EditText editText) {

        String email = editText.getText().toString().trim();

        if (email.equals("") || (!email.contains("@") && !email.contains("."))) {
            PopupCollections.tooltipMessage(activity, editText, "Email Id is badly formatted ! ").show();
            return false;
        }
        else {
            return true;
        }

    }

    // Validate password input field
    public boolean passwords(EditText pass1, EditText pass2) {

        String pass = pass1.getText().toString().trim();
        String conPass = pass2.getText().toString().trim();

        if (pass.equals("") || pass.length() <= 5) {
            PopupCollections.tooltipMessage(activity, pass1, "Password cannot be empty and must be longer than six characters! ").show();
            return false;
        } else if (conPass.equals("") || !conPass.equals(pass)) {
            PopupCollections.tooltipMessage(activity, pass2, "Password did not match ! ").show();
            return false;
        }  else {
            return true;
        }

    }


    // Input field validation for different layouts
    public static boolean inputFieldValidation(Activity activity, EditText editText) {

        String value = editText.getText().toString();

        if (value.equals("")) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, editText.getHint().toString() + " cannot be left empty ! ").show();
            return false;
        } else {
            return true;
        }

    }

    public boolean password(EditText password) {
        String pass = password.getText().toString().trim();

        if (pass.equals("") || pass.length() <= 5) {
            PopupCollections.tooltipMessage(activity, password, "Password cannot be empty and must be longer than six characters! ").show();
            return false;
        }  else {
            return true;
        }
    }

}
