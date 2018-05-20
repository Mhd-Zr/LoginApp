package com.example.mzreikat.firstpro.userinfotabs;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.mzreikat.firstpro.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowInfoFragment extends Fragment
{
    private TextView nameR;
    private TextView collegeR;
    private TextView phoneR;
    private TextView studyFieldR;


    public ShowInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_info, container, false);

        nameR = view.findViewById(R.id.show_info_nameR);
        collegeR = view.findViewById(R.id.show_info_collegeR);
        phoneR = view.findViewById(R.id.show_info_phoneR);
        studyFieldR = view.findViewById(R.id.show_info_studyR);

        Button showInfo = view.findViewById(R.id.show_info_showInfo);
        showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUserInfo();
            }
        });

        return view;
    }

    public void displayUserInfo() {
        SharedPreferences sharedPref = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String firstName = sharedPref.getString("firstName", "defaultFName");
        String lastName  = sharedPref.getString("lastName", "defaultLName");
        String college   = sharedPref.getString("college", "defaultCollege");
        String phone     = sharedPref.getString("phone", "defaultPhone");
        String studyField= sharedPref.getString("studyField", "defaultField");

        nameR.setText(firstName + " " + lastName);
        collegeR.setText(college);
        phoneR.setText(phone);
        studyFieldR.setText(studyField);
    }
}