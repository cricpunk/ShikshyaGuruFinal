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
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionMainFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryLoader;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryLoaderImageLoader;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.ChatFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.MessageFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.QuestionsFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.UserLoaderFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.UserMainFragment;


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
                        showFragment(openInstitutionMainFragment(bundle), fragHolderId, fragmentManager);
                        break;
                    case "institutions_loader":
                        showFragment(openInstitutionLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "courses_loader":
                        showFragment(openCourseLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "courses_opener":
                        showFragment(openCourseOpener(bundle), fragHolderId, fragmentManager);
                        break;
                    case "gallery_loader":
                        showFragment(openGalleryLoader(bundle), fragHolderId, fragmentManager);
                        break;
                    case "open_gallery_image":
                        showFragment(openFullImage(bundle), fragHolderId, fragmentManager);
                        break;
                    case "user_main":
                        showFragment(openUserMainPage(bundle), fragHolderId, fragmentManager);
                        break;
                    case "user_loader":
                        showFragment(openUserLoaderPage(bundle), fragHolderId, fragmentManager);
                        break;
                    case "message_loader":
                        showFragment(new MessageFragment(), fragHolderId, fragmentManager);
                        break;
                    case "chat_loader":
                        showFragment(openChatFragment(bundle), fragHolderId, fragmentManager);
                        break;
                    case "question_loader":
                        showFragment(new QuestionsFragment(), fragHolderId, fragmentManager);
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

    private static InstitutionMainFragment openInstitutionMainFragment(Bundle bundle) {
        InstitutionMainFragment mainFragment = new InstitutionMainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    private static InstitutionLoaderFragment openInstitutionLoader(Bundle bundle) {
        InstitutionLoaderFragment institutionLoaderFragment = new InstitutionLoaderFragment();
        institutionLoaderFragment.setArguments(bundle);
        return institutionLoaderFragment;
    }

    private static ViewPagerProgrammesCoursesFragment openCourseLoader(Bundle bundle) {
        ViewPagerProgrammesCoursesFragment coursesLoader = new ViewPagerProgrammesCoursesFragment();
        coursesLoader.setArguments(bundle);
        return coursesLoader;
    }

    private static ViewPagerProgrammesCoursesLoaderFragment openCourseOpener(Bundle bundle) {
        ViewPagerProgrammesCoursesLoaderFragment coursesLoader = new ViewPagerProgrammesCoursesLoaderFragment();
        coursesLoader.setArguments(bundle);
        return coursesLoader;
    }

    private static ViewPagerGalleryLoader openGalleryLoader(Bundle bundle) {
        ViewPagerGalleryLoader galleryLoader = new ViewPagerGalleryLoader();
        galleryLoader.setArguments(bundle);
        return galleryLoader;
    }

    private static ViewPagerGalleryLoaderImageLoader openFullImage(Bundle bundle) {
        ViewPagerGalleryLoaderImageLoader imageLoader = new ViewPagerGalleryLoaderImageLoader();
        imageLoader.setArguments(bundle);
        return imageLoader;
    }

    private static UserMainFragment openUserMainPage(Bundle bundle) {
        UserMainFragment mainFragment = new UserMainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    private static UserLoaderFragment openUserLoaderPage(Bundle bundle) {
        UserLoaderFragment loaderFragment = new UserLoaderFragment();
        loaderFragment.setArguments(bundle);
        return loaderFragment;
    }

    private static ChatFragment openChatFragment(Bundle bundle) {
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        return chatFragment;
    }

}
