package com.example.yathra.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TrainModel;
import com.example.yathra.R;
import com.example.yathra.UpdateTimetable;
import com.example.yathra.UpdateTrain;

import java.util.ArrayList;

public class TrainAdapter extends  RecyclerView.Adapter<TrainAdapter.TrainVH>{

    ArrayList<TrainModel> trains;
    Context context;

    public TrainAdapter(ArrayList<TrainModel> trains, Context context) {
        this.trains = trains;
        this.context = context;
    }

    @NonNull
    @Override
    public TrainVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_one_train, parent, false);
        TrainVH trainVH = new TrainVH(view);

        return trainVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainVH holder, int position) {

        final TrainModel trainM = trains.get(position);
        holder.trainName.setText(trainM.getTrainName());
        holder.passengers.setText(trainM.getNoOfPassengers());
        holder.category.setText(trainM.getTrainCategory());

        holder.trainUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(context, UpdateTrain.class);
                intent1.putExtra("TrainModel", trainM);
                context.startActivity(intent1);
            }
        });


        holder.trainDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirmation!");
                builder.setMessage("Are you sure to delete " + trainM.getTrainName() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);

                //Yes
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(context);

                        int result = DBHelper.deleteTrains(trainM.getId());

                        if(result > 0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            trains.remove(trainM);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //No
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return trains.size();
    }

    class TrainVH extends RecyclerView.ViewHolder {

        TextView trainName, passengers, category;
        CardView trainUpdate, trainDelete;

        public TrainVH(@NonNull View itemView) {
            super(itemView);

            trainName = itemView.findViewById(R.id.tvTrainNameTT);
            passengers = itemView.findViewById(R.id.tvPassengersTT);
            category = itemView.findViewById(R.id.tvCategoryTT);
            trainUpdate = itemView.findViewById(R.id.CardUpdateRowDisplayTT);
            trainDelete = itemView.findViewById(R.id.CardDeleteRowDisplayTT);
        }
    }
}
