package java.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import Database.XmlReaderTask;

public class MainActivity extends AppCompatActivity {

    // database connection...

    //ArrayList<Order> orders;

    void addNew(Order order) {
        SO.s.orders.add(order);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // FROM HARDCODED TEST ARRAY
        ArrayList<Order> test_orders = new ArrayList<Order>(5);
        test_orders.add(new Order(1, "fish", 20));
        test_orders.add(new Order(3, "potato", 5));
        test_orders.add(new Order(5, "salad", 5));
        test_orders.add(new Order(3, "McDonald's from next door", 10));
        test_orders.add(new Order(2, "chicken sushi", 10));

        RecyclerView recycler = findViewById(R.id.recycler);

//        SO.s.context = MainActivity.this;


        SO.s.customAdapter = new OrdersCustomAdapter(MainActivity.this);
        recycler.setAdapter(SO.s.customAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SO.s.addOrders(test_orders);

        // FROM DATABASE
//        XmlReaderTask xmlReaderTask = new XmlReaderTask();
//        xmlReaderTask.tableList = null;
//        xmlReaderTask.handler = new Handler();//Håller  koll på trådsom är ansvar för  nätverk
//        xmlReaderTask.execute();

        //SO.s.orders.add(new Order(2, "test", 0));


        // Test delete after 5 seconds

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SO.s.orders.clear();
//            }
//        }, 5000);

    }
    //TEST
    // Wofflans wåffeltest
}