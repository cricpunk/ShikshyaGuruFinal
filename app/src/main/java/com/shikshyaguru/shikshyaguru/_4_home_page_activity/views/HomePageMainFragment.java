package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.util.Pair;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.firebase.database.FirebaseDatabase;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.ColorWrapper;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.DataHelper;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.InstitutionsSuggestion;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.views.BaseExampleFragment;
import com.shikshyaguru.shikshyaguru._0_2_recyclerview_slider_effect.RecyclerViewSliderEffect;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.FakeDataSource;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageOptionsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageSliderListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.ListOfTotalInstitutions;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.presenter.HomePageController;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsHomePageActivity;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 * Created by Pankaj Koirala on 9/24/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class  HomePageMainFragment extends BaseExampleFragment implements
        ViewInterface,
        AppBarLayout.OnOffsetChangedListener,
        View.OnClickListener {

    private FirebaseDatabase mDatabase;

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
    private List<ListOfTotalInstitutions> listOfTotalInstitutions;
    private RecyclerView recyclerViewInstitutionsCollection;

//  ####################### CONTROLLER #######################
    private HomePageController homePageController;

    public HomePageMainFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        View view = inflater.inflate(R.layout._4_1_hp_main_fragment, container, false);
        mDatabase = FirebaseDatabase.getInstance();

        searchBarSection(view);
        sliderSection(view);
        optionsSection(view);
        newsSection(view);
        institutionsCollectionSection(view);

        // THIS IS DEPENDENCY INJECTION FOR CONTROLLER CLASS
        homePageController = new HomePageController(this, new FakeDataSource());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void searchBarSection(View view) {
        mSearchView = view.findViewById(R.id.floating_search_view);
        AppBarLayout mAppBar = view.findViewById(R.id.appbar);
        mAppBar.addOnOffsetChangedListener(this);
        setupDrawer();
        setupSearchBar();
    }

    private void sliderSection(View view) {
        recyclerViewSlider = view.findViewById(R.id.rec_hp_slider);
    }

    private void optionsSection(View view) {
        recyclerViewOptions = view.findViewById(R.id.rec_hp_options);
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
        recyclerViewInstitutionsCollection = view.findViewById(R.id.rec_institutions_collection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lbl_all_news:
                homePageController.onAllNewsClick();
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

    public void setUpSliderAdapterAndView(FirebaseRecyclerOptions<HomePageSliderListItem> sliderOption) {
        
        RecyclerView.LayoutManager layoutManager = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSlider.setLayoutManager(layoutManager);
        SponsorSliderAdapter sponsorSliderAdapter = new SponsorSliderAdapter(sliderOption);
        recyclerViewSlider.setAdapter(sponsorSliderAdapter);
        recyclerViewSlider.setHasFixedSize(true);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerViewSlider);
        RecyclerViewSliderEffect.autoScrollRecyclerView(sponsorSliderAdapter, recyclerViewSlider);

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

        sponsorSliderAdapter.startListening();

    }

    @Override
    public void openSliderItemDetails(String id, String image, String name, String place, String slogan, ActivityOptions options) {

        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "institutions_loader");
        intent.putExtra("ID", id);
        intent.putExtra("IMAGE", image);
        intent.putExtra("NAME", name);
        intent.putExtra("PLACE", place);
        intent.putExtra("SLOGAN", slogan);

        startActivity(intent, options.toBundle());

    }

    @Override
    public void setUpOptionsAdapterAndView(List<HomePageOptionsListItem> listOfOptions) {
        this.listOfOptions = listOfOptions;
        recyclerViewOptions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        CustomOptionsAdapter customOptionsAdapter = new CustomOptionsAdapter();
        recyclerViewOptions.setAdapter(customOptionsAdapter);
    }

    @Override
    public void setupNewsHeadlinesAdapterAndView(FirebaseRecyclerOptions<NewsListItem> newsOption) {

        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        NewsAdapter newsAdapter = new NewsAdapter(newsOption);
        recyclerViewNews.setAdapter(newsAdapter);
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

        newsAdapter.startListening();
    }

    @Override
    public void openNewsMainFragment() {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "news_main");
        startActivity(intent);
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

    @Override
    public void setupInstitutionsCollectionAdapterAndView(List<ListOfTotalInstitutions> listOfTotalInstitutions) {
        this.listOfTotalInstitutions = listOfTotalInstitutions;
        recyclerViewInstitutionsCollection.setNestedScrollingEnabled(false);
        recyclerViewInstitutionsCollection.setLayoutManager(new LinearLayoutManager(getContext()));
        InstitutionsCollectionAdapter institutionsCollectionAdapter = new InstitutionsCollectionAdapter();
        recyclerViewInstitutionsCollection.setAdapter(institutionsCollectionAdapter);
    }

    @Override
    public void openInstitutionsMainFragment(int instCategory, String title) {
        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "institutions_main");
        intent.putExtra("CATEGORY", instCategory);
        intent.putExtra("TITLE", title);
        startActivity(intent);
    }

    @Override
    public void openInstitutionsLoaderFragment1(String institutionsIcon, String institutionsName, String institutionsRating, String institutionsCityName) {
        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "institutions_loader");
        startActivity(intent);
    }

