package Database;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @Headers({"Content-Type: application/json"})
    @PUT("se.miun.enities.kitchenorder/{id}")
    Call<Kitchenorder> updateKitchen(@Path("id") int id, @Body Kitchenorder body);

    @Headers({"Accept: application/xml"})
    @GET("se.miun.enities.kitchenapp2")
    Call<Kitchenapp2s> viewList();

    @Headers({"Accept: application/json"})
    @GET("se.miun.enities.kitchenorder")
    Call<List<Kitchenorder>> kitchenList();

}
