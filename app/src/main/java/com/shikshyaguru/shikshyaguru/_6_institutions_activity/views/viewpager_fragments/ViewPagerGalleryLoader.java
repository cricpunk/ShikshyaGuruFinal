package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_5_glide.GlideApp;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionGalleryData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;

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
    private String category;

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
        return inflater.inflate(R.layout.fragment_view_pager_gallery_loader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rootView = view;
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            category = getArguments().getString("CATEGORY");
        }

        Toolbar toolbar = view.findViewById(R.id.tb_inst_loader_vp_Gallery_image_loader);
        Toolbars.setUpToolbar(toolbar, activity, category);

        new InstitutionsController(this, new InstitutionFakeDataSource());

    }

    @Override
    public void setUpGallery(InstitutionGalleryData galleryData) {
        this.galleryData = galleryData;
        RecyclerView galleryRecyclerView = rootView.findViewById(R.id.rec_inst_loader_vp_Gallery_image_loader);
        galleryRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        galleryRecyclerView.setLayoutManager(layoutManager);
        GalleryAdapter adapter = new GalleryAdapter();
        galleryRecyclerView.setAdapter(adapter);
    }

    class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.rec_image, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GalleryViewHolder holder, int position) {
            int image = (int) galleryData.getCategoryWithImages().get(category).get(position);
            GlideApp.with(context)
                    .load(image)
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return galleryData.getCategoryWithImages().get(category).size();
        }

        class GalleryViewHolder extends RecyclerView.ViewHolder {

            private ImageView image;

            GalleryViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.iv_rec_image);
            }

        }

    }

}
