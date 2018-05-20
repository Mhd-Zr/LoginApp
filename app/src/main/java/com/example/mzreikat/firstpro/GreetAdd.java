package com.example.mzreikat.firstpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GreetAdd extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet_add);

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.greetAdd_textView);
        textView.setText(intent.getStringExtra("Email"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.greetAdd_messageEditText);


        EditText firstN  = findViewById(R.id.greetAdd_firstNumEditText);
        EditText secondN = findViewById(R.id.greetAdd_secondNumEditText);

        long result = 0;


        String part1;
        String part2;

        try {
            // checking valid integer using parseInt() method
            String input1 = firstN.getText().toString();

            Long.parseLong(input1);
            part1 = "valid";
            result += Long.parseLong(input1);
        }
        catch (NumberFormatException e) {
            part1 = "invalid";
        }

        try {
            // checking valid integer using parseInt() method
            String input2 = secondN.getText().toString();

            Long.parseLong(input2);
            part2 = "valid";
            result += Long.parseLong(input2);
        }
        catch (NumberFormatException e) {
            part2 = "invalid";
        }

        String message = editText.getText().toString();
        if (part1.equals("valid") && part2.equals("valid")) {
            message = message + ",Result:," + result;
        }

        else {
            message = message + "Enter a valid inputs";
        }

        intent.putExtra("greetAct", message);
        intent.putExtra("activity", "GreetAdd");
        startActivity(intent);
    }
}