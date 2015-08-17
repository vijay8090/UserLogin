package com.vijay.userlogin;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    LinearLayout llStats;
    TextView txtPlayCount, txtEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llStats = (LinearLayout) findViewById(R.id.llStats);
        txtPlayCount = (TextView) findViewById(R.id.txtNowPlaying);
        txtEarned = (TextView) findViewById(R.id.txtEarned);

        // layout background transparent
        llStats.getBackground().setAlpha(150);
        llStats.setVisibility(View.VISIBLE);

        Intent i = getIntent();
        String now_playing = i.getStringExtra("now_playing");
        String earned = i.getStringExtra("earned");

        // Diplaying the text
        txtPlayCount.setText(now_playing);
        txtEarned.setText(earned);
    }
}
