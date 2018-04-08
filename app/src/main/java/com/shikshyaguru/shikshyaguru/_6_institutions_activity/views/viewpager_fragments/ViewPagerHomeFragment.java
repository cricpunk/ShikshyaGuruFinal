package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_2_recyclerview_slider_effect.RecyclerViewSliderEffect;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsHomePageActivity;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeIntroData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeNewsAndEventsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPHomeController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsLoaderFragment;
import com.squareup.picasso.Picasso;

public class ViewPagerHomeFragment extends Fragment implements ViewPagerHomeInterface {

    private LayoutInflater inflater;
    VPHomeController controller;

    private RecyclerView recyclerViewNewsAndEvent;
    private RecyclerView recyclerViewIntro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_1_0_view_pager_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        int viewPagerPosition = FragmentPagerItem.getPosition(getArguments());
//        TextView title = (TextView) view.findViewById(R.id.item_number);
//        title.setText(String.valueOf(viewPagerPosition));

        controller = new VPHomeController(this, new InstitutionFakeDataSource());
        initNewsSection(view);
        initIntroSection(view);


    }

    private void initNewsSection(View view) {
        TextView newsAndEventText = view.findViewById(R.id.lbl_news);
        TextView allNews = view.findViewById(R.id.lbl_all_news);
        newsAndEventText.setText(R.string.news_events);
        allNews.setText(R.string.see_all);

        View recInclude = view.findViewById(R.id.inc_rec_news);
        recyclerViewNewsAndEvent = recInclude.findViewById(R.id.rec_news);

        controller.setUpNewsAndEvents(InstitutionsLoaderFragment.id);

    }

    private void initIntroSection(View view) {
        this.recyclerViewIntro = view.findViewById(R.id.rec_inst_loader_vp_home_intro);
        controller.setUpHomeIntro(InstitutionsLoaderFragment.id);
    }

    @Override
    public void setUpNewsAdapterAndView(FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> newsOption) {

        recyclerViewNewsAndEvent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        NewsEventAdapter newsEventAdapter = new NewsEventAdapter(newsOption);

        recyclerViewNewsAndEvent.setAdapter(newsEventAdapter);
        recyclerViewNewsAndEvent.setHasFixedSize(true);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerViewNewsAndEvent);

        recyclerViewNewsAndEvent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
//                updateState(scrollState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                //scrollListenerOnScrolled(recyclerView);
                RecyclerViewSliderEffect.scrollListenerOnScrolled(recyclerView);
            }
        });

        recyclerViewNewsAndEvent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //layoutChangeListenerOnLayoutChange(recyclerViewSlider);
                RecyclerViewSliderEffect.layoutChangeListenerOnLayoutChange(recyclerViewNewsAndEvent);
            }
        });

        newsEventAdapter.startListening();

    }

    @Override
    public void setUpHomeIntroAdapterAndView(FirebaseRecyclerOptions<InstitutionHomeIntroData> introOption) {

        recyclerViewIntro.setNestedScrollingEnabled(false);
        recyclerViewIntro.setLayoutManager(new LinearLayoutManager(getContext()));
        IntroAdapter adapter = new IntroAdapter(introOption);
        recyclerViewIntro.setAdapter(adapter);

        adapter.startListening();

    }

    @Override
    public void openNews(InstitutionHomeNewsAndEventsData newsList, ActivityOptions options) {

        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "news_loader");
        intent.putExtra("IMAGE", newsList.getImage_url());
        intent.putExtra("HEADING", newsList.getNews_heading());
        intent.putExtra("NEWS", newsList.getNews_content());
        intent.putExtra("PLACE", newsList.getPlace_name());
        intent.putExtra("WRITER", newsList.getFull_name());
        intent.putExtra("TIME", newsList.getId());

        startActivity(intent, options.toBundle());
    }

    class NewsEventAdapter extends FirebaseRecyclerAdapter<InstitutionHomeNewsAndEventsData, NewsEventAdapter.NewsEventViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        NewsEventAdapter(@NonNull FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> options) {
            super(options);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onBindViewHolder(@NonNull NewsEventAdapter.NewsEventViewHolder holder, int position, @NonNull InstitutionHomeNewsAndEventsData model) {
            holder.newsHeadlines.setText(model.getNews_heading());
            holder.newsWriter.setText(model.getFull_name());
            holder.newsFrom.setText(model.getPlace_name());
            holder.newsPostTime.setText("3m |");
            Picasso.get()
                    .load(model.getImage_url())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.logo_for_news)
                    .into(holder.newsHeadlinesIcon);

            holder.newsList = model;

        }

        @NonNull
        @Override
        public NewsEventAdapter.NewsEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._4_5_rec_news_items, parent, false);
            return new NewsEventAdapter.NewsEventViewHolder(view);
        }

        // This class is bridge between NewsListItem(Data) class and news_recycler_view(View) layout
        class NewsEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ViewGroup container;
            private ImageView newsHeadlinesIcon;
            private TextView newsHeadlines;
            private TextView newsPostTime;
            private TextView newsFrom;
            private TextView newsWriter;
            private ImageView moreIcon;

            InstitutionHomeNewsAndEventsData newsList;

            NewsEventViewHolder(View itemView) {
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

    class IntroAdapter extends FirebaseRecyclerAdapter<InstitutionHomeIntroData, IntroAdapter.IntroViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        IntroAdapter(@NonNull FirebaseRecyclerOptions<InstitutionHomeIntroData> options) {
            super(options);
        }

        @NonNull
        @Override
        public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_1_1_rec_vp_home_intro, parent, false);
            return new IntroViewHolder(view);
        }

        @Override
        protected void onBindViewHolder(@NonNull IntroViewHolder holder, int position, @NonNull InstitutionHomeIntroData model) {

            holder.introHeading.setText(model.getMessage_heading());
            holder.intro.setText(model.getMessage_content());
            Picasso.get()
                    .load(model.getImage_url())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.logo_for_news)
                    .into(holder.image);

        }

        class IntroViewHolder extends RecyclerView.ViewHolder {

            private ImageView image;
            private TextView introHeading;
            private TextView intro;

            IntroViewHolder(View itemView) {
                super(itemView);

                image = itemView.findViewById(R.id.iv_inst_loader_vp_home);
                introHeading = itemView.findViewById(R.id.lbl_inst_loader_vp_home_page_intro_heading);
                intro = itemView.findViewById(R.id.lbl_inst_loader_vp_home_intro);

            }
        }

    }

}
