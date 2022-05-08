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
import com.example.yathra.Model.TimetableModel;
import com.example.yathra.R;
import com.example.yathra.UpdateTimetable;

import java.util.ArrayList;

public class TimetableAdapter extends  RecyclerView.Adapter<TimetableAdapter.TimetableVH> {

    ArrayList<TimetableModel>timetables;
    Context context;

    public TimetableAdapter(ArrayList<TimetableModel> timetables, Context context) {
        this.timetables = timetables;
        this.context = context;
    }

    @NonNull
    @Override
    public TimetableVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_one_timetable, parent, false);
        TimetableVH tvh = new TimetableVH(view);

        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableVH holder, int position) {

        final TimetableModel tm = timetables.get(position);
        holder.routeName.setText(tm.getrName());
        holder.tDate.setText(tm.getDate());
        holder.arriveTime.setText(tm.getaTime());
        holder.departTime.setText(tm.getdTime());
        holder.trainName.setText(tm.gettName());

        holder.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Toast.makeText(context, tm.getrName() + "Will Be Updated", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(context, UpdateTimetable.class);
                intent1.putExtra("TimetableModel", tm);
                context.startActivity(intent1);
            }
        });

        holder.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, tm.getrName() + "Will Be Deleted", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirmation!");
                builder.setMessage("Are you sure to delete " + tm.getrName() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);

                //Yes
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(context);

                        int result = DBHelper.deleteTimetable(tm.getId());

                        if(result > 0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            timetables.remove(tm);
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
        return timetables.size();
    }

    class TimetableVH extends RecyclerView.ViewHolder {

        TextView routeName, tDate, arriveTime, departTime, trainName;
        CardView cardUpdate, cardDelete;

        public TimetableVH(@NonNull View v) {
            super(v);

            routeName = v.findViewById(R.id.RouteNameRowTimetable);
            tDate = v.findViewById(R.id.DateRowTimetable);
            arriveTime = v.findViewById(R.id.ArriveTimeRowTimetable);
            departTime = v.findViewById(R.id.DepartTimeRowTimetable);
            trainName = v.findViewById(R.id.TrainNameRowTimetable);
            cardUpdate = v.findViewById(R.id.CardUpdateRowDisplay);
            cardDelete = v.findViewById(R.id.CardDeleteRowDisplay);

        }
    }

}
