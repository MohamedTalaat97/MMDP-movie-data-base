package com.example.mmdp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Toolbar action_bar;
    ImageView home_background;
    EditText search_input;
    Button search_btn;
    ImageView background;
    Animation biganim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        action_bar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(action_bar);
        home_background = findViewById(R.id.background);
        //search_input = findViewById(R.id.search_input);
        //search_btn = findViewById(R.id.search_btn);
        background = findViewById(R.id.background);
        biganim = AnimationUtils.loadAnimation(this, R.anim.godown);
       background.startAnimation(biganim);



    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                // User chose the "Settings" item, show the app settings UI...
                //toast("add a new movie",getApplicationContext());

                return true;

            case R.id.action_search:

                // User chose the "Favorite" action, mark the current item

                return true;


            case R.id.action_about:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    public void toast(String msg, Context w) {
        Toast.makeText(w.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //SearchView v = new SearchView();
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent i = new Intent(MainActivity.this , SearchActivity.class);
                i.putExtra("query", s);
                startActivity(i);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
/*

    void goTo(Class s) {


        Intent intent = new Intent(this, s);
        startActivity(intent);
        overridePendingTransition(R.anim.goup, R.anim.godown);
        finish();
    }

      @Override
    public void onBackPressed(){
        super.onBackPressed();
        //Add the OnBackPressed into Other activity when the BackPressed
        overridePendingTransition(R.anim.godown, R.anim.godown);
    }
*/
    //go to restore your account class


}
