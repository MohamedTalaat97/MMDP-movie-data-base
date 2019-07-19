package com.example.mmdp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

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

public class FixtureLoader extends AsyncTaskLoader<ArrayList<Fixture>> {


    public static final String LOG_TAG = FixturesActivity.class.getName();
    private static final String REQUEST_URL =
            "https://apifootball.com/api/?action=get_events&from=2018-9-19&to=2018-9-29&league_id=109&APIkey=e8d4f87c7fa47ef66cd0eb8d252231d7756e4669b498e0e907892cfef81ac39f";


    public FixtureLoader(Context context, String url) {
        super(context);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Fixture> loadInBackground() {

        URL url = createUrl(REQUEST_URL);

        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("ioexception in doinback", e.getMessage());
        }


        ArrayList<Fixture> fixtures = extractFeatureFromJson(jsonResponse);


        return fixtures;

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


    private ArrayList<Fixture> extractFeatureFromJson(String Responce) {

        if (Responce.isEmpty() || Responce == null) {

            return null;
        }
        ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
        try {
            JSONArray matches = new JSONArray(Responce);
            for (int i = 0; i < matches.length(); i++) {

                JSONObject match = matches.getJSONObject(i);
                String home = matches.getJSONObject(i).getString("match_hometeam_name").toString();
                String date = matches.getJSONObject(i).getString("match_date").toString();
                String away = matches.getJSONObject(i).getString("match_awayteam_name").toString();

                fixtures.add(new Fixture(home, away, date));

            }

            return fixtures;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }
}

