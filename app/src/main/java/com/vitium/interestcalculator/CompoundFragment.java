package com.vitium.interestcalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompoundFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CompoundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompoundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompoundFragment newInstance(String param1, String param2) {
        CompoundFragment fragment = new CompoundFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FloatingActionButton fab;
    Spinner mySpinner;
    TextView interest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        int color = ContextCompat.getColor(getActivity(), R.color.icons);
        fab = (FloatingActionButton) getView().findViewById(R.id.myFabC);
        fab.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        fab = (FloatingActionButton) getView().findViewById(R.id.myFabC);
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
                    principal = Double.parseDouble(((EditText)getView().findViewById(R.id.editText4)).getText().toString());
                    rate = Double.parseDouble(((EditText)getView().findViewById(R.id.editText5)).getText().toString());
                    time = Double.parseDouble(((EditText)getView().findViewById(R.id.editText6)).getText().toString());
                    compound = Double.parseDouble(((EditText)getView().findViewById(R.id.editText6)).getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Please fill all info with numbers.", Toast.LENGTH_SHORT).show();
                }

                //calculate the interest, round using format string
                A = principal * java.lang.Math.pow((1 + (((rate/100) / time))), compound * time);
                String format = String.format("%1$,.2f", A);

                interest = (TextView)getView().findViewById(R.id.textView7);
                interest.setText(format);

            }

        });
        return inflater.inflate(R.layout.fragment_compound, container, false);
    }

}
