package com.shikshyaguru.shikshyaguru._7_user_activity.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDataSource;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDetails;
import com.shikshyaguru.shikshyaguru._7_user_activity.presenter.UserController;

import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class MessageFragment extends Fragment implements MessageInterface {

    private LayoutInflater inflater;
    private UserController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._7_2_6_user_message_fragment, container, false);
        this.inflater = inflater;

        // Change status bar color always from inside onCreateView
        StatusBar.changeStatusBarColor(getContext(), Objects.requireNonNull(getActivity()).getWindow(), R.color.black_toolbar);

        // Setup blue_toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), "Messages");
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        controller = new UserController(this, new UserDataSource());

        controller.displayAllMessages();

        return view;
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

    class UserAdapter extends FirebaseRecyclerAdapter<UserDetails, UserAdapter.UserViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public UserAdapter(@NonNull FirebaseRecyclerOptions<UserDetails> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position, @NonNull UserDetails model) {

        }

        @NonNull
        @Override
        public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._7_2_1_rec_user, parent, false);
            return new UserAdapter.UserViewHolder(view);
        }

        class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private RelativeLayout rootView;
            private ImageView userIcon;
            private TextView userName;
            private TextView userUserName;
            private TextView userType;
            private TextView userInstitution;
            private CircularProgressButton followBtn;

            UserViewHolder(View itemView) {
                super(itemView);

                rootView = itemView.findViewById(R.id.root_user);
                userIcon = itemView.findViewById(R.id.iv_user_icon);
                userName = itemView.findViewById(R.id.lbl_user_name);
                userUserName = itemView.findViewById(R.id.lbl_user_user_name);
                userType = itemView.findViewById(R.id.lbl_user_type);
                userInstitution = itemView.findViewById(R.id.lbl_user_institution);
                followBtn = itemView.findViewById(R.id.btn_follow_following);

                rootView.setOnClickListener(this);
                followBtn.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.root_user:
                        break;

                    case R.id.btn_follow_following:
                        break;

                    default:
                        break;

                }

            }

        }

    }

}
