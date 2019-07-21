package com.example.mmdp;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
    private String REQUEST_URL = "http://www.omdbapi.com/?apikey=28b6bce0";
    private Uri.Builder builder;
    public static final String LOG_TAG = MediaInfoActivity.class.getName();

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


        builder = new Uri.Builder();
        REQUEST_URL = Uri.parse("http://www.omdbapi.com/?apikey=28b6bce0")
                .buildUpon()
                .appendQueryParameter("t","get movie name from view").toString();

//can make other requests

        //MediaInfoActivity.MediaAsyncTask task = new MediaInfoActivity.MediaAsyncTask();
        //task.execute(REQUEST_URL);


        ArrayList<Media> media_array = new ArrayList<Media>();
        ContentValues cv = new ContentValues();
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");
        cv.put(MediaContract.MediaTable.COLUMN_TITLE,"aqua");


        //db.insert(table name , null ,cv)
        //if == -1 insertion failed

    }


    ////////////////////////////////
    ///////////////////////////////
    //download photo from link poster
//hat el async task w 5od mno el poster w 7oto fe el image view

}