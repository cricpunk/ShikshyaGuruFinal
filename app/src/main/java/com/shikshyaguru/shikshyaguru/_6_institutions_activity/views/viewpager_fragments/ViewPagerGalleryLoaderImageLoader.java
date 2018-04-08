package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import android.annotation.SuppressLint;
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
import com.davemorrissey.labs.subscaleview.decoder.DecoderFactory;
import com.davemorrissey.labs.subscaleview.decoder.ImageDecoder;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.fenjuly.library.ArrowDownloadButton;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_5_picasso.PicassoDecoder;
import com.shikshyaguru.shikshyaguru._0_5_picasso.PicassoRegionDecoder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:18 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class ViewPagerGalleryLoaderImageLoader extends Fragment {

    private View rootView;
    private int clickedPosition;

    private LayoutInflater inflater;
    private ViewPager viewPager;
    private ImageView moreButton;
    private TextView imageCounter;

    private ArrayList<String> images, description, ids;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
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
            images = getArguments().getStringArrayList("IMAGES");
            description = getArguments().getStringArrayList("DESCRIPTION");
            ids = getArguments().getStringArrayList("IDS");
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
            SubsamplingScaleImageView imageView = view.findViewById(R.id.ivss_full_image);

            loadImageByUrl(imageView, images.get(position), new okhttp3.OkHttpClient());

            container.addView(view);
            return view;
        }

        void loadImageByUrl(SubsamplingScaleImageView scaleImageView, final String url, final okhttp3.OkHttpClient okHttpClient) {

            scaleImageView.setMaxScale(5.0f);
            final Picasso picasso = Picasso.get();

            scaleImageView.setBitmapDecoderFactory(new DecoderFactory<ImageDecoder>() {
                @NonNull
                @Override
                public ImageDecoder make() {

                    return new PicassoDecoder(url, picasso);
                }
            });

            scaleImageView.setRegionDecoderFactory(new DecoderFactory<ImageRegionDecoder>() {
                @NonNull
                @Override
                public ImageRegionDecoder make() {
                    return new PicassoRegionDecoder(okHttpClient);
                }
            });

            scaleImageView.setImage(ImageSource.uri(url));
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

            @SuppressLint("RestrictedApi") Context wrapper = new ContextThemeWrapper(getActivity(), R.style.defaultPopup);
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
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        progress = progress + 1;
//                        download.setProgress(progress);
//                    }
//                });
            }
        }, 800, 20);

        download.setVisibility(View.VISIBLE);

        download.startAnimating();
        hideDownloadIcon(download);
    }

    private void hideDownloadIcon(final ArrowDownloadButton download) {
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
