package com.shikshyaguru.shikshyaguru._7_user_activity.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.ChatMessage;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserdataSource;
import com.shikshyaguru.shikshyaguru._7_user_activity.presenter.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;

/*
 * Created by Pankaj Koirala on 4/14/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class ChatFragment extends Fragment implements ChatInterface {

    private LayoutInflater inflater;
    private View rootView;
    private String title;
    private String friendUID;

    private EditText messageEditText;

    private static final int ITEM_TYPE_SEND = 0;
    private static final int ITEM_TYPE_RECEIVED = 1;

    private LinearLayoutManager mLayoutManager;
    private DatabaseReference mMessagesDBRef;
    private DatabaseReference mUsersRef;

    private List<ChatMessage> chatList = new ArrayList<>();

    private UserController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._7_2_7_user_chat_fragment, container, false);
        this.inflater = inflater;
        this.rootView = view;

        if (getArguments() != null) {
            this.title = getArguments().getString("TITLE");
            this.friendUID = getArguments().getString("UID");
        }
        // Change status bar color always from inside onCreateView
        StatusBar.changeStatusBarColor(getContext(), Objects.requireNonNull(getActivity()).getWindow(), R.color.black_toolbar);

        // Setup blue_toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), title);
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        initComponents(view);

        controller = new UserController(this, new UserdataSource());
        controller.displayAllChats(friendUID);

        return view;
    }

    private void initComponents(View view) {

        messageEditText = view.findViewById(R.id.et_chat_message);
        ImageButton btnSendMessage = view.findViewById(R.id.btn_send_message_chat);


        //init Firebase
        mMessagesDBRef = FirebaseDatabase.getInstance().getReference().child("messages");
        mUsersRef = FirebaseDatabase.getInstance().getReference().child("users");


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                //String senderId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if (message.isEmpty()) {
                    Toast.makeText(getContext(), "You must enter a message", Toast.LENGTH_SHORT).show();
                } else {
                    //message is entered, send
                    sendMessageToFirebase(NavigationDrawerFragment.currentUser.getUid(), friendUID, message);
                }
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Objects.requireNonNull(getActivity()).onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void sendMessageToFirebase(String senderId, String receiverId, String message) {
        chatList.clear();

        ChatMessage newMsg = new ChatMessage(senderId, receiverId, message);
        mMessagesDBRef.push().setValue(newMsg).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    //error
                    Toast.makeText(getContext(), "Error " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Message sent successfully!", Toast.LENGTH_SHORT).show();
                    messageEditText.setText(null);
                    hideSoftKeyboard();
                }
            }
        });

    }

    public void hideSoftKeyboard() {
        if (Objects.requireNonNull(getActivity()).getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(Objects.requireNonNull(getActivity()).getCurrentFocus()).getWindowToken(), 0);
        }
    }


    @Override
    public void setUpChatAdapter(List<ChatMessage> chatList) {
        this.chatList = chatList;

        RecyclerView chatRecView = rootView.findViewById(R.id.rec_chat_messages);
        chatRecView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        chatRecView.setLayoutManager(layoutManager);
        ChatAdapter adapter = new ChatAdapter();
        chatRecView.setAdapter(adapter);

    }

    class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

        public void add(int position, ChatMessage message) {
            chatList.add(position, message);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            chatList.remove(position);
            notifyItemRemoved(position);
        }

        @NonNull
        @Override
        public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = null;

            if (viewType == ITEM_TYPE_SEND) {
                view = inflater.inflate(R.layout._7_2_8_rec_send_message_bubble, parent, false);
            } else if (viewType == ITEM_TYPE_RECEIVED) {
                view = inflater.inflate(R.layout._7_2_9_rec_received_message_bubble, parent, false);
            }

            return new ChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
            ChatMessage chatMessage = chatList.get(position);
            holder.chatMessage.setText(chatMessage.getMessage());
        }

        @Override
        public int getItemCount() {
            return chatList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (chatList.get(position).getSender().equals(NavigationDrawerFragment.currentUser.getUid())) {
                return ITEM_TYPE_SEND;
            } else {
                return ITEM_TYPE_RECEIVED;
            }
        }


    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        private TextView chatMessage;

        ChatViewHolder(View itemView) {
            super(itemView);

            chatMessage = itemView.findViewById(R.id.chatMsgTextView);
        }

    }

}
