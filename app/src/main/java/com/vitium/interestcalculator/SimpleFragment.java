package com.vitium.interestcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimpleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SimpleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimpleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(String param1, String param2) {
        SimpleFragment fragment = new SimpleFragment();
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
        fab = (FloatingActionButton) getView().findViewById(R.id.myFAB);
        fab.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        fab = (FloatingActionButton) getView().findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //grabbing values from edit text
                double principal = 0;
                double rate = 0;
                double time = 0;
                try {
                    principal = Double.parseDouble(((EditText)getView().findViewById(R.id.editPrincipal)).getText().toString());
                    rate = Double.parseDouble(((EditText)getView().findViewById(R.id.editRate)).getText().toString());
                    time = Double.parseDouble(((EditText)getView().findViewById(R.id.editTime)).getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Please fill all info with numbers.", Toast.LENGTH_SHORT).show();
                }

                //grabbing spinner value to see if years or months
                mySpinner = (Spinner) getView().findViewById(R.id.spinner2);
                String text = mySpinner.getSelectedItem().toString();
                //if months, do the conversion
                if(text.equals("Months")){
                    time = time / 12;
                }

                //calculate the interest, round using format string
                double A = principal * (1 + ((rate/100) * time));
                String format = String.format("%1$,.2f", A);

                interest = (TextView)getView().findViewById(R.id.simpleInterest);
                interest.setText(format);

                startActivity(new Intent(getActivity(),CompoundActivity.class));


            }

        });
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
