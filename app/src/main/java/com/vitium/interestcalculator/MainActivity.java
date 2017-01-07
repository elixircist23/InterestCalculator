package com.vitium.interestcalculator;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;




public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    Spinner mySpinner;
    TextView interest;

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view

        // Set the list's click listener


        int color = ContextCompat.getColor(this, R.color.icons);
        fab = (FloatingActionButton) findViewById(R.id.myFAB);
        fab.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        fab = (FloatingActionButton) findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //grabbing values from edit text
                double principal = 0;
                double rate = 0;
                double time = 0;
                try {
                    principal = Double.parseDouble(((EditText)findViewById(R.id.editPrincipalC)).getText().toString());
                    rate = Double.parseDouble(((EditText)findViewById(R.id.editRate)).getText().toString());
                    time = Double.parseDouble(((EditText)findViewById(R.id.editTime)).getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please fill all info with numbers.", Toast.LENGTH_SHORT).show();
                }

                //grabbing spinner value to see if years or months
                mySpinner = (Spinner) findViewById(R.id.spinner2);
                String text = mySpinner.getSelectedItem().toString();
                //if months, do the conversion
                if(text.equals("Months")){
                    time = time / 12;
                }

                //calculate the interest, round using format string
                double A = principal * (1 + ((rate/100) * time));
                String format = String.format("%1$,.2f", A);

                interest = (TextView)findViewById(R.id.simpleInterest);
                interest.setText(format);

                //startActivity(new Intent(MainActivity.this,CompoundActivity.class));


            }

        });

    }
}
