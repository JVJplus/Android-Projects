package com.jvjplus.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xw.repo.BubbleSeekBar;

import net.igenius.customcheckbox.CustomCheckBox;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    String query;
    SearchView searchView;
    Button searchBtn;
    Button random_btn;
    boolean showMultiColors=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Exit App Not Working????
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchBtn = (Button) findViewById(R.id.search_btn);
        random_btn = (Button) findViewById(R.id.random_btn);

        multicolor_checkbox();
        randomButton();
        addListeners();
    }

    private void randomButton() {
        random_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Generate Random Word
                String randomWord=RandomWordGenerator.getRandom();
                searchView.setQuery(randomWord,false);
            }
        });
    }

    private void multicolor_checkbox() {
        final CustomCheckBox checkbox=(CustomCheckBox)findViewById(R.id.multicolor_checkbox);
        checkbox.setChecked(true,true);

//        checkbox.setOnCheckedChangeListener(new CustomCheckBox.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CustomCheckBox checkBox, boolean isChecked) {
//                showMultiColors=isChecked;
//            }
//        });

        LinearLayout ll=(LinearLayout)findViewById(R.id.checkbox_ll);
        ll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(showMultiColors==true){
                    checkbox.setChecked(false,true);
                    showMultiColors=!showMultiColors;
                }else{
                    checkbox.setChecked(true,true);
                    showMultiColors=!showMultiColors;
                }
            }
        });
    }

    private void addListeners() {
//      searchView.setQuery("java programming", false);
        searchView.setOnQueryTextListener(this);
        searchBtn.setOnClickListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query = query;
        startIntent();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    @Override
    public void onClick(View v) {
        query = searchView.getQuery().toString();
//        Log.e("Button se",query);
        startIntent();
    }

    public void startIntent() {
        Intent intent = new Intent(this, SearchResult.class);
        intent.putExtra("query", query.trim());

        BubbleSeekBar bar = (BubbleSeekBar) findViewById(R.id.noOfResultsSeekBar);
        int noOfResults=bar.getProgress();

        intent.putExtra("noOfResults", noOfResults);
        intent.putExtra("showMultiColors",showMultiColors);
        startActivity(intent);
    }
}