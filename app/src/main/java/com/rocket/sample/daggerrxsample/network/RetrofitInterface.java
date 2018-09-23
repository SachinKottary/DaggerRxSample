package com.rocket.sample.daggerrxsample.network;

import com.rocket.sample.daggerrxsample.model.DeliveryDetails;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @GET()
    Observable<ArrayList<DeliveryDetails>> getDeliveryDetails(@Url String url, @QueryMap Map<String, String> options);

}
