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

    private HashMap<String, ArrayList<String>> categoryWithImages;
    private HashMap<String, ArrayList<String>> categoryWithDescription;
    private HashMap<String, ArrayList<String>> categoryWithIds;

    InstitutionGalleryData() {
        // For firebase
    }

    public HashMap<String, ArrayList<String>> getCategoryWithImages() {
        return categoryWithImages;
    }

    public void setCategoryWithImages(HashMap<String, ArrayList<String>> categoryWithImages) {
        this.categoryWithImages = categoryWithImages;
    }

    public HashMap<String, ArrayList<String>> getCategoryWithDescription() {
        return categoryWithDescription;
    }

    public void setCategoryWithDescription(HashMap<String, ArrayList<String>> categoryWithDescription) {
        this.categoryWithDescription = categoryWithDescription;
    }

    public HashMap<String, ArrayList<String>> getCategoryWithIds() {
        return categoryWithIds;
    }

    public void setCategoryWithIds(HashMap<String, ArrayList<String>> categoryWithIds) {
        this.categoryWithIds = categoryWithIds;
    }

}
