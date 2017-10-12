package com.shikshyaguru.shikshyaguru._0_3_dynamic_fragment_loader;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsLoaderFragment;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsMainFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionMainFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsLoaderFragment;

public class DynamicFragmentLoader {

    public static void loadFragment(Fragment defaultFrag, Bundle bundle, int fragHolderId, FragmentManager fragmentManager) {
        if (bundle != null) {
            String requestCode = (String) bundle.get("REQUEST_CODE");
            if (requestCode != null) {
                switch (requestCode) {
                    case "news_main":
                        showFragment(new NewsMainFragment(), fragHolderId, fragmentManager);
                        break;
                    case "news_loader":
                        showFragment(new NewsLoaderFragment(), fragHolderId, fragmentManager);
                        break;
                    case "institutions_main":
                        showFragment(new InstitutionMainFragment(), fragHolderId, fragmentManager);
                        break;
                    case "institutions_loader":
                        showFragment(new InstitutionsLoaderFragment(), fragHolderId, fragmentManager);
                    default:
                        break;
                }
            } else {
                showFragment(defaultFrag, fragHolderId, fragmentManager);
            }
        } else {
            showFragment(defaultFrag, fragHolderId, fragmentManager);
        }
    }

    private static void showFragment(Fragment fragment, int fragHolderId, FragmentManager fragmentManager) {
        fragmentManager
                .beginTransaction()
                .replace(fragHolderId, fragment).commit();
    }

}
