package com.example.mmdp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MediaInfoActivity extends AppCompatActivity {


    TextView tile;
    ImageView poster;
    TextView year;
    TextView rate;
    TextView release;
    TextView run;
    TextView actor;
    TextView director;
    TextView writer;
    TextView language;
    TextView country;
    TextView awards;
    TextView type;
    TextView imdb;
    TextView rotten;
    TextView boxoffice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_info);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //add movie to data base here
                Snackbar.make(view, "movie added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
}