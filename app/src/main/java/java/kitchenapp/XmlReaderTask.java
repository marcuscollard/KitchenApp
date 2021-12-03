package java.kitchenapp;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;


import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class XmlReaderTask extends AsyncTask<Void, Void, MenuItems> {
    Order order;
    Order order2;
    MenuItems tableList = new MenuItems();
    //String responseText;
    Handler handler;
    protected MenuItems doInBackground(Void... voids) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //logging.level(HttpLoggingInterceptor.Level.BODY);   // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()//http://localhost:8080/Anton2/webresources/se.miun.entities.menuitem
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        RestMenuItem service = retrofit.create(RestMenuItem.class);
        Call<MenuItems> listName = service.listNames();
        try {

            Response<MenuItems> result = listName.execute();
            tableList = result.body();

            //responseText = tableList.bordorder.get(0).foodname;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tableList;

    }
    protected void onPostExecute(MenuItems result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                order = new Order(result.menuItemList.get(0).id,
                    result.menuItemList.get(0).foodName, result.menuItemList.get(0).price);
                SO.s.orders.add(order);
                order2 = new Order(result.menuItemList.get(1).id,
                        result.menuItemList.get(1).foodName, result.menuItemList.get(1).price);
                SO.s.orders.add(order2);
            }
        });


    }
}
