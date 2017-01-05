package com.vitium.interestcalculator;

import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
                //grabbing values from edit text
                double principal = Double.parseDouble(((EditText)findViewById(R.id.editPrincipal)).getText().toString());
                double rate = Double.parseDouble(((EditText)findViewById(R.id.editRate)).getText().toString());
                double time = Double.parseDouble(((EditText)findViewById(R.id.editTime)).getText().toString());

                //grabbing spinner value to see if years or months
                Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
                String text = mySpinner.getSelectedItem().toString();
                //if months, do the conversion
                if(text.equals("Months")){
                    time = time / 12;
                }

                //calculate the interest, round using format string
                double A = principal * (1 + ((rate/100) * time));
                String format = String.format("%1$,.2f", A);

                TextView tv1 = (TextView)findViewById(R.id.simpleInterest);
                tv1.setText(format);

            }

        });

    }
}
