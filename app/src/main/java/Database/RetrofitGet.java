package Database;

import android.os.AsyncTask;
import android.os.Handler;


import androidx.recyclerview.widget.SortedList;

import java.io.IOException;
import java.kitchenapp.Order;
import java.kitchenapp.SO;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitGet extends AsyncTask<Void, Void, Void> {
    public Kitchenapp2s viewList = new Kitchenapp2s();
    public List<Kitchenorder> kitchenList =  new ArrayList<Kitchenorder>();
    public Handler handler;
    final int MILLISECONDS_BETWEEN_UPDATES = 1000;
    protected Void doInBackground(Void... voids) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().cache(null);
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofitView = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        Api service = retrofitView.create(Api.class);
        Call<Kitchenapp2s> listView = service.viewList();
        try {

            Response<Kitchenapp2s> result = listView.execute();
            viewList = result.body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Retrofit retrofitKitchen = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        Api serviceKitchen = retrofitKitchen.create(Api.class);
        Call<List<Kitchenorder>> listKitchen = serviceKitchen.kitchenList();
        try {

            Response<List<Kitchenorder>> result = listKitchen.execute();
            kitchenList = result.body();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }

    protected void onPostExecute(Void result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Long localTime = Long.parseLong("0");
                Long databaseTime;

                SortedList<Order> items = SO.s.getOrders();
                if(items.size() != 0)
                    localTime = items.get(0).getPriority();
                for(int i = 1; i < items.size(); i++){
                    if(localTime < items.get(i).getPriority())
                        localTime = items.get(i).getPriority();
                }

                databaseTime = Long.parseLong(viewList.viewTable.get(0).timestamp);
                for(int i = 1; i < viewList.viewTable.size(); i++){
                    if (databaseTime < Long.parseLong(viewList.viewTable.get(i).timestamp))
                        databaseTime = Long.parseLong(viewList.viewTable.get(i).timestamp);
                }

                if(databaseTime > localTime){
                    boolean color = false;


                    for(int i = 0; i < kitchenList.size(); i++){
                        if(!kitchenList.get(i).isDone() && kitchenList.get(i).getId() != 1){
                            for(int j = 0; j < viewList.viewTable.size(); j++){
                                if(kitchenList.get(i).getId()==viewList.viewTable.get(j).kitchenid){
                                    if(Integer.valueOf(viewList.viewTable.get(j).foodtype) == 2)//color
                                        color = true;

                                    boolean exist = false;
                                    int id = viewList.viewTable.get(j).id;
                                    for(int k = 0;k < items.size(); k++){
                                        int localOrderId = items.get(k).getId();
                                        if(id == localOrderId)
                                            exist = true;
                                    }
                                    if(!exist){
                                        int tableNr = viewList.viewTable.get(j).tablenr;
                                        String foodName = viewList.viewTable.get(j).foodname;
                                        int time = viewList.viewTable.get(j).time;
                                        Long timeStamp = Long.parseLong(viewList.viewTable.get(j).timestamp);
                                        int kitchenid = viewList.viewTable.get(j).kitchenid;
                                        boolean done = kitchenList.get(i).isDone();
                                        String notes = viewList.viewTable.get(j).notes;

                                        Order order = new Order(tableNr, foodName, time, timeStamp, color, id, kitchenid, done);
                                        if(notes != null){
                                            order.setNotes(notes);
                                        }
                                        SO.s.addOrder(order);
                                    }
                                    color = false;
                                }
                            }
                        }
                    }


                    /*for(int i = 0; i < viewList.viewTable.size(); i++){//loopar igenom databas
                        if(Integer.valueOf(viewList.viewTable.get(i).foodtype) == 2)//color
                            color = true;
                        if(!kitchenList.get(i).isDone()) {
                            int id = viewList.viewTable.get(i).id;
                            if(id != 1){
                                int tableNr = viewList.viewTable.get(i).tablenr;
                                String foodName = viewList.viewTable.get(i).foodname;
                                int time = viewList.viewTable.get(i).time;
                                Long timeStamp = Long.parseLong(viewList.viewTable.get(i).timestamp);
                                int kitchenid = viewList.viewTable.get(i).kitchenid;
                                boolean done = kitchenList.get(i).isDone();
                                String notes = viewList.viewTable.get(i).notes;
                                Order order = new Order(tableNr, foodName, time, timeStamp, color, id, kitchenid, done);
                                if(notes != null){
                                    order.setNotes(notes);
                                }
                                SO.s.addOrder(order);
                            }
                        }
                        color = false;
                    }*/
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
