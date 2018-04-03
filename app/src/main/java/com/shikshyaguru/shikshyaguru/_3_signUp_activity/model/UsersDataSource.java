package com.shikshyaguru.shikshyaguru._3_signUp_activity.model;
/*
 * Created by Pankaj Koirala on 3/23/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsersDataSource implements UserDataSourceInterface{

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mDatabase.getReference("users");

    @Override
    public void createNewUser(String uId, NewUserData newUserData) {
        mRef.child(uId).setValue(newUserData);
    }

}
