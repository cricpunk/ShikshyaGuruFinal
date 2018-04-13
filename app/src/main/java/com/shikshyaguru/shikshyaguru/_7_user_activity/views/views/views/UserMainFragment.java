package com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views;
/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.InternetConnection;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model.UserDetails;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model.UserdataSource;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.presenter.UserController;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class UserMainFragment extends Fragment implements UserMainInterface {

    private RelativeLayout currentLayout;

    private LayoutInflater inflater;
    private String title;
    private String category;

    private RelativeLayout spinnerLayout;
    private ProgressBar progressBar;
    private TextView checkInternet;
    private Button btnRetry;
    private RecyclerView userRecyclerView;

    private UserController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._7_2_0_user_main_fragment, container, false);
        this.inflater = inflater;

        if (getArguments() != null) {
            this.title = getArguments().getString("TITLE");
            this.category = getArguments().getString("CATEGORY");
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

        switch (category) {

            case "followers":

                break;
            case "following":
                controller.displayFollowers(NavigationDrawerFragment.currentUser.getUid());
                break;
            default:
                controller.displayAllUser(category);
                break;

        }



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

    private void initComponents(View view) {
        currentLayout = view.findViewById(R.id.root_user_main);
        userRecyclerView = view.findViewById(R.id.rec_user_main_loader);
        spinnerLayout = view.findViewById(R.id.l_progress_bar);
        progressBar = view.findViewById(R.id.pg_loading);
        checkInternet = view.findViewById(R.id.lbl_no_internet_connection);
        btnRetry = view.findViewById(R.id.btn_retry);

    }

    @Override
    public void setUpUsersAdapter(FirebaseRecyclerOptions<UserDetails> options) {

        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        UserAdapter newsAdapter = new UserAdapter(options);
        userRecyclerView.setAdapter(newsAdapter);
        userRecyclerView.setHasFixedSize(true);

        newsAdapter.startListening();

    }

    @Override
    public void setUpFollowers(Object followers) {

    }

    @Override
    public void openUserLoaderPage(UserDetails userDetails) {
        Intent intent = new Intent(getContext(), UserHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "user_loader");
        intent.putExtra("UID", userDetails.getuId());
        intent.putExtra("IMAGE", userDetails.getImageUrl());
        intent.putExtra("NAME", userDetails.getName());
        intent.putExtra("USER_NAME", userDetails.getUserName());
        intent.putExtra("TYPE", userDetails.getUserType());
        intent.putExtra("INSTITUTION", userDetails.getInstitution());

        startActivity(intent);
    }

    class UserAdapter extends FirebaseRecyclerAdapter<UserDetails, UserAdapter.UserViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        UserAdapter(@NonNull FirebaseRecyclerOptions<UserDetails> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull UserDetails model) {

            try {
                Picasso.get()
                        .load(model.getImageUrl())
                        .fit()
                        .centerCrop()
                        .placeholder(R.drawable.ic_user)
                        .into(holder.userIcon);
                holder.userName.setText(model.getName());
                if (model.getUserType() != null) {
                    switch (model.getUserType()) {
                        case "1":
                            holder.userType.setText(R.string.typeStudent);
                            break;
                        case "2":
                            holder.userType.setText(R.string.typeTeacher);
                            break;
                        case "3":
                            holder.userType.setText(R.string.typeInstitution);
                            break;
                        default:
                            break;

                    }
                }

                holder.userUserName.setText(String.format("@%s", model.getUserName()));
                holder.userInstitution.setText(model.getInstitution());

                holder.userDetails = model;

            } catch (Exception e) {
                showSnackbar(e.getMessage());
            }
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._7_2_1_rec_user, parent, false);
            return new UserViewHolder(view);
        }

        class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private RelativeLayout rootView;
            private ImageView userIcon;
            private TextView userName;
            private TextView userUserName;
            private TextView userType;
            private TextView userInstitution;
            private CircularProgressButton followBtn;

            private UserDetails userDetails;

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
                        controller.openUserLoaderPage(userDetails);
                        break;

                    case R.id.btn_follow_following:
                        break;

                    default:
                        break;

                }

            }

        }

    }

    @Override
    public void showSpinner() {

        if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

            spinnerLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

        } else {

            spinnerLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            checkInternet.setVisibility(View.VISIBLE);
            btnRetry.setVisibility(View.VISIBLE);

            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkInternet.setVisibility(View.GONE);
                    btnRetry.setVisibility(View.GONE);
                    controller.displayAllUser(category);
                }
            });

        }

    }

    @Override
    public void removeSpinner() {
        spinnerLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnackbar(String message) {
        PopupCollections.simpleSnackBar(currentLayout, message, LoginFragment.COLOR_GREEN);
    }


}
