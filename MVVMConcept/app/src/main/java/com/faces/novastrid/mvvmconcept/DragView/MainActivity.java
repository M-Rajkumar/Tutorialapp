package com.faces.novastrid.mvvmconcept.DragView;


import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.faces.novastrid.mvvmconcept.R;
import com.faces.novastrid.mvvmconcept.databinding.MainActivityBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnDragListener {

    private MainActivityBinding mainActivityBinding;
    public ObservableArrayList<ExcercisePojo> exerciseList;
    public ObservableArrayList<ExcercisePojo> exerciseSelectedList = new ObservableArrayList<>();
    public ExcercisePojo exerciseToMove;
    private int newContactPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_drag);
        loadExerciseData();
        mainActivityBinding.setMainActivity(this);
        mainActivityBinding.rcvSelectedExercise.setOnDragListener(this);
    }


    public void loadExerciseData() {
        exerciseList = new ObservableArrayList<>();
        for (int i = 1; i <= 10; i++) {
            exerciseList.add(new ExcercisePojo(i, "exercise " + i));
        }
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        View selectedView = (View) dragEvent.getLocalState();
        RecyclerView rcvSelected = (RecyclerView) view;
        int currentPosition = -1;
        try {
            currentPosition = mainActivityBinding.rcvChooseExercise.getChildAdapterPosition(selectedView);

            // Ensure the position is valid.
            if (currentPosition != -1) {
                exerciseToMove = exerciseList.get(currentPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_LOCATION:
                View onTopOf = rcvSelected.findChildViewUnder(dragEvent.getX(), dragEvent.getY());
                newContactPosition = rcvSelected.getChildAdapterPosition(onTopOf);
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                //when Item is dropped off to recyclerview.
                exerciseSelectedList.add(exerciseToMove);
                exerciseList.remove(exerciseToMove);
                mainActivityBinding.rcvChooseExercise.getAdapter().notifyItemRemoved(currentPosition);
                mainActivityBinding.executePendingBindings();

                //This is to hide/add the container!
                /*ViewGroup owner = (ViewGroup) (view.getParent());
                if (owner != null) {
                    //owner.removeView(selectedView);
                    //owner.addView(selectedView);

                    try {
                        LinearLayout rlContainer = (LinearLayout) view;
                        rlContainer.addView(selectedView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //selectedView.setVisibility(View.VISIBLE);
                }*/

                break;

            case DragEvent.ACTION_DRAG_ENDED:
                // Reset the visibility for the Contact item's view.
                // This is done to reset the state in instances where the drag action didn't do anything.
                selectedView.setVisibility(View.VISIBLE);
                // Boundary condition, scroll to top is moving list item to position 0.
                if (newContactPosition != -1) {
                    rcvSelected.scrollToPosition(newContactPosition);
                    newContactPosition = -1;
                } else {
                    rcvSelected.scrollToPosition(0);
                }
            default:
                break;
        }
        return true;
    }
}