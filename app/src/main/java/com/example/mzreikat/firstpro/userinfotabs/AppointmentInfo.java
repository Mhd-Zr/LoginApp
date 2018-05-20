package com.example.mzreikat.firstpro.userinfotabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentInfo extends Fragment implements View.OnClickListener{
    public static Fragment ai;

    private TextView mPerson;
    private TextView mSubject;
    private TextView mLocation;
    private TextView mStartTime;
    private TextView mEndTime;

    public AppointmentInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment_info, container, false);
        ai = this;

        mPerson = view.findViewById(R.id.timeInfo_meetingWithR);
        mSubject = view.findViewById(R.id.timeInfo_subjectR);
        mLocation = view.findViewById(R.id.timeInfo_roomR);
        mStartTime = view.findViewById(R.id.timeInfo_startR);
        mEndTime = view.findViewById(R.id.timeInfo_endR);

        displayUserInfo();

        Button editInfoBtn = view.findViewById(R.id.timeInfo_editBtn);
        editInfoBtn.setOnClickListener(this);

        Button sendInviteBtn = view.findViewById(R.id.timeInfo_inviteBtn);
        sendInviteBtn.setOnClickListener(this);

        return view;
    }

    HashSet<String> withWhom;
    String subject;
    String text;
    public void displayUserInfo() {
        SharedPreferences sharedPref = Objects.requireNonNull(this.getActivity()).getSharedPreferences("appointment", Context.MODE_PRIVATE);

        Set<String> defaultVal = new HashSet<>();

        withWhom = (HashSet<String>) sharedPref.getStringSet("emails", defaultVal);
        subject = sharedPref.getString("subject", "defaultSub");
        String location = sharedPref.getString("location", "defaultRoom");
        String startsAt = sharedPref.getString("startTime", "defaultStart");
        String endsAt = sharedPref.getString("endTime", "defaultEnd");

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : withWhom) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }

        text = "Room: " + location + "\n" + "Start Time: " + startsAt + "\n" + "End Time: " + endsAt;
        mPerson.setText(stringBuilder);
        mSubject.setText(subject);
        mLocation.setText(location);
        mStartTime.setText(startsAt);
        mEndTime.setText(endsAt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timeInfo_editBtn:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
                break;
            case R.id.timeInfo_inviteBtn:
                int size = withWhom.size();

                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] to = withWhom.toArray(new String[size]);/*new String[size];*/
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Send Invite"));

                Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}