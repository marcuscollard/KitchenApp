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

    void addNew(Order order) {
        SO.s.orders.add(order);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XmlReaderTask xmlReaderTask = new XmlReaderTask();
        xmlReaderTask.tableList = null;
        xmlReaderTask.handler = new Handler();//Håller  koll på trådsom är ansvar för  nätverk
        xmlReaderTask.execute();

        RecyclerView recycler = findViewById(R.id.recycler);

        //orders = Order.populateOrders();

        SO.s.orders.add(new Order(0, "", 0));
        SO.s.orders.add(new Order(0, "7777", 0));

        customAdapter = new OrdersCustomAdapter(MainActivity.this);
        recycler.setAdapter(customAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SO.s.orders.add(new Order(1, "", 0));


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