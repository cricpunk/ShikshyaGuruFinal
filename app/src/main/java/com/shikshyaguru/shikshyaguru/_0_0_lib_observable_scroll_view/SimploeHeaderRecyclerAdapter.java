package com.shikshyaguru.shikshyaguru._0_0_lib_observable_scroll_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cricpunk on 8/15/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

class SimpleHeaderRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private LayoutInflater mInflater;
//    private ArrayList<String> mItems;
//    private View mHeaderView;

    SimpleHeaderRecyclerAdapter(Context context, ArrayList<String> items, View headerView) {
        mInflater = LayoutInflater.from(context);
//        mItems = items;
//        mHeaderView = headerView;
    }

    @Override
    public int getItemCount() {
//        if (mHeaderView == null) {
//            return mItems.size();
//        } else {
//            return mItems.size() + 1;
//        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return null;
            //new HeaderViewHolder(mHeaderView);
        } else {
            return new ItemViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        if (viewHolder instanceof ItemViewHolder) {
////            ((ItemViewHolder) viewHolder).textView.setText(mItems.get(position - 1));
//        }
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View view) {
            super(view);
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}

