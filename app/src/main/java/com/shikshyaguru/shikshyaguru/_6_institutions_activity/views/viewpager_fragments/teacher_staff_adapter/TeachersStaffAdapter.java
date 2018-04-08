//package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.teacher_staff_adapter;
///*
// * Created by Pankaj Koirala on 10/25/2017.
// * Kathmandu, Nepal
// * Koiralapankaj007@gmail.com
// */
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.shikshyaguru.shikshyaguru.R;
//
//public class TeachersStaffAdapter extends RecyclerView.Adapter<TeachersStaffAdapter.TeachersStaffViewHolder> {
//
//    @Override
//    public TeachersStaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//       return null;
//    }
//
//    @Override
//    public void onBindViewHolder(TeachersStaffViewHolder holder, int position) {
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    protected class TeachersStaffViewHolder extends RecyclerView.ViewHolder {
//
//        public ImageView image;
//        public TextView name;
//        public TextView profession;
//        View subject;
//        View phone;
//        View email;
//        public ImageView subjectContentIcon;
//        public ImageView phoneContentIcon;
//        public ImageView emailContentIcon;
//        public TextView subjectContent1;
//        public TextView subjectContent2;
//        public TextView phoneContent1;
//        public TextView phoneContent2;
//        public TextView emailContent1;
//        public TextView emailContent2;
//        public TextView rating;
//        protected ImageView more;
//        public TextView counter;
//
//        public TeachersStaffViewHolder(View itemView) {
//            super(itemView);
//
//            image = (ImageView) itemView.findViewById(R.id.iv_teachers_business_card_image);
//            name = (TextView) itemView.findViewById(R.id.lbl_teachers_business_card_name);
//            profession = (TextView) itemView.findViewById(R.id.lbl_teachers_business_card_profession);
//
//            subject = itemView.findViewById(R.id.inc_teachers_business_card_subject);
//            subjectContentIcon = (ImageView) subject.findViewById(R.id.iv_teachers_business_card_content_icon);
//            subjectContent1 = (TextView) subject.findViewById(R.id.lbl_teachers_business_card_content1);
//            subjectContent2 = (TextView) subject.findViewById(R.id.lbl_teachers_business_card_content2);
//
//            phone = itemView.findViewById(R.id.inc_teachers_business_card_phone);
//            phoneContentIcon = (ImageView) phone.findViewById(R.id.iv_teachers_business_card_content_icon);
//            phoneContent1 = (TextView) phone.findViewById(R.id.lbl_teachers_business_card_content1);
//            phoneContent2 = (TextView) phone.findViewById(R.id.lbl_teachers_business_card_content2);
//
//            email = itemView.findViewById(R.id.inc_teachers_business_card_email);
//            emailContentIcon = (ImageView) email.findViewById(R.id.iv_teachers_business_card_content_icon);
//            emailContent1 = (TextView) email.findViewById(R.id.lbl_teachers_business_card_content1);
//            emailContent2 = (TextView) email.findViewById(R.id.lbl_teachers_business_card_content2);
//
//            rating = (TextView) itemView.findViewById(R.id.lbl_teachers_business_card_rating);
//            counter = (TextView) itemView.findViewById(R.id.lbl_vp_teachers_business_card_counter);
//            more = (ImageView) itemView.findViewById(R.id.iv_teachers_business_card_more);
//
//        }
//
//    }
//
//}




//    private class TeachersAdapter extends TeachersStaffAdapter {
//
//        @Override
//        public TeachersStaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout._6_2_6_1_teachers_business_card, parent, false);
//            return new TeachersViewHolder(view);
//        }
//
//        @SuppressLint("SetTextI18n")
//        @Override
//        public void onBindViewHolder(TeachersStaffViewHolder holder, int position) {
//            super.onBindViewHolder(holder, position);
//            holder.image.setImageResource(R.drawable.me);
//            holder.name.setText("Pankaj Koirala");
//            holder.profession.setText("Lecturer / Tutor");
//
//            holder.subjectContentIcon.setImageResource(R.drawable.ic_mind);
//            holder.phoneContentIcon.setImageResource(R.drawable.ic_phone);
//            holder.phoneContentIcon.setRotation(270);
//            holder.emailContentIcon.setImageResource(R.drawable.ic_website);
//
//            holder.subjectContent1.setText("JAVA, PHP");
//            //holder.subjectContent2.setText("Web Development");
//            holder.subjectContent2.setVisibility(View.GONE);
//
//            holder.phoneContent1.setText("977 0123-456-789");
//            holder.phoneContent2.setText("977 0123-456-789");
//
//            holder.emailContent1.setText("koiralapankaj007@gmail.com");
//            holder.emailContent2.setText("www.playsof.com");
//
//            holder.rating.setText("4.5");
//            holder.counter.setText(String.valueOf(position + 1));
//        }
//
//        @Override
//        public int getItemCount() {
//            return 15;
//        }
//
//        private class TeachersViewHolder extends TeachersStaffViewHolder implements View.OnClickListener{
//
//            TeachersViewHolder(View itemView) {
//                super(itemView);
//                more.setOnClickListener(this);
//            }
//
//            @Override
//            public void onClick(View v) {
//                Context wrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.darkPopup);
//                PopupMenu popupMenu = new PopupMenu(wrapper, more, Gravity.END);
//                popupMenu.getMenuInflater().inflate(R.menu.teachers_business_card_popup, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.teachers_card_popup_visit_profile:
//                                Toast.makeText(getContext(), "Visit profile", Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.teachers_card_popup_send_message:
//                                Toast.makeText(getContext(), "Send message", Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.teachers_card_popup_rate:
//                                Toast.makeText(getContext(), "Rate", Toast.LENGTH_SHORT).show();
//                                break;
//                            default:
//                                break;
//
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        }
//    }



