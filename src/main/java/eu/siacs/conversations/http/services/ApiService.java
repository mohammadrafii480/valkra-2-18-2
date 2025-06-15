package eu.siacs.conversations.http.services;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/v1/user/register")
    Call<JsonObject> CreateAccount(@Body RequestBody body);

    @GET("api/v1/user/get-user")
    Call<JsonObject> getUser(@Query("email") String email,
                             @Query("appCode") String appCode);

    @PATCH("api/v1/user/update-password")
    Call<JsonObject> UpdatePassword(@Body RequestBody body);

    @POST("api/v2/forgot/send-link")
    Call<JsonObject> ForgotPassword(@Body RequestBody body);

    @DELETE("api/v1/user/unregister")
    Call<JsonObject> UnregisterAccount(@Query("email") String email,
                                       @Query("appCode") String appCode,
                                       @Header("Authorization") String authHeader);
}
