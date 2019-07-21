package com.example.mmdp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_media);
    }


    private void DisplayDataBaseInfo() {
        //instance of el media db ele  by3ml create
        MediaDB helper = new MediaDB(this);

        //ba5od mno database
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MediaContract.MediaTable.TABLE_NAME, null);

        try {

            int noofresults = cursor.getCount();

        } finally {
            cursor.close();
        }


    }
}
