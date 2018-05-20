package com.example.mzreikat.firstpro.recyclerandlistviews;

public class RecyclerViewListItem
{
    private int image;
    private String head;
    private String desc;

    public RecyclerViewListItem(int image, String head, String desc) {
        this.image = image;
        this.head  = head;
        this.desc  = desc;
    }

    public int getImage() {
        return image;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}