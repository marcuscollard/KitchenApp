package java.kitchenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


// fills Recycler view with myViewHolders(which is the model of order_row_layout)

public class OrdersCustomAdapter extends RecyclerView.Adapter<OrdersCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<Order> orders;

    OrdersCustomAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
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
        holder.tableNumber.setText(String.valueOf(orders.get(position).getTableNumber()));
        holder.name.setText(String.valueOf(orders.get(position).getName()));
        holder.time.setText(String.valueOf(orders.get(position).getTime()));
        //holder.finished_status??
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tableNumber, name, time;
        Button finished_status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tableNumber = itemView.findViewById(R.id.tableNumber);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            finished_status = itemView.findViewById(R.id.finished_status);
        }
    }
}
