package com.oyp.recyclerview.itemtouchhelper.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.oyp.recyclerview.itemtouchhelper.R;

import java.util.ArrayList;

public class SlideSingleAdapter extends RecyclerView.Adapter<SlideSingleAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SliderItemDomain> list;


    SlideSingleAdapter(ArrayList<SliderItemDomain> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideSingleAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slidesingleitemlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String label = list.get(position).getLabel_name();
        String selectedurl = list.get(position).getIcon_selected();
        String clickaction = list.get(position).getClick_action();


        if (clickaction.equals("")) {
            clickaction = list.get(position).getName();
            holder.imv.setImageResource(list.get(position).getIcon());
            holder.text.setText(list.get(position).getName());

        } else {

            holder.text.setText(label);

            setImage(context, selectedurl, R.drawable.serrano, holder.imv);
        }

//        if (clickaction.equals("")) {
//            clickaction = list.get(position).getName();
//            holder.imv.setImageResource(list.get(position).getIcon());
//            holder.text.setText(list.get(position).getName());
//
//        } else {
//
//            holder.text.setText(label);
//
//            CommonUtils.setImage(context, selectedurl, R.drawable.app_icon, holder.imv);
//        }
//
//        if (position == list.size() - 1) {
//            holder.line.setVisibility(View.GONE);
//        }
//
//        final String finalClickaction = clickaction;
//        holder.sidemenusinglelayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                sidemenuInterface.clickSideMenuItem(finalClickaction);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        TextView text;
        View line;
        LinearLayout sidemenusinglelayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.draweritemicom);
            text = itemView.findViewById(R.id.draweritemname);
            line = itemView.findViewById(R.id.linebelow);
            sidemenusinglelayout = itemView.findViewById(R.id.sidemenusinglelayout);
        }
    }

    public static void setImage(Context context, String url, int placeholder, ImageView imageView) {

        if (!((Activity) context).isFinishing()) {
            Glide.with(context).load(url)
                    .apply(new RequestOptions()
                            .fitCenter().placeholder(placeholder)
                            .override(512, 512))
                    .into(imageView);
        }

    }
}
