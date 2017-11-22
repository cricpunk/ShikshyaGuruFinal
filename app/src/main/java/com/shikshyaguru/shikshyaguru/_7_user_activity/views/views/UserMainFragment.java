package com.shikshyaguru.shikshyaguru._7_user_activity.views.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shikshyaguru.shikshyaguru.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class UserMainFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private LayoutInflater inflater;
    private SlidingUpPanelLayout mLayout;
    private View mainLayout;
    private View bgLayout;

    private TextView sendMessage;
    private TextView askQuestion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._7_1_0_user_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;

        bgLayout = view.findViewById(R.id.i_background_content);
        mainLayout = view.findViewById(R.id.i_main_content);

        sendMessage = mainLayout.findViewById(R.id.btn_user_send_message);
        askQuestion = mainLayout.findViewById(R.id.btn_user_ask_question);
        sendMessage.setOnClickListener(this);
        askQuestion.setOnClickListener(this);

        initToolbarAndLayout();
        initSlidingPanel();
    }


    private void initToolbarAndLayout() {
        Toolbar toolbar = bgLayout.findViewById(R.id.tb_user_main_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar getSupportActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (getSupportActionBar != null) {
            getSupportActionBar.setDisplayHomeAsUpEnabled(false);
            getSupportActionBar.setDisplayShowTitleEnabled(false);
        }

    }

    private void initSlidingPanel() {
        mLayout = rootView.findViewById(R.id.sliding_layout);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("Sliding Up", "onPanelSlide, offset " + slideOffset);
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.19f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                    }
                }

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i("Sliding Up", "onPanelStateChanged " + newState);
            }
        });

//        mLayout.setFadeOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
//            }
//        });

    }

    @Override
    public void onClick(View v) {

    }
}
