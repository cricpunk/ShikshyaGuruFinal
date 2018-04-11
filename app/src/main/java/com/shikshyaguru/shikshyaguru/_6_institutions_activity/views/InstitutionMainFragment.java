package com.shikshyaguru.shikshyaguru._6_institutions_activity.views;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionMainFragment extends Fragment implements InstitutionMainInterface {

    private int category;
    private String title;

    private LayoutInflater inflater;
    private RecyclerView instRecyclerView;
    private InstitutionsController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout._6_1_0_ihp_main_fragment, container, false);

        this.inflater = inflater;
        this.instRecyclerView = view.findViewById(R.id.rec_inst_home_main_frag_inst_items);

        if (getArguments() != null ) {
            this.category = getArguments().getInt("CATEGORY");
            this.title = getArguments().getString("TITLE");
        }

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), title);
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = new InstitutionsController(this, new InstitutionDataSource());
        controller.fetchInstitutionList(category);
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
    public void setUpInstitutionAdapter(FirebaseRecyclerOptions<InstitutionsListItemParent> options) {
        instRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        InstitutionAdapter institutionAdapter = new InstitutionAdapter(options);
        instRecyclerView.setAdapter(institutionAdapter);
        instRecyclerView.setHasFixedSize(true);

        institutionAdapter.startListening();
    }

    @Override
    public void openInstitutionDetails(InstitutionsListItemParent instDetails, ActivityOptions options) {

        String slogan = null;

        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "institutions_loader");
        intent.putExtra("ID", instDetails.getId());
        intent.putExtra("IMAGE", instDetails.getIcon_image());
        intent.putExtra("NAME", instDetails.getName());
        intent.putExtra("PLACE", instDetails.getCity());

        //noinspection ConstantConditions
        intent.putExtra("SLOGAN", slogan);

        startActivity(intent, options.toBundle());
    }

    private class InstitutionAdapter extends FirebaseRecyclerAdapter<InstitutionsListItemParent, InstitutionAdapter.InstitutionViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */

        InstitutionAdapter(@NonNull FirebaseRecyclerOptions<InstitutionsListItemParent> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull InstitutionAdapter.InstitutionViewHolder holder, int position, @NonNull InstitutionsListItemParent model) {
            //holder.institutionIcon.setImageResource(model.getIcon_image());
            holder.institutionName.setText(model.getName());
            holder.institutionRating.setText(model.getRating());
            holder.institutionCityName.setText(model.getCity());
            Picasso.get()
                    .load(model.getIcon_image())
                    .placeholder(R.drawable.logo)
                    .into(holder.institutionIcon);

            holder.instDetails = model;

        }

        @NonNull
        @Override
        public InstitutionAdapter.InstitutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_1_1_rec_inst_items, parent, false);
            return new InstitutionAdapter.InstitutionViewHolder(view);
        }

        class InstitutionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ViewGroup container;
            private ImageView institutionIcon;
            private TextView institutionName;
            private ImageView institutionMore;
            private TextView institutionRating;
            private TextView institutionCityName;

            private InstitutionsListItemParent instDetails;

            InstitutionViewHolder(View itemView) {
                super(itemView);

                container = itemView.findViewById(R.id.root_institutions);
                institutionIcon = itemView.findViewById(R.id.iv_institutions_icon);
                institutionName = itemView.findViewById(R.id.lbl_institutions_name);
                institutionMore = itemView.findViewById(R.id.iv_institutions_more);
                institutionRating = itemView.findViewById(R.id.lbl_institutions_rating);
                institutionCityName = itemView.findViewById(R.id.lbl_institutions_city_name);

                container.setOnClickListener(this);
                institutionMore.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.root_institutions:

                        Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(institutionIcon, "institutionImage");
                        pairs[1] = new Pair<View, String>(institutionName, "institutionName");
//                        pairs[2] = new Pair<View, String>(institutionCityName, "institutionPlace");

                        //noinspection unchecked
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);

                        controller.onInstitutionItemClick(instDetails, options);

                        break;
                    case R.id.iv_institutions_more:

                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

                        break;
                    default:break;
                }

            }
        }

    }

}
