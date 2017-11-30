package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.fenjuly.library.ArrowDownloadButton;
import com.shikshyaguru.shikshyaguru.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.google.android.gms.internal.zzagr.runOnUiThread;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:18 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class ViewPagerGalleryLoaderImageLoader extends Fragment {

    private Context context;
    private View rootView;
    private int clickedPosition;
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private ViewPager viewPager;
    private ImageView moreButton;
    private TextView imageCounter;

    private int progress = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.context = getContext();
        return inflater.inflate(R.layout._6_2_5_4_gallery_loader_image_swipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;

        initViewPager();
        viewPager.addOnPageChangeListener(viewPagerChangeListener);
        moreButton = view.findViewById(R.id.iv_image_more);
        moreButton.setOnClickListener(onMoreIconClick);

    }

    private void initViewPager() {

        if (getArguments() != null) {
            clickedPosition = getArguments().getInt("POSITION");
            images = getArguments().getIntegerArrayList("IMAGES");
        }

        viewPager = rootView.findViewById(R.id.vp_full_image_gallery);
        imageCounter = rootView.findViewById(R.id.lbl_full_image_gallery_counter);

        GalleryPagerAdapter adapter = new GalleryPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(clickedPosition);
        imageCounter.setText(getString(R.string.gallery_image_counter, clickedPosition + 1, images.size()));

    }

    private ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            imageCounter.setText(getString(R.string.gallery_image_counter, position + 1, images.size()));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    class GalleryPagerAdapter extends PagerAdapter {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view = inflater.inflate(R.layout._6_2_5_5_full_size_image, container, false);
            final SubsamplingScaleImageView imageView = view.findViewById(R.id.ivss_full_image);


//            GlideApp
//                    .with(context)
//                    .asBitmap()
//                    .load(images.get(position))
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                            imageView.setImage(ImageSource.bitmap(resource));
//                        }
//                    });

            imageView.setImage(ImageSource.resource(images.get(position)));

            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((RelativeLayout) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

    }


    private View.OnClickListener onMoreIconClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Context wrapper = new ContextThemeWrapper(getActivity(), R.style.defaultPopup);
            //PopupMenu popupMenu = new PopupMenu(wrapper, more, Gravity.END);

            PopupMenu popupMenu = new PopupMenu(wrapper, moreButton, Gravity.END);
            popupMenu.getMenuInflater().inflate(R.menu.image_popup, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.image_download:
                            ArrowDownloadButton download = rootView.findViewById(R.id.btn_image_download);
                            showDownloadIcon(download);
                            Toast.makeText(getContext(), "Download", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.image_share:
                            Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
    };

    private void showDownloadIcon(final ArrowDownloadButton download) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress = progress + 1;
                        download.setProgress(progress);
                    }
                });
            }
        }, 800, 20);

        download.setVisibility(View.VISIBLE);

        download.startAnimating();
        hideDownloadIcon(download);
    }

    private void hideDownloadIcon(final ArrowDownloadButton download) {
        progress = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                download.setVisibility(View.GONE);
            }
        }, 3500);
        resetDownloadIcon(download);
    }

    private void resetDownloadIcon(final ArrowDownloadButton download) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                download.reset();
            }
        }, 3500);
    }
}
