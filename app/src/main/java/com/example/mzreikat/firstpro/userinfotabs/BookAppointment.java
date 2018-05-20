package com.example.mzreikat.firstpro.userinfotabs;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookAppointment extends Fragment
{
    private EditText to_email;
    private EditText subject;
    private EditText room;
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;

    public static Fragment ba;

    public BookAppointment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_appointment, container, false);
        ba = this;

        to_email = view.findViewById(R.id.bookApt_to_email);
        subject  = view.findViewById(R.id.bookApt_subjField);
        room     = view.findViewById(R.id.bookApt_roomField);

        startTimePicker = view.findViewById(R.id.bookApt_startTimePicker);
        endTimePicker   = view.findViewById(R.id.bookApt_endTimePicker);

        Button saveBtn = view.findViewById(R.id.bookApt_saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInformation();
                Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_SHORT).show();

                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.bookApt_rootFragment, new AppointmentInfo())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void saveInformation() {
        SharedPreferences sharedPreferences =
                Objects.requireNonNull(this.getActivity()).getSharedPreferences("appointment", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        /*editor.putString("to_email", to_email.getText().toString());*/
        editor.putStringSet("emails", emailsSet());
        editor.putString("subject" , subject.getText().toString());
        editor.putString("location", room.getText().toString());

        String startTime = setTheTime(startTimePicker.getHour(), startTimePicker.getMinute());
        editor.putString("startTime", startTime);

        String endTime = setTheTime(endTimePicker.getHour(), endTimePicker.getMinute());
        editor.putString("endTime", endTime);

        editor.apply();
    }

    public Set<String> emailsSet() {
        String getEmails = to_email.getText().toString();
        String[] emails = getEmails.split(",");

        return new HashSet<>(Arrays.asList(emails));
    }

    public String setTheTime(int hour, int minute) {
        String startHour;
        if (hour < 10) {
            startHour = "0" + hour;
        }
        else {
            switch (hour)
            {
                case 13:
                    startHour = "01";
                    break;
                case 14:
                    startHour = "02";
                    break;
                case 15:
                    startHour = "03";
                    break;
                case 16:
                    startHour = "04";
                    break;
                case 17:
                    startHour = "05";
                    break;
                case 18:
                    startHour = "06";
                    break;
                case 19:
                    startHour = "07";
                    break;
                case 20:
                    startHour = "08";
                    break;
                case 21:
                    startHour = "09";
                    break;
                case 22:
                    startHour = "10";
                    break;
                case 23:
                    startHour = "11";
                    break;
                case 24:
                    startHour = "12";
                    break;
                default:
                    startHour = String.valueOf(hour);
            }
        }
        String startMinute = String.valueOf(minute);
        if (minute < 10) {
            startMinute = "0" + minute;
        }
        String am_pm;
        if (hour < 12) {
            am_pm = "AM";
        }
        else {
            am_pm = "PM";
        }
        return startHour + ":" + startMinute + " " + am_pm;
    }
}