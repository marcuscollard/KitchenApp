package java.kitchenapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NameService {
    @Headers({
            "Accept: application/xml"
    })
    @GET("se.miun.bordorder")
    Call<TableList> listNames();
}
