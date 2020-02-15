package com.faces.novastrid.mvvmconcept.DragView;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.faces.novastrid.mvvmconcept.R;
import com.faces.novastrid.mvvmconcept.databinding.ChooseExerciseItemBinding;
import com.faces.novastrid.mvvmconcept.databinding.SelectedExerciseItemBinding;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ProjectHolder> {

    private ObservableList<ExcercisePojo> exerciseObservableList = new ObservableArrayList<>();
    private Context context;
    private RecyclerView recyclerExercise;
    private int layoutId;

    public ExerciseListAdapter(RecyclerView recyclerExercise, ObservableArrayList<ExcercisePojo> exerciseObservableList, int layoutId) {
        this.exerciseObservableList = exerciseObservableList;
        this.recyclerExercise = recyclerExercise;
        this.layoutId = layoutId;
    }

    @Override
    public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        context = parent.getContext();
        return new ProjectHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseListAdapter.ProjectHolder holder, int position) {
        final ExcercisePojo excercisePojo = exerciseObservableList.get(position);
        if (layoutId == R.layout.layout_choose_exercise_item) {
            ChooseExerciseItemBinding chooseExerciseItemBinding = (ChooseExerciseItemBinding) holder.chooseExerciseItemBinding;
            chooseExerciseItemBinding.setExercise(excercisePojo);
            chooseExerciseItemBinding.setChooseExerciseListAdapter(this);
        } else {
            SelectedExerciseItemBinding selectedExerciseItemBinding = (SelectedExerciseItemBinding) holder.chooseExerciseItemBinding;
            selectedExerciseItemBinding.setExercise(excercisePojo);
            selectedExerciseItemBinding.setChooseExerciseListAdapter(this);
        }
        holder.chooseExerciseItemBinding.executePendingBindings();
    }



    @Override
    public int getItemCount() {
        if (exerciseObservableList == null) {
            return 0;
        }
        return exerciseObservableList.size();
    }

    public class ProjectHolder extends RecyclerView.ViewHolder {
        public ViewDataBinding chooseExerciseItemBinding;

        public ProjectHolder(View itemView) {
            super(itemView);
            chooseExerciseItemBinding = DataBindingUtil.bind(itemView);
        }
    }


    public boolean onLongClick(View view) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        return true;
    }

   /* public void onListItemClick(View view) {
        Intent intent = new Intent(context, ExerciseCategoryDetail.class);
        Bundle bundle = new Bundle();
        int position = recyclerExercise.getChildLayoutPosition(view);
        //Utils.makeShortToast(context, "pos : " + position);
        ExerciseCategoryPojo excercisePojo = exerciseObservableList.get(position);
        bundle.putParcelable(Constants.EXCERCISE_CATEGORY_OBJ, excercisePojo);
        bundle.putBoolean(Constants.CAN_ADD_EXERCISE, true);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
*/

}