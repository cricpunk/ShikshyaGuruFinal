package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryLoaderInterface;

import java.util.ArrayList;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:26 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPGalleryController {

    private ViewPagerGalleryInterface galleryInterface;
    private ViewPagerGalleryLoaderInterface galleryLoaderInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPGalleryController(ViewPagerGalleryInterface galleryInterface, InstitutionDataSourceInterface dataSource) {
        this.galleryInterface = galleryInterface;
        this.dataSource = dataSource;
    }

    public VPGalleryController(ViewPagerGalleryLoaderInterface galleryLoaderInterface, InstitutionDataSourceInterface dataSource) {
        this.galleryLoaderInterface = galleryLoaderInterface;
        this.dataSource = dataSource;
    }

    public void setUpGalleryCategory(String id) {
        galleryInterface.setUpGalleryCategory(dataSource.getInstitutionGalleryData(id));
    }

    public void onImageClick(int position, ArrayList<String> images, ArrayList<String> desc, ArrayList<String> ids) {
        galleryLoaderInterface.onImageClick(position, images, desc, ids);
    }

    public void onGalleryCategoryClickNew(String category, ArrayList<String> images, ArrayList<String> desc, ArrayList<String> ids) {
        galleryInterface.onGalleryCategoryClick(category, images, desc, ids);
    }

}
