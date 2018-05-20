package com.example.mzreikat.firstpro.displayinfotabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mzreikat.firstpro.recyclerandlistviews.ListViewActivity;
import com.example.mzreikat.firstpro.R;
import com.example.mzreikat.firstpro.ReadContactsActivity;
import com.example.mzreikat.firstpro.recyclerandlistviews.RecyclerViewActivity;

public class Tab3Fragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        Button listViewBtn = view.findViewById(R.id.tab3_showListView);
        listViewBtn.setOnClickListener(v -> {
            Intent intent1 = new Intent(this.getActivity(), ListViewActivity.class);
            startActivity(intent1);
        });

        Button recyclerViewBtn = view.findViewById(R.id.tab3_showRecyclerView);
        recyclerViewBtn.setOnClickListener(v -> {
            Intent intent2 = new Intent(this.getActivity(), RecyclerViewActivity.class);
            startActivity(intent2);
        });

        Button readContactsBtn = view.findViewById(R.id.tab3_showContacts);
        readContactsBtn.setOnClickListener(v -> {
            Intent intent3 = new Intent(this.getActivity(), ReadContactsActivity.class);
            startActivity(intent3);
        });

        return view;
    }
}