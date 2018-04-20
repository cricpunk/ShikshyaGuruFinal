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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionProgrammesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPProgrammesController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;

import java.util.HashMap;
import java.util.List;

public class ViewPagerProgrammesCoursesLoaderFragment extends Fragment implements ViewPagerProgrammesCoursesLoaderFragmentInterface {

    private LayoutInflater inflater;
    private View rootView;
    private InstitutionProgrammesData programmesData;
    private VPProgrammesController controller;

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

        controller = new VPProgrammesController(this, new InstitutionDataSource());

        controller.setUpCoursesLoader(level, faculty, programme);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onCoursesClickListener(String levelName, String courseName) {
        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "courses_loader");
        intent.putExtra("LEVEL_NAME", levelName);
        intent.putExtra("FACULTY_NAME", courseName);
        startActivity(intent);
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

            holder.programmesLevelName.setText(programmeName);

            holder.recProgrammesLabel.setNestedScrollingEnabled(false);
            holder.recProgrammesLabel.setHasFixedSize(false);
            holder.recProgrammesLabel.setLayoutManager(new LinearLayoutManager(getContext()));

            ProgrammesFacultiesAdapter adapter = new ProgrammesFacultiesAdapter();

            //Setting value in another adapter
            adapter.setSubjects(programmesData.getSubjectCollection().get(programmeName));
            adapter.setFees(programmesData.getFeeCollection().get(programmeName));


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

            ProgrammesLevelViewHolder(View itemView) {
                super(itemView);
                layoutLevel = itemView.findViewById(R.id.l_programmes_level);
                anchorView = itemView.findViewById(R.id.v_inst_loader_vp_programmes_level);
                programmesLevelName = itemView.findViewById(R.id.lbl_inst_loader_vp_programmes_label_name);
                recProgrammesLabel = itemView.findViewById(R.id.rec_inst_loader_vp_programmes_courses);

            }

        }

    }

    private class ProgrammesFacultiesAdapter extends RecyclerView.Adapter<ProgrammesFacultiesAdapter.ProgrammesCoursesViewHolder> {

        private List<HashMap<String, String>> subjects, fees;

        public void setSubjects(List<HashMap<String, String>> subjectsAndFees) {
            this.subjects = subjectsAndFees;
        }

        public void setFees(List<HashMap<String, String>> fees) {
            this.fees = fees;
        }

        @NonNull
        @Override
        public ProgrammesCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_9_rec_vp_programmes_courses_opener_item, parent, false);
            return new ProgrammesCoursesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProgrammesCoursesViewHolder holder, int position) {
//            holder.coursesName.setText(facultyName.get(position));
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
