package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import java.util.ArrayList;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 10:34 AM 24 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public interface ViewPagerGalleryLoaderInterface {

    void onImageClick(int position, ArrayList<String> images, ArrayList<String> desc, ArrayList<String> ids);
}
