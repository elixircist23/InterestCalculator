package com.vitium.interestcalculator;

import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CompoundActivity extends AppCompatActivity {

    FloatingActionButton fab;
    Spinner mySpinner;
    TextView interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound);
        int color = ContextCompat.getColor(this, R.color.icons);
        fab = (FloatingActionButton) findViewById(R.id.myFabC);
        fab.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        fab = (FloatingActionButton) findViewById(R.id.myFabC);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double principal = 0;
                double rate = 0;
                double time = 0;
                double compound = 0;
                double A;

                //grabbing values from edit text
                try {
                    principal = Double.parseDouble(((EditText)findViewById(R.id.editText4)).getText().toString());
                    rate = Double.parseDouble(((EditText)findViewById(R.id.editText5)).getText().toString());
                    time = Double.parseDouble(((EditText)findViewById(R.id.editText6)).getText().toString());
                    compound = Double.parseDouble(((EditText)findViewById(R.id.editText6)).getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(CompoundActivity.this, "Please fill all info with numbers.", Toast.LENGTH_SHORT).show();
                }

                //calculate the interest, round using format string
                A = java.lang.Math.pow((principal * (1 + ((rate/100) / compound))), compound * time);
                String format = String.format("%1$,.2f", A);

                interest = (TextView)findViewById(R.id.textView7);
                interest.setText(format);

            }

        });
    }
}
