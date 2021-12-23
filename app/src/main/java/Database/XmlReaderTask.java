package Database;

import android.os.AsyncTask;
import android.os.Handler;


import androidx.recyclerview.widget.SortedList;

import java.io.IOException;
import java.kitchenapp.Order;
import java.kitchenapp.SO;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class XmlReaderTask extends AsyncTask<Void, Void, Void> {
    public Kitchenapp2s tableList = new Kitchenapp2s();
    public Handler handler;
    final int MILLISECONDS_BETWEEN_UPDATES = 1000;
    ArrayList<Order> array = new ArrayList<Order>();
    protected Void doInBackground(Void... voids) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofitView = new Retrofit.Builder()//http://localhost:8080/Anton2/webresources/se.miun.entities.menuitem
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        RestViewKitchen service = retrofitView.create(RestViewKitchen.class);
        Call<Kitchenapp2s> listKitchen = service.listKitchen();
        try {

            Response<Kitchenapp2s> result = listKitchen.execute();
            tableList = result.body();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }

    protected void onPostExecute(Void result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                SortedList<Order> items = SO.s.getOrders();
                boolean done = false;
                boolean isNew = true;
                for(int i = 0; i < tableList.kitchenTable.size(); i++){//loopar igenom databas
                    if(Integer.valueOf(tableList.kitchenTable.get(i).foodtype) == 2)//color
                        done = true;
                    for(int j = 0; j < items.size() && isNew == true; j++){//loopar igenom befintilg
                        if(items.get(j).getId() == tableList.kitchenTable.get(i).id){
                            isNew = false;
                        }
                    }
                    if(isNew) {
                        int id = tableList.kitchenTable.get(i).id;
                        if(id != 1){
                            int tableNr = tableList.kitchenTable.get(i).tablenr;
                            String foodName = tableList.kitchenTable.get(i).foodname;
                            int time = tableList.kitchenTable.get(i).time;
                            String timeStampS = tableList.kitchenTable.get(i).timestamp;
                            Long timeStamp = Long.parseLong(tableList.kitchenTable.get(i).timestamp);
                            SO.s.addOrder(new Order(tableNr, foodName, time, timeStamp, done, id));
                        }
                    }
                    isNew = true;
                    done = false;
                }
            }
        });


    }

    /*protected void onPostExecute(Void result) {
        //Problemet är att tableList inte uppdateras
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        SortedList<Order> items = SO.s.getOrders();
                        boolean done = false;
                        boolean isNew = true;
                        for(int i = 0; i < tableList.kitchenTable.size(); i++){
                            if(Integer.valueOf(tableList.kitchenTable.get(i).foodtype) == 2)
                                done = true;
                            for(int j = 0; j < items.size(); j++){
                                if(items.get(j).getPriority() == (int) Long.parseLong(tableList.kitchenTable.get(i).timestamp)){
                                    isNew = false;
                                }
                            }
                            if(isNew) {
                                SO.s.addOrder(new Order(tableList.kitchenTable.get(i).tablenr,
                                        tableList.kitchenTable.get(i).foodname, tableList.kitchenTable.get(i).time,
                                        (int) Long.parseLong(tableList.kitchenTable.get(i).timestamp), done));
                            }
                            isNew = true;
                            done = false;
                        }

                    }
                });
                handler2.postDelayed(this, MILLISECONDS_BETWEEN_UPDATES);
            }
        }, MILLISECONDS_BETWEEN_UPDATES);
    }*/
}
