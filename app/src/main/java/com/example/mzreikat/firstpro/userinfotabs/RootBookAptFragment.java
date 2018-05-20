package com.example.mzreikat.firstpro.userinfotabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mzreikat.firstpro.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootBookAptFragment extends Fragment {


    public RootBookAptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_root_book_apt, container, false);

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.bookApt_rootFragment, new BookAppointment())
                .commit();

        return view;
    }
}