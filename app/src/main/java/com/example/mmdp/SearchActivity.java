package com.example.mmdp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    private String REQUEST_URL = "http://www.omdbapi.com/?apikey=28b6bce0";
    String movie_name = ""; // hyege mn el activity ele fat7a
    private Uri.Builder builder;
    public static final String LOG_TAG = SearchActivity.class.getName();

    ArrayList<Media> MEDIA_ARRAY;
    ListView MediaListView;
    ProgressBar pb;

    //take name of searched  movei here from opening activity
    //make the layout
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            movie_name = null;
        } else {
            movie_name = extras.getString("query");
        }

        builder = new Uri.Builder();
        REQUEST_URL = Uri.parse("http://www.omdbapi.com/?apikey=28b6bce0")
                .buildUpon()
                .appendQueryParameter("s", movie_name).toString();


        //can make other requests

        MediaAsyncTask task = new MediaAsyncTask();
        task.execute(REQUEST_URL);


        ArrayList<Media> media_array = new ArrayList<Media>();


    }

    private void updateUi(final ArrayList<Media> media_array) {


        MediaListView = (ListView) findViewById(R.id.search_ListView);
        MediaListView.setEmptyView(findViewById(R.id.nosearch));

        TextView tv = (TextView) findViewById(R.id.nosearch);
        pb = (ProgressBar) findViewById(R.id.arProgressBar);

        tv.setText("no results");
        pb.setVisibility(View.INVISIBLE);

        ListAdapter customAdapter = new ListAdapter(this, R.layout.media_item, media_array);

        MediaListView.setAdapter(customAdapter);


        MediaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                toast(MEDIA_ARRAY.get(position).getTitle(), getApplicationContext());
                // get title ab3to l activity media info w el async bta3ha el data bta3t el movie
                //w b3d kda momken y3ml add fe el data base

            }
        });

    }

    public void toast(String msg, Context w) {
        Toast.makeText(w.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private class MediaAsyncTask extends AsyncTask<String, Void, ArrayList<Media>> {

        @Override

        protected ArrayList<Media> doInBackground(String... passed_url) {

            URL url = createUrl(passed_url[0]);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";

            try {
                jsonResponse = makeHttpRequest(url);

            } catch (IOException e) {
                Log.e("ioexception in doinback", e.getMessage());
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Media> media = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return media;
        }


        @Override
        protected void onPostExecute(ArrayList<Media> arr) {
            if (arr == null) {
                return;
            }

            updateUi(arr);
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


        private ArrayList<Media> extractFeatureFromJson(String Responce) {

            if (Responce.isEmpty() || Responce == null) {

                return null;
            }
            MEDIA_ARRAY = new ArrayList<Media>();

            try {
                JSONObject res = new JSONObject(Responce);
                int totalResults = res.getInt("totalResults");

                if (totalResults != 0) {
                    boolean responce = res.getBoolean("Response");
                    JSONArray arr = res.getJSONArray("Search");
                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject m = arr.getJSONObject(i);
                        String title = m.getString("Title");
                        String year = m.getString("Year");
                        String type = m.getString("Type");
                        String poster = m.getString("Poster");

                        Media new_item = new Media(title + " (" + year + ')', year, type, poster);

                        MEDIA_ARRAY.add(new_item);

                    }
                    return MEDIA_ARRAY;
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;

        }
    }
}
