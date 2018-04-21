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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionProgrammesData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPProgrammesController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;

import java.util.Objects;

public class ViewPagerProgrammesCourses extends Fragment implements ViewPagerProgrammesCoursesInterface {

    private LayoutInflater inflater;
    private View rootView;
    private InstitutionProgrammesData programmesData;

    private String level, faculty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._6_2_2_7_view_page_programmes_courses_fragment, container, false);
        this.inflater = inflater;
        this.rootView = view;

        if (getArguments() != null) {
            this.level = getArguments().getString("LEVEL_NAME");
            this.faculty = getArguments().getString("FACULTY_NAME");
        }

        String title = level.toUpperCase()+" / "+faculty.toUpperCase();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), title);
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        VPProgrammesController controller = new VPProgrammesController(this, new InstitutionDataSource());

        controller.setUpProgrammesCourses(InstitutionLoaderFragment.id, level, faculty);

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
        RecyclerView programmesLevelRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_courses);
        ProgrammesLevelAdapter adapter = new ProgrammesLevelAdapter();
        programmesLevelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        programmesLevelRecyclerView.setAdapter(adapter);

    }

    private class ProgrammesLevelAdapter extends RecyclerView.Adapter<ProgrammesLevelAdapter.ProgrammesLevelViewHolder> {

        @NonNull
        @Override
        public ProgrammesLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_6_rec_vp_offers_courses_item, parent, false);
            return new ProgrammesLevelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ProgrammesLevelViewHolder holder, int position) {

            String courseName = programmesData.getProgrammesCourses().get(position);
            holder.coursesName.setText(courseName.toUpperCase());

        }

        @Override
        public int getItemCount() {
            return programmesData.getProgrammesCourses().size();
        }

        class ProgrammesLevelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView coursesName;
            private ViewGroup coursesRootView;

            ProgrammesLevelViewHolder(View itemView) {
                super(itemView);
                coursesName = itemView.findViewById(R.id.lbl_inst_loader_vp_programmes_faculty_name);
                coursesRootView = itemView.findViewById(R.id.root_inst_loader_vp_programmes_courses);
                coursesRootView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "courses_opener");
                intent.putExtra("LEVEL_NAME", level);
                intent.putExtra("FACULTY_NAME", faculty);
                intent.putExtra("COURSE_NAME", coursesName.getText().toString().toLowerCase());
                startActivity(intent);

            }
        }
    }

}
