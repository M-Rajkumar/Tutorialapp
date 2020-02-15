package com.oyp.recyclerview.itemtouchhelper.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.oyp.recyclerview.itemtouchhelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SlideCheckActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SlideAdapter slideSorterAdapter;
    ArrayList<SlideGroupDomain> slideSorterArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_check);


        recyclerView = findViewById(R.id.recyclerView);

        slideSorterAdapter = new SlideAdapter(SlideCheckActivity.this, slideSorterArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(SlideCheckActivity.this, 5, GridLayoutManager.HORIZONTAL,false));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setHasFixedSize(true);
        groupsetfuntion();

        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback(slideSorterAdapter, false, false);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {

            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getLayoutPosition() != 0) {
                    SlideGroupDomain slideSorter =slideSorterArrayList.get(viewHolder.getAdapterPosition());
//                    if (slideSorter != null) {
//                        if (slideSorter.isDragEnable()) {
//                            itemTouchHelper.startDrag(viewHolder);
//                        }
//                    }
                    itemTouchHelper.startDrag(viewHolder);
                }
            }
        });

//        recyclerView.setAdapter(slideSorterAdapter);
//        slideSorterAdapter.notifyDataSetChanged();


    }

    private void groupsetfuntion() {




        slideSorterArrayList = new ArrayList<>();

            ArrayList<SliderItemDomain> sec1 = new ArrayList<>();
            sec1.add(new SliderItemDomain(R.drawable.serrano, "First cell"));
//        sec1.add(new SliderItemDomain(R.drawable.my_report, "My Report"));
            slideSorterArrayList.add(new SlideGroupDomain(1, sec1));

            ArrayList<SliderItemDomain> sec2 = new ArrayList<>();
            sec2.add(new SliderItemDomain(R.drawable.serrano, "Secong cell"));
//        sec2.add(new SliderItemDomain(R.drawable.sidenotification, "Notifications"));
            slideSorterArrayList.add(new SlideGroupDomain(2, sec2));

            ArrayList<SliderItemDomain> sec3 = new ArrayList<>();
            sec3.add(new SliderItemDomain(R.drawable.serrano, "Grp cell one"));
            sec3.add(new SliderItemDomain(R.drawable.serrano, "Grp cell two"));
            sec3.add(new SliderItemDomain(R.drawable.serrano, "Grp cell three"));
            sec3.add(new SliderItemDomain(R.drawable.serrano, "Grp cell three"));
            sec3.add(new SliderItemDomain(R.drawable.serrano, "Grp cell three"));
            sec3.add(new SliderItemDomain(R.drawable.serrano, "Grp cell three"));
            slideSorterArrayList.add(new SlideGroupDomain(3, sec3));

            SlideAdapter adapter = new SlideAdapter(this, slideSorterArrayList);
        recyclerView.setAdapter(adapter);


    }
}
