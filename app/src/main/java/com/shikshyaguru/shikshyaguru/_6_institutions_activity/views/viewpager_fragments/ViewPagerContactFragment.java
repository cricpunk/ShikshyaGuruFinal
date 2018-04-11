package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_4_animation_collection.CircularReveal;
import com.shikshyaguru.shikshyaguru._0_6_widgets.InternetConnection;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_8_validation.PerformValidation;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionContactData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPContactController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderFragment;

import java.util.Objects;

public class ViewPagerContactFragment extends Fragment implements
        View.OnFocusChangeListener, ViewPagerContactInterface {

    private NestedScrollView currentLayout;
    private EditText name, phoneNumber, emailId, message;
    VPContactController controller;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout._6_2_10_0_view_pager_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentLayout = view.findViewById(R.id.current_layout);
        controller = new VPContactController(this, new InstitutionDataSource());
        sendMessageSection(view);
    }


    private void sendMessageSection(View view) {
        name = view.findViewById(R.id.lbl_vp_contact_name);
        phoneNumber = view.findViewById(R.id.lbl_vp_contact_phone);
        emailId = view.findViewById(R.id.lbl_vp_contact_email);
        message = view.findViewById(R.id.lbl_vp_contact_message);
        FloatingActionButton sendMessageButton = view.findViewById(R.id.fab_vp_contact_send);

        name.setOnFocusChangeListener(this);
        phoneNumber.setOnFocusChangeListener(this);
        emailId.setOnFocusChangeListener(this);
        message.setOnFocusChangeListener(this);
        sendMessageButton.setImageResource(R.drawable.ic_send);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onSendMessageButtonClick();
            }
        });

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        CircularReveal circularReveal = new CircularReveal(getActivity(), v, hasFocus);
        circularReveal.circularBackground();
    }

    @Override
    public void onSendMessageButtonClick() {

        if (PerformValidation.inputFieldValidation(getActivity(), name) &&
                PerformValidation.inputFieldValidation(getActivity(), emailId) &&
                PerformValidation.inputFieldValidation(getActivity(), message)
                ) {

            if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

                String txtName = name.getText().toString();
                String txtPhone = phoneNumber.getText().toString();
                String txtEmail = emailId.getText().toString();
                String txtMessage = message.getText().toString();

                InstitutionContactData contactData = new InstitutionContactData(
                        txtName, txtPhone, txtEmail, txtMessage
                );

                DatabaseError databaseError = controller.sendMessage(InstitutionLoaderFragment.id, contactData);
                if ( databaseError == null ) {
                    PopupCollections.simpleSnackBar(currentLayout, "Message has been send successfully !", LoginFragment.COLOR_GREEN);
                    name.setText("");
                    name.clearFocus();
                    phoneNumber.setText("");
                    emailId.setText("");
                    message.setText("");
                    message.clearFocus();
                } else {
                    PopupCollections.simpleSnackBar(currentLayout, databaseError.getMessage(), LoginFragment.COLOR_RED);
                }

            } else {
                PopupCollections.simpleSnackBar(currentLayout, LoginFragment.NO_INTERNET_CONNECTION, LoginFragment.COLOR_GREEN);
            }

        }

    }

}
