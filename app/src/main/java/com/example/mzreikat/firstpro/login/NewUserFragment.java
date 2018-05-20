package com.example.mzreikat.firstpro.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mzreikat.firstpro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewUserFragment extends Fragment
{
    public static Fragment nuf;

    public NewUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);
        nuf = this;

        return view;
    }

}
