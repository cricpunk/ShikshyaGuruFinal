package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:33 PM 23 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class InstitutionGalleryData {

    private HashMap<String, ArrayList<Integer>> categoryWithImages;

    public HashMap<String, ArrayList<Integer>> getCategoryWithImages() {
        return categoryWithImages;
    }

    public void setCategoryWithImages(HashMap<String, ArrayList<Integer>> categoryWithImages) {
        this.categoryWithImages = categoryWithImages;
    }
}
