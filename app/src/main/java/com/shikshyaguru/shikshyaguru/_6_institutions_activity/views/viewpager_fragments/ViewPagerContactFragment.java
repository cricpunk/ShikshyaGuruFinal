package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_4_animation_collection.CircularReveal;

public class ViewPagerContactFragment extends Fragment implements
        View.OnFocusChangeListener {

    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.context = getActivity();
        return inflater.inflate(R.layout._6_2_10_0_view_pager_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sendMessageSection(view);

    }


    private void sendMessageSection(View view) {
        EditText name = view.findViewById(R.id.lbl_vp_contact_name);
        EditText phoneNumber = view.findViewById(R.id.lbl_vp_contact_phone);
        EditText emailId = view.findViewById(R.id.lbl_vp_contact_email);
        EditText message = view.findViewById(R.id.lbl_vp_contact_message);
        FloatingActionButton sendMessageButton = view.findViewById(R.id.fab_vp_contact_send);

        name.setOnFocusChangeListener(this);
        phoneNumber.setOnFocusChangeListener(this);
        emailId.setOnFocusChangeListener(this);
        message.setOnFocusChangeListener(this);
        sendMessageButton.setImageResource(R.drawable.ic_send);
    }



    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        CircularReveal circularReveal = new CircularReveal(getActivity(), v, hasFocus);
        circularReveal.circularReveal();
    }

}
