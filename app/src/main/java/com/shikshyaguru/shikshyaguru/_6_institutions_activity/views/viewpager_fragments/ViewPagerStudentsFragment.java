package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_2_recyclerview_slider_effect.RecyclerViewSliderEffect;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.CustomLinearLayoutManager;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionStudentAlumniData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;

import java.util.List;

public class ViewPagerStudentsFragment extends Fragment implements ViewPagerStudentsInterface{

    private List<InstitutionStudentAlumniData> studentAlumniData;
    private View rootView;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_3_0_view_pager_students, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.rootView = view;
        new InstitutionsController(this, new InstitutionFakeDataSource());
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setUpStudentAlumni(List<InstitutionStudentAlumniData> studentAlumniData) {
        this.studentAlumniData = studentAlumniData;
        final RecyclerView studentAlumniRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_student_alumni);
        RecyclerView.LayoutManager layoutManager = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        studentAlumniRecyclerView.setLayoutManager(layoutManager);
        StudentAlumniAdapter adapter = new StudentAlumniAdapter();
        studentAlumniRecyclerView.setAdapter(adapter);
        studentAlumniRecyclerView.setHasFixedSize(true);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(studentAlumniRecyclerView);

        studentAlumniRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        studentAlumniRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //layoutChangeListenerOnLayoutChange(recyclerViewSlider);
                RecyclerViewSliderEffect.layoutChangeListenerOnLayoutChange(studentAlumniRecyclerView);
            }
        });
    }

    class StudentAlumniAdapter extends RecyclerView.Adapter<StudentAlumniAdapter.StudentAlumniViewHolder> {

        @Override
        public StudentAlumniViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_3_1_rec_students_alumni, parent, false);
            return new StudentAlumniViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StudentAlumniViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class StudentAlumniViewHolder extends RecyclerView.ViewHolder {

            StudentAlumniViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

}
