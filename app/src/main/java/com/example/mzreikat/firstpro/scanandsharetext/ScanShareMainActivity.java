package com.example.mzreikat.firstpro.scanandsharetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mzreikat.firstpro.R;

public class ScanShareMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_share_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.scanShareMain_container, new ScanShareFragment())
                .commit();
    }
}