package java.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // database connection...

    ArrayList<Order> orders;
    OrdersCustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);

        orders = Order.populateOrders();

        customAdapter = new OrdersCustomAdapter(MainActivity.this, orders);
        recycler.setAdapter(customAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                customAdapter.orders.clear();
//            }
//        }, 5000);



    }
    //TEST
    // Wofflans wåffeltest
}