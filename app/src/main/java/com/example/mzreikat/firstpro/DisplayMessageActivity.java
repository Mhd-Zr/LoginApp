package com.example.mzreikat.firstpro;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mzreikat.firstpro.displayinfotabs.PageAdapter;
import com.example.mzreikat.firstpro.displayinfotabs.Tab1Fragment;
import com.example.mzreikat.firstpro.displayinfotabs.Tab2Fragment;
import com.example.mzreikat.firstpro.displayinfotabs.Tab3Fragment;

public class DisplayMessageActivity extends AppCompatActivity
{
    private static final String TAG = "DisplayMessageActivity";
    private PageAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Log.d(TAG, "onCreate : Starting");

        pageAdapter = new PageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.display_viewPager);
        setupViewPage(viewPager);

        TabLayout tabLayout = findViewById(R.id.display_tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPage(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "User Information");
        adapter.addFragment(new Tab2Fragment(), "Message");
        adapter.addFragment(new Tab3Fragment(), "Lists");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        TabLayout tabLayout = findViewById(R.id.display_tabLayout);

        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
                super.onBackPressed();
                break;
            case 1:
                TabLayout.Tab tab = tabLayout.getTabAt(0);
                tab.select();
                break;
            case 2:
                TabLayout.Tab tab1 = tabLayout.getTabAt(1);
                tab1.select();
        }
    }
}