package com.example.ciccc_cirac.flag_quiz_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    // TODO 1) keys for reading data from SharedPreferences , two strings are keyname
    public static final String CHOICES = "pref_numberOfChoices"; //value = 6
    public static final String REGIONS = "pref_regionsToInclude"; //value = asia
    public boolean phoneDevice = true; //if true=phone & false=tablet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // set default values in the app's SharedPreferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // register listener for SharedPreferences changes
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(preferenceChangeListener);



        // determine screen size
        int ScreenSize = getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        // if device is a tablet, set phoneDevice to false
        // if running on phone-sized device, allow only portrait orientation
        if(ScreenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                ScreenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            phoneDevice=false;

        if(phoneDevice)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
            //// TODO 2) Create a quizfragment instance
            MainActivityFragment quizFragment = (MainActivityFragment)
                    getFragmentManager().findFragmentById(R.id.quizFragment);

            //TODO 3) This method is for updating the options
            quizFragment.updateGuessRows(PreferenceManager
                    .getDefaultSharedPreferences(this));

            //TODO 4) this method is for updating the regions
            quizFragment.updateRegions(PreferenceManager
                    .getDefaultSharedPreferences(this));

            //TODO 10) Call a method to start the quiz
            quizFragment.startQuiz();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String choice = sharedPreferences.getString(CHOICES,"0");
        Set<String> regions = sharedPreferences.getStringSet(REGIONS, null);

        for (String s : regions) {
            Log.d("default region ",s +" "+ choice);
        }
    }

    private  OnSharedPreferenceChangeListener preferenceChangeListener=
    new OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            MainActivityFragment quizFragment =
                    (MainActivityFragment) getFragmentManager().findFragmentById(R.id.quizFragment);
            //check which preference is changed
            if(key.equals(CHOICES))
            {
                quizFragment.updateGuessRows(sharedPreferences);
                quizFragment.startQuiz();
            }
            else if(key.equals(REGIONS))
            {
                quizFragment.updateRegions(sharedPreferences);
                quizFragment.startQuiz();
            }
    Toast.makeText(getApplicationContext(),R.string.restarting_quiz,Toast.LENGTH_SHORT).show();
        }
    };

//    private OnSharedPreferenceChangeListener preferencesChangeListener = new OnSharedPreferenceChangeListener() {
//        @Override
//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//
//            MainActivityFragment quizFragment = (MainActivityFragment)
//                    getFragmentManager().findFragmentById(R.id.quizFragment);
//
//            if(key.equals(CHOICES))
//            {
//                quizFragment.updateGuessRows(sharedPreferences);
//                quizFragment.startQuiz();
//            }
//            else if(key.equals(REGIONS))
//            {
//                quizFragment.updateRegions(sharedPreferences);
//                quizFragment.startQuiz();
//            }
//            Toast.makeText(MainActivity.this,
//                    R.string.restarting_quiz,
//                    Toast.LENGTH_SHORT).show();
//        }
//    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent preferencesIntent = new Intent(this, SettingsActivity.class);
        startActivity(preferencesIntent);
        return super.onOptionsItemSelected(item);
    }
}

