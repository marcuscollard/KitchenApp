package Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestResturang {
    @Headers({
            "Accept: application/xml"
    })
    @GET("se.miun.enities.resturangorder")
    Call<Resturangorders> listResturang();
}
