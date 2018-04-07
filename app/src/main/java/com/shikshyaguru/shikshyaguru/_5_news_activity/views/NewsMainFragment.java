package com.shikshyaguru.shikshyaguru._5_news_activity.views;
/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;
import com.shikshyaguru.shikshyaguru._5_news_activity.model.NewsDataSource;
import com.shikshyaguru.shikshyaguru._5_news_activity.presenter.NewsController;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class NewsMainFragment extends Fragment implements NewsViewInterface {

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerViewNews;

    NewsController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        View view = inflater.inflate(R.layout._5_1_nhp_main_fragment, container, false);

        recyclerViewNews = view.findViewById(R.id.rec_news_loader);

        // Change status bar color always from inside onCreateView
        StatusBar.changeStatusBarColor(getContext(), Objects.requireNonNull(getActivity()).getWindow(), R.color.black_toolbar);

        // Setup toolbar
        Toolbar toolbar = view.findViewById(R.id.tb_news_home);
        Toolbars.setUpToolbar(toolbar, getActivity(), "News");
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = new NewsController(this, new NewsDataSource());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Objects.requireNonNull(getActivity()).onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void setNewsAdapter(FirebaseRecyclerOptions<NewsListItem> newsList) {

        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        NewsAdapter newsAdapter = new NewsAdapter(newsList);
        recyclerViewNews.setAdapter(newsAdapter);
        recyclerViewNews.setHasFixedSize(true);

        newsAdapter.startListening();

    }

    @Override
    public void openNewsLoaderFragment(NewsListItem newsListItem, ActivityOptions options) {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "news_loader");
        intent.putExtra("IMAGE", newsListItem.getImage());
        intent.putExtra("HEADING", newsListItem.getHeading());
        intent.putExtra("NEWS", newsListItem.getNews());
        intent.putExtra("PLACE", newsListItem.getPlace());
        intent.putExtra("WRITER", newsListItem.getWriter());
        intent.putExtra("TIME", newsListItem.getTime());

        startActivity(intent, options.toBundle());
    }

    private class NewsAdapter extends FirebaseRecyclerAdapter<NewsListItem, NewsAdapter.NewsViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        NewsAdapter(@NonNull FirebaseRecyclerOptions<NewsListItem> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull NewsViewHolder holder, int position, @NonNull NewsListItem model) {

            holder.newsHeadlines.setText(model.getHeading());
            holder.newsWriter.setText(model.getWriter());
            holder.newsFrom.setText(model.getPlace());
            holder.newsPostTime.setText("3m |");
            Picasso.get()
                    .load(model.getImage())
                    .placeholder(R.drawable.logo_for_news)
                    .into(holder.newsHeadlinesIcon);

            holder.newsList = model;

        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._5_3_rec_news_items, parent, false);
            return new NewsViewHolder(view);
        }

        class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ViewGroup container;
            private ImageView newsHeadlinesIcon;
            private TextView newsHeadlines;
            private TextView newsPostTime;
            private TextView newsFrom;
            private TextView newsWriter;
            private ImageView moreIcon;

            NewsListItem newsList;

            NewsViewHolder(View itemView) {
                super(itemView);

                container = itemView.findViewById(R.id.root_news_headlines);

                newsHeadlinesIcon = itemView.findViewById(R.id.iv_news_headline_icon);
                newsHeadlines = itemView.findViewById(R.id.lbl_news_headlines);
                newsPostTime = itemView.findViewById(R.id.lbl_news_post_time);
                newsFrom = itemView.findViewById(R.id.lbl_news_from);
                newsWriter = itemView.findViewById(R.id.lbl_news_writer);
                moreIcon = itemView.findViewById(R.id.iv_news_headline_more);

                container.setOnClickListener(this);
                moreIcon.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.root_news_headlines:

                        Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(newsHeadlinesIcon, "newsImageIcon");
                        pairs[1] = new Pair<View, String>(newsHeadlines, "newsHeading");

                        //noinspection unchecked
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);

                        controller.onNewsListItemClick(newsList, options);

                        break;
                    case R.id.iv_news_headline_more:
                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    default:break;
                }

            }
        }

    }

}
