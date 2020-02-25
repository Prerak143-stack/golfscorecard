package com.example.patel.golfscorecard;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.example.patel.golfscorecard.preferences";
    private static final String KEY_STROKE = "keystroke";
    private Holes[] holes = new Holes[18];
    private ListAdapter listAdapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int strokes = 0;

        for(int i = 0; i < holes.length ; i++){

            strokes = sharedPreferences.getInt(KEY_STROKE + i, 0);
            holes[i] = new Holes("Hole" + (i+1) + ":", strokes);

        }



        listAdapter = new ListAdapter(this, holes);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(listAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        for(int i = 0; i < holes.length ; i++) {
            editor.putInt(KEY_STROKE + i, holes[i].getStrokeCount());
            editor.apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reset_strokes, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.resetMenuItem){
            editor.clear();
            editor.apply();

            for(int i = 0; i < holes.length ; i++) {
                holes[i].setStrokeCount(0);
            }
            listAdapter.notifyDataSetChanged();
        }

        return true;
    }
}
