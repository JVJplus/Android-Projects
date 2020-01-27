package com.jvjplus.booklistingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SearchResultAdapter extends ArrayAdapter<BookDetails> {

    public SearchResultAdapter(Activity context, ArrayList<BookDetails> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout, parent, false);
        }


//        Earthquake currentEarthquake = getItem(position);
//        Change Details Of Views

        return listItemView;
    }
}