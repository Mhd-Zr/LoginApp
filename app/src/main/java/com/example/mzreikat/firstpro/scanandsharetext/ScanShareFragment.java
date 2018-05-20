package com.example.mzreikat.firstpro.scanandsharetext;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanShareFragment extends Fragment implements View.OnClickListener {
    private SurfaceView surfaceView;
    private TextView textView;
    private CameraSource cameraSource;
    final int REQUESTED_CAMERA_PERMISSION_ID = 1001;

    public ScanShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_share, container, false);

        detectAutoRotate();

        surfaceView = rootView.findViewById(R.id.fragment_scan_share_surfaceView);
        textView = rootView.findViewById(R.id.fragment_scan_share_textView);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();

        if (!textRecognizer.isOperational()) {
            Log.w("ScanShareFragment", "Detector dependencies are not yet available");
        } else
        {
            cameraSource = new CameraSource.Builder(Objects.requireNonNull(getContext()), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUESTED_CAMERA_PERMISSION_ID);
                            return;
                        }
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            setupTextRecognizer(textRecognizer);
        }

        Button shareBtn = rootView.findViewById(R.id.fragment_scan_share_shareBtn);
        shareBtn.setOnClickListener(this);

        return rootView;
    }

    public void setupTextRecognizer(TextRecognizer textRecognizer) {
        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < items.size(); i++) {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }
                            textView.setText(stringBuilder.toString());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_scan_share_shareBtn:
                String currentText = textView.getText().toString();
                if (currentText.equals("") || currentText.equals("No Text")) {
                    Toast.makeText(getContext(), "No Text Detected!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String subject = "Detected using ScanAndShareText app";

                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, currentText);
                    startActivity(Intent.createChooser(intent, "Share Via"));
                }
        }
    }

    public void detectAutoRotate() {
        if (android.provider.Settings.System.getInt(Objects.requireNonNull(getActivity()).getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 0){
            Toast.makeText(getContext(), "Auto Rotate is OFF", Toast.LENGTH_LONG).show();
        }
    }
}