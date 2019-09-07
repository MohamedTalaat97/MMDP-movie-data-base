package com.example.mmdp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class MediaInfoActivity extends AppCompatActivity {


    private String REQUEST_URL = "http://www.omdbapi.com/?apikey=28b6bce0";
    private Uri.Builder builder;
    public static final String LOG_TAG = MediaInfoActivity.class.getName();
    private String title, year, rated, runtime, genre, director, writer, actors, plot, country, awards, poster, language, imdbRating, boxOffice, type;

    private Media MEDIA_OBJ;

    /*
    TextView tile, year, type, poster, genre, imdb, rotten, rate, release, actor,
            director, writer, language, country, awards, boxoffice, runtime;
*/

    ImageView poster_image;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_info);
        Intent i = getIntent();
        if (i.hasExtra("title"))
            title = getIntent().getStringExtra("title");
        else toast("no title sent to mediainfoactivity",getApplicationContext());

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting a database connection

                //replace all this with a function call mn el controller

                MediaDB dbhelper = new MediaDB(getApplicationContext());
                SQLiteDatabase db = dbhelper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(MediaContract.MediaTable.COLUMN_TITLE, title);

                String m = "";

                if (db.insert(MediaContract.MediaTable.TABLE_NAME,null,cv) != -1) {
                    //add movie to data base here
                  m = "movie added";
                }
                else m = "error";

                Snackbar.make(view, m ,Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        builder = new Uri.Builder();
        REQUEST_URL = Uri.parse("http://www.omdbapi.com/?apikey=28b6bce0")
                .buildUpon()
                .appendQueryParameter("t", title).toString();

        //can make other requests

        MediaInfoActivity.MediaAsyncTask task = new MediaInfoActivity.MediaAsyncTask();
        task.execute(REQUEST_URL);


    }

    public void toast(String msg, Context w) {
        Toast.makeText(w.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private class MediaAsyncTask extends AsyncTask<String, Void, Media> {

        @Override

        protected Media doInBackground(String... passed_url) {

            URL url = createUrl(passed_url[0]);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";

            try {
                jsonResponse = makeHttpRequest(url);

            } catch (IOException e) {
                Log.e("ioexception in doinback", e.getMessage());
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            Media media = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return media;
        }


        private void updateUi(final Media media_object) {


            TextView title, year, type, poster, genre, imdb, rotten, rate, release, actor,
                    director, writer, language, country, awards, boxoffice, runtime;

            title = findViewById(R.id.text_title);
            title.setText(media_object.getTitle());
            rate = findViewById(R.id.text_rate);
            rate.setText(media_object.getRated());
            runtime = findViewById(R.id.text_run);
            runtime.setText(media_object.getRuntime());
            genre = findViewById(R.id.text_genre);
            genre.setText(media_object.getGenre());
            imdb = findViewById(R.id.text_imdb);
            imdb.setText(media_object.getImdb_rating());
            rotten = findViewById(R.id.text_rotten);
            rotten.setText(media_object.getRotten_rating());


            poster_image = findViewById(R.id.image_poster);
            MediaInfoActivity.DownloadImagesTask task = new MediaInfoActivity.DownloadImagesTask();
            task.execute(media_object.getPoster_link());


        }

        @Override
        protected void onPostExecute(Media obj) {
            if (obj == null) {
                return;
            }

            updateUi(obj);
        }


        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        private String makeHttpRequest(URL url) throws IOException {

            String jsonResponse = "";

            if (url == null) {

                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();


                if (urlConnection.getResponseCode() == 200) {

                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);

                } else {
                    Log.e(LOG_TAG, "responce code is " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {

                Log.e(LOG_TAG, "promlem with connecting");
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }


        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }


        private Media extractFeatureFromJson(String Responce) {

            if (Responce.isEmpty() || Responce == null) {
                Log.d(LOG_TAG, "responce is empty");

                return null;
            }
            //MEDIA_OBJ = new Media();

            try {
                //extract hna


                JSONObject res = new JSONObject(Responce);


                String responce;

                if (res.has("Response"))
                    responce = res.getString("Response");
                else responce = "N/A";


                if (res.has("Year"))
                    year = res.getString("Year");
                else year = "N/A";


                if (res.has("Rated"))
                    rated = res.getString("Rated");
                else rated = "N/A";


                if (res.has("Runtime"))
                    runtime = res.getString("Runtime");
                else runtime = "N/A";


                if (res.has("Genre"))
                    genre = res.getString("Genre");
                else genre = "N/A";


                if (res.has("Director"))
                    director = res.getString("Director");
                else director = "N/A";


                if (res.has("Writer"))
                    writer = res.getString("Writer");
                else writer = "N/A";


                if (res.has("Actors"))
                    actors = res.getString("Actors");
                else actors = "N/A";


                if (res.has("Plot"))
                    plot = res.getString("Plot");
                else plot = "N/A";


                if (res.has("Country"))
                    country = res.getString("Country");
                else country = "N/A";


                if (res.has("Awards"))
                    awards = res.getString("Awards");
                else awards = "N/A";

                if (res.has("Poster"))
                    poster = res.getString("Poster");
                else poster = "N/A";

                if (res.has("Language"))
                    language = res.getString("Language");
                else language = "N/A";

                String rotten = "";

                if (res.has("Ratings")) {

                    JSONArray rating_array = res.getJSONArray("Ratings");


                    try {
                        for (int i = 0; i < rating_array.length(); i++) {
                            JSONObject temp = rating_array.getJSONObject(i);

                            String source = temp.getString("Source");
                            if (source == "Rotten Tomatoes") {
                                rotten = temp.getString("Value");
                            }

                        }
                        rotten = "N/A";
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "error in rotten tomatoes");
                    }


                }


                if (res.has("imdbRating"))
                    imdbRating = res.getString("imdbRating");
                else imdbRating = "N/A";


                if (res.has("Type"))
                    type = res.getString("Type");
                else type = "N/A";

                if (res.has("BoxOffice"))
                    boxOffice = res.getString("BoxOffice");
                else boxOffice = "N/A";


                Media new_item = new Media(title, year, type, poster, genre, imdbRating,
                        rotten, rated, director, actors, writer, language
                        , country, awards, boxOffice, runtime);


                return new_item;


            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;

        }
    }

    public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return download_Image(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            poster_image.setImageBitmap(result);
        }


        private Bitmap download_Image(String url) {
            //---------------------------------------------------
            Bitmap bm = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("media info ", "Error getting the image from server : " + e.getMessage().toString());
            }
            return bm;
            //---------------------------------------------------
        }


    }
}