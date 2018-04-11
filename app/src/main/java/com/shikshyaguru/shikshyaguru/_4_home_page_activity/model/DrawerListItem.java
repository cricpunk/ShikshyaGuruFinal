package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;
/*
 * Created by Pankaj Koirala on 9/27/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import java.util.List;

public class DrawerListItem {

    private int icon;
    private String header;
    private List<String> favouriteInstitution;

    DrawerListItem(int icon, String header) {
        this.icon = icon;
        this.header = header;
    }

    public List<String> getFavouriteInstitution() {
        return favouriteInstitution;
    }

    public void setFavouriteInstitution(List<String> favouriteInstitution) {
        this.favouriteInstitution = favouriteInstitution;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
