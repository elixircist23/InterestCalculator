package com.vitium.interestcalculator;

import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int color = ContextCompat.getColor(this, R.color.icons);
        fab = (FloatingActionButton) findViewById(R.id.myFAB);
        fab.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        fab = (FloatingActionButton) findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("sdf", "sdf");
            }

        });
    }
}
