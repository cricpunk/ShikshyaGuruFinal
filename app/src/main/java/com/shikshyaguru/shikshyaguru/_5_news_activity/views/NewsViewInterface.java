package com.shikshyaguru.shikshyaguru._5_news_activity.views;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;

/*
 * Created by Pankaj Koirala on 4/7/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public interface NewsViewInterface {

    void setNewsAdapter(FirebaseRecyclerOptions<NewsListItem> newsList);

}