//    private class StaffAdapter extends TeachersStaffAdapter {
//
//        @Override
//        public TeachersStaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout._6_2_6_1_teachers_business_card, parent, false);
//            return new StaffViewHolder(view);
//        }
//
//        @SuppressLint("SetTextI18n")
//        @Override
//        public void onBindViewHolder(TeachersStaffViewHolder holder, int position) {
//            super.onBindViewHolder(holder, position);
//            holder.image.setImageResource(R.drawable.me);
//            holder.name.setText("Roshan Koirala");
//            holder.profession.setText("Accountant");
//
//            holder.subjectContentIcon.setImageResource(R.drawable.ic_mind);
//            holder.phoneContentIcon.setImageResource(R.drawable.ic_phone);
//            holder.phoneContentIcon.setRotation(270);
//            holder.emailContentIcon.setImageResource(R.drawable.ic_website);
//
//            holder.subjectContent1.setText("Accounting");
//            holder.subjectContent2.setText("Finance");
//            //holder.subjectContent2.setVisibility(View.GONE);
//
//            holder.phoneContent1.setText("977 0123-456-789");
//            holder.phoneContent2.setText("977 0123-456-789");
//
//            holder.emailContent1.setText("city1c116039@islingtoncollege.edu.np");
//            //holder.emailContent2.setText("www.playsof.com");
//            holder.emailContent2.setVisibility(View.GONE);
//            holder.rating.setVisibility(View.GONE);
//            holder.counter.setText(String.valueOf(position + 1));
//        }
//
//        @Override
//        public int getItemCount() {
//            return 10;
//        }
//
//        private class StaffViewHolder extends TeachersStaffViewHolder implements View.OnClickListener{
//
//            StaffViewHolder(View itemView) {
//                super(itemView);
//                more.setOnClickListener(this);
//            }
//
//            @Override
//            public void onClick(View v) {
//                Context wrapper = new ContextThemeWrapper(getActivity(), R.style.darkPopup);
//                PopupMenu popupMenu = new PopupMenu(wrapper, more, Gravity.END);
//                popupMenu.getMenuInflater().inflate(R.menu.teachers_business_card_popup, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.teachers_card_popup_visit_profile:
//                                Toast.makeText(getContext(), "Visit profile", Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.teachers_card_popup_send_message:
//                                Toast.makeText(getContext(), "Send message", Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.teachers_card_popup_rate:
//                                Toast.makeText(getContext(), "Rate", Toast.LENGTH_SHORT).show();
//                                break;
//                            default:
//                                break;
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        }
//    }
