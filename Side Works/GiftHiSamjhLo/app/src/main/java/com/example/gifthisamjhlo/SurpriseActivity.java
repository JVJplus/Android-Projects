package com.example.gifthisamjhlo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SurpriseActivity extends AppCompatActivity {

    boolean minus=true,plus=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        handlePlusButton();
        handleMinusButton();
        handleBahutHoGyaButton();
        handleChangePhotoButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleBahutHoGyaButton() {
        Button button = (Button) findViewById(R.id.bahutHoGyaButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SurpriseActivity.this, Feedback.class);
                startActivity(intent);
            }
        });
    }

    private void handleChangePhotoButton() {
        Button button = (Button) findViewById(R.id.changePhotoButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(SurpriseActivity.this, "This Feature will be Updated Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handlePlusButton() {
        Button button = (Button) findViewById(R.id.plus_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView image=(ImageView)findViewById(R.id.pngImage);
                LinearLayout imageContainer=(LinearLayout)findViewById(R.id.image_container);
                int currentHeight=image.getHeight();

                image.requestLayout();
                image.getLayoutParams().height = currentHeight+20>=imageContainer.getHeight()?currentHeight:currentHeight+20;

                if(image.getLayoutParams().height==currentHeight && plus==true){
                    Toast.makeText(SurpriseActivity.this, "Screen se bahar nikalne ka plan hai kya?", Toast.LENGTH_SHORT).show();
                    plus=false;
                }
            }
        });
    }

    private void handleMinusButton() {
        Button button = (Button) findViewById(R.id.minus_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView image=(ImageView)findViewById(R.id.pngImage);
                int currentHeight=image.getHeight();

                image.requestLayout();
                image.getLayoutParams().height = currentHeight-20<=0?0:currentHeight-20;

                if(currentHeight==0 && minus==true){
                    Toast.makeText(SurpriseActivity.this, "WOW! You Got Invisible!", Toast.LENGTH_SHORT).show();
                    minus=false;
                }
            }
        });
    }
}
