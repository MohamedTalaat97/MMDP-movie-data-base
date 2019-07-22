package com.example.mmdp;

import android.provider.BaseColumns;

public final class MediaContract {


    private MediaContract() {
    }

    public static final class MediaTable implements BaseColumns {


        public static final String TABLE_NAME = "media ";
        public static final String COLUMN_TITLE = " title ";
        public static final String COLUMN_YEAR = "year ";
        public static final String COLUMN_RATED = "rated ";
        public static final String COLUMN_RUN_TIME = "runtime ";
        public static final String COLUMN_DIRECTOR = "director ";
        public static final String COLUMN_ACTORS = "actors ";
        public static final String COLUMN_WRITERs = "writers ";
        public static final String COLUMN_PLOT = "plot ";
        public static final String COLUMN_LANGUAGE = "language ";
        public static final String COLUMN_COUNTRY = "country ";
        public static final String COLUMN_AWARDS = "awards ";
        public static final String COLUMN_TYPE = "type ";
        public static final String COLUMN_IMDB_RATING = "imdb ";
        public static final String COLUMN_ROTTEN_RATING = "rotten ";
        public static final String COLUMN_BOXOFFICE = "boxoffice ";
        public static final String COLUMN_POSTER = "poster ";
        public static final String COLUMN_Release = "release ";


    }
}
