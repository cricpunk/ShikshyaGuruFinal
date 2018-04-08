package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionGalleryData;

import java.util.ArrayList;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 2:31 PM 15 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public interface ViewPagerGalleryInterface {

    void setUpGalleryCategory(FirebaseRecyclerOptions<InstitutionGalleryData> options);

    void onGalleryCategoryClick(String category, ArrayList<String> images, ArrayList<String> desc, ArrayList<String> ids);

}
