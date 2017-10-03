package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.nineoldandroids.view.ViewHelper;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.ColorWrapper;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.DataHelper;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.InstitutionsSuggestion;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.views.BaseExampleFragment;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.logic.Controller;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.FakeDataSource;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.ListOfInstitutionsHeading;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsHomePageActivity;
import com.shikshyaguru.shikshyaguru._5_news_activity.views.NewsLoaderFragment;
import com.shikshyaguru.shikshyaguru.animation_collection.ToolbarAnimation;

import java.util.List;

/*
 * Created by Pankaj Koirala on 9/24/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class HomePageMainFragment extends BaseExampleFragment implements
        ObservableScrollViewCallbacks,
        ViewInterface,
        View.OnClickListener {

    private final String TAG = "HOME PAGE MAIN FRAGMENT";
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    public static FloatingSearchView mSearchView;
    private AppBarLayout mAppBar;
    private boolean mIsDarkSearchTheme = false;
    private String mLastQuery = "";

    private CardView mCardView;
    private View anchorSliderBg;
    private ObservableScrollView mScrollView;
    private SliderLayout imageSlider;

    private static final String EXTRA_NEWS = "EXTRA_NEWS";
    private static final String INSTITUTIONS_ICON = "INSTITUTIONS_ICON";
//    private static final String INSTITUTIONS_NAME = "INSTITUTIONS_NAME";
//    private static final String INSTITUTIONS_RATING = "INSTITUTIONS_RATING";
//    private static final String INSTITUTIONS_CITY_NAME = "INSTITUTIONS_CITY_NAME";

    private List<NewsListItem> listOfNewsData;
    private List<ListOfInstitutionsHeading> listOfInstitutionsHeadings;

    private LayoutInflater layoutInflater;
    private RecyclerView newsRecyclerView;
    private RecyclerView institutionsCollectionRecyclerView;


    private Button button;

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
//        ButterKnife.bind(getActivity());

        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        mAppBar = (AppBarLayout) view.findViewById(R.id.appbar);

        mCardView = (CardView) view.findViewById(R.id.cv_img_slider);
        anchorSliderBg = view.findViewById(R.id.anchor_slider_bg);
        imageSlider = (SliderLayout) view.findViewById(R.id.image_slider_layout);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);

        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollable);
        mScrollView.setScrollViewCallbacks(this);

        TextView allNews = (TextView) view.findViewById(R.id.lbl_all_news);
        allNews.setOnClickListener(this);

        newsRecyclerView = (RecyclerView) view.findViewById(R.id.rec_news);
        institutionsCollectionRecyclerView = (RecyclerView) view.findViewById(R.id.rec_institutions_collection);

        // This is dependency injection
        controller = new Controller(this, new FakeDataSource());

        slider();
        setupDrawer();
        setupSearchBar();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lbl_all_news:
                controller.onAllNewsClick();
                break;
            case R.id.button:
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

                break;
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
        if (!mSearchView.setSearchFocused(false)) {
            return false;
        }
        return true;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        ViewHelper.setTranslationY(imageSlider, scrollY / 2);
        ViewHelper.setTranslationY(mCardView, (float) (scrollY / 2.5));
        ViewHelper.setTranslationY(anchorSliderBg, (float) (scrollY / 1.5));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ToolbarAnimation animation = new ToolbarAnimation(mAppBar, mScrollView, mSearchView);
        animation.onUpOrCancelMotionEvent(scrollState);
    }

    @Override
    public void onStop() {
        imageSlider.stopAutoCycle();
        super.onStop();
    }

    public void slider() {
        TextSliderView textSliderView = new TextSliderView(getContext());
        textSliderView
                .description("This is demo")
                .image(R.drawable.example);
        imageSlider.addSlider(textSliderView);
    }


    @Override
    public void setupNewsHeadlinesAdapterAndView(List<NewsListItem> listOfNewsData) {
        this.listOfNewsData = listOfNewsData;
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomNewsAdapter customNewsAdapter = new CustomNewsAdapter();
        newsRecyclerView.setAdapter(customNewsAdapter);
    }

    @Override
    public void setupInstitutionsCollectionAdapterAndView(List<ListOfInstitutionsHeading> listOfInstitutionsHeadings) {
        this.listOfInstitutionsHeadings = listOfInstitutionsHeadings;
        institutionsCollectionRecyclerView.setNestedScrollingEnabled(false);
        institutionsCollectionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomInstitutionsCollectionAdapter customInstitutionsCollectionAdapter = new CustomInstitutionsCollectionAdapter();
        institutionsCollectionRecyclerView.setAdapter(customInstitutionsCollectionAdapter);
    }

    @Override
    public void openSingleNewsActivity(String news) {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra(EXTRA_NEWS, news);
        startActivity(intent);
    }

    @Override
    public void openNewsHomePage() {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void openInstitutionsHomeActivity(int institutionsIcon, String institutionsName, String institutionsRating, String institutionsCityName) {
        Intent intent = new Intent(getContext(), NewsHomePageActivity.class);
        intent.putExtra(INSTITUTIONS_ICON, institutionsIcon);
        startActivity(intent);
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
                textView.setText(Html.fromHtml(text));
            }

        });
    }

}













//    setupWindowAnimations();
//        TravelViewPagerAdapter adapter = new TravelViewPagerAdapter(getFragmentManager());
//        adapter.addAll(generateTravelList());
//        imageSlider.setAdapter(adapter);
//        ExpandingPagerFactory.setupViewPager(imageSlider);
//
//        imageSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                ExpandingFragment expandingFragment = ExpandingPagerFactory.getCurrentFragment(imageSlider);
//                if (expandingFragment != null && expandingFragment.isOpenend()) {
//                    expandingFragment.close();
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


//    ====================================View Pager================================

//    @Override
//    public void onExpandingClick(View v) {
//        //v is expanding fragment layout
//
//        View view = v.findViewById(R.id.image);
//        Travel travel = generateTravelList().get(imageSlider.getCurrentItem());
//        startInfoActivity(view, travel);
//    }

//    @Override
//    public void onBackPressed() {
//        if(!ExpandingPagerFactory.onBackPressed(imageSlider)){
//            super.onBackPressed();
//        }
//    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void setupWindowAnimations() {
//        Explode slideTransition = new Explode();
//        getActivity().getWindow().setReenterTransition(slideTransition);
//        getActivity().getWindow().setExitTransition(slideTransition);
//    }
//
//    private List<Travel> generateTravelList() {
//        List<Travel> travels = new ArrayList<>();
//        for (int i = 0; i < 5; ++i) {
//            travels.add(new Travel("Seychelles", R.drawable.seychelles));
//            travels.add(new Travel("Shang Hai", R.drawable.shh));
//            travels.add(new Travel("New York", R.drawable.newyork));
//            travels.add(new Travel("castle", R.drawable.p1));
//        }
//        return travels;
//    }

//    @SuppressWarnings("unchecked")
//    private void startInfoActivity(View view, Travel travel) {
//        Activity activity = getActivity();
//        ActivityCompat.startActivity(activity,
//                InfoActivity.newInstance(activity, travel),
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        activity,
//                        new Pair<>(view, getString(R.string.transition_image)))
//                        .toBundle());
//    }

//    ====================================View Pager================================