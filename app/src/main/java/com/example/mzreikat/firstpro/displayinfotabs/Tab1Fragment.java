package com.example.mzreikat.firstpro.displayinfotabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

public class Tab1Fragment extends Fragment
{
    private TextView nameL;
    private TextView nameR;

    private TextView collegeL;
    private TextView collegeR;

    private TextView phoneL;
    private TextView phoneR;

    private TextView studyFieldL;
    private TextView studyFieldR;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        nameL = view.findViewById(R.id.tab2_messageL);
        nameR = view.findViewById(R.id.tab2_messageR);
        collegeL = view.findViewById(R.id.tab2_resultL);
        collegeR = view.findViewById(R.id.tab2_resultR);
        phoneL = view.findViewById(R.id.tab1_phoneL);
        phoneR = view.findViewById(R.id.tab1_phoneR);

        studyFieldL = view.findViewById(R.id.tab1_studyL);
        studyFieldR = view.findViewById(R.id.tab1_studyR);

        Intent intent = this.getActivity().getIntent();
        final String previousActivity = intent.getStringExtra("activity");

        Button showInfoBtn = view.findViewById(R.id.tab2_showMessage);
        showInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousActivity.equals("UserInfo")) {
                    displayUserInfo();
                }
                else {
                    Toast.makeText(getActivity(), "Move to the Next Activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void displayUserInfo() {
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String firstName = sharedPref.getString("firstName", "defaultFName");
        String lastName  = sharedPref.getString("lastName", "defaultLName");
        String college   = sharedPref.getString("college", "defaultCollege");
        String phone     = sharedPref.getString("phone", "defaultPhone");
        String studyField= sharedPref.getString("studyField", "defaultField");

        nameL.setText("Name:");
        nameR.setText(firstName + " " + lastName);

        collegeL.setText("College:");
        collegeR.setText(college);

        phoneL.setText("Phone:");
        phoneR.setText(phone);

        studyFieldL.setText("Study Field:");
        studyFieldR.setText(studyField);
    }
}