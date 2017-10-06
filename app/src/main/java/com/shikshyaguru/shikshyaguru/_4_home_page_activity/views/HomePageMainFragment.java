package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Html;

import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.ColorWrapper;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.DataHelper;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.InstitutionsSuggestion;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.views.BaseExampleFragment;
import com.shikshyaguru.shikshyaguru._0_2_recyclerview_slider_effect.RecyclerViewSliderEffect;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.logic.Controller;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.FakeDataSource;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageOptionsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageSliderListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.ListOfInstitutionsHeading;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsHomePageActivity;

import java.util.List;

/*
 * Created by Pankaj Koirala on 9/24/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class HomePageMainFragment extends BaseExampleFragment implements
        ViewInterface,
AppBarLayout.OnOffsetChangedListener,
        View.OnClickListener {

//  ####################### ROOT SECTION #######################
    private final String TAG = "HOME PAGE MAIN FRAGMENT";
    private LayoutInflater layoutInflater;

//  ####################### SEARCH BAR SECTION #######################
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    public static FloatingSearchView mSearchView;
    private boolean mIsDarkSearchTheme = false;
    private String mLastQuery = "";

//  ####################### SLIDER SECTION #######################
    private List<HomePageSliderListItem> listOfSliderCandidates;
    private RecyclerView recyclerViewSlider;

//  ####################### OPTIONS SECTION #######################
    private List<HomePageOptionsListItem> listOfOptions;
    private RecyclerView recyclerViewOptions;

//  ####################### NEWS SECTION #######################
    private static final String EXTRA_NEWS = "EXTRA_NEWS";
    private static final String INSTITUTIONS_ICON = "INSTITUTIONS_ICON";
    //private static final String INSTITUTIONS_NAME = "INSTITUTIONS_NAME";
    //private static final String INSTITUTIONS_RATING = "INSTITUTIONS_RATING";
    //private static final String INSTITUTIONS_CITY_NAME = "INSTITUTIONS_CITY_NAME";
    private List<NewsListItem> listOfNewsData;
    private RecyclerView recyclerViewNews;

//  ####################### INSTITUTIONS COLLECTION SECTION #######################
    private List<ListOfInstitutionsHeading> listOfInstitutionsHeadings;
    private RecyclerView recyclerViewInstitutionsCollection;

//  ####################### CONTROLLER #######################
    private Controller controller;

    public HomePageMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        return inflater.inflate(R.layout._4_1_hp_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBarSection(view);
        sliderSection(view);
        optionsSection(view);
        newsSection(view);
        institutionsCollectionSection(view);

        // THIS IS DEPENDENCY INJECTION FOR CONTROLLER CLASS
        controller = new Controller(this, new FakeDataSource());

    }

    private void searchBarSection(View view) {
        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        AppBarLayout mAppBar = (AppBarLayout) view.findViewById(R.id.appbar);
        mAppBar.addOnOffsetChangedListener(this);
        setupDrawer();
        setupSearchBar();
    }

    private void sliderSection(View view) {
        recyclerViewSlider = (RecyclerView) view.findViewById(R.id.rec_hp_slider);
    }

    private void optionsSection(View view) {
        recyclerViewOptions = (RecyclerView) view.findViewById(R.id.rec_hp_options);
    }

    private void newsSection(View view) {
        //ConstraintLayout rootNewsSection = (ConstraintLayout) view.findViewById(R.id.root_news_section);
        //View newsHeaderBG = view.findViewById(R.id.v_news_header_bg);
        TextView news = (TextView) view.findViewById(R.id.lbl_news);
        news.setText(R.string.news);
        TextView allNews = (TextView) view.findViewById(R.id.lbl_all_news);
        allNews.setText(R.string.allNews);
        allNews.setOnClickListener(this);
        recyclerViewNews = (RecyclerView) view.findViewById(R.id.rec_news);
    }

    private void institutionsCollectionSection(View view) {
        recyclerViewInstitutionsCollection = (RecyclerView) view.findViewById(R.id.rec_institutions_collection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lbl_all_news:
                controller.onAllNewsClick();
            default:
                break;
        }
    }

    @Override
    public boolean onActivityBackPress() {
        //if mSearchView.setSearchFocused(false) causes the focused search
        //to close, then we don't want to close the activity. if mSearchView.setSearchFocused(false)
        //returns false, we know that the search was already closed so the call didn't change the focus
        //state and it makes sense to call supper onBackPressed() and close the activity
        return mSearchView.setSearchFocused(false);
        //if (!mSearchView.setSearchFocused(false)) {
        //return false;
        //}
        //return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mSearchView.setTranslationY(verticalOffset);
    }

//  ############################# VIEW INTERFACE IMPLEMENTATIONS START #############################

    public void setUpSliderAdapterAndView(List<HomePageSliderListItem> listOfSliderCandidates) {
        this.listOfSliderCandidates = listOfSliderCandidates;
        RecyclerView.LayoutManager layoutManager = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSlider.setLayoutManager(layoutManager);
        final CustomSliderAdapter customSliderAdapter = new CustomSliderAdapter();
        recyclerViewSlider.setAdapter(customSliderAdapter);
        recyclerViewSlider.setHasFixedSize(true);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerViewSlider);
        autoScrollRecyclerView(customSliderAdapter);

        recyclerViewSlider.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
//                updateState(scrollState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                RecyclerViewSliderEffect.scrollListenerOnScrolled(recyclerView);
            }
        });

        recyclerViewSlider.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                RecyclerViewSliderEffect.layoutChangeListenerOnLayoutChange(recyclerViewSlider);
            }
        });
    }

    @Override
    public void setUpOptionsAdapterAndView(List<HomePageOptionsListItem> listOfOptions) {
        this.listOfOptions = listOfOptions;
        recyclerViewOptions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        CustomOptionsAdapter customOptionsAdapter = new CustomOptionsAdapter();
        recyclerViewOptions.setAdapter(customOptionsAdapter);
    }

    @Override
    public void setupNewsHeadlinesAdapterAndView(List<NewsListItem> listOfNewsData) {
        this.listOfNewsData = listOfNewsData;
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        CustomNewsAdapter customNewsAdapter = new CustomNewsAdapter();
        recyclerViewNews.setAdapter(customNewsAdapter);
        recyclerViewNews.setHasFixedSize(true);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerViewNews);

        recyclerViewNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        recyclerViewNews.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //layoutChangeListenerOnLayoutChange(recyclerViewSlider);
                RecyclerViewSliderEffect.layoutChangeListenerOnLayoutChange(recyclerViewNews);
            }
        });
    }

    @Override
    public void openNewsHomePage() {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "news_home");
        startActivity(intent);
    }

    @Override
    public void openSingleNewsActivity(String news) {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra(EXTRA_NEWS, news);
        intent.putExtra("REQUEST_CODE", "news_loader");
        startActivity(intent);
    }

    @Override
    public void setupInstitutionsCollectionAdapterAndView(List<ListOfInstitutionsHeading> listOfInstitutionsHeadings) {
        this.listOfInstitutionsHeadings = listOfInstitutionsHeadings;
        recyclerViewInstitutionsCollection.setNestedScrollingEnabled(false);
        recyclerViewInstitutionsCollection.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomInstitutionsCollectionAdapter customInstitutionsCollectionAdapter = new CustomInstitutionsCollectionAdapter();
        recyclerViewInstitutionsCollection.setAdapter(customInstitutionsCollectionAdapter);
    }

    @Override
    public void openInstitutionsHomeActivity(int institutionsIcon, String institutionsName, String institutionsRating, String institutionsCityName) {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra(INSTITUTIONS_ICON, institutionsIcon);
        startActivity(intent);
    }

//  ############################# VIEW INTERFACE IMPLEMENTATIONS END #############################


    private class CustomOptionsAdapter extends RecyclerView.Adapter<CustomOptionsAdapter.OptionsViewHolder> {

        @Override
        public OptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.rec_hp_options, parent, false);
            return new OptionsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OptionsViewHolder holder, int position) {
            HomePageOptionsListItem currentItem = listOfOptions.get(position);
            holder.optionName.setText(currentItem.getOptionName());
        }

        @Override
        public int getItemCount() {
            return listOfOptions.size();
        }

        class OptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private CardView cvOptionName;
            private TextView optionName;

            OptionsViewHolder(View itemView) {
                super(itemView);
                optionName = (TextView) itemView.findViewById(R.id.lbl_option_name);
                cvOptionName = (CardView) itemView.findViewById(R.id.cv_option_name);
                cvOptionName.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CustomNewsAdapter extends RecyclerView.Adapter<CustomNewsAdapter.CustomNewsViewHolder> {

        @Override
        public CustomNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_5_rec_news_items, parent, false);
            return new CustomNewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomNewsViewHolder holder, int position) {
            NewsListItem currentItem = listOfNewsData.get(position);
            holder.newsHeadlines.setText(currentItem.getNews());
        }

        @Override
        public int getItemCount() {
            // Helps the adapter decide how many items it will need to manage
            return listOfNewsData.size();
        }

        // This class is bridge between NewsListItem(Data) class and news_recycler_view(View) layout
        class CustomNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ViewGroup container;
            private TextView newsHeadlines;
            //            private TextView newsHeading;


            CustomNewsViewHolder(View itemView) {
                super(itemView);

                newsHeadlines = (TextView) itemView.findViewById(R.id.lbl_news_headlines);
//                newsHeading = (TextView) itemView.findViewById(R.id.lbl_news_heading);
//                newsHeading.setText("News");
                container = (ViewGroup) itemView.findViewById(R.id.root_news_headlines);
                container.setOnClickListener(this);
                //allNews.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                NewsListItem newsListItem = listOfNewsData.get(this.getAdapterPosition());
                controller.onNewsListItemClick(newsListItem);
            }
        }
    }

    /*
     * This adapter class will display card view for each institution category.
     * Click event for See All text view will be managed from this class.
     * This class will call CustomInstitutionAdapter to inflate complete institutions list dynamically
     */
    class CustomInstitutionsCollectionAdapter extends RecyclerView.Adapter<CustomInstitutionsCollectionAdapter.CustomInstitutionsCollectionViewHolder> {

        @Override
        public CustomInstitutionsCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_2_institutions_section, parent, false);
            return new CustomInstitutionsCollectionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomInstitutionsCollectionViewHolder holder, int position) {
            ListOfInstitutionsHeading currentItem = listOfInstitutionsHeadings.get(position);

            holder.institutionHeading.setText(currentItem.getInstitutionHeading());
            holder.institutionSeeAll.setText(R.string.see_all);

            holder.institutionsRecyclerViewInside.setNestedScrollingEnabled(false);
            holder.institutionsRecyclerViewInside.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            CustomInstitutionAdapter customInstitutionAdapter = new CustomInstitutionAdapter();
            customInstitutionAdapter.setDataInside(currentItem.getRelatedInstitutionData());
            holder.institutionsRecyclerViewInside.setAdapter(customInstitutionAdapter);
            SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
            snapHelper.attachToRecyclerView(holder.institutionsRecyclerViewInside);
        }


        @Override
        public int getItemCount() {
            return listOfInstitutionsHeadings.size();
        }

        class CustomInstitutionsCollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView institutionHeading;
            private TextView institutionSeeAll;
            private RecyclerView institutionsRecyclerViewInside;

            CustomInstitutionsCollectionViewHolder(View itemView) {
                super(itemView);
                this.institutionHeading = (TextView) itemView.findViewById(R.id.lbl_institutions);
                this.institutionSeeAll = (TextView) itemView.findViewById(R.id.lbl_see_all);
                this.institutionsRecyclerViewInside = (RecyclerView) itemView.findViewById(R.id.rec_institutions);
                institutionSeeAll.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked item = " + institutionHeading.getText() + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    /*
     * This adapter class is for displaying all institutions horizontally.
     * Setting those institutions details will be done from this class.
     * All click event for those institutions will be managed from this class.
     * This class will be used to inflate complete institutions list dynamically
     * by CustomInstitutionCollectionAdapter.
     */
    private class CustomInstitutionAdapter extends RecyclerView.Adapter<CustomInstitutionAdapter.CustomInstitutionViewHolder> {

        private List<?> dataInside;

        private void setDataInside(List<?> dataInside) {
            this.dataInside = dataInside;
        }

        @Override
        public CustomInstitutionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_3_rec_institutions_items, parent, false);
            return new CustomInstitutionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomInstitutionViewHolder holder, int position) {
            InstitutionsListItemParent currentItem = (InstitutionsListItemParent) dataInside.get(position);
            holder.institutionIcon.setImageResource(currentItem.getInstitutionsIcon());
            holder.institutionName.setText(currentItem.getInstitutionsName());
            holder.institutionRating.setText(currentItem.getInstitutionsRating());
            holder.institutionCityName.setText(currentItem.getInstitutionCityName());
        }

        @Override
        public int getItemCount() {
            // Helps the adapter decide how many items it will need to manage
            return dataInside.size();
        }

        // This class is bridge between NewsListItem(Data) class and news_recycler_view(View) layout
        class CustomInstitutionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView institutionIcon;
            private TextView institutionName;
            private TextView institutionRating;
            private TextView institutionCityName;
            private ViewGroup container;

            CustomInstitutionViewHolder(View itemView) {
                super(itemView);

                this.institutionIcon = (ImageView) itemView.findViewById(R.id.iv_institutions_icon);
                this.institutionName = (TextView) itemView.findViewById(R.id.lbl_institutions_name);
                this.institutionRating = (TextView) itemView.findViewById(R.id.lbl_institutions_rating);
                this.institutionCityName = (TextView) itemView.findViewById(R.id.lbl_institutions_city_name);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_institutions);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                InstitutionsListItemParent institutionListItem = (InstitutionsListItemParent) dataInside.get(this.getAdapterPosition());
                controller.onInstitutionsItemClick(institutionListItem);
            }
        }
    }

    private class CustomSliderAdapter extends RecyclerView.Adapter<CustomSliderAdapter.SliderViewHolder> {

        @Override
        public SliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_8_hp_slider, parent, false);
            return new SliderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SliderViewHolder holder, int position) {
            HomePageSliderListItem currentItem = listOfSliderCandidates.get(position);
            holder.candidateImage.setImageResource(currentItem.getImage());
            holder.candidateName.setText(currentItem.getInstitutionName());
            holder.candidateCity.setText(currentItem.getCityName());
        }

        @Override
        public int getItemCount() {
            return listOfSliderCandidates.size();
        }

        class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private CardView sliderCardView;
            private ImageView candidateImage;
            private TextView candidateName;
            private TextView candidateCity;
            private TextView candidateSlogan;

            SliderViewHolder(View view) {
                super(view);

                sliderCardView = (CardView) view.findViewById(R.id.cv_candidates_slider);
                candidateImage = (ImageView) view.findViewById(R.id.iv_candidate_slider_image);
                candidateName = (TextView) view.findViewById(R.id.lbl_candidate_name);
                candidateCity = (TextView) view.findViewById(R.id.lbl_candidate_city);
                candidateSlogan = (TextView) view.findViewById(R.id.lbl_candidate_slogan);

                sliderCardView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Card View Clicked at : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void setupDrawer() {
        attachSearchViewActivityDrawer(mSearchView);
    }

    private void setupSearchBar() {

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();

                    //simulates a query call to a data source
                    //with a new query.
                    DataHelper.findSuggestions(getActivity(), newQuery, 5,
                            FIND_SUGGESTION_SIMULATED_DELAY, new DataHelper.OnFindSuggestionsListener() {

                                @Override
                                public void onResults(List<InstitutionsSuggestion> results) {

                                    //this will swap the data and
                                    //render the collapse/expand animations as necessary
                                    mSearchView.swapSuggestions(results);

                                    //let the users know that the background
                                    //process has completed
                                    mSearchView.hideProgress();
                                }
                            });
                }

                Log.d(TAG, "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {

                InstitutionsSuggestion institutionsSuggestion = (InstitutionsSuggestion) searchSuggestion;
                DataHelper.findColors(getActivity(), institutionsSuggestion.getBody(),
                        new DataHelper.OnFindColorsListener() {

                            @Override
                            public void onResults(List<ColorWrapper> results) {
                                //show search results
                            }

                        });
                Log.d(TAG, "onSuggestionClicked()");

                mLastQuery = searchSuggestion.getBody();
            }

            @Override
            public void onSearchAction(String query) {
                mLastQuery = query;

                DataHelper.findColors(getActivity(), query,
                        new DataHelper.OnFindColorsListener() {

                            @Override
                            public void onResults(List<ColorWrapper> results) {
                                //show search results
                            }

                        });
                Log.d(TAG, "onSearchAction()");
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

                //show suggestions when search bar gains focus (typically history suggestions)
                mSearchView.swapSuggestions(DataHelper.getHistory(getActivity(), 3));

                Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {

                //set the title of the bar so that when focus is returned a new query begins
                mSearchView.setSearchBarTitle(mLastQuery);

                //you can also set setSearchText(...) to make keep the query there when not focused and when focus returns
                //mSearchView.setSearchText(searchSuggestion.getBody());

                Log.d(TAG, "onFocusCleared()");
            }
        });


        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.action_voice_rec) {
                    Toast.makeText(getActivity().getApplicationContext(), item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                } else {

                    //just print action
                    Toast.makeText(getActivity().getApplicationContext(), item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHome"
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

                Log.d(TAG, "onHomeClicked()");
            }
        });

        /*
         * Here you have access to the left icon and the text of a given suggestion
         * item after as it is bound to the suggestion list. You can utilize this
         * callback to change some properties of the left icon and the text. For example, you
         * can load the left icon images using your favorite image loading library, or change text color.
         *
         *
         * Important:
         * Keep in mind that the suggestion list is a RecyclerView, so views are reused for different
         * items in the list.
         */
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                InstitutionsSuggestion institutionsSuggestion = (InstitutionsSuggestion) item;

                String textColor = mIsDarkSearchTheme ? "#ffffff" : "#000000";
                String textLight = mIsDarkSearchTheme ? "#bfbfbf" : "#787878";

                if (institutionsSuggestion.getIsHistory()) {
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_black_24dp, null));

                    Util.setIconColor(leftIcon, Color.parseColor(textColor));
                    leftIcon.setAlpha(.36f);
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTextColor(Color.parseColor(textColor));
                String text = institutionsSuggestion.getBody()
                        .replaceFirst(mSearchView.getQuery(),
                                "<font color=\"" + textLight + "\">" + mSearchView.getQuery() + "</font>");
                textView.setText(fromHtml(text));
            }

        });
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    private void autoScrollRecyclerView(final CustomSliderAdapter adapter) {
        final int speedScroll = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;
            @Override
            public void run() {
                if(count < adapter.getItemCount()){
                    if(count==adapter.getItemCount()-1){
                        flag = false;
                    }else if(count == 0){
                        flag = true;
                    }
                    if(flag) count++;
                    else count--;

                    recyclerViewSlider.smoothScrollToPosition(count);
                    handler.postDelayed(this,speedScroll);
                }
            }
        };
        handler.postDelayed(runnable,speedScroll);
    }

}
