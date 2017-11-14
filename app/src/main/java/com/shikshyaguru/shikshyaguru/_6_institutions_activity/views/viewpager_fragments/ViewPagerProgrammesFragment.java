package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionProgrammesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;

public class ViewPagerProgrammesFragment extends Fragment implements ViewPagerProgrammesInterface {

    private LayoutInflater inflater;
    private View rootView;
    private InstitutionProgrammesData programmesData;
    private InstitutionsController controller;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_2_0_view_pager_programmes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;
        controller = new InstitutionsController(this, new InstitutionFakeDataSource());
    }

    @Override
    public void setUpProgrammesLevel(InstitutionProgrammesData programmesData) {
        this.programmesData = programmesData;
        RecyclerView programmesLevelRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_programmes_level);
        ProgrammesLevelAdapter adapter = new ProgrammesLevelAdapter();
        programmesLevelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        programmesLevelRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onCoursesClickListener() {
        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "courses_loader");
        startActivity(intent);
    }

    private class ProgrammesLevelAdapter extends RecyclerView.Adapter<ProgrammesLevelAdapter.ProgrammesLevelViewHolder> {

        @Override
        public ProgrammesLevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_1_rec_vp_programmes_level_item, parent, false);
            return new ProgrammesLevelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProgrammesLevelViewHolder holder, int position) {
            String programmesLabelName = programmesData.getProgrammesLevelName()[position];
            holder.programmesLevelName.setText(programmesLabelName);

            holder.recProgrammesLabel.setNestedScrollingEnabled(false);
            holder.recProgrammesLabel.setHasFixedSize(false);
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            holder.recProgrammesLabel.setLayoutManager(gridLayoutManager);
            ProgrammesCoursesAdapter adapter = new ProgrammesCoursesAdapter();
            adapter.setCoursesName(programmesData.getProgrammesCoursesName().get(programmesLabelName));
            holder.recProgrammesLabel.setAdapter(adapter);

            holder.layoutLevel.measure(View.MeasureSpec.makeMeasureSpec(holder.layoutLevel.getLayoutParams().width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            holder.anchorView.getLayoutParams().height = holder.layoutLevel.getMeasuredHeight();

        }

        @Override
        public int getItemCount() {
            return programmesData.getProgrammesLevelName().length;
        }

        class ProgrammesLevelViewHolder extends RecyclerView.ViewHolder {

            private TextView programmesLevelName;
            private RecyclerView recProgrammesLabel;
            private LinearLayout layoutLevel;
            private RelativeLayout anchorView;

            ProgrammesLevelViewHolder(View itemView) {
                super(itemView);
                layoutLevel = itemView.findViewById(R.id.l_programmes_level);
                anchorView = itemView.findViewById(R.id.v_inst_loader_vp_programmes_level);
                programmesLevelName = itemView.findViewById(R.id.lbl_inst_loader_vp_programmes_label_name);
                recProgrammesLabel = itemView.findViewById(R.id.rec_inst_loader_vp_programmes_courses);

            }
        }
    }

    private class ProgrammesCoursesAdapter extends RecyclerView.Adapter<ProgrammesCoursesAdapter.ProgrammesCoursesViewHolder> {

        private String[] coursesName;

        private void setCoursesName(String[] coursesName) {
            this.coursesName = coursesName;
        }

        @Override
        public ProgrammesCoursesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_2_rec_vp_offers_courses_item, parent, false);
            return new ProgrammesCoursesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProgrammesCoursesViewHolder holder, int position) {
            holder.coursesName.setText(coursesName[position]);
        }

        @Override
        public int getItemCount() {
            return coursesName.length;
        }

        class ProgrammesCoursesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView coursesName;
            private ViewGroup coursesRootView;

            ProgrammesCoursesViewHolder(View itemView) {
                super(itemView);
                coursesName = itemView.findViewById(R.id.lbl_inst_loader_vp_programmes_courses_name);
                coursesRootView = itemView.findViewById(R.id.root_inst_loader_vp_programmes_courses);
                coursesRootView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                controller.onCoursesClickListener();
            }
        }
    }
}
