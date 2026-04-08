package com.example.sit305_4_1p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int colors[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] colors) {
        this.context = applicationContext;
        this.colors = colors;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        icon.setImageResource(colors[i]);
        return view;
    }
}