package com.jvjplus.booklistingapp;

import android.app.Activity;
import android.content.Context;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResultAdapter extends ArrayAdapter<BookDetails> {

    public SearchResultAdapter(Context context, ArrayList<BookDetails> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BookDetails book = getItem(position);

        Log.e("Title: ",book.title);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout, parent, false);
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.layout, parent, false);
        }

        // Lookup view for data population
        TextView title=(TextView) convertView.findViewById(R.id.title);
        title.setText(book.title);

        TextView authors=(TextView)convertView.findViewById(R.id.authors);
        authors.setText(book.authors.get(0));
//        Add other authors too
//        Check if available!

        TextView publishers=(TextView)convertView.findViewById(R.id.publisher);
        publishers.setText(book.publisher);

        TextView publishedDate=(TextView)convertView.findViewById(R.id.published_date);
        publishedDate.setText(book.publishDate);

        TextView tags=(TextView)convertView.findViewById(R.id.tags);
        if(book.categories!=null)
            tags.setText("Tags: "+book.categories.get(0));
        else
            tags.setVisibility(View.INVISIBLE);

        TextView description=(TextView)convertView.findViewById(R.id.description);
        description.setText(book.description);

        TextView noOfPages=(TextView)convertView.findViewById(R.id.noOfPages);
        noOfPages.setText("Number of pages: "+book.pageCount);

        RatingBar rating=(RatingBar)convertView.findViewById(R.id.ratings);
        rating.setRating((float) book.avgRating);

        TextView ratingsCnt=(TextView)convertView.findViewById(R.id.noOfRatings);
        ratingsCnt.setText(book.ratingsCount+" Votes");

        TextView cost=(TextView)convertView.findViewById(R.id.cost);
        cost.setText("Rs "+book.amount);

//        Handle Buttons Buy and Preview

        return convertView;
    }
}