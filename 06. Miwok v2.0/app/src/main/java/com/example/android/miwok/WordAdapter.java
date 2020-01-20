package com.example.android.miwok;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> androidFlavors, int color) {
        super(context, 0, androidFlavors);
        mColorResourceId=color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView translationTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        translationTextView.setText(currentWord.getDefaultTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getMiwokTranslation());

        ImageView imageView= (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getmImageResourceId());
        }
        else{
            imageView.setVisibility(View.GONE);
        }


//        Why not direct mColorResourceId?

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}