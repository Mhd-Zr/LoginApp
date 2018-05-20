package com.example.mzreikat.firstpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mzreikat.firstpro.login.LoginFragment;
import com.example.mzreikat.firstpro.login.NewUserFragment;
import com.example.mzreikat.firstpro.login.RootLoginFragment;
import com.example.mzreikat.firstpro.userinfotabs.AppointmentInfo;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.loginActId, new RootLoginFragment())
                .commit();
    }
    @Override
    public void onBackPressed() {
        if (NewUserFragment.nuf == null) {
            finish();
        }
        else {
            if ((!NewUserFragment.nuf.isInLayout())) {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}