package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.michael.easydialog.EasyDialog;
import com.shikshyaguru.shikshyaguru.R;

import java.util.Objects;

/*
 * Created by Pankaj Koirala on 4/2/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class PopupCollections {

    public static void simpleSnackBar(View view, String message, String messageColor) {

        final Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_INDEFINITE);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor(messageColor));

        snackbar.setAction("dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }

    public static ProgressDialog authenticationProgress(Context context) {

        ProgressDialog progressDialog = new ProgressDialog(context, R.style.DarkDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        return progressDialog;

    }

    public static EasyDialog tooltipMessage(Activity activity, View targetView, String message) {

        @SuppressLint("InflateParams")
        View view = activity.getLayoutInflater().inflate(R.layout.tooltip,null);
        TextView lblMessage = view.findViewById(R.id.lbl_tooltip_message);
        lblMessage.setText(message);

        return new EasyDialog(Objects.requireNonNull(activity))
                .setLayout(view)
                .setBackgroundColor(Color.parseColor("#222222"))
                .setLocationByAttachedView(targetView)
                .setGravity(EasyDialog.GRAVITY_TOP)
                .setAnimationTranslationShow(EasyDialog.DIRECTION_X, 1000, -600, 100, -50, 50, 0)
                .setAnimationAlphaShow(1000, 0.3f, 1.0f)
                .setAnimationTranslationDismiss(EasyDialog.DIRECTION_X, 500, -50, 800)
                .setAnimationAlphaDismiss(500, 1.0f, 0.0f)
                .setTouchOutsideDismiss(true)
                .setMatchParent(false);

    }

}
