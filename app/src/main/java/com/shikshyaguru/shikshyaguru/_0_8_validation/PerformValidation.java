package com.shikshyaguru.shikshyaguru._0_8_validation;

import android.app.Activity;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;

import java.util.Objects;

/*
 * Created by Pankaj Koirala on 4/3/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class PerformValidation {

    private static FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference mRef = mDatabase.getReference();

    // For validating user name and password
    private static boolean validUserName, validEmailId;

    private static boolean isValidUserName() {
        return validUserName;
    }

    private static void setValidUserName(boolean validUserName) {
        PerformValidation.validUserName = validUserName;
    }

    private static boolean isValidEmailId() {
        return validEmailId;
    }

    private static void setValidEmailId(boolean validEmailId) {
        PerformValidation.validEmailId = validEmailId;
    }


    public static boolean userName(final Activity activity, final EditText editText) {

        final String userName = editText.getText().toString().trim();
        Query userNameQuery = mRef.child("users").orderByChild("user_name").equalTo(userName);
        userNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0 ) {
                    setValidUserName(false);
                } else {
                    setValidUserName(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (userName.equals("")) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, "User name cannot be empty ! ").show();
            return false;
        } else if (userName.length() <= 5){
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, "User name must contain more than five characters ! ").show();
            return false;
        } else if (!isValidUserName()) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, "This user name has been already taken ! ").show();
            return false;
        } else {
            return true;
        }

    }

    public static boolean emailId(final Activity activity, final EditText editText) {

        final String email = editText.getText().toString().trim();

        Query emailQuery = mRef.child("users").orderByChild("email").equalTo(email);
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0 ) {
                    setValidEmailId(false);
                } else {
                    setValidEmailId(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (email.equals("")) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, "Email Id cannot be empty ! ").show();
            return false;
        } else if (!email.contains("@") && !email.contains(".")){
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, "Email is badly formatted ! ").show();
            return false;
        } else if (!isValidEmailId()) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, "This email has already got access privilege. Signup with another account or go back to login page ! ").show();
            return false;
        } else {
            return true;
        }

    }

    public static boolean passwords(Activity activity, EditText pass1, EditText pass2) {

        String pass = pass1.getText().toString().trim();
        String conPass = pass2.getText().toString().trim();

        if (pass.equals("")) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), pass1, "Password cannot be empty ! ").show();
            return false;
        } else if (conPass.equals("")) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), pass2, "Confirm Password cannot be empty ! ").show();
            return false;
        } else if (pass.length() <= 5 && conPass.length() <= 5){
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), pass1, "Password must be longer than six characters ! ").show();
            return false;
        } else if (!pass.equals(conPass)) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), pass2, "Password did not match ! ").show();
            return false;
        } else {
            return true;
        }

    }

    public static boolean inputFieldValidation(Activity activity, EditText editText) {

        String value = editText.getText().toString();

        if (value.equals("")) {
            PopupCollections.tooltipMessage(Objects.requireNonNull(activity), editText, editText.getHint().toString() + " cannot be left empty ! ").show();
            return false;
        } else {
            return true;
        }

    }


}
