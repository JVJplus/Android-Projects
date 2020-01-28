package com.jvjplus.booklistingapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

//        Log.e("Title: ", book.title);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout, parent, false);
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.layout, parent, false);
        }

        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(book.title);


        TextView authors = (TextView) convertView.findViewById(R.id.authors);
        String authorsText = book.authors.get(0);
        if (book.authors.size() > 1)
            authorsText += ", " + book.authors.get(1);
        authors.setText(authorsText);


        TextView publishers = (TextView) convertView.findViewById(R.id.publisher);
        publishers.setText(book.publisher);

        TextView publishedDate = (TextView) convertView.findViewById(R.id.published_date);
        publishedDate.setText(book.publishDate);

        TextView tags = (TextView) convertView.findViewById(R.id.tags);
        if (book.categories != null) {
            String tagsString = "Tags: ";
            for (int x = 0; x < book.categories.size() - 1; x++) {
                tagsString += book.categories.get(x) + ", ";
            }
            tagsString += book.categories.get(book.categories.size() - 1);
            tags.setText(tagsString);
        } else
            tags.setVisibility(View.INVISIBLE);

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(book.description);

        TextView noOfPages = (TextView) convertView.findViewById(R.id.noOfPages);
        if (book.pageCount == -1)
            noOfPages.setVisibility(View.INVISIBLE);
        else
            noOfPages.setText("Number of pages: " + book.pageCount);

        RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratings);
        rating.setRating((float) book.avgRating);

        TextView ratingsCnt = (TextView) convertView.findViewById(R.id.noOfRatings);
        if (book.ratingsCount == -1)
            ratingsCnt.setText("No Votes");
        else
            ratingsCnt.setText(book.ratingsCount + " Votes");

        TextView cost = (TextView) convertView.findViewById(R.id.cost);
        if (book.amount == -1)
//            cost.setText("Price Not Mentioned");
            cost.setVisibility(View.INVISIBLE);
        else
            cost.setText("Rs " + book.amount);

        String bg=getBackgroundColor((int) book.avgRating);
        convertView.findViewById(R.id.layout_container).setBackgroundColor(Color.parseColor(bg));
//        Handle Buttons Buy and Preview

        return convertView;
    }

    String getBackgroundColor(int ratings) {
        switch (ratings) {
            case 5:
                return "#12B448";
            case 4:
                return "#07A197";
            case 3:
                return "#68539E";
            case 2:
                return "#9E537C";
            case 1:
                return "#B77F80";
            default:
                return "#6AADEE";
        }
    }
}