package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionStaffData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPStaffController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.teacher_staff_adapter.TeachersStaffAdapter;

import java.util.List;

public class ViewPagerStaffFragment extends Fragment implements ViewPagerStaffInterface {

    private View rootView;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_7_0_view_pager_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rootView = view;
        new VPStaffController(this, new InstitutionFakeDataSource());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUpStaffList(List<InstitutionStaffData> institutionStaffData) {
        RecyclerView recyclerView = rootView.findViewById(R.id.rec_staff);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StaffAdapter adapter = new StaffAdapter();
        recyclerView.setAdapter(adapter);
    }

    private class StaffAdapter extends TeachersStaffAdapter {

        @Override
        public TeachersStaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_6_1_teachers_business_card, parent, false);
            return new StaffViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(TeachersStaffViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            holder.image.setImageResource(R.drawable.me);
            holder.name.setText("Roshan Koirala");
            holder.profession.setText("Accountant");

            holder.subjectContentIcon.setImageResource(R.drawable.ic_mind);
            holder.phoneContentIcon.setImageResource(R.drawable.ic_phone);
            holder.phoneContentIcon.setRotation(270);
            holder.emailContentIcon.setImageResource(R.drawable.ic_website);

            holder.subjectContent1.setText("Accounting");
            holder.subjectContent2.setText("Finance");
            //holder.subjectContent2.setVisibility(View.GONE);

            holder.phoneContent1.setText("977 0123-456-789");
            holder.phoneContent2.setText("977 0123-456-789");

            holder.emailContent1.setText("city1c116039@islingtoncollege.edu.np");
            //holder.emailContent2.setText("www.playsof.com");
            holder.emailContent2.setVisibility(View.GONE);
            holder.rating.setVisibility(View.GONE);
            holder.counter.setText(String.valueOf(position + 1));
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        private class StaffViewHolder extends TeachersStaffViewHolder implements View.OnClickListener{

            StaffViewHolder(View itemView) {
                super(itemView);
                more.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(getActivity(), R.style.darkPopup);
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
                            case R.id.teachers_card_popup_rate:
                                Toast.makeText(getContext(), "Rate", Toast.LENGTH_SHORT).show();
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
