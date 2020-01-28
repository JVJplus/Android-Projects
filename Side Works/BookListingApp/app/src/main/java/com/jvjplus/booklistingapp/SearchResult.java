package com.jvjplus.booklistingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchResult extends AppCompatActivity {
    ProgressBar progressBar;
    Intent intent;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        intent = getIntent();
        progressBar = (ProgressBar) findViewById(R.id.progress);
        query = intent.getStringExtra("query");
        getSupportActionBar().setTitle("Results for: " + query);

        LoadDatas datas = new LoadDatas(query);
        datas.execute();

        ListView yourListView = (ListView) findViewById(R.id.result_container);

        // get data from the table by the ListAdapter
        SearchResultAdapter customAdapter = new SearchResultAdapter(this, new ArrayList<BookDetails>());

        yourListView .setAdapter(customAdapter);
    }

    //    LoadDatas.java
    public class LoadDatas extends AsyncTask<Void, Integer, String> {

        int maxResult = 8, bookDetailsAvailable = maxResult;
        String query;

        public LoadDatas(String query) {
            this.query = query;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            progressBar.setMax(100);
        }

        @Override
        protected void onPostExecute(String jsonData) {
            //        Update Results

            progressBar.setVisibility(View.GONE);

            updateAdapter(extractFeaturesFromJSON(jsonData));
        }

        private void updateAdapter(ArrayList<BookDetails> bookDetails){
//            Toast.makeText(SearchResult.this, bookDetailsAvailable+" Books Found! and length: "+bookDetails.size(), Toast.LENGTH_SHORT).show();

            ListView yourListView = (ListView) findViewById(R.id.result_container);
            // get data from the table by the ListAdapter
            SearchResultAdapter customAdapter = new SearchResultAdapter(getApplicationContext(),bookDetails);
            yourListView.setAdapter(customAdapter);
        }

        private ArrayList<BookDetails> extractFeaturesFromJSON(String jsonData) {
            ArrayList<BookDetails> booksDetails = new ArrayList<BookDetails>();
            try {
                JSONObject jsonObj = new JSONObject(jsonData);
                JSONArray items = jsonObj.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    BookDetails singleItem = new BookDetails();

                    try {
                        JSONObject itemsObject = items.getJSONObject(i);
                        JSONObject volumeInfo = itemsObject.getJSONObject("volumeInfo");
                        singleItem.title = volumeInfo.getString("title");

                        try {
                            JSONArray authors = volumeInfo.getJSONArray("authors");
                            singleItem.authors = new ArrayList<String>();
                            for (int j = 0; j < authors.length(); j++) {
                                singleItem.authors.add(authors.getString(j));
                            }
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }

                        try {
                            JSONArray categories = volumeInfo.getJSONArray("categories");
                            singleItem.categories = new ArrayList<String>();
                            for (int j = 0; j < categories.length(); j++) {
                                singleItem.categories.add(categories.getString(j));
                            }
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }

                        try {
                            singleItem.publisher = volumeInfo.getString("publisher");
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }
                        try {
                            singleItem.publishDate = volumeInfo.getString("publishedDate");
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }
                        try {
                            singleItem.description = volumeInfo.getString("description");
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }

                        try {
                            singleItem.language = volumeInfo.getString("language");
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }

                        try {
                            singleItem.avgRating = volumeInfo.getDouble("averageRating");
                            singleItem.ratingsCount = volumeInfo.getDouble("ratingsCount");
                        } catch (JSONException e) {
                            //e.printStackTrace();
                        }

                        try {
                            singleItem.pageCount = volumeInfo.getInt("pageCount");
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }

                        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                        singleItem.smallThumbnailLink = imageLinks.getString("smallThumbnail");
                        singleItem.thumbnailLink = imageLinks.getString("thumbnail");

                        singleItem.previewLink = volumeInfo.getString("previewLink");
                        singleItem.infoLink = volumeInfo.getString("infoLink");

                        JSONObject saleInfo = itemsObject.getJSONObject("saleInfo");
                        singleItem.saleability = saleInfo.getString("saleability").matches("FOR_SALE");

                        if (singleItem.saleability) {
                            JSONObject retailPrice = saleInfo.getJSONObject("retailPrice");
                            singleItem.amount = retailPrice.getDouble("amount");
                            singleItem.buyLink = saleInfo.getString("buyLink");
                        } else {
                            singleItem.amount = -1;
                        }

//                        Finally Add Item In container
                        booksDetails.add(singleItem);
                    } catch (Exception e) {
                        e.printStackTrace();
                        bookDetailsAvailable--;
                        Toast.makeText(SearchResult.this, "Issues In extracting API " + i, Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(SearchResult.this, "Errors on API!", Toast.LENGTH_SHORT).show();
            }
            return booksDetails;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
//            TODO: Find How To Find Current Progress???
        }


        @Override
        protected String doInBackground(Void... query) {
            String api = generateAPI(this.query);
            String json = downloadJSON(api);
            return json;
        }

        private String downloadJSON(String apiUrl) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiUrl);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        String generateAPI(String query) {
            String temp = "", queryWithPlusSigns = "";
            query += " ";
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == ' ') {
                    //Remove unnecessary spaces
                    while (i < query.length() && query.charAt(i) == ' ') i++;
                    i--;
                    queryWithPlusSigns += temp + "+";
                    temp = "";
                } else {
                    temp += query.charAt(i);
                }
            }
//        Remove Last + sign
            queryWithPlusSigns = queryWithPlusSigns.substring(0, queryWithPlusSigns.length() - 1);

            String url = "https://www.googleapis.com/books/v1/volumes?q=" + queryWithPlusSigns + "&maxResults=" + maxResult;

            Log.e("generatedAPI:", url);

            return url;
        }
    }


}
