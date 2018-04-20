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
import android.support.v7.widget.GridLayoutManager;
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

import java.util.List;

public class ViewPagerProgrammesLevelFragment extends Fragment implements ViewPagerProgrammesLevelInterface {

    private LayoutInflater inflater;
    private View rootView;
    private InstitutionProgrammesData programmesData;
    private VPProgrammesController controller;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_2_0_view_pager_programmes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;
        controller = new VPProgrammesController(this, new InstitutionDataSource());
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
    public void onCoursesClickListener(String levelName, String courseName) {
        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "courses_loader");
        intent.putExtra("LEVEL_NAME", levelName);
        intent.putExtra("FACULTY_NAME", courseName);
        startActivity(intent);
    }

    private class ProgrammesLevelAdapter extends RecyclerView.Adapter<ProgrammesLevelAdapter.ProgrammesLevelViewHolder> {

        @NonNull
        @Override
        public ProgrammesLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_1_rec_vp_programmes_level_item, parent, false);
            return new ProgrammesLevelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ProgrammesLevelViewHolder holder, int position) {
//            String programmesLabelName = programmesData.getProgrammesLevelName()[position];
            String programmesLabelName = programmesData.getProgrammesLevelNameList().get(position);
            holder.programmesLevelName.setText(programmesLabelName);

            holder.recProgrammesLabel.setNestedScrollingEnabled(false);
            holder.recProgrammesLabel.setHasFixedSize(false);
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            holder.recProgrammesLabel.setLayoutManager(gridLayoutManager);
            ProgrammesFacultiesAdapter adapter = new ProgrammesFacultiesAdapter();
//            adapter.setFacultyName(programmesData.getProgrammesCoursesName().get(programmesLabelName));

            //Setting value in another adapter
            adapter.setFacultyName(programmesData.getProgrammesCoursesNameList().get(programmesLabelName));
            adapter.setLevelName(programmesLabelName);

            holder.recProgrammesLabel.setAdapter(adapter);

            holder.layoutLevel.measure(View.MeasureSpec.makeMeasureSpec(holder.layoutLevel.getLayoutParams().width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            holder.anchorView.getLayoutParams().height = holder.layoutLevel.getMeasuredHeight();

        }

        @Override
        public int getItemCount() {
//            return programmesData.getProgrammesLevelName().length;
            return programmesData.getProgrammesLevelNameList().size();
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

        private String levelName;
        private List<String> facultyName;

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }
        private void setFacultyName(List<String> facultyName) {
            this.facultyName = facultyName;
        }

        @NonNull
        @Override
        public ProgrammesCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_2_rec_vp_programmes_faculties, parent, false);
            return new ProgrammesCoursesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProgrammesCoursesViewHolder holder, int position) {
            holder.coursesName.setText(facultyName.get(position));
        }

        @Override
        public int getItemCount() {
            return facultyName.size();
        }



        class ProgrammesCoursesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView coursesName;
            private ViewGroup coursesRootView;

            ProgrammesCoursesViewHolder(View itemView) {
                super(itemView);
                coursesName = itemView.findViewById(R.id.lbl_inst_loader_vp_programmes_faculty_name);
                coursesRootView = itemView.findViewById(R.id.root_inst_loader_vp_programmes_courses);
                coursesRootView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                controller.onCoursesClickListener(levelName, String.valueOf(coursesName.getText()));
            }
        }
    }
}
