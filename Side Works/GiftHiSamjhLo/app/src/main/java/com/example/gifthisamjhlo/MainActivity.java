package com.example.gifthisamjhlo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int thanksButtonClickCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        handleThanksButton();
        handleSurpriseButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Happy Birthday Aur 1 Bar !B",Toast.LENGTH_LONG).show();
    }

    private void handleSurpriseButton() {
        final Button button = (Button) findViewById(R.id.surpriseButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SurpriseInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleThanksButton() {
        Button button = (Button) findViewById(R.id.thanksButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String reply="";
                if(thanksButtonClickCount==0) {
                    reply = "Mention Not Re!";
                    thanksButtonClickCount++;
                }
                else if(thanksButtonClickCount==1){
                    reply = "Ha Ha You Are Welcome!";
                    thanksButtonClickCount++;
                }
                else if(thanksButtonClickCount==2){
                    reply = "Ha Thik h, bas kar rulayegi kya?";
                    thanksButtonClickCount++;
                }
                else if(thanksButtonClickCount==3){
                    reply = "Ab Bahut Ho rha";
                    thanksButtonClickCount++;
                }
                else if(thanksButtonClickCount==4){
                    reply = "Dabate Rah, Lagta hai bahut MaAAzAAA aa rha h!";
                    thanksButtonClickCount++;
                }
                else{
                    reply = (thanksButtonClickCount+1)+" Bar Daba Di!";
                    thanksButtonClickCount++;
                }
                Toast.makeText(getBaseContext(),reply,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
