package com.auribises.gw2018d;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpperFragment extends Fragment implements View.OnClickListener{


    Button btnSubmit;
    MyListener listener;

    public UpperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_upper, container, false);
        View view = inflater.inflate(R.layout.fragment_upper, container, false);

        btnSubmit = view.findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"You Clicked button in Upper Fragment",Toast.LENGTH_LONG).show();
        listener.handler(3.5f);
    }

    public void registerMyListener(MyListener listener){
        this.listener = listener;
    }
}
