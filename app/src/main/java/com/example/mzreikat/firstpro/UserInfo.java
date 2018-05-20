package com.example.mzreikat.firstpro;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mzreikat.firstpro.displayinfotabs.PageAdapter;
import com.example.mzreikat.firstpro.userinfotabs.RegistrationFragment;
import com.example.mzreikat.firstpro.userinfotabs.RootBookAptFragment;
import com.example.mzreikat.firstpro.userinfotabs.ShowInfoFragment;
import com.example.mzreikat.firstpro.userinfotabs.AppointmentInfo;

public class UserInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private static final String TAG = "UserInfoActivity";
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Toolbar toolbar = findViewById(R.id.user_info_toolBar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.user_info_layout_id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        NavigationView navigationView = findViewById(R.id.user_info_navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        Log.d(TAG, "onCreate : Starting");
        //PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.user_info__viewPager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.user_info__tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.user_info_layout_id);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            switch (tabLayout.getSelectedTabPosition())
            {
                case 0:
                    super.onBackPressed();
                    break;
                case 1:
                    TabLayout.Tab tab = tabLayout.getTabAt(0);
                    tab.select();
                    break;
                case 2:
                    if (!AppointmentInfo.ai.isInLayout()) {
                        getSupportFragmentManager().popBackStack();
                        break;
                    } else {
                        TabLayout.Tab tabb = tabLayout.getTabAt(1);
                        tabb.select();
                        break;
                    }

                default:
                    break;
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());

        adapter.addFragment(new RegistrationFragment(), "Registration");
        adapter.addFragment(new ShowInfoFragment(), "View Info");
        adapter.addFragment(new RootBookAptFragment(), "Book an Appointment");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.navMenu_home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                HomeActivity.fa.finish();
                break;
            case R.id.navMenu_notification:
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navMenu_setting:
                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                break;
            case R.id.navMenu_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                String shareSubj = "FirstPro app";
                String shareBody = "Try our app today on play store";

                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubj);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;
            case R.id.navMenu_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfo.this);
                builder.setTitle("Do you want to Logout?")
                        .setMessage("Are you sure?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent logoutIntent = new Intent(UserInfo.this, LoginActivity.class);
                                startActivity(logoutIntent);
                                finish();
                                HomeActivity.fa.finish();
                            }
                        })
                        .setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
        return false;
    }
}