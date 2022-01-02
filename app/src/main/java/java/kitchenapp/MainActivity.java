package java.kitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import Database.RetrofitGet;

public class MainActivity extends AppCompatActivity {

    // database connection...
    final int MILLISECONDS_BETWEEN_UPDATES = 1000;
    public static Handler handler2 = new Handler();
    public static RetrofitGet retrofitGet = new RetrofitGet();
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

        //Button refreshButton = findViewById(R.id.refreshButton);
        RecyclerView recycler = findViewById(R.id.recycler);

        SO.s.customAdapter = new OrdersCustomAdapter(MainActivity.this);
        recycler.setAdapter(SO.s.customAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SortedList<Order> items = SO.s.getOrders();

        /*refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitGet = new RetrofitGet();
                retrofitGet.viewList = null;
                retrofitGet.kitchenList = null;
                retrofitGet.handler = new Handler();
                retrofitGet.execute();
            }
        });*/

        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                retrofitGet = new RetrofitGet();
                retrofitGet.viewList = null;
                retrofitGet.kitchenList = null;
                retrofitGet.handler = new Handler();
                retrofitGet.execute();
                handler2.postDelayed(this, MILLISECONDS_BETWEEN_UPDATES);
            }
        }, MILLISECONDS_BETWEEN_UPDATES);


    }
}