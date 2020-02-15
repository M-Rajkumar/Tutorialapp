package com.oyp.recyclerview.itemtouchhelper.view;

import java.util.ArrayList;

public class SlideGroupDomain {
    private int id;
    private ArrayList<SliderItemDomain> list;

    public SlideGroupDomain(int id, ArrayList<SliderItemDomain> list) {
        this.id = id;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<SliderItemDomain> getList() {
        return list;
    }

    public void setList(ArrayList<SliderItemDomain> list) {
        this.list = list;
    }
}
