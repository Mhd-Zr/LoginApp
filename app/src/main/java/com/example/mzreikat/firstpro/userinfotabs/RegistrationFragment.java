package com.example.mzreikat.firstpro.userinfotabs;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment
{
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText collegeName;
    private EditText studyField;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        Intent intent = this.getActivity().getIntent();
        String str = intent.getStringExtra("Email");

        TextView enterInfoV = view.findViewById(R.id.regFrag_EnterInfo);
        enterInfoV.setText(str);

        firstName   = view.findViewById(R.id.regFrag_firstName);
        lastName    = view.findViewById(R.id.regFrag_lastName);
        phoneNumber = view.findViewById(R.id.regFrag_phoneNumber);
        collegeName = view.findViewById(R.id.regFrag_collegeName);
        studyField  = view.findViewById(R.id.regFrag_studyField);

        Button saveInfoBtn = view.findViewById(R.id.regFrag_saveBtn);
        saveInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInformation();
            }
        });

        return view;
    }

    private void saveInformation()
    {
        SharedPreferences sharedPref    = this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("firstName", firstName.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.putString("phone", phoneNumber.getText().toString());
        editor.putString("college", collegeName.getText().toString());
        editor.putString("studyField", studyField.getText().toString());
        editor.apply();

        // Reset errors.
        firstName.setError(null);
        lastName.setError(null);
        collegeName.setError(null);

        // Store values at the time of the login attempt.
        String firstN   = firstName.getText().toString();
        String lastN    = lastName.getText().toString();
        String collegeN = collegeName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a first name.
        if (TextUtils.isEmpty(firstN)) {
            firstName.setError(getString(R.string.error_field_required));
            focusView = firstName;
            cancel = true;
        }

        // Check for a last name.
        if (TextUtils.isEmpty(lastN)) {
            lastName.setError(getString(R.string.error_field_required));
            focusView = lastName;
            cancel = true;
        }

        // Check for a college name.
        if (TextUtils.isEmpty(collegeN)) {
            collegeName.setError(getString(R.string.error_field_required));
            focusView = collegeName;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            moveOn();
        }
    }

    public void moveOn() {
        Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_LONG).show();

        firstName.setText(null);
        lastName.setText(null);
        phoneNumber.setText(null);
        collegeName.setText(null);
        studyField.setText(null);
    }
}