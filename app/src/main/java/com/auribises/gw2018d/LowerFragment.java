package com.auribises.gw2018d;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class LowerFragment extends Fragment implements MyListener{

    RatingBar ratingBar;

    public LowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lower, container, false);

        ratingBar = view.findViewById(R.id.ratingBar);

        return view;
    }

    @Override
    public void handler(float rating) {
        ratingBar.setRating(rating);
    }
}
