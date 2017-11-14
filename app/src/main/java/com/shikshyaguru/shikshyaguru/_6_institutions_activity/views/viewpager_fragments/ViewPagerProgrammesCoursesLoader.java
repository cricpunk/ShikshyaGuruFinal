package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionsProgrammesCoursesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewPagerProgrammesCoursesLoader extends Fragment implements
        ViewPagerProgrammesCoursesLoaderInterface,
        View.OnClickListener{

    private LayoutInflater inflater;
    private View rootView;
    private InstitutionsProgrammesCoursesData coursesData;
    private InstitutionsController controller;
    private TextView xi;
    private TextView xii;
    private View dotXi;
    private View dotXii;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_2_3_fragment_view_pager_programmes_courses_loader, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;

        initYearButtons();
        initToolbarAndLayout();
        this.controller = new InstitutionsController(this, new InstitutionFakeDataSource());
    }

    private void initToolbarAndLayout() {
        Toolbar toolbar = rootView.findViewById(R.id.tb_inst_loader_vp_programmes_courses_loader);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar getSupportActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (getSupportActionBar != null) {
            getSupportActionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar.setDisplayShowTitleEnabled(false);
        }

        AppBarLayout mAppBar = rootView.findViewById(R.id.abl_inst_loader_vp_programmes_courses_loader);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            }
        });
    }

    private void initYearButtons() {
        xi = rootView.findViewById(R.id.lbl_courses_loader_xi);
        xii = rootView.findViewById(R.id.lbl_courses_loader_xii);

        dotXi = rootView.findViewById(R.id.v_course_loader_xi_dot);
        dotXii = rootView.findViewById(R.id.v_course_loader_xii_dot);

        changeBackground(xi, xii, dotXi, dotXii);
        xi.setOnClickListener(this);
        xii.setOnClickListener(this);
    }

    @Override
    public void onYearBtnClickListener(String year) {

        if (year.equals("xi")) {
            changeBackground(xi, xii, dotXi, dotXii);
        } else {
            changeBackground(xii, xi, dotXii, dotXi);
        }
    }

    private void changeBackground(TextView active, TextView inactive, View dotActive, View dotInactive) {

        // Get both textView background (Active and inactive)
        LayerDrawable bgDrawableActive = (LayerDrawable) active.getBackground();
        LayerDrawable bgDrawableInactive = (LayerDrawable) inactive.getBackground();

        // Get background drawable shape using drawable item id for active textView
        GradientDrawable bgShapeOuterActive = (GradientDrawable) bgDrawableActive.findDrawableByLayerId(R.id.d_circle_outer);
        GradientDrawable bgShapeInnerActive = (GradientDrawable) bgDrawableActive.findDrawableByLayerId(R.id.d_circle_inner);

        // Get background drawable shape using drawable item id for inactive textView
        GradientDrawable bgShapeOuterInActive = (GradientDrawable) bgDrawableInactive.findDrawableByLayerId(R.id.d_circle_outer);
        GradientDrawable bgShapeInnerInActive = (GradientDrawable) bgDrawableInactive.findDrawableByLayerId(R.id.d_circle_inner);

        // Set color for active textView background shape
        bgShapeInnerActive.setColor(Color.parseColor("#212121"));
        bgShapeOuterActive.setStroke(4, Color.parseColor("#212121"));

        // Set color for inactive textView background shape
        bgShapeInnerInActive.setColor(Color.parseColor("#FAFAFA"));
        bgShapeOuterInActive.setStroke(4, Color.parseColor("#FAFAFA"));

        // Set color for active and inactive textView text
        active.setTextColor(Color.parseColor("#FAFAFA"));
        inactive.setTextColor(getResources().getColor(R.color.colorAppMain));

        dotActive.setVisibility(View.VISIBLE);
        dotInactive.setVisibility(View.GONE);
    }

    @Override
    public void setUpOptionsAdapter(InstitutionsProgrammesCoursesData coursesData) {
        this.coursesData = coursesData;
        RecyclerView coursesOptionRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_programmes_courses_loader);
        coursesOptionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CoursesOptionAdapter adapter = new CoursesOptionAdapter();
        coursesOptionRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onMoreIconClickListener(Button button) {
        //Context contextWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.teachersBusinessCardPopup);
        PopupMenu popupMenu = new PopupMenu(getContext(), button, Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.courses_options_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.courses_option_fee_structure:
                        Toast.makeText(getContext(), "Fee structure", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.courses_option_class_shift:
                        Toast.makeText(getContext(), "Class shift", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.courses_option_routine:
                        Toast.makeText(getContext(), "Routine", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lbl_courses_loader_xi:
                controller.onYearBtnClickListener("xi");
                break;
            case R.id.lbl_courses_loader_xii:
                controller.onYearBtnClickListener("xii");
                break;
            default:
                break;
        }
    }

    private class CoursesOptionAdapter extends RecyclerView.Adapter<CoursesOptionAdapter.CoursesOptionViewHolder> {

        @Override
        public CoursesOptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.rec_courses_loader_options, parent, false);
            return new CoursesOptionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CoursesOptionViewHolder holder, int position) {
            holder.optionHeading.setText("Option" + " " + (position+1));

            //Creating total subjects list
            List<String> totalSubjectsList = new ArrayList<>();
            //Adding compulsorySubjects to totalSubjectsList
            Collections.addAll(totalSubjectsList, coursesData.getCompulsorySubjects());
            //Adding optionalSubjects to totalSubjectsList
            Collections.addAll(totalSubjectsList, coursesData.getSubjectOptionsCollection().get(position));
            //Converting total subjects list list to string array
            String[] totalSubjects = totalSubjectsList.toArray(new String[totalSubjectsList.size()]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 16;
            for (String totalSubject : totalSubjects) {
                TextView textView = new TextView(getContext());
                textView.setLayoutParams(layoutParams);
                textView.setText(totalSubject);
                textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.ic_dot), null, null, null);
                textView.setCompoundDrawablePadding(16);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(8,8,8,8);
                textView.setTextColor(Color.parseColor("#E6222222"));
                holder.subjectsHolder.addView(textView);
            }
        }

        @Override
        public int getItemCount() {
            return coursesData.getSubjectOptionsCollection().size();
        }

        class CoursesOptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView optionHeading;
            private Button btnMore;
            private LinearLayout subjectsHolder;

            CoursesOptionViewHolder(View itemView) {
                super(itemView);

                optionHeading = itemView.findViewById(R.id.lbl_rec_courses_loader_option);
                btnMore = itemView.findViewById(R.id.btn_rec_courses_loader_more);
                subjectsHolder = itemView.findViewById(R.id.ll_courses_loader_subjects);

                btnMore.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                controller.onMoreIconClickListener(btnMore);
            }
        }
    }

}
