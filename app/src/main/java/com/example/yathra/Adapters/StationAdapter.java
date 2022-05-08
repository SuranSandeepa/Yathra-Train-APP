package com.example.yathra.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.StationModel;
import com.example.yathra.Model.TrainModel;
import com.example.yathra.R;
import com.example.yathra.UpdateStation;
import com.example.yathra.UpdateTrain;

import java.util.ArrayList;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationVH>{

    ArrayList<StationModel> stations;
    Context context;

    public StationAdapter(ArrayList<StationModel> stations, Context context) {
        this.stations = stations;
        this.context = context;
    }

    @NonNull
    @Override
    public StationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_one_station, parent, false);
        StationVH stationVH = new StationVH(view);

        return stationVH;
    }

    @Override
    public void onBindViewHolder(@NonNull StationVH holder, int position) {

        final StationModel stationM = stations.get(position);
        holder.sName.setText(stationM.getStationName());
        holder.mName.setText(stationM.getMasterName());
        holder.cName.setText(stationM.getCity());

        holder.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(context, UpdateStation.class);
                intent1.putExtra("StationModel", stationM);
                context.startActivity(intent1);
            }
        });

        holder.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirmation!");
                builder.setMessage("Are you sure to delete " + stationM.getStationName() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);

                //Yes
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(context);

                        int result = DBHelper.deleteStations(stationM.getStationID());

                        if(result > 0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            stations.remove(stationM);
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
        return stations.size();
    }

    class StationVH extends RecyclerView.ViewHolder{

        TextView sName, mName, cName;
        CardView cardUpdate, cardDelete;

        public StationVH(@NonNull View itemView) {
            super(itemView);

            sName = itemView.findViewById(R.id.stationDisplayST);
            mName = itemView.findViewById(R.id.stationMasterDisplayST);
            cName = itemView.findViewById(R.id.cityDisplayST);
            cardUpdate = itemView.findViewById(R.id.CardUpdateRowDisplayST);
            cardDelete = itemView.findViewById(R.id.CardDeleteRowDisplayST);
        }
    }
}
