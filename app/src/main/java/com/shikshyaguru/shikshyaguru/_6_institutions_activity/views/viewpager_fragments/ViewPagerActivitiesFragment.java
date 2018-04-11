package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Styles;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionActivitiesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPActivitiesController;


public class ViewPagerActivitiesFragment extends Fragment implements ViewPagerActivitiesInterface{

    private Context context;
    private LayoutInflater inflater;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        this.inflater = inflater;

        return inflater.inflate(R.layout._6_2_8_0_view_pager_activities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.rootView = view;
        new VPActivitiesController(this, new InstitutionDataSource());
        //setUpAnchorView();
    }

    @Override
    public void setUpActivitiesCategory(InstitutionActivitiesData activitiesData) {
        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.rec_activities_root);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        ActivitiesCategoryAdapter adapter = new ActivitiesCategoryAdapter();
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(adapter);
    }

    class ActivitiesCategoryAdapter extends RecyclerView.Adapter<ActivitiesCategoryAdapter.ActivitiesCategoryViewHolder> {

        @Override
        public ActivitiesCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.rec_activities_category, parent, false);
            return new ActivitiesCategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivitiesCategoryViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class ActivitiesCategoryViewHolder extends RecyclerView.ViewHolder {

            private RecyclerView categoryItemRecyclerView;
            private View anchorView;

            ActivitiesCategoryViewHolder(View itemView) {
                super(itemView);
                categoryItemRecyclerView = itemView.findViewById(R.id.rec_activities_category_item);
                anchorView = itemView.findViewById(R.id.v_activities_category_bg);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                CategoryItemAdapter adapter = new CategoryItemAdapter();
                categoryItemRecyclerView.setLayoutManager(layoutManager);
                categoryItemRecyclerView.setAdapter(adapter);

                anchorView.getLayoutParams().height = Styles.sGetHeight(categoryItemRecyclerView) + 40;

            }

        }

    }

    class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder> {

        @Override
        public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.rec_activities_category_item, parent, false);
            return new CategoryItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryItemViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class CategoryItemViewHolder extends RecyclerView.ViewHolder {

            CategoryItemViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
