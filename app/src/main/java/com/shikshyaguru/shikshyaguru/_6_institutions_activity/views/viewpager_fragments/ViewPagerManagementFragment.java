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

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionManagementData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPManagementController;

import java.util.List;

public class ViewPagerManagementFragment extends Fragment implements ViewPagerManagementInterface {

    private LayoutInflater inflater;
    private View rootView;
    private List<InstitutionManagementData> managementData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_4_0_view_pager_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rootView = view;
        new VPManagementController(this, new InstitutionFakeDataSource());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUpManagementAdapter(List<InstitutionManagementData> managementData) {
        this.managementData = managementData;
        RecyclerView mgmtRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_management);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        MgmtAdapter adapter = new MgmtAdapter();
        mgmtRecyclerView.setLayoutManager(layoutManager);
        mgmtRecyclerView.setAdapter(adapter);

    }

    class MgmtAdapter extends RecyclerView.Adapter<MgmtAdapter.MgmtViewHolder> {

        @Override
        public MgmtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_4_1_rec_management, parent, false);
            return new MgmtViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MgmtViewHolder holder, int position) {
            InstitutionManagementData currentItem = managementData.get(position);
            int messageId = Integer.parseInt(currentItem.getMessage());

            holder.image.setImageResource(currentItem.getImage());
            holder.message.setText(getContext().getResources().getString(messageId));
            // While loading data from database replace above line by below one
            //holder.message.setText(currentItem.getMessage());

            holder.name.setText(
                    String.valueOf(
                            currentItem.getName()
                                    + " (" + currentItem.getAcademicQualification()
                                    + ", " + currentItem.getInstitution()
                                    + ") "
                    )
            );
            holder.post.setText(currentItem.getPost());
        }

        @Override
        public int getItemCount() {
            return managementData.size();
        }

        class MgmtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ImageView image;
            private ImageView more;
            private TextView name;
            private TextView post;
            private TextView message;

            MgmtViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.iv_inst_loader_vp_management_mgmt_head);
                name = itemView.findViewById(R.id.lbl_inst_loader_vp_management_name);
                post = itemView.findViewById(R.id.lbl_inst_loader_vp_management_post);
                message = itemView.findViewById(R.id.lbl_inst_loader_vp_management_message);
                more = itemView.findViewById(R.id.iv_inst_loader_vp_management_more);
                more.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Context wrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.teachersBusinessCardPopup);
                PopupMenu popupMenu = new PopupMenu(wrapper, more, Gravity.END);
                popupMenu.getMenuInflater().inflate(R.menu.management_popup, popupMenu.getMenu());

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
