package com.example.mmdp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MediaDB extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "media.db";
    //make create table and drop bta3t el update

    public MediaDB (Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createQuery = "CREATE TABLE " +MediaContract.MediaTable.TABLE_NAME +'('
                +MediaContract.MediaTable.COLUMN_TITLE +"TEXT PRIMARY KEY "
                +MediaContract.MediaTable.COLUMN_RATED +"TEXT "
                +MediaContract.MediaTable.COLUMN_ROTTEN_RATING +" TEXT "
                +MediaContract.MediaTable.COLUMN_IMDB_RATING +" TEXT "
                +MediaContract.MediaTable.COLUMN_RUN_TIME +" TEXT "
                +MediaContract.MediaTable.COLUMN_DIRECTOR +" TEXT "
                +MediaContract.MediaTable.COLUMN_WRITERs +" TEXT "
                +MediaContract.MediaTable.COLUMN_ACTORS +" TEXT "
                +MediaContract.MediaTable.COLUMN_AWARDS +" TEXT "
                +MediaContract.MediaTable.COLUMN_BOXOFFICE +" TEXT "
                +MediaContract.MediaTable.COLUMN_COUNTRY +" TEXT "
                +MediaContract.MediaTable.COLUMN_LANGUAGE +" TEXT "
                +MediaContract.MediaTable.COLUMN_PLOT +" TEXT "
                +MediaContract.MediaTable.COLUMN_TITLE +" TEXT "
                +MediaContract.MediaTable.COLUMN_YEAR +" TEXT "
                +MediaContract.MediaTable.COLUMN_TYPE +" TEXT );";


        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
