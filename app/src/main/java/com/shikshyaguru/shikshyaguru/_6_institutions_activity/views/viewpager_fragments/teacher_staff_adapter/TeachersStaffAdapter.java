package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.teacher_staff_adapter;
/*
 * Created by Pankaj Koirala on 10/25/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shikshyaguru.shikshyaguru.R;

public class TeachersStaffAdapter extends RecyclerView.Adapter<TeachersStaffAdapter.TeachersStaffViewHolder> {

    @Override
    public TeachersStaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return null;
    }

    @Override
    public void onBindViewHolder(TeachersStaffViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class TeachersStaffViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;
        public TextView profession;
        View subject;
        View phone;
        View email;
        public ImageView subjectContentIcon;
        public ImageView phoneContentIcon;
        public ImageView emailContentIcon;
        public TextView subjectContent1;
        public TextView subjectContent2;
        public TextView phoneContent1;
        public TextView phoneContent2;
        public TextView emailContent1;
        public TextView emailContent2;
        public TextView rating;
        protected ImageView more;
        public TextView counter;

        public TeachersStaffViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.iv_teachers_business_card_image);
            name = (TextView) itemView.findViewById(R.id.lbl_teachers_business_card_name);
            profession = (TextView) itemView.findViewById(R.id.lbl_teachers_business_card_profession);

            subject = itemView.findViewById(R.id.inc_teachers_business_card_subject);
            subjectContentIcon = (ImageView) subject.findViewById(R.id.iv_teachers_business_card_content_icon);
            subjectContent1 = (TextView) subject.findViewById(R.id.lbl_teachers_business_card_content1);
            subjectContent2 = (TextView) subject.findViewById(R.id.lbl_teachers_business_card_content2);

            phone = itemView.findViewById(R.id.inc_teachers_business_card_phone);
            phoneContentIcon = (ImageView) phone.findViewById(R.id.iv_teachers_business_card_content_icon);
            phoneContent1 = (TextView) phone.findViewById(R.id.lbl_teachers_business_card_content1);
            phoneContent2 = (TextView) phone.findViewById(R.id.lbl_teachers_business_card_content2);

            email = itemView.findViewById(R.id.inc_teachers_business_card_email);
            emailContentIcon = (ImageView) email.findViewById(R.id.iv_teachers_business_card_content_icon);
            emailContent1 = (TextView) email.findViewById(R.id.lbl_teachers_business_card_content1);
            emailContent2 = (TextView) email.findViewById(R.id.lbl_teachers_business_card_content2);

            rating = (TextView) itemView.findViewById(R.id.lbl_teachers_business_card_rating);
            counter = (TextView) itemView.findViewById(R.id.lbl_vp_teachers_business_card_counter);
            more = (ImageView) itemView.findViewById(R.id.iv_teachers_business_card_more);

        }

    }

}
