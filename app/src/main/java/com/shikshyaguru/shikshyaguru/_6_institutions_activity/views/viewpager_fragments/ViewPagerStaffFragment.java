package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionStaffData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPStaffController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsLoaderFragment;
import com.squareup.picasso.Picasso;

public class ViewPagerStaffFragment extends Fragment implements ViewPagerStaffInterface {

    private View rootView;
    private LayoutInflater inflater;
    VPStaffController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_7_0_view_pager_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;
        controller = new VPStaffController(this, new InstitutionFakeDataSource());
        controller.setupStaffList(InstitutionsLoaderFragment.id);
    }

    @Override
    public void setUpStaffList(FirebaseRecyclerOptions<InstitutionStaffData> options) {

        RecyclerView recyclerView = rootView.findViewById(R.id.rec_staff);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StaffAdapter adapter = new StaffAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }

    private class StaffAdapter extends FirebaseRecyclerAdapter<InstitutionStaffData, StaffAdapter.StaffViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        StaffAdapter(@NonNull FirebaseRecyclerOptions<InstitutionStaffData> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull StaffViewHolder holder, int position, @NonNull InstitutionStaffData model) {

            holder.subjectContentIcon.setImageResource(R.drawable.ic_mind);
            holder.phoneContentIcon.setImageResource(R.drawable.ic_phone);
            holder.phoneContentIcon.setRotation(270);
            holder.emailContentIcon.setImageResource(R.drawable.ic_website);

            Picasso.get()
                    .load(model.getImage_url())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(holder.image);

            holder.name.setText(model.getFull_name());
            holder.profession.setText(model.getDesignation());
            holder.subjectContent1.setText(model.getExperiance());
            //holder.subjectContent2.setText("Web Development");
            holder.subjectContent2.setVisibility(View.GONE);

            holder.phoneContent1.setText(model.getPhone_1());
            if (!model.getPhone_2().equals("")) {
                holder.phoneContent2.setText(model.getPhone_2());
            } else {
                holder.phoneContent2.setVisibility(View.GONE);
            }

            holder.emailContent1.setText(model.getEmail_1());
            if (!model.getEmail_2().equals("")) {
                holder.emailContent2.setText(model.getEmail_2());
            } else {
                holder.emailContent2.setVisibility(View.GONE);
            }

            holder.rating.setVisibility(View.GONE);
            holder.counter.setText(String.valueOf(position + 1));

        }

        @NonNull
        @Override
        public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_6_1_teachers_business_card, parent, false);
            return new StaffViewHolder(view);
        }

        class StaffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ImageView image;
            private TextView name;
            private TextView profession;
            private View subject;
            private View phone;
            private View email;
            private ImageView subjectContentIcon;
            private ImageView phoneContentIcon;
            private ImageView emailContentIcon;
            private TextView subjectContent1;
            private TextView subjectContent2;
            private TextView phoneContent1;
            private TextView phoneContent2;
            private TextView emailContent1;
            private TextView emailContent2;
            private TextView rating;
            private ImageView more;
            private TextView counter;

            StaffViewHolder(View itemView) {
                super(itemView);

                image = itemView.findViewById(R.id.iv_teachers_business_card_image);
                name = itemView.findViewById(R.id.lbl_teachers_business_card_name);
                profession = itemView.findViewById(R.id.lbl_teachers_business_card_profession);

                subject = itemView.findViewById(R.id.inc_teachers_business_card_subject);
                subjectContentIcon = subject.findViewById(R.id.iv_teachers_business_card_content_icon);
                subjectContent1 = subject.findViewById(R.id.lbl_teachers_business_card_content1);
                subjectContent2 = subject.findViewById(R.id.lbl_teachers_business_card_content2);

                phone = itemView.findViewById(R.id.inc_teachers_business_card_phone);
                phoneContentIcon = phone.findViewById(R.id.iv_teachers_business_card_content_icon);
                phoneContent1 = phone.findViewById(R.id.lbl_teachers_business_card_content1);
                phoneContent2 = phone.findViewById(R.id.lbl_teachers_business_card_content2);

                email = itemView.findViewById(R.id.inc_teachers_business_card_email);
                emailContentIcon = email.findViewById(R.id.iv_teachers_business_card_content_icon);
                emailContent1 = email.findViewById(R.id.lbl_teachers_business_card_content1);
                emailContent2 = email.findViewById(R.id.lbl_teachers_business_card_content2);

                rating = itemView.findViewById(R.id.lbl_teachers_business_card_rating);
                counter = itemView.findViewById(R.id.lbl_vp_teachers_business_card_counter);
                more = itemView.findViewById(R.id.iv_teachers_business_card_more);

                more.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                Context wrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.darkPopup);
                PopupMenu popupMenu = new PopupMenu(wrapper, more, Gravity.END);
                popupMenu.getMenuInflater().inflate(R.menu.teachers_business_card_popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.teachers_card_popup_visit_profile:
                                Toast.makeText(getContext(), "Visit profile", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.teachers_card_popup_send_message:
                                Toast.makeText(getContext(), "Send message", Toast.LENGTH_SHORT).show();
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

}
