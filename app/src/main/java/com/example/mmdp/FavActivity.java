package com.example.mmdp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Fixture>> {
    private static final String REQUEST_URL =
            "https://apifootball.com/api/?action=get_events&from=2018-9-19&to=2018-9-29&league_id=109&APIkey=e8d4f87c7fa47ef66cd0eb8d252231d7756e4669b498e0e907892cfef81ac39f";
    ListView FixturesListView;
    ArrayList<Fixture> fixtures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
       // getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }



    @Override
    public Loader<ArrayList<Fixture>> onCreateLoader(int i, Bundle bundle) {
   return  new FixtureLoader(FavActivity.this,REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader <ArrayList<Fixture>> loader, ArrayList<Fixture> fixtures) {
        FixturesListView = (ListView) findViewById(R.id.list);


        ListAdapter customAdapter = new ListAdapter(this, R.layout.fixture_item, fixtures);

        FixturesListView.setAdapter(customAdapter);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Fixture>> loader) {

    }
}