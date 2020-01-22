package com.jvjplus.booklistingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SearchResult extends AppCompatActivity {
    TextView search_tv;
    Intent intent;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        search_tv = (TextView) findViewById(R.id.search_tv);
        intent = getIntent();
        query = intent.getStringExtra("query");
        getSupportActionBar().setTitle("Results for :" + query);

//        TODO: from here
        search_tv.setText(query);
        LoadDatas datas = new LoadDatas(query);
        datas.execute();
    }


    //    LoadDatas.java
    public class LoadDatas extends AsyncTask<Void, Void, String> {

        int maxResult = 1;
        String query;

        public LoadDatas(String query) {
            this.query = query;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//        Show Progress Bar
        }

        @Override
        protected void onPostExecute(String jsonData) {
//        Hide Progress Bar
//        Update Results

            search_tv.setText(jsonData.toString());

            List<BookDetails> items[]=extractFeaturesFromJSON(jsonData);

        }

        private List<BookDetails>[] extractFeaturesFromJSON(String jsonData) {
            return new List[0];
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
//        Update Progress Bar
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

            Log.d("Search Result: ", "generateAPI: " + url);

            return url;
        }
    }


}
