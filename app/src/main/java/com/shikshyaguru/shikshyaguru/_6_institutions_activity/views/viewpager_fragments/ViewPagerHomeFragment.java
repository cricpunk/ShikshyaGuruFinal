package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_2_recyclerview_slider_effect.RecyclerViewSliderEffect;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.CollegeHomeIntroData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.CollegesHomeNewsAndEventsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.CollegesHomeFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionViewInterface;

import java.util.List;

public class ViewPagerHomeFragment extends Fragment implements InstitutionViewInterface {

    private LayoutInflater inflater;
    InstitutionsController controller;

    private List<CollegesHomeNewsAndEventsData> newsAndEventData;
    private List<CollegeHomeIntroData> introData;
    private RecyclerView recyclerViewNewsAndEvent;
    private RecyclerView recyclerViewIntro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_1_view_pager_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        int position = FragmentPagerItem.getPosition(getArguments());
//        TextView title = (TextView) view.findViewById(R.id.item_number);
//        title.setText(String.valueOf(position));

        initNewsSection(view);
        initIntroSection(view);
        controller = new InstitutionsController(this, new CollegesHomeFakeDataSource());
    }


    private void initNewsSection(View view) {
        TextView newsAndEventText = (TextView) view.findViewById(R.id.lbl_news);
        TextView allNews = (TextView) view.findViewById(R.id.lbl_all_news);
        newsAndEventText.setText("News and Events");
        allNews.setText("See All");

        View recInclude = view.findViewById(R.id.inc_rec_news);
        recyclerViewNewsAndEvent = (RecyclerView) recInclude.findViewById(R.id.rec_news);
    }

    private void initIntroSection(View view) {
        this.recyclerViewIntro = (RecyclerView) view.findViewById(R.id.rec_inst_loader_vp_home_intro);
    }

    @Override
    public void setUpNewsAdapterAndView(List<CollegesHomeNewsAndEventsData> newsAndEventsData) {
        this.newsAndEventData = newsAndEventsData;
        recyclerViewNewsAndEvent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        NewsAdapter adapter = new NewsAdapter();
        recyclerViewNewsAndEvent.setAdapter(adapter);
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
    }

    @Override
    public void setUpHomeIntroAdapterAndView(List<CollegeHomeIntroData> introData) {
        this.introData = introData;
        recyclerViewIntro.setNestedScrollingEnabled(false);
        recyclerViewIntro.setLayoutManager(new LinearLayoutManager(getContext()));
        IntroAdapter adapter = new IntroAdapter();
        recyclerViewIntro.setAdapter(adapter);
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

        @Override
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._4_5_rec_news_items, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsViewHolder holder, int position) {
            CollegesHomeNewsAndEventsData currentItem = newsAndEventData.get(position);
            holder.newsHeadlines.setText(currentItem.getNewsAndEvents());
        }

        @Override
        public int getItemCount() {
            return newsAndEventData.size();
        }

        class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ViewGroup container;
            private ImageView newsHeadlinesIcon;
            private TextView newsHeadlines;
            private TextView newsPostTime;
            private TextView newsFrom;
            private TextView newsWriter;
            private ImageView moreIcon;

            NewsViewHolder(View itemView) {
                super(itemView);

                container = (ViewGroup) itemView.findViewById(R.id.root_news_headlines);
                newsHeadlinesIcon = (ImageView) itemView.findViewById(R.id.iv_news_headline_icon);
                newsHeadlines = (TextView) itemView.findViewById(R.id.lbl_news_headlines);
                newsPostTime = (TextView) itemView.findViewById(R.id.lbl_news_post_time);
                newsFrom = (TextView) itemView.findViewById(R.id.lbl_news_from);
                newsWriter = (TextView) itemView.findViewById(R.id.lbl_news_writer);
                moreIcon = (ImageView) itemView.findViewById(R.id.iv_news_headline_more);

                container.setOnClickListener(this);
                moreIcon.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.root_news_headlines:
//                        NewsListItem newsListItem = listOfNewsData.get(this.getAdapterPosition());
//                        homePageController.onNewsListItemClick(newsListItem);
                        break;
                    case R.id.iv_news_headline_more:
                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.IntroViewHolder> {

        @Override
        public IntroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_rec_vp_home_intro, parent, false);
            return new IntroViewHolder(view);
        }

        @Override
        public void onBindViewHolder(IntroViewHolder holder, int position) {

            CollegeHomeIntroData currentItem = introData.get(position);
            int introId = Integer.parseInt(currentItem.getIntro());

            holder.image.setImageResource(currentItem.getImage());
            holder.introHeading.setText(currentItem.getIntroHeading());
            // While loading data from database replace intro id by currentItem.getIntro()
            holder.intro.setText(getContext().getResources().getString(introId));

        }

        @Override
        public int getItemCount() {
            return introData.size();
        }

        class IntroViewHolder extends RecyclerView.ViewHolder {

            private ImageView image;
            private TextView introHeading;
            private TextView intro;

            IntroViewHolder(View itemView) {
                super(itemView);

                image = (ImageView) itemView.findViewById(R.id.iv_inst_loader_vp_home);
                introHeading = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_home_page_intro_heading);
                intro = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_home_intro);
            }
        }
    }
}
