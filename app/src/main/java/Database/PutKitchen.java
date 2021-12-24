package Database;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PutKitchen {
    @Headers({"Content-Type: application/json"})
    @PUT("se.miun.enities.kitchenorder/{id}")
    Call<Kitchenorder> updateUser(@Path("id") int id, @Body Kitchenorder body);
}
