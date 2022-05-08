package com.example.yathra.Adapters;

import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TicketModel;
import com.example.yathra.R;
import com.example.yathra.UpdateTicket;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketAdapter extends  RecyclerView.Adapter<TicketAdapter.TicketVH> {

    ArrayList<TicketModel> tickets;
    Context context;

    public TicketAdapter(ArrayList<TicketModel> tickets, Context context) {
        this.tickets = tickets;
        this.context = context;
    }

    @NonNull
    @Override
    public TicketVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_one_ticket, parent, false);
        TicketVH ticketVH = new TicketVH(view);

        return ticketVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketVH holder, int position) {

        final TicketModel ticketM = tickets.get(position);
        holder.tvDate.setText(ticketM.getDate());
        holder.tvTime.setText(ticketM.getTime());
        holder.tvClass.setText(ticketM.getClassType());
        holder.tvFrom.setText(ticketM.getFrom());
        holder.tvTo.setText(ticketM.getTo());
        holder.tvPrice.setText(ticketM.getPrice());

        holder.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ticketM.getDate() + " Ticket will be updatd", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, UpdateTicket.class);
                intent.putExtra("TicketModel", ticketM);
                context.startActivity(intent);

            }
        });

        holder.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ticketM.getDate() + " Ticket will be Deleted", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirmation !!!");
                builder.setMessage("Are you Sure want to delete " + ticketM.getDate() + " Ticket");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(context);

                        int result = DBHelper.deleteTickets(ticketM.getId());

                        if(result > 0){
                            Toast.makeText(context, "Ticket Deleted" + result, Toast.LENGTH_SHORT).show();
                            tickets.remove(ticketM);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    class TicketVH extends RecyclerView.ViewHolder {

        TextView tvDate, tvTime, tvClass, tvFrom, tvTo, tvPrice;
        CardView cardUpdate, cardDelete;

        public TicketVH(@NonNull View view) {
            super(view);

            tvDate = view.findViewById(R.id.DateRowTicket);
            tvTime = view.findViewById(R.id.TimeRowTicket);
            tvClass = view.findViewById(R.id.ClassRowTicket);
            tvFrom = view.findViewById(R.id.FromRowTicket);
            tvTo = view.findViewById(R.id.ToRowTicket);
            tvPrice = view.findViewById(R.id.PriceRowTicket);

            cardUpdate = view.findViewById(R.id.CardUpdateRowDisplayTicket);
            cardDelete = view.findViewById(R.id.CardDeleteRowDisplayTicket);

        }
    }
}
