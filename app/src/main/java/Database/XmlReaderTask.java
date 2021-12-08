package Database;

import android.os.AsyncTask;
import android.os.Handler;


import java.io.IOException;
import java.kitchenapp.Order;
import java.kitchenapp.SO;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class XmlReaderTask extends AsyncTask<Void, Void, Void> {
    Order order;
    Order order2;
    public MenuItems tableList = new MenuItems();
    public Cookingtimes cookingTable = new Cookingtimes();
    public Resturangorders resturangTable = new Resturangorders();
    //String responseText;
    public Handler handler;
    protected Void doInBackground(Void... voids) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //logging.level(HttpLoggingInterceptor.Level.BODY);   // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofitMenuItem = new Retrofit.Builder()//http://localhost:8080/Anton2/webresources/se.miun.entities.menuitem
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        RestMenuItem service = retrofitMenuItem.create(RestMenuItem.class);
        Call<MenuItems> listName = service.listNames();
        try {

            Response<MenuItems> result = listName.execute();
            tableList = result.body();

            //responseText = tableList.bordorder.get(0).foodname;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Retrofit retrofitCooking = new Retrofit.Builder()//http://localhost:8080/Anton2/webresources/se.miun.entities.menuitem
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        RestCookingtime serviceCooking = retrofitCooking.create(RestCookingtime.class);
        Call<Cookingtimes> listCooking = serviceCooking.listCooking();
        try {

            Response<Cookingtimes> result = listCooking.execute();
            cookingTable = result.body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Retrofit retrofitResturang = new Retrofit.Builder()//http://localhost:8080/Anton2/webresources/se.miun.entities.menuitem
                .baseUrl("http://10.0.2.2:8080/Anton2/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        RestResturang serviceResturang = retrofitResturang.create(RestResturang.class);
        Call<Resturangorders> listResturang = serviceResturang.listResturang();
        try {

            Response<Resturangorders> result = listResturang.execute();
            resturangTable = result.body();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }
    protected void onPostExecute(Void result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<Order> array = new ArrayList<Order>();
                for(int i = 0; i < resturangTable.resturangorderTable.size(); i++){
                    order = new Order(resturangTable.resturangorderTable.get(i).tablenr,
                            tableList.menuItemList.get((resturangTable.resturangorderTable.get(i).dishid)-1).foodName,
                            Integer.valueOf(cookingTable.cookingtimeTable.get((resturangTable.resturangorderTable.get(i).dishid)-1).time));
                    array.add(order);
                }
                SO.s.addOrders(array);
            }
        });


    }
}
