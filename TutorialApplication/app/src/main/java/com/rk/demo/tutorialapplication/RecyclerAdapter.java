package com.rk.demo.tutorialapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public ArrayList<RecycleModel>recycleModels;
    public Context context;
    String teamidstr,teamnamestr;

    public RecyclerAdapter(Context context,ArrayList<RecycleModel> recycleModels) {
        this.context=context;
        this.recycleModels = recycleModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        teamidstr = recycleModels.get(i).getId();
        teamnamestr = recycleModels.get(i).getTeam();

        holder.teamid.setText(teamidstr);
        holder.teamname.setText(teamnamestr);

    }



    @Override
    public int getItemCount() {
        if (recycleModels != null) {
            return recycleModels.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView teamid,teamname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamid = itemView.findViewById(R.id.textViewid);
            teamname = itemView.findViewById(R.id.textViewname);
        }
    }
}
