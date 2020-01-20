package com.example.gifthisamjhlo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        handleFeedbackButton();
        handleSrcCode();
    }

    private void handleSrcCode() {
        TextView srcCode=(TextView) findViewById(R.id.src_code);
        srcCode.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleFeedbackButton() {
        Button button = (Button) findViewById(R.id.feedbackButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                composeEmail();
            }
        });
    }

    public void composeEmail() {
        String subject = "Feedback Of The Birthday App";
        String message="";

        RatingBar ratings = (RatingBar) findViewById(R.id.ratingBar);

        message+="Ratings Given: "+ratings.getRating()+"\n___________________________\n\n\n";


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_BCC, "jvj.co.in@gmail.com");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setData(Uri.parse("mailto:jvjplus@gmail.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}