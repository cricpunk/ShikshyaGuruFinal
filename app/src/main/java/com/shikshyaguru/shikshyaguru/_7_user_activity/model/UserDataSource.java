package com.shikshyaguru.shikshyaguru._7_user_activity.model;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.ChatInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.MessageInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.UserLoaderInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.UserMainInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class UserDataSource implements UserDataSourceInterface {

    private static final String CHAT_ID_SPLITTER = "@@@@";

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private String uId = NavigationDrawerFragment.currentUser.getUid();

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    private String currentTime = dateFormat.format(Calendar.getInstance().getTime());

    @Override
    public FirebaseRecyclerOptions<UserDetails> getAllUsers(final UserMainInterface mainInterface, String category) {

        Query query = mDatabase.getReference().child("users").orderByChild("profile");

        SnapshotParser<UserDetails> snapshotParser = new SnapshotParser<UserDetails>() {
            @NonNull
            @Override
            public UserDetails parseSnapshot(@NonNull DataSnapshot snapshot) {

                UserDetails userDetails = new UserDetails();

                try {

                    userDetails.setuId(snapshot.getKey());
                    Long userType = snapshot.child("profile").child("type").getValue(Long.class);
                    userDetails.setName(snapshot.child("profile").child("name").getValue(String.class));
                    assert userType != null;
                    userDetails.setUserType(String.valueOf(userType.intValue()));
                    userDetails.setUserName(snapshot.child("profile").child("user_name").getValue(String.class));
                    userDetails.setInstitution(snapshot.child("profile").child("institution").getValue(String.class));
                    userDetails.setImageUrl(snapshot.child("profile").child("image").getValue(String.class));

                } catch (Exception e) {
                    mainInterface.showSnackbar(e.getMessage());
                }

                mainInterface.removeSpinner();
                return userDetails;

            }
        };

        return new FirebaseRecyclerOptions.Builder<UserDetails>().setQuery(query, snapshotParser).build();

    }

    @Override
    public void getFollowerFollowingData(final HashMap<String, Boolean> list, final UserMainInterface mainInterface) {

        final ArrayList<UserDetails> followersFollowing = new ArrayList<>();

        for (final String uId : list.keySet()) {

            DatabaseReference userRef = mDatabase.getReference().child("users").child(uId).child("profile");

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    try {

                        UserDetails userDetails = new UserDetails();

                        Long userType = dataSnapshot.child("type").getValue(Long.class);
                        userDetails.setuId(uId);
                        userDetails.setName(dataSnapshot.child("name").getValue(String.class));
                        assert userType != null;
                        userDetails.setUserType(String.valueOf(userType.intValue()));
                        userDetails.setUserName(dataSnapshot.child("user_name").getValue(String.class));
                        userDetails.setInstitution(dataSnapshot.child("institution").getValue(String.class));

                        followersFollowing.add(userDetails);

                        mainInterface.setUpFollowersFollowing(followersFollowing);
                        mainInterface.removeSpinner();


                    } catch (Exception e) {
                        mainInterface.showSnackbar(e.getMessage());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }

    @Override
    public Object getUserProfileDetails(UserLoaderInterface loaderInterface, String uId) {
        return null;
    }

    @Override
    public void makeInstitutionSuggestionToFriend(final UserMainInterface mainInterface, final String friendId, final String institutionId) {

        Query query = mDatabase.getReference().child("users").child(friendId).child("suggestions").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // True means no previous suggestions
                // False means suggestion has been made already
                boolean validationPass = true;

                // Validate either same user has already suggested same institution or not
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String uIdRef = snapshot.child(uId).getKey();
                    String instIdRef = snapshot.child(uId).getValue(String.class);

                    if (uIdRef != null && Objects.equals(instIdRef, institutionId)) {
                        validationPass = false;
                        break;
                    }

                }

                if (validationPass) {

                    HashMap<String, String> suggestion = new HashMap<>();
                    suggestion.put(uId, institutionId);
                    mDatabase.getReference().child("users").child(friendId).child("suggestions").child(currentTime).setValue(suggestion, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                mainInterface.showSnackbar("Suggestion has been made successfully !");
                            } else {
                                mainInterface.showSnackbar(databaseError.getMessage());
                            }
                        }
                    });

                } else {
                    mainInterface.showSnackbar("You have already suggested this institution !");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mainInterface.showSnackbar(databaseError.getMessage());
            }
        });


    }



    // This method will identify right chat id and send message
    @Override
    public void sendMessage(final String friendUID, String message) {

        final DatabaseReference messageRef = mDatabase.getReference().child("messages");
        final ChatMessage newMsg = new ChatMessage(uId, friendUID, message);

        // Possible two chat id for conversation
        final String chatIdSender = uId + CHAT_ID_SPLITTER + friendUID;
        final String chatIdReceiver = friendUID + CHAT_ID_SPLITTER + uId;

        Query query1 = messageRef.child(chatIdSender);
        final Query query2 = messageRef.child(chatIdReceiver);

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // If first id exist
                if (dataSnapshot.exists()) {
                    //Send message
                    sendMessageToFirebase(messageRef.child(dataSnapshot.getKey()), newMsg);
                } else {

                    // If first id does not exist in database search second id
                    query2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {

                            // If second id exist then send message
                            if (snapshot.exists()) {
                                //Send message
                                sendMessageToFirebase(messageRef.child(snapshot.getKey()), newMsg);

                            } else {
                                // Both id does not exist that means this if first chat
                                // So create new chat id and push message into that id
                                //Send message
                                sendMessageToFirebase(messageRef.child(chatIdSender), newMsg);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // To omit code repetition this method has been generated.
    private void sendMessageToFirebase(DatabaseReference chatRef, ChatMessage newMsg) {

        chatRef.push().setValue(newMsg).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (!task.isSuccessful()) {
                    //error
                } else {
                    // Success
                }

            }
        });

    }

    // Display messages between two users
    @Override
    public void getChatDetails(final ChatInterface chatInterface, final String friendUID) {

        final DatabaseReference messageRef = mDatabase.getReference().child("messages");

        // Possible two chat id for conversation
        final String chatIdSender = uId + CHAT_ID_SPLITTER + friendUID;
        final String chatIdReceiver = friendUID + CHAT_ID_SPLITTER + uId;

        Query query1 = messageRef.child(chatIdSender);
        final Query query2 = messageRef.child(chatIdReceiver);

        final List<ChatMessage> chatList = new ArrayList<>();

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("CheckingResult : " + dataSnapshot.getKey());
                // If first id exist
                if (dataSnapshot.exists()) {
                    //Get messages
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        chatList.add(snapshot.getValue(ChatMessage.class));
                        System.out.println("CheckingResult : " + chatList);
                    }

                } else {

                    // If first id does not exist in database search second id
                    query2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot postSnapshot) {

                            System.out.println("CheckingResult post: " + postSnapshot.getKey());
                            // If second id exist then send message
                            if (postSnapshot.exists()) {
                                //Get messages
                                for (DataSnapshot snapshot : postSnapshot.getChildren()) {
                                    chatList.add(snapshot.getValue(ChatMessage.class));
                                    System.out.println("CheckingResult post: " + chatList);
                                }

                            }

                            chatInterface.setUpChatAdapter(chatList);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                chatInterface.setUpChatAdapter(chatList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });




    }


    @Override
    public FirebaseRecyclerOptions<ChatMessage> getAllMessages(MessageInterface messageInterface) {

        Query query = mDatabase.getReference().child("users").orderByChild("profile");

        SnapshotParser<ChatMessage> snapshotParser = new SnapshotParser<ChatMessage>() {
            @NonNull
            @Override
            public ChatMessage parseSnapshot(@NonNull DataSnapshot snapshot) {

                return new ChatMessage();

            }
        };

        return new FirebaseRecyclerOptions.Builder<ChatMessage>().setQuery(query, snapshotParser).build();

    }


    @Override
    public void sendFollowRequest(final UserMainInterface mainInterface, String friendId) {

        // In friend database table record will be stored at followers node
        DatabaseReference friendDBRef = mDatabase.getReference().child("users").child(friendId).child("followers").child(uId).child("status");

        // In own database table record will be stored at following node
        DatabaseReference ownDBRef = mDatabase.getReference().child("users").child(uId).child("following").child(friendId).child("status");

        friendDBRef.setValue(false);
        ownDBRef.setValue(false, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null) {
                    mainInterface.showSnackbar("Following request has been send successfully !");
                } else {
                    mainInterface.showSnackbar(databaseError.getMessage());
                }

            }
        });

    }

    @Override
    public void acceptFollowingRequest(final UserMainInterface mainInterface, String friendId) {

        DatabaseReference dbRef = mDatabase.getReference().child("users").child(uId).child("followers").child(friendId).child("status");

        dbRef.setValue(true, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null) {
                    mainInterface.showSnackbar("Following request has been accepted !");
                } else {
                    mainInterface.showSnackbar(databaseError.getMessage());
                }

            }
        });

    }

    @Override
    public void stopFollowing(final UserMainInterface mainInterface, String friendId) {

        // In friend database table record will be stored at followers node
        DatabaseReference friendDBRef = mDatabase.getReference().child("users").child(friendId).child("followers").child(uId);

        // In own database table record will be stored at following node
        DatabaseReference ownDBRef = mDatabase.getReference().child("users").child(uId).child("following").child(friendId);

        friendDBRef.removeValue();
        ownDBRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null) {
                    mainInterface.showSnackbar("You have stop following successfully !");
                } else {
                    mainInterface.showSnackbar(databaseError.getMessage());
                }

            }
        });

    }

    @Override
    public void cancelFollowingRequest(final UserMainInterface mainInterface, String friendId) {

        // In friend database table record will be stored at followers node
        DatabaseReference friendDBRef = mDatabase.getReference().child("users").child(friendId).child("followers").child(uId);

        // In own database table record will be stored at following node
        DatabaseReference ownDBRef = mDatabase.getReference().child("users").child(uId).child("following").child(friendId);

        friendDBRef.removeValue();
        ownDBRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null) {
                    mainInterface.showSnackbar("You have cancel following request successfully !");
                } else {
                    mainInterface.showSnackbar(databaseError.getMessage());
                }

            }
        });

    }

    @Override
    public void loadUserFullPage(UserMainInterface mainInterface, UserDetails userDetails) {
        mainInterface.openUserLoaderPage(userDetails);
    }

}
