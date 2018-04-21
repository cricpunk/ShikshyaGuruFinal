package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionProgrammesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPProgrammesController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ViewPagerProgrammesCoursesLoader extends Fragment implements ViewPagerProgrammesCoursesLoaderInterface {

    private LayoutInflater inflater;
    private View rootView;
    private InstitutionProgrammesData programmesData;

    private String level, faculty, programme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._6_2_2_10_view_pager_programmes_course_loader, container, false);
        this.inflater = inflater;
        this.rootView = view;

        if (getArguments() != null) {

            this.level = getArguments().getString("LEVEL_NAME");
            this.faculty = getArguments().getString("FACULTY_NAME");
            this.programme = getArguments().getString("COURSE_NAME");

        }

        String title = level.toUpperCase()+" / "+faculty.toUpperCase()+" / "+programme.toUpperCase();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), title);
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);


        VPProgrammesController controller = new VPProgrammesController(this, new InstitutionDataSource());

        controller.setUpCoursesLoader(level, faculty, programme);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void setUpProgrammesCourses(InstitutionProgrammesData programmesData) {
        this.programmesData = programmesData;
        RecyclerView programmesLevelRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_programmes_level);
        ProgrammesLevelAdapter adapter = new ProgrammesLevelAdapter();
        programmesLevelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        programmesLevelRecyclerView.setAdapter(adapter);
    }

    private class ProgrammesLevelAdapter extends RecyclerView.Adapter<ProgrammesLevelAdapter.ProgrammesLevelViewHolder> {

        @NonNull
        @Override
        public ProgrammesLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_8_rec_vp_programmes_courses_opener, parent, false);
            return new ProgrammesLevelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ProgrammesLevelViewHolder holder, int position) {

            String programmeName = programmesData.getOptionSemList().get(position);

            holder.fees = programmesData.getFeeCollection().get(programmeName);
            holder.programmesLevelName.setText(programmeName.toUpperCase());

            holder.recProgrammesLabel.setNestedScrollingEnabled(false);
            holder.recProgrammesLabel.setHasFixedSize(false);
            holder.recProgrammesLabel.setLayoutManager(new LinearLayoutManager(getContext()));

            ProgrammesFacultiesAdapter adapter = new ProgrammesFacultiesAdapter();

            //Setting value in another adapter
            adapter.setSubjects(programmesData.getSubjectCollection().get(programmeName));

            holder.recProgrammesLabel.setAdapter(adapter);

            holder.layoutLevel.measure(View.MeasureSpec.makeMeasureSpec(holder.layoutLevel.getLayoutParams().width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            holder.anchorView.getLayoutParams().height = holder.layoutLevel.getMeasuredHeight();

        }

        @Override
        public int getItemCount() {
            return programmesData.getOptionSemList().size();
        }

        class ProgrammesLevelViewHolder extends RecyclerView.ViewHolder {

            private TextView programmesLevelName;
            private RecyclerView recProgrammesLabel;
            private LinearLayout layoutLevel;
            private View anchorView;

            private List<HashMap<String, String>> fees;

            ProgrammesLevelViewHolder(View itemView) {
                super(itemView);
                layoutLevel = itemView.findViewById(R.id.l_programmes_level);
                anchorView = itemView.findViewById(R.id.v_inst_loader_vp_programmes_level);
                programmesLevelName = itemView.findViewById(R.id.lbl_inst_loader_vp_programmes_label_name);
                recProgrammesLabel = itemView.findViewById(R.id.rec_inst_loader_vp_programmes_courses);

                programmesLevelName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onMoreIconClickListener(programmesLevelName);
                    }
                });

            }

            void onMoreIconClickListener(TextView textView) {
                //Context contextWrapper = new android.view.ContextThemeWrapper(activity, R.style.teachersBusinessCardPopup);
                PopupMenu popupMenu = new PopupMenu(Objects.requireNonNull(getContext()), textView, Gravity.END);
                popupMenu.getMenuInflater().inflate(R.menu.courses_options_popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.courses_option_fee_structure:
                                Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
                                intent.putExtra("REQUEST_CODE", "fee_loader");
                                intent.putExtra("LEVEL_NAME", level);
                                intent.putExtra("FACULTY_NAME", faculty);
                                intent.putExtra("COURSE_NAME", programme);
                                intent.putExtra("OPT_SEM", programmesLevelName.getText().toString());
                                intent.putExtra("FEE_LIST", (Serializable) fees);
                                startActivity(intent);
                                break;
                            case R.id.courses_option_routine:
                                Toast.makeText(getContext(), "Will be updated soon..", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }

        }

    }

    private class ProgrammesFacultiesAdapter extends RecyclerView.Adapter<ProgrammesFacultiesAdapter.ProgrammesCoursesViewHolder> {

        private List<HashMap<String, String>> subjects;

        public void setSubjects(List<HashMap<String, String>> subjectsAndFees) {
            this.subjects = subjectsAndFees;
        }

        @NonNull
        @Override
        public ProgrammesCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_9_rec_vp_programmes_courses_opener_item, parent, false);
            return new ProgrammesCoursesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProgrammesCoursesViewHolder holder, int position) {
            HashMap<String, String> currentItem = subjects.get(position);

            holder.subjectName.setText(currentItem.get("subject"));
            holder.subjectCode.setText(currentItem.get("code"));
            holder.teacher.setText(currentItem.get("teacher"));
            holder.timing.setText(currentItem.get("timing"));
        }

        @Override
        public int getItemCount() {
            return subjects.size();
        }

        class ProgrammesCoursesViewHolder extends RecyclerView.ViewHolder{

            private TextView subjectName;
            private TextView subjectCode;
            private TextView teacher;
            private TextView timing;

            ProgrammesCoursesViewHolder(View itemView) {
                super(itemView);
                subjectName = itemView.findViewById(R.id.lbl_subject_name);
                subjectCode = itemView.findViewById(R.id.lbl_subject_code);
                teacher = itemView.findViewById(R.id.lbl_teacher_name);
                timing = itemView.findViewById(R.id.lbl_schedule);
            }

        }

    }

}
