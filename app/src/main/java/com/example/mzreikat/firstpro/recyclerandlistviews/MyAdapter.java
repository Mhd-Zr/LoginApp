package com.example.mzreikat.firstpro.recyclerandlistviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mzreikat.firstpro.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<RecyclerViewListItem> listItems;
    private Context context;

    public MyAdapter(List<RecyclerViewListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recyclerview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        RecyclerViewListItem recycleListItem = listItems.get(position);

        holder.imageView.setImageResource(recycleListItem.getImage());
        holder.textViewHead.setText(recycleListItem.getHead());
        holder.textViewDesc.setText(recycleListItem.getDesc());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked " + recycleListItem.getHead(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView  textViewHead;
        private TextView  textViewDesc;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView    = itemView.findViewById(R.id.recycler_imageView);
            textViewHead = itemView.findViewById(R.id.textViewHeading);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);

            relativeLayout = itemView.findViewById(R.id.recycler_relativeLayout);
        }
    }
}