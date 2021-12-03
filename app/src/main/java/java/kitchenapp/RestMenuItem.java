package java.kitchenapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestMenuItem {
    @Headers({
            "Accept: application/xml"
    })
    @GET("se.miun.entities.menuitem")//mac
    //@GET("se.miun.enities.menuitem")//windows
    Call<MenuItems> listNames();
}
