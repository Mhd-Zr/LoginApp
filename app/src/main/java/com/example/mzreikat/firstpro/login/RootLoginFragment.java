package com.example.mzreikat.firstpro.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mzreikat.firstpro.R;
import com.example.mzreikat.firstpro.userinfotabs.BookAppointment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootLoginFragment extends Fragment {


    public RootLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_root_login, container, false);

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_root_fragment, new LoginFragment())
                .commit();

        return view;
    }
}