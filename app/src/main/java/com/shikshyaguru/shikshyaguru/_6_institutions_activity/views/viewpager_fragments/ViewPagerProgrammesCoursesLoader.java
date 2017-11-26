package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionProgrammesCoursesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPProgrammesController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewPagerProgrammesCoursesLoader extends Fragment implements
        ViewPagerProgrammesCoursesLoaderInterface,
        View.OnClickListener {

    private Context context;
    private Activity activity;

    private String title;
    private LayoutInflater inflater;
    private View rootView;
    private InstitutionProgrammesCoursesData coursesData;
    private VPProgrammesController controller;
    private TextView xi;
    private TextView xii;
    private View dotXi;
    private View dotXii;

    private boolean isXi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        this.context = getContext();

        assert activity != null;
        this.activity = getActivity();

        assert activity != null;
        StatusBar.changeStatusBarColor(context, activity.getWindow(), R.color.colorAppMain);

        return inflater.inflate(R.layout._6_2_2_3_fragment_view_pager_programmes_courses_loader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;

        if (getArguments() != null) {
            title = getArguments().getString("COURSE_NAME");
        }

        Toolbar toolbar = view.findViewById(R.id.tb_inst_loader_vp_programmes_courses_loader);
        Toolbars.setUpToolbar(toolbar, activity, title);

        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        this.controller = new VPProgrammesController(this, new InstitutionFakeDataSource());
        initYearButtons();
        isXi = true;
        controller.setUpCoursesAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                if (getFragmentManager().getBackStackEntryCount() > 0) {
//                    getFragmentManager().popBackStack();
//                } else {
//                    Toast.makeText(getContext(), "Does not work", Toast.LENGTH_SHORT).show();
//                }

                Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "institutions_loader");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onYearBtnClickListener(String year) {

        if (year.equals("xi")) {
            isXi = true;
            controller.setUpCoursesAdapter();
            changeBackground(xi, xii, dotXi, dotXii);
        } else {
            isXi = false;
            controller.setUpCoursesAdapter();
            changeBackground(xii, xi, dotXii, dotXi);
        }
    }

    @Override
    public void setUpOptionsAdapter(InstitutionProgrammesCoursesData coursesData) {
        this.coursesData = coursesData;
        RecyclerView coursesOptionRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_programmes_courses_loader);
        coursesOptionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CoursesOptionAdapter adapter = new CoursesOptionAdapter();
        coursesOptionRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onMoreIconClickListener(Button button) {
        //Context contextWrapper = new android.view.ContextThemeWrapper(activity, R.style.teachersBusinessCardPopup);
        PopupMenu popupMenu = new PopupMenu(context, button, Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.courses_options_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (isXi) {
                    switch (item.getItemId()) {
                        case R.id.courses_option_fee_structure:
                            Toast.makeText(getContext(), "Fee structure XI", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.courses_option_class_shift:
                            Toast.makeText(getContext(), "Class shift XI", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.courses_option_routine:
                            Toast.makeText(getContext(), "Routine XI", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (item.getItemId()) {
                        case R.id.courses_option_fee_structure:
                            Toast.makeText(getContext(), "Fee structure XII", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.courses_option_class_shift:
                            Toast.makeText(getContext(), "Class shift XII", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.courses_option_routine:
                            Toast.makeText(getContext(), "Routine XII", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
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

    private void initYearButtons() {
        xi = rootView.findViewById(R.id.lbl_courses_loader_xi);
        xii = rootView.findViewById(R.id.lbl_courses_loader_xii);

        xi.setText(R.string.xi);
        xii.setText(R.string.xii);

        dotXi = rootView.findViewById(R.id.v_course_loader_xi_dot);
        dotXii = rootView.findViewById(R.id.v_course_loader_xii_dot);

        changeBackground(xi, xii, dotXi, dotXii);
        xi.setOnClickListener(this);
        xii.setOnClickListener(this);
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

    private class CoursesOptionAdapter extends RecyclerView.Adapter<CoursesOptionAdapter.CoursesOptionViewHolder> {

        @Override
        public CoursesOptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_4_rec_11_12_courses_loader_options, parent, false);
            return new CoursesOptionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CoursesOptionViewHolder holder, int position) {
            holder.optionHeading.setText(String.valueOf("Option" + " " + (position + 1)));

            //Creating total subjects list
            List<String> totalSubjectsList = new ArrayList<>();

            if (isXi) {
                //Adding compulsorySubjects of grade XI to totalSubjectsList
                Collections.addAll(totalSubjectsList, coursesData.getCompulsorySubjectsXi());
                //Adding optionalSubjects  of grade XI to totalSubjectsList
                Collections.addAll(totalSubjectsList, coursesData.getSubjectOptionsCollectionXi().get(position));
            } else {
                //Adding compulsorySubjects of grade XII to totalSubjectsList
                Collections.addAll(totalSubjectsList, coursesData.getCompulsorySubjectsXii());
                //Adding optionalSubjects of grade XII to totalSubjectsList
                Collections.addAll(totalSubjectsList, coursesData.getSubjectOptionsCollectionXii().get(position));
            }


            //Converting total subjects list list to string array
            String[] totalSubjects = totalSubjectsList.toArray(new String[totalSubjectsList.size()]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 16;
            for (String totalSubject : totalSubjects) {
                TextView textView = new TextView(getContext());
                textView.setLayoutParams(layoutParams);
                textView.setText(totalSubject);
                textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.ic_dot), null, null, null);
                textView.setCompoundDrawablePadding(16);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(8, 8, 8, 8);
                textView.setTextColor(Color.parseColor("#E6222222"));
                holder.subjectsHolder.addView(textView);
            }
        }

        @Override
        public int getItemCount() {
            if (isXi) {
                return coursesData.getSubjectOptionsCollectionXi().size();
            } else {
                return coursesData.getSubjectOptionsCollectionXii().size();
            }

        }

        class CoursesOptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    /*private class BachelorCoursesAdapter extends RecyclerView.Adapter<BachelorCoursesAdapter.BachelorCoursesViewHolder> {

        @Override
        public BachelorCoursesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_4_rec_11_12_courses_loader_options, parent, false);
            return new BachelorCoursesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BachelorCoursesViewHolder holder, int viewPagerPosition) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class BachelorCoursesViewHolder extends RecyclerView.ViewHolder {

            BachelorCoursesViewHolder(View itemView) {
                super(itemView);
            }
        }
    }*/

}
