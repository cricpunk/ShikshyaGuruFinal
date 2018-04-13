package com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model;

import android.support.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserLoaderInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserMainInterface;

import java.util.HashMap;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class UserdataSource implements UserDataSourceInterface {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    @Override
    public FirebaseRecyclerOptions<UserDetails> displayAll(final UserMainInterface mainInterface, String category) {

        Query query = mDatabase.getReference().child("users").orderByChild("profile");

        SnapshotParser<UserDetails> snapshotParser = new SnapshotParser<UserDetails>() {
            @NonNull
            @Override
            public UserDetails parseSnapshot(@NonNull DataSnapshot snapshot) {

                UserDetails userDetails = new UserDetails();

                try {

                    Long userType = snapshot.child("profile").child("type").getValue(Long.class);

                    userDetails.setuId(snapshot.getKey());
                    userDetails.setName(snapshot.child("profile").child("name").getValue(String.class));
                    assert userType != null;
                    userDetails.setUserType(String.valueOf(userType.intValue()));
                    userDetails.setUserName(snapshot.child("profile").child("user_name").getValue(String.class));
                    userDetails.setInstitution(snapshot.child("profile").child("institution").getValue(String.class));

                } catch (Exception e ) {
                    mainInterface.showSnackbar(e.getMessage());
                }
                mainInterface.removeSpinner();
                return userDetails;
            }
        };

        return new FirebaseRecyclerOptions.Builder<UserDetails>().setQuery(query, snapshotParser).build();

    }

    @Override
    public Object getUserProfileDetails(UserLoaderInterface loaderInterface, String uId) {
        return null;
    }

    @Override
    public Object getFollowers(String uid) {

        Query followers = mDatabase.getReference().child("users").child(uid).child("followers");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        followers.addListenerForSingleValueEvent(valueEventListener);

        SnapshotParser<HashMap<String, UserDetails>> snapshotParser = new SnapshotParser<HashMap<String, UserDetails>>() {
            @NonNull
            @Override
            public HashMap<String, UserDetails> parseSnapshot(@NonNull DataSnapshot snapshot) {

                return null;
            }
        };

        return null;
    }

}
