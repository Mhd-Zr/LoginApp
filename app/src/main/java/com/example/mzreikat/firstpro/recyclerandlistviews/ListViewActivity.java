package com.example.mzreikat.firstpro.recyclerandlistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity
{
    private ListView list_view_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        list_view_left = findViewById(R.id.listView_list);

        HashMap<String, String> map = new HashMap();
        map.put("Jama", "20");
        map.put("Mann", "18");
        map.put("Marcus", "21");
        map.put("Brian", "23");
        map.put("Jesus", "32");
        map.put("Ambrish", "38");
        map.put("Amer", "48");
        map.put("Qusai", "22");
        map.put("Yamen", "19");
        map.put("Adam", "26");
        map.put("Marten", "23");
        map.put("Rusty", "50");
        map.put("Destin", "60");
        map.put("David", "57");
        map.put("Samer", "13");

        List<HashMap<String, String>> mapsList = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, mapsList, R.layout.list_item_listview,
                new String[]{"First Line", "Second Line"}, new int[]{R.id.textV1, R.id.textV2});

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) iterator.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());

            mapsList.add(resultsMap);
        }

        list_view_left.setAdapter(adapter);

        list_view_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(ListViewActivity.this, map.get(mapsList.get(position)) + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}