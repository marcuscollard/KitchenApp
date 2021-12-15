package java.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import Database.XmlReaderTask;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // database connection...
    final int MILLISECONDS_BETWEEN_UPDATES = 10000;

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


        // Handler is similar to async

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            int timeStepCounter = 1; // to sort by when the order arrived, assigned as Order.priority

            Random rand = new Random();
            int testTableNumber;

            @Override
            public void run() {
                //FROM DATABASE
//                XmlReaderTask xmlReaderTask = new XmlReaderTask();
//                xmlReaderTask.tableList = null;
//                xmlReaderTask.handler = new Handler();//Håller  koll på trådsom är ansvar för  nätverk
//                xmlReaderTask.execute();

                //FROM TESTARRAY
                if(SO.s.orders.size() < 10) {
                    testTableNumber = rand.nextInt(7) + 1;
                    ArrayList<Order> test_orders = new ArrayList<Order>(5);
                    test_orders.add(new Order(testTableNumber, "McDonald's from next door", 20, timeStepCounter, false));
                    test_orders.add(new Order(testTableNumber, "fish", 10, timeStepCounter, true));
                    test_orders.add(new Order(testTableNumber, "Gött mos", 20, timeStepCounter, true));
                    test_orders.add(new Order(testTableNumber, "Wok med kyckling", 20, timeStepCounter, false));
                    test_orders.add(new Order(testTableNumber, "Surströmming", 10, timeStepCounter, false));
                    test_orders.add(new Order(testTableNumber, "Köttbullar", 1, timeStepCounter, false));
                    test_orders.add(new Order(testTableNumber, "Toast skagen", 2, timeStepCounter, true));


                    test_orders.get(1).setNotes("double cheese burger and hold the lettuce");
                    SO.s.addOrders(test_orders);
                }
                timeStepCounter = timeStepCounter+1;
                handler.postDelayed(this, MILLISECONDS_BETWEEN_UPDATES);
            }
        }, MILLISECONDS_BETWEEN_UPDATES);



    }
}