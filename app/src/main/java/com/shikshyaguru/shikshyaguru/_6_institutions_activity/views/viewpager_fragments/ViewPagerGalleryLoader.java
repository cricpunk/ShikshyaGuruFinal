package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionGalleryData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPGalleryController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 4:23 PM 23 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class ViewPagerGalleryLoader extends Fragment implements ViewPagerGalleryLoaderInterface{

    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private View rootView;
    private InstitutionGalleryData galleryData;
    private VPGalleryController controller;
    private String category;
    private ArrayList<String> images, description, ids;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.context = getContext();

        assert activity != null;
        this.activity = getActivity();

        assert activity != null;
        Window window = activity.getWindow();

        StatusBar.changeStatusBarColor(context, window, R.color.black_toolbar);
        return inflater.inflate(R.layout._6_2_5_2_view_pager_gallery_loader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rootView = view;
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            category = getArguments().getString("CATEGORY");
            images = getArguments().getStringArrayList("IMAGES");
            description = getArguments().getStringArrayList("DESCRIPTION");
            ids = getArguments().getStringArrayList("IDS");
        }


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), category.substring(0,1).toUpperCase() + category.substring(1));
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);


        controller = new VPGalleryController(this, new InstitutionFakeDataSource());
        setUpGalleryAdapter();

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

    private void setUpGalleryAdapter() {

        RecyclerView galleryRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_Gallery_image_loader);
        galleryRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        galleryRecyclerView.setLayoutManager(layoutManager);
        GalleryAdapter adapter = new GalleryAdapter();
        galleryRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onImageClick(int position, ArrayList<String> images, ArrayList<String> desc, ArrayList<String> ids) {

        Intent intent = new Intent(context, InstitutionsHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "open_gallery_image");
        intent.putExtra("POSITION", position);
        intent.putStringArrayListExtra("IMAGES", images);
        intent.putStringArrayListExtra("DESCRIPTION", desc);
        intent.putStringArrayListExtra("IDS", ids);
        startActivity(intent);

    }

    class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

        @NonNull
        @Override
        public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_5_3_rec_gallery_loader_image, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

            Picasso.get()
                    .load(images.get(position))
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.logo_for_news)
                    .into(holder.image);

            holder.images = images;
            holder.description = description;
            holder.ids = ids;

        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ImageView image;

            private ArrayList<String> images;
            private ArrayList<String> description;
            private ArrayList<String> ids;

            GalleryViewHolder(View itemView) {
                super(itemView);

                //images = galleryData.getCategoryWithImages().get(category);

                image = itemView.findViewById(R.id.iv_rec_image);
                image.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                controller.onImageClick(getAdapterPosition(), images, description, ids);
            }
        }

    }

}
