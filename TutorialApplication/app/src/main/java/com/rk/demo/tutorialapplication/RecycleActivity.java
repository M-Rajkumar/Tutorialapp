package com.rk.demo.tutorialapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RecycleActivity extends AppCompatActivity {

    RecyclerAdapter recyclerAdapter;
    ArrayList<RecycleModel>recycleModels;


    RecyclerView recycle;
    String myobjectresponse;
    JSONObject response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recycle=findViewById(R.id.recycle);

        myobjectresponse=getIntent().getStringExtra("myobject");
        recyclerinit();

        try {
            response = new JSONObject(myobjectresponse);

            setmeetingdetails(response);


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }



    private void recyclerinit() {

        recycleModels = new ArrayList<>();

        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManagerWrapper(RecycleActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerAdapter = new RecyclerAdapter(RecycleActivity.this,recycleModels);
        recycle.setAdapter(recyclerAdapter);
    }

    private void setmeetingdetails(JSONObject response) {

        try {
            JSONArray teamArray = response.getJSONArray("teams");
            String id ="",team="";
            for (int i = 0; i <teamArray.length() ; i++) {
                JSONObject jsonObject= teamArray.getJSONObject(i);

                id = jsonObject.getString("id");
                 team = jsonObject.getString("team");

                recycleModels.add(new RecycleModel(id,team));
            }

            recyclerAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
