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

public class XmlReaderTask extends AsyncTask<Void, Void, TableList> {
    Order order;
    TableList tableList = new TableList();
    //String responseText;
    Handler handler;
    protected TableList doInBackground(Void... voids) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //logging.level(HttpLoggingInterceptor.Level.BODY);   // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/AntonsOrder/webresources/")//http://10.0.2.2:8080/WebbTest2/webresources/    http://localhost:8080/WebbTest2/webresources/se.miun.register
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();
        NameService service = retrofit.create(NameService.class);
        Call<TableList> listName = service.listNames();
        try {

            Response<TableList> result = listName.execute();
            tableList = result.body();

            //responseText = tableList.bordorder.get(0).foodname;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tableList;

    }
    protected void onPostExecute(TableList result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                order = new Order(result.bordorder.get(0).tablenr,
                    result.bordorder.get(0).foodname, result.bordorder.get(0).bordpriority);
                SO.s.orders.add(order);
            }
        });


    }
}
