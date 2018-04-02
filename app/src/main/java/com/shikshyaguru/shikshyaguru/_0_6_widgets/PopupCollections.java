package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.shikshyaguru.shikshyaguru.R;

/*
 * Created by Pankaj Koirala on 4/2/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class PopupCollections {

    public static void simpleSnackBar(View view, String message, String messageColor) {

        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor(messageColor));
        snackbar.show();
    }

    public static ProgressDialog authenticationProgress(Context context) {

        ProgressDialog progressDialog = new ProgressDialog(context, R.style.DarkDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        return progressDialog;

    }

}
