package com.shikshyaguru.shikshyaguru._5_news_activity.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.squareup.picasso.Picasso;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class NewsLoaderFragment extends Fragment implements View.OnClickListener{

    private String image, heading, news, place, writer, time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null ) {

            image = getArguments().getString("IMAGE");
            heading = getArguments().getString("HEADING");
            news = getArguments().getString("NEWS");
            place = getArguments().getString("PLACE");
            writer = getArguments().getString("WRITER");
            time = getArguments().getString("TIME");

        }

        return inflater.inflate(R.layout._5_2_nhp_news_loader_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);
    }

    private void initComponents(View view) {

        ImageView ivNews = view.findViewById(R.id.iv_news_headline_icon);
        TextView lblHeading = view.findViewById(R.id.lbl_news_heading);
        TextView lblNews = view.findViewById(R.id.lbl_news_news_content);
        TextView lblPlace = view.findViewById(R.id.lbl_news_from);
        TextView lblWriter = view.findViewById(R.id.lbl_news_writer);
        TextView lblTime = view.findViewById(R.id.lbl_news_post_time);
        ImageView moreIcon = view.findViewById(R.id.iv_news_headline_more);

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.logo_for_news)
                .into(ivNews);

        lblHeading.setText(heading);
        lblNews.setText(news);
        lblPlace.setText(place);
        lblWriter.setText(writer);
        lblTime.setText("3m |");

        moreIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

    }

}
