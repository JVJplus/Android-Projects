package com.jvjplus.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    String query;
    SearchView searchView;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Exit App Not Working????
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchBtn=(Button)findViewById(R.id.search_btn);

        addListenerToSearch();
    }

    private void addListenerToSearch() {

//        TODO: Remove Below Line Later!
        searchView.setQuery("java programming",false);
        searchView.setOnQueryTextListener(this);
        searchBtn.setOnClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query=query;
        startIntent();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    @Override
    public void onClick(View v) {
        query=searchView.getQuery().toString();
        Log.e("Button se",query);
        startIntent();
    }

    public void startIntent(){
        Intent intent=new Intent(this,SearchResult.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }
}