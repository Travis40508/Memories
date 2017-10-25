package com.example.travistressler.memories.Util.GoogleApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by travistressler on 10/25/17.
 */

public interface GoogleGeoApi {
    @GET("json")
    Call<GoogleAddress> getAddress(@Query("address") String address, @Query("api_key") String apiKey);
}
