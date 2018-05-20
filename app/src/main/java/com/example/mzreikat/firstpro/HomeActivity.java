package com.example.mzreikat.firstpro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mzreikat.firstpro.database.DBHelper;
import com.example.mzreikat.firstpro.scanandsharetext.ScanShareMainActivity;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener
{
    private TextToSpeech tts;

    @SuppressLint("StaticFieldLeak")
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fa = this;
        tts = new TextToSpeech(this, this);

        setupBtns();

        /*DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {"email", "pass", "number"};

        @SuppressLint("Recycle")
        Cursor cursor = database.query("emailsInfo", projection, null, null, null, null, null);
        cursor.moveToPosition(0);
        System.out.println("pass is: " + cursor.getString(1));
        *//*SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email", "moh@roh.com");
        values.put("pass", "241");
        values.put("number", "3");

        long row = database.insert("emailsInfo", null, values);
        System.out.println("row number is: " + row);*/
    }

    public void setupBtns() {
        Button fillInfoBtn = findViewById(R.id.home_fillInfoBtn);
        fillInfoBtn.setOnClickListener(this);

        Button writeMsgBtn = findViewById(R.id.home_writeMsgBtn);
        writeMsgBtn.setOnClickListener(this);

        Button lunchMapsBtn = findViewById(R.id.home_lunchMap);
        lunchMapsBtn.setOnClickListener(this);

        Button speakBtn = findViewById(R.id.home_speakBtn);
        speakBtn.setOnClickListener(this);

        Button scanBtn = findViewById(R.id.home_scanShareTextBtn);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent previousDataIntent = getIntent();
        String email  = previousDataIntent.getStringExtra("Email");

        Intent intent, chooser;
        switch (id) {
            case R.id.home_fillInfoBtn:
                intent = new Intent(HomeActivity.this, UserInfo.class);
                intent.putExtra("Email", "Information about\n" + email);
                startActivity(intent);
                break;
            case R.id.home_writeMsgBtn:
                intent = new Intent(HomeActivity.this, GreetAdd.class);
                intent.putExtra("Email", email + "'s message");
                startActivity(intent);
                break;
            case R.id.home_lunchMap:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:19.076,72.8777"));
                chooser = Intent.createChooser(intent, "Lunch Maps");
                startActivity(chooser);
                break;
            case R.id.home_speakBtn:
                speakOut();
                break;
            case R.id.home_scanShareTextBtn:
                intent = new Intent(HomeActivity.this, ScanShareMainActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        //shutting down the tts
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS)
        {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            else { tts.setSpeechRate((float) 0.9);}
        }
        else {
            Log.e("TTS", "Initialization Failed");
        }
    }

    private void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void speakOut() {
        EditText editText = findViewById(R.id.home_speakTxt);
        String text = editText.getText().toString();
        if (textContainsArabic(text)) {
            Toast.makeText(this, "Not supported yet", Toast.LENGTH_SHORT).show();
            text = "Not supported yet";
        }
        else {
            if (text.trim().equals("")) {
                Toast.makeText(this, "Enter some text", Toast.LENGTH_SHORT).show();
                text = "Enter some text";
            }
        }
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public static boolean textContainsArabic(String text) {
        for (char charac : text.toCharArray()) {
            if (Character.UnicodeBlock.of(charac) == Character.UnicodeBlock.ARABIC) {
                return true;
            }
        }
        return false;
    }
}