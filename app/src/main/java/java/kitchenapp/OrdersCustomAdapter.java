package java.kitchenapp;

import android.content.Context;
import android.graphics.Color;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;


// fills Recycler view with myViewHolders(which is the model of order_row_layout)

public class OrdersCustomAdapter extends RecyclerView.Adapter<OrdersCustomAdapter.MyViewHolder> {

    Context context;

    OrdersCustomAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersCustomAdapter.MyViewHolder holder, int position) {
        Order order = SO.s.orders.get(position);
        holder.tableNumber.setText(String.valueOf(order.getTableNumber()));
        holder.name.setText(String.valueOf(order.getName()));
        holder.time.setText(String.valueOf(order.getTime()));
        holder.finished_status.setText(holder.BUTTON_NOT_DONE_TEXT);
        holder.order = order;
        holder.notes.setText(order.getNotes());

        // show notes if they exist for order
        final boolean isExpanded = !order.getNotes().equals("");
        holder.notes.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);

        // update button on redraw
        if(order.isDone()) {
            holder.finished_status.setText(holder.BUTTON_DONE_TEXT);
        } else {
            holder.finished_status.setText(holder.BUTTON_NOT_DONE_TEXT);
        }
        if(order.isStarter())
        {
            holder.layout.setBackgroundColor(0xFF00FF00);
        }
    }

    @Override
    public int getItemCount() {
        return SO.s.orders.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // constants
        final String BUTTON_DONE_TEXT = "\u2713 done";
        final String BUTTON_NOT_DONE_TEXT = "not done";

        // internal variables
        TextView tableNumber, name, time;
        Button finished_status;
        Order order;
        TextView notes;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // set internal variables
            tableNumber = itemView.findViewById(R.id.tableNumber);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            finished_status = itemView.findViewById(R.id.finished_status);
            layout = itemView.findViewById(R.id.orderLinearLayout);
            notes = itemView.findViewById(R.id.notes);

            // "DONE" button
            finished_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Order temp = SO.s.orders.get(getAdapterPosition());
                    if(order.isDone()) {
                        order.setDoneAs(false);
                        finished_status.setText(BUTTON_NOT_DONE_TEXT);
                    } else {
                        order.setDoneAs(true);
                        finished_status.setText(BUTTON_DONE_TEXT);

                        if(allOrdersDone()) {
                            Log.d("DB", "skicka bord: " + order.getTableNumber());
                        }
                    }
                }
            });
        }

        // check if all orders for a table are done
        private boolean allOrdersDone() {
            int tableToSearch = order.getTableNumber();

            for (int i = 0; i < SO.s.orders.size(); i++) {
                Order o = SO.s.orders.get(i);
                if(o.getTableNumber() == tableToSearch) {
                    if(!o.isDone()) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}








