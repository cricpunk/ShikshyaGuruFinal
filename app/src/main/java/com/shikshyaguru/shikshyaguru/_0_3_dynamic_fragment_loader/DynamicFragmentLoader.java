package com.shikshyaguru.shikshyaguru._0_3_dynamic_fragment_loader;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.SignUpFragment;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsLoaderFragment;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsMainFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionMainFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsLoaderFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryLoader;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryLoaderImageLoader;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoader;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.UserLoaderFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.UserMainFragment;


public class DynamicFragmentLoader {

    public static void loadFragment(Fragment defaultFrag, Bundle bundle, int fragHolderId, FragmentManager fragmentManager) {

        if (bundle != null) {
            String requestCode = (String) bundle.get("REQUEST_CODE");

            if (requestCode != null) {
                switch (requestCode) {
                    case "login":
                        showFragment(new LoginFragment(), fragHolderId, fragmentManager);
                        break;
                    case "sign_up":
                        showFragment(new SignUpFragment(), fragHolderId, fragmentManager);
                        break;
                    case "news_main":
                        showFragment(new NewsMainFragment(), fragHolderId, fragmentManager);
                        break;
                    case "news_loader":
                        showFragment(openNewsLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "institutions_main":
                        showFragment(new InstitutionMainFragment(), fragHolderId, fragmentManager);
                        break;
                    case "institutions_loader":
                        showFragment(openInstitutionLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "courses_loader":
                        showFragment(openCourseLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "gallery_loader":
                        showFragment(openGalleryLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "open_gallery_image":
                        showFragment(openFullImage(bundle), fragHolderId, fragmentManager);
                        break;
                    case "user_main":
                        showFragment(new UserMainFragment(), fragHolderId, fragmentManager);
                        break;
                    case "user_loader":
                        showFragment(new UserLoaderFragment(), fragHolderId, fragmentManager);
                        break;
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
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragHolderId, fragment);
        transaction.commit();
    }

    private static NewsLoaderFragment openNewsLoader(Bundle bundle) {
        NewsLoaderFragment newsLoaderFragment = new NewsLoaderFragment();
        newsLoaderFragment.setArguments(bundle);
        return newsLoaderFragment;
    }

    private static InstitutionsLoaderFragment openInstitutionLoader(Bundle bundle) {
        InstitutionsLoaderFragment institutionsLoaderFragment = new InstitutionsLoaderFragment();
        institutionsLoaderFragment.setArguments(bundle);
        return institutionsLoaderFragment;
    }

    private static ViewPagerProgrammesCoursesLoader openCourseLoader(Bundle bundle) {
        Bundle bundle1 = new Bundle();
        bundle1.putString("COURSE_NAME", (String) bundle.get("COURSE_NAME"));
        ViewPagerProgrammesCoursesLoader coursesLoader = new ViewPagerProgrammesCoursesLoader();
        coursesLoader.setArguments(bundle1);
        return coursesLoader;
    }

    private static ViewPagerGalleryLoader openGalleryLoader(Bundle bundle) {
        Bundle bundle1 = new Bundle();
        bundle1.putString("CATEGORY", (String) bundle.get("CATEGORY"));
        ViewPagerGalleryLoader galleryLoader = new ViewPagerGalleryLoader();
        galleryLoader.setArguments(bundle1);
        return galleryLoader;
    }

    private static ViewPagerGalleryLoaderImageLoader openFullImage(Bundle bundle) {
        Bundle bundle1 = new Bundle();
        bundle1.putInt("POSITION", bundle.getInt("POSITION"));
        bundle1.putIntegerArrayList("IMAGES", bundle.getIntegerArrayList("IMAGES"));
        ViewPagerGalleryLoaderImageLoader imageLoader = new ViewPagerGalleryLoaderImageLoader();
        imageLoader.setArguments(bundle1);
        return imageLoader;
    }

}
