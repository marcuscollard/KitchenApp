package Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestViewKitchen {
    @Headers({
            "Accept: application/xml"
    })
    @GET("se.miun.enities.kitchenapp2")
    Call<Kitchenapp2s> listKitchen();
}
