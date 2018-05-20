package com.example.mzreikat.firstpro.displayinfotabs;

import android.content.Intent;
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

public class Tab2Fragment extends Fragment
{
    private TextView messageL;
    private TextView messageR;

    private TextView resultL;
    private TextView resultR;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        messageL = view.findViewById(R.id.tab2_messageL);
        messageR = view.findViewById(R.id.tab2_messageR);
        resultL  = view.findViewById(R.id.tab2_resultL);
        resultR  = view.findViewById(R.id.tab2_resultR);

        Intent intent = this.getActivity().getIntent();
        final String previousActivity = intent.getStringExtra("activity");

        Button showMessageBtn = view.findViewById(R.id.tab2_showMessage);
        showMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousActivity.equals("GreetAdd")) {
                    displayMessage();
                }
                else {
                    Toast.makeText(getActivity(), "Move to the Previous Activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void displayMessage() {
        messageL.setText("Message:");
        // Get the Intent that started this activity and extract the string
        Intent intent  = this.getActivity().getIntent();
        String line = intent.getStringExtra("greetAct");

        String[] messages = line.split(",");

        // Capture the layout's TextView and set the string as its text
        messageR.setText(messages[0]);
        resultL.setText(messages[1]);
        resultR.setText(messages[2]);
    }
}