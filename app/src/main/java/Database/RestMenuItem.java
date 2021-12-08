package Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestMenuItem {
    @Headers({
            "Accept: application/xml"
    })
    @GET("se.miun.enities.menuitem")
    Call<MenuItems> listNames();
}
