package com.jvjplus.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerToSearch();
    }

    private void addListenerToSearch() {
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
//        TODO: Remove Below Line Later!
        searchView.setQuery("flowers home",false);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query=query;

        Intent intent=new Intent(this,SearchResult.class);
        intent.putExtra("query", query);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}