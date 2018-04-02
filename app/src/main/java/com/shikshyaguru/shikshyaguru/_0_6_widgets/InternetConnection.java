package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*
 * Created by Pankaj Koirala on 4/2/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class InternetConnection {

    public static boolean hasInternetConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;

        if (connectivityManager != null) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        }

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
