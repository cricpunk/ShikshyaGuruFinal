package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

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

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ViewPagerProgrammesFeeStructure extends Fragment {

    private LayoutInflater inflater;
    private View rootView;

    private String level, faculty, programme, opt_sem;
    private List<HashMap<String, String>> fees;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._6_2_2_12_view_pager_programmes_fee_structure, container, false);
        this.inflater = inflater;
        this.rootView = view;

        if (getArguments() != null) {

            this.level = getArguments().getString("LEVEL_NAME");
            this.faculty = getArguments().getString("FACULTY_NAME");
            this.programme = getArguments().getString("COURSE_NAME");
            this.opt_sem = getArguments().getString("OPT_SEM");
            //noinspection unchecked
            this.fees = (List<HashMap<String, String>>) getArguments().getSerializable("FEE_LIST");

        }

        String title = opt_sem.toUpperCase()+" - Fee structure ";
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), title);
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);


        setUpFeeStructure();

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

    public void setUpFeeStructure() {
        RecyclerView programmesLevelRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_programmes_fee_structure);
        ProgrammesLevelAdapter adapter = new ProgrammesLevelAdapter();
        programmesLevelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        programmesLevelRecyclerView.setAdapter(adapter);
    }

    private class ProgrammesLevelAdapter extends RecyclerView.Adapter<ProgrammesLevelAdapter.ProgrammesLevelViewHolder> {

        @NonNull
        @Override
        public ProgrammesLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_2_11_fee_structure_item, parent, false);
            return new ProgrammesLevelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ProgrammesLevelViewHolder holder, int position) {

            HashMap<String, String> currentItem = fees.get(position);

            holder.feeName.setText(currentItem.get("type"));
            holder.amount.setText(currentItem.get("amount"));

        }

        @Override
        public int getItemCount() {
            return fees.size();
        }

        class ProgrammesLevelViewHolder extends RecyclerView.ViewHolder {

            private TextView feeName, amount;

            ProgrammesLevelViewHolder(View itemView) {
                super(itemView);

                this.feeName = itemView.findViewById(R.id.lbl_fee_name);
                this.amount = itemView.findViewById(R.id.lbl_amount);

            }


        }

    }


}
