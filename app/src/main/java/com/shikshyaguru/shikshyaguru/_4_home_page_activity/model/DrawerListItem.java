package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;
/*
 * Created by Pankaj Koirala on 9/27/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class DrawerListItem {

    private int icon;
    private String header;

    public DrawerListItem(int icon, String header) {
        this.icon = icon;
        this.header = header;
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
