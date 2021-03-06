package com.jvjplus.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchResultAdapter extends ArrayAdapter<BookDetails> {

    private Context context;
    boolean showMultiColors;
    public SearchResultAdapter(Context context, ArrayList<BookDetails> objects, boolean showMultiColors) {
        super(context, 0, objects);
        this.showMultiColors=showMultiColors;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BookDetails book = getItem(position);

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
        String authorsText = "";
        if(book.authors!=null) {
            authorsText += book.authors.get(0);
            if(book.authors.size() > 1)
                authorsText += ", " + book.authors.get(1);
        }
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
        bg="#B3"+bg.substring(1); //set opacity to 50%
        convertView.findViewById(R.id.layout_container).setBackgroundColor(Color.parseColor(bg));

//        Add Image
        ImageView imgView=(ImageView) convertView.findViewById(R.id.image);
        String imageUrl=book.smallThumbnailLink;
        imageUrl=imageUrl.replaceAll("http:","https:");
//        Log.d("URL",imageUrl);
        Glide.with(getContext())
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.image_not_found_error)
                .into(imgView);


//        Add Buy and Preview Links
        Button previewBtn = (Button) convertView.findViewById(R.id.preview_btn);
        final String previewUrl=book.previewLink.replaceAll("http:","https:");

        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(previewUrl));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        Button buyBtn = (Button) convertView.findViewById(R.id.get_btn);
        if(book.saleability==true) {
            final String buyUrl = book.buyLink.replaceAll("http:", "https:");
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(buyUrl));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
        else {
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "This book is not available for sale.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }

    String getBackgroundColor(int ratings) {
        if(showMultiColors==false)
            return "#07A197";

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