//  ############################# VIEW INTERFACE IMPLEMENTATIONS END #############################

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

    private class SponsorSliderAdapter extends FirebaseRecyclerAdapter<HomePageSliderListItem, SponsorSliderAdapter.SponsorViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */

        SponsorSliderAdapter(@NonNull FirebaseRecyclerOptions<HomePageSliderListItem> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull SponsorViewHolder holder, int position, @NonNull HomePageSliderListItem model) {
            holder.candidateName.setText(model.getName());
            holder.candidateSlogan.setText(model.getSlogan());
            holder.candidateCity.setText(model.getCity());
            Picasso.get()
                    .load(model.getMain_image())
                    .placeholder(R.drawable.logo_for_news)
                    .into(holder.candidateImage);
            holder.id = model.getId();
            holder.name = model.getName();
            holder.image = model.getMain_image();
            holder.place = model.getCity();
            holder.slogan = model.getSlogan();

        }

        @NonNull
        @Override
        public SponsorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View  v = LayoutInflater.from(getContext()).inflate(R.layout._4_8_hp_slider, parent, false);
            return new SponsorViewHolder(v);
        }

        class SponsorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private CardView sliderCardView;
            private ImageView candidateImage;
            private TextView candidateName;
            private TextView candidateCity;
            private TextView candidateSlogan;

            private String id, image, name, place, slogan;

            SponsorViewHolder(View itemView) {
                super(itemView);
                sliderCardView = itemView.findViewById(R.id.cv_candidates_slider);
                candidateImage = itemView.findViewById(R.id.iv_candidate_slider_image);
                candidateName = itemView.findViewById(R.id.lbl_candidate_name);
                candidateCity = itemView.findViewById(R.id.lbl_candidate_city);
                candidateSlogan = itemView.findViewById(R.id.lbl_candidate_slogan);

                id = null;
                image = null;
                name = null;
                place = null;
                slogan = null;

                sliderCardView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Card View Clicked at : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(candidateImage, "institutionImage");
                pairs[1] = new Pair<View, String>(candidateName, "institutionName");
                pairs[2] = new Pair<View, String>(candidateCity, "institutionPlace");
                pairs[3] = new Pair<View, String>(candidateSlogan, "institutionSlogan");

                //noinspection unchecked
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);

                homePageController.onInstitutionItemClick(id, image, name, place, slogan, options);
            }
        }

    }

    private class CustomOptionsAdapter extends RecyclerView.Adapter<CustomOptionsAdapter.OptionsViewHolder> {

        @NonNull
        @Override
        public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_9_rec_hp_options, parent, false);
            return new OptionsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
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
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();


