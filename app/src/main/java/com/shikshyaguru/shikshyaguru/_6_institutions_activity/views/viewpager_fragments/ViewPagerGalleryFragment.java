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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionGalleryData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPGalleryController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsLoaderFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerGalleryFragment extends Fragment implements ViewPagerGalleryInterface{

    private LayoutInflater inflater;
    private View rootView;
    private VPGalleryController controller;
    private InstitutionGalleryData galleryData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._6_2_5_0_view_pager_gallery, container, false);
        this.inflater = inflater;
        this.rootView = view;

        controller = new VPGalleryController(this, new InstitutionFakeDataSource());
        controller.setUpGalleryCategory(InstitutionsLoaderFragment.id);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpAnchorView();
    }

    private void setUpAnchorView() {

        RelativeLayout mainLayout = rootView.findViewById(R.id.rl_vp_gallery_main);
        View anchorView = rootView.findViewById(R.id.v_vp_gallery_anchor);

        mainLayout.measure(View.MeasureSpec.makeMeasureSpec(mainLayout.getLayoutParams().width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        anchorView.getLayoutParams().height = mainLayout.getMeasuredHeight();

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) anchorView.getLayoutParams();
        layoutParams.setMargins(100, 0, 100, 0);
        anchorView.setLayoutParams(layoutParams);

    }

    @Override
    public void setUpGalleryCategory(FirebaseRecyclerOptions<InstitutionGalleryData> options) {

        RecyclerView galleryCategoryRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_gallery);
        galleryCategoryRecyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        galleryCategoryRecyclerView.setLayoutManager(layoutManager);
        GalleryCategoryAdapter adapter = new GalleryCategoryAdapter(options);
        galleryCategoryRecyclerView.setAdapter(adapter);

        adapter.startListening();
    }

    @Override
    public void onGalleryCategoryClick(String category, ArrayList<String> images, ArrayList<String> desc, ArrayList<String> ids) {
        Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "gallery_loader");
        intent.putExtra("CATEGORY", category);
        intent.putStringArrayListExtra("IMAGES", images);
        intent.putStringArrayListExtra("DESCRIPTION", desc);
        intent.putStringArrayListExtra("IDS", ids);
        startActivity(intent);

    }

    private class GalleryCategoryAdapter extends FirebaseRecyclerAdapter<InstitutionGalleryData, GalleryCategoryAdapter.GalleryCategoryViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        GalleryCategoryAdapter(@NonNull FirebaseRecyclerOptions<InstitutionGalleryData> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull GalleryCategoryViewHolder holder, int position, @NonNull InstitutionGalleryData model) {

            ViewGroup.MarginLayoutParams rootLP = (ViewGroup.MarginLayoutParams) holder.rootView.getLayoutParams();
            ViewGroup.MarginLayoutParams categoryBgLP = (ViewGroup.MarginLayoutParams) holder.categoryBg.getLayoutParams();
            ViewGroup.MarginLayoutParams imageBgLP = (ViewGroup.MarginLayoutParams) holder.imageBg.getLayoutParams();

            if (position % 2 == 0) {
                rootLP.setMargins(0, 0, 150, 40);
                categoryBgLP.setMargins(0,10,10,10);
                imageBgLP.setMargins(116,0,0,0);
                holder.rootView.setLayoutParams(rootLP);
                holder.categoryBg.setLayoutParams(categoryBgLP);
                holder.imageBg.setLayoutParams(imageBgLP);
            } else {
                rootLP.setMargins(150, 0, 0, 40);
                categoryBgLP.setMargins(10,10,0,10);
                imageBgLP.setMargins(0,0,116,0);
                holder.rootView.setLayoutParams(rootLP);
                holder.categoryBg.setLayoutParams(categoryBgLP);
                holder.imageBg.setLayoutParams(imageBgLP);
            }

            // Category name of the current index
            String category = (new ArrayList<>(model.getCategoryWithImages().keySet())).get(position);
            // First image from the array to display in category view
            String image = model.getCategoryWithImages().get(category).get(0);

            // Images, descriptions and ids of the current category
            // This will be transferred to gallery loader fragment trough intent
            holder.images = model.getCategoryWithImages().get(category);
            holder.desc = model.getCategoryWithImages().get(category);
            holder.ids = model.getCategoryWithImages().get(category);
            holder.category = category;

            Picasso.get()
                    .load(image)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.logo_for_news)
                    .into(holder.categoryBg);

            holder.categoryName.setText(category);
            holder.categoryTotalImage.setText(String.valueOf(model.getCategoryWithImages().get(category).size()));


        }

        @NonNull
        @Override
        public GalleryCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_5_1_view_pager_gallery_category, parent, false);
            return new GalleryCategoryViewHolder(view);
        }

        class GalleryCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private RelativeLayout rootView;
            private View imageBg;
            private ImageView categoryBg;
            private TextView categoryName;
            private TextView categoryTotalImage;

            String category;
            ArrayList<String> images;
            ArrayList<String> desc;
            ArrayList<String> ids;

            GalleryCategoryViewHolder(View itemView) {
                super(itemView);

                rootView = itemView.findViewById(R.id.root_vp_gallery_rec_item);
                imageBg = itemView.findViewById(R.id.v_inst_loader_vp_gallery_category_bg);
                categoryBg = itemView.findViewById(R.id.iv_inst_loader_vp_gallery_category_bg);
                categoryName = itemView.findViewById(R.id.lbl_inst_loader_vp_gallery_category_name);
                categoryTotalImage = itemView.findViewById(R.id.lbl_inst_loader_vp_gallery_category_total_image);
                rootView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                //controller.onGalleryCategoryClick((String) categoryName.getText());
                controller.onGalleryCategoryClickNew(category, images, desc, ids);
            }
        }

    }



}
