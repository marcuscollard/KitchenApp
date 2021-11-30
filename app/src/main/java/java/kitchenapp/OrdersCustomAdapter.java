package java.kitchenapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;


// fills Recycler view with myViewHolders(which is the model of order_row_layout)

public class OrdersCustomAdapter extends RecyclerView.Adapter<OrdersCustomAdapter.MyViewHolder> {

    Context context;
    //private SortedList<Order> orders;

    OrdersCustomAdapter(Context context) {
        this.context = context;
    }

//    public void addAll(ArrayList<Order> orders) {
//        this.orders.addAll(orders);
//    }
//
//    public int add(Order order) {
//        return this.orders.add(order);
//    }

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
        holder.order = order;
    }

    @Override
    public int getItemCount() {
        return SO.s.orders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tableNumber, name, time;
        Button finished_status;
        Order order;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tableNumber = itemView.findViewById(R.id.tableNumber);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            //finished_status = itemView.findViewById(R.id.finished_status);

            finished_status = itemView.findViewById(R.id.finished_status);

            finished_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "clicked " + getAdapterPosition() + " aka " + order.getTableNumber());
                    Log.d("demo", "duplicate");
                    // create new identical order

                }
            });
        }
    }
}