//                for (Map.Entry<String, String> entry : categories.entrySet()) {
//
//                    System.out.println(entry.getKey() + " : " + entry.getValue());
//                }

            }
        }
    }

    class NewsAdapter extends FirebaseRecyclerAdapter<NewsListItem, NewsAdapter.NewsViewHolder> {

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
            View view = layoutInflater.inflate(R.layout._4_5_rec_news_items, parent, false);
            return new NewsViewHolder(view);
        }

        // This class is bridge between NewsListItem(Data) class and news_recycler_view(View) layout
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

                        homePageController.onNewsListItemClick(newsList, options);

                        break;
                    case R.id.iv_news_headline_more:
                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:break;
                }
            }
        }

    }


    /*
     * This adapter class will display card view for each institution category.
     * Click event for See All text view will be managed from this class.
     * This class will call CustomInstitutionAdapter to inflate complete institutions list dynamically
     */
    class InstitutionsCollectionAdapter extends RecyclerView.Adapter<InstitutionsCollectionAdapter.InstitutionsCollectionViewHolder> {

        @NonNull
        @Override
        public InstitutionsCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_2_institutions_section, parent, false);
            return new InstitutionsCollectionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InstitutionsCollectionViewHolder holder, int position) {

            ListOfTotalInstitutions currentItem = listOfTotalInstitutions.get(position);

            holder.institutionHeading.setText(currentItem.getInstitutionHeading());
            holder.institutionSeeAll.setText(R.string.see_all);
            holder.instCategory = currentItem.getCategory();
            holder.title = currentItem.getInstitutionHeading();

            holder.institutionsRecyclerViewInside.setNestedScrollingEnabled(false);
            holder.institutionsRecyclerViewInside.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            FirebaseRecyclerOptions<?> options = currentItem.getRelatedInstitutionOptions();

            //noinspection unchecked
            InstitutionAdapter institutionAdapter = new InstitutionAdapter((FirebaseRecyclerOptions<InstitutionsListItemParent>) options);

            holder.institutionsRecyclerViewInside.setAdapter(institutionAdapter);
            SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
            snapHelper.attachToRecyclerView(holder.institutionsRecyclerViewInside);

            institutionAdapter.startListening();

        }


        @Override
        public int getItemCount() {
            return listOfTotalInstitutions.size();
        }

        class InstitutionsCollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView institutionHeading;
            private TextView institutionSeeAll;
            private RecyclerView institutionsRecyclerViewInside;

            private int instCategory;
            private String title;

            InstitutionsCollectionViewHolder(View itemView) {
                super(itemView);
                institutionHeading = itemView.findViewById(R.id.lbl_institutions);
                institutionSeeAll = itemView.findViewById(R.id.lbl_see_all);
                institutionsRecyclerViewInside = itemView.findViewById(R.id.rec_institutions);
                institutionSeeAll.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                homePageController.onAllInstitutionsClick(instCategory, title);
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
    private class InstitutionAdapter extends FirebaseRecyclerAdapter<InstitutionsListItemParent, InstitutionAdapter.InstitutionViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */

        InstitutionAdapter(@NonNull FirebaseRecyclerOptions<InstitutionsListItemParent> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull InstitutionViewHolder holder, int position, @NonNull InstitutionsListItemParent model) {
            //holder.institutionIcon.setImageResource(model.getIcon_image());
            holder.institutionName.setText(model.getName());
            holder.institutionRating.setText(model.getRating());
            holder.institutionCityName.setText(model.getCity());
            Picasso.get()
                    .load(model.getIcon_image())
                    .placeholder(R.drawable.logo)
                    .into(holder.institutionIcon);

            holder.id = model.getId();
            holder.name = model.getName();
            holder.image = model.getIcon_image();
            holder.place = model.getCity();

        }

        @NonNull
        @Override
        public InstitutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_3_rec_institutions_items, parent, false);
            return new InstitutionViewHolder(view);
        }

        class InstitutionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ViewGroup container;
            private ImageView institutionIcon;
            private TextView institutionName;
            private ImageView institutionMore;
            private TextView institutionRating;
            private TextView institutionCityName;

            private String id, image, name, place;

            InstitutionViewHolder(View itemView) {
                super(itemView);

                container = itemView.findViewById(R.id.root_institutions);
                institutionIcon = itemView.findViewById(R.id.iv_institutions_icon);
                institutionName = itemView.findViewById(R.id.lbl_institutions_name);
                institutionMore = itemView.findViewById(R.id.iv_institutions_more);
                institutionRating = itemView.findViewById(R.id.lbl_institutions_rating);
                institutionCityName = itemView.findViewById(R.id.lbl_institutions_city_name);

                container.setOnClickListener(this);
                institutionMore.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.root_institutions:

                        Pair[] pairs = new Pair[1];
                        pairs[0] = new Pair<View, String>(institutionIcon, "institutionImage");
//                        pairs[1] = new Pair<View, String>(institutionName, "institutionName");
//                        pairs[2] = new Pair<View, String>(institutionCityName, "institutionPlace");

                        //noinspection unchecked
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);

                        homePageController.onInstitutionItemClick(id, image, name, place, null, options);

                        break;
                    case R.id.iv_institutions_more:

                       Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

                        break;
                    default:break;
                }

            }
        }

    }

}
