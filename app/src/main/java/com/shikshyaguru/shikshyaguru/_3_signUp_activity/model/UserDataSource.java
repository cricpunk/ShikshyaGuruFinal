package com.shikshyaguru.shikshyaguru._3_signUp_activity.model;
/*
 * Created by Pankaj Koirala on 3/23/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.google.firebase.database.FirebaseDatabase;

public class UserDataSource implements UserDataSourceInterface{

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    @Override
    public void createNewUser(String uId, NewUserData newUserData) {

        mDatabase.getReference().child("users").child(uId).child("profile").setValue(newUserData);

    }


}
