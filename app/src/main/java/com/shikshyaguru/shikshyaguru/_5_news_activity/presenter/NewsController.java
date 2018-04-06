package com.shikshyaguru.shikshyaguru._5_news_activity.presenter;

import com.shikshyaguru.shikshyaguru._5_news_activity.model.NewsDataSourceInterface;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsViewInterface;

/*
 * Created by Pankaj Koirala on 4/7/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class NewsController {

    private NewsViewInterface newsView;
    private NewsDataSourceInterface dataSource;

    public NewsController(NewsViewInterface newsView, NewsDataSourceInterface dataSource) {
        this.newsView = newsView;
        this.dataSource = dataSource;

        fetchAllNews();
    }

    private void fetchAllNews() {
        newsView.setNewsAdapter(dataSource.getNewsList());
    }


}
