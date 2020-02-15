package com.oyp.recyclerview.itemtouchhelper.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oyp.recyclerview.itemtouchhelper.R;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SlideGroupDomain> list;


    public SlideAdapter(Context context, ArrayList<SlideGroupDomain> list
                         ) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slideitemlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.recycler.setLayoutManager(new GridLayoutManager(context, 3));
        holder.recycler.setHasFixedSize(true);

        SlideSingleAdapter adapter=new SlideSingleAdapter(list.get(position).getList(),context);

        holder.recycler.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }else
            return 0;
    }

    public List<SlideGroupDomain> getDataList() {
        return list;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycler;
        ViewHolder(View itemView) {
            super(itemView);
            recycler=itemView.findViewById(R.id.drawer_item_recycler);
        }
    }
}