package com.example.mzreikat.firstpro.recyclerandlistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<RecyclerViewListItem> listItems;

    private EditText insertText;
    private EditText removeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        createList();
        buildRecyclerView();

        insertText = findViewById(R.id.recyclerView_insert);
        removeText = findViewById(R.id.recyclerView_remove);

        Button insertBtn = findViewById(R.id.recyclerView_insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(insertText.getText().toString())-1;
                if (position > listItems.size()) {
                    Toast.makeText(RecyclerViewActivity.this, "You only have " + listItems.size(), Toast.LENGTH_SHORT).show();
                }
                else {
                    if (position < 0) {
                        position++;
                    }
                    Toast.makeText(RecyclerViewActivity.this, (position+1)+" added", Toast.LENGTH_LONG).show();
                    insertItem(position);
                }
            }
        });

        //Remove Button//
        Button removeBtn = findViewById(R.id.recyclerView_removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(removeText.getText().toString())-1;
                if (position >= listItems.size()) {
                    Toast.makeText(RecyclerViewActivity.this, "You only have " + listItems.size(), Toast.LENGTH_SHORT).show();
                }
                else {
                    if (position < 0) {
                        position++;
                    }
                    Toast.makeText(RecyclerViewActivity.this, (position+1)+" removed", Toast.LENGTH_LONG).show();
                    removeItem(position);
                }
            }
        });
    }

    public void insertItem(int position) {
        listItems.add(position, new RecyclerViewListItem(R.drawable.ic_new, "New heading " + (position+1), "New item #" + (position+1)));
        adapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listItems.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Setting the adapter
        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }

    public void createList() {
        int images[] = {R.drawable.ic_android, R.drawable.ic_audiotrack,
                R.drawable.ic_computer, R.drawable.ic_local_phone,
                R.drawable.ic_android, R.drawable.ic_audiotrack,
                R.drawable.ic_computer, R.drawable.ic_local_phone,
                R.drawable.ic_android, R.drawable.ic_audiotrack,
                R.drawable.ic_computer, R.drawable.ic_local_phone};

        listItems = new ArrayList<>();
        for (int i = 0; i <= 11; i++) {
            RecyclerViewListItem listItem = new RecyclerViewListItem(images[i], "Heading " + (i+1),"Item #" + (i+1));
            listItems.add(listItem);
        }
    }
}