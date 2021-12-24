package java.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import Database.Kitchenapp2s;
import Database.XmlReaderTask;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // database connection...
    final int MILLISECONDS_BETWEEN_UPDATES = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INITIAL HARDCODED TEST ARRAY
        //ArrayList<Order> test_orders = new ArrayList<Order>(5);
        //test_orders.add(new Order(1, "fish", 20));
        //test_orders.add(new Order(3, "potato", 5));
        //test_orders.add(new Order(5, "salad", 5));
        //test_orders.add(new Order(3, "McDonald's from next door", 10));
        //test_orders.add(new Order(2, "chicken sushi", 10));

        //test_orders.get(2).setNotes("testetstsetestestestsetse");

        //SO.s.addOrders(test_orders);


        RecyclerView recycler = findViewById(R.id.recycler);

        SO.s.customAdapter = new OrdersCustomAdapter(MainActivity.this);
        recycler.setAdapter(SO.s.customAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SortedList<Order> items = SO.s.getOrders();

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                XmlReaderTask xmlReaderTask = new XmlReaderTask();
                xmlReaderTask.tableList = null;
                xmlReaderTask.handler = new Handler();//Håller  koll på trådsom är ansvar för  nätverk
                xmlReaderTask.execute();

                handler2.postDelayed(this, MILLISECONDS_BETWEEN_UPDATES);
            }
        }, MILLISECONDS_BETWEEN_UPDATES);


    }
}