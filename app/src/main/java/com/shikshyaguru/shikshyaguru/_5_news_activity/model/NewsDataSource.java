package com.shikshyaguru.shikshyaguru._5_news_activity.model;

import android.support.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;

/*
 * Created by Pankaj Koirala on 4/7/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class NewsDataSource implements NewsDataSourceInterface {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    @Override
    public FirebaseRecyclerOptions<NewsListItem> getNewsList() {

        Query query = mDatabase.getReference().child("news");

        SnapshotParser<NewsListItem> snapshotParser = new SnapshotParser<NewsListItem>() {
            @NonNull
            @Override
            public NewsListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                NewsListItem newsListItem = snapshot.getValue(NewsListItem.class);
                assert newsListItem != null;
                newsListItem.setTime(snapshot.getKey());

                return newsListItem;
            }
        };

        return new FirebaseRecyclerOptions.Builder<NewsListItem>().setQuery(query, snapshotParser).build();

    }

}
