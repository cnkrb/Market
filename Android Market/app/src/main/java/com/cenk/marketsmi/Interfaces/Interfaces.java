package com.cenk.marketsmi.Interfaces;


import com.cenk.marketsmi.Models.CategoriModel;
import com.cenk.marketsmi.Models.Code;
import com.cenk.marketsmi.Models.Info;
import com.cenk.marketsmi.Models.Price;
import com.cenk.marketsmi.Models.Product;
import com.cenk.marketsmi.Models.SearchAltCategori;
import com.cenk.marketsmi.Models.SearchCategori;
import com.cenk.marketsmi.Models.Siparis;
import com.cenk.marketsmi.Models.Sube;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface Interfaces {

    @GET("api/getHakkimda")
    Call<List<Info>> getHakkimda();

    @GET("api/getOdeme")
    Call<List<Price>> getOdeme();

    @GET("api/getSubes")
    Call<List<Sube>> getSubes();

    @FormUrlEncoded
    @POST("api/getCategories")
    Call<List<CategoriModel>> getCategories(@Field("sube_id") Integer sube_id);

    @FormUrlEncoded
    @POST("api/searchKategori")
    Call<List<SearchCategori>> searchKategori(@Field("sube_id") Integer sube_id,@Field("katagori_id") String katagori_id, @Field("old") Integer old);

    @FormUrlEncoded
    @POST("api/searchAltKategori")
    Call<List<SearchAltCategori>> searchAltKategori(@Field("sube_id") Integer sube_id,@Field("altkatagori_id") String altkatagori_id, @Field("old") Integer old);

    @FormUrlEncoded
    @POST("api/getAsc")
    Call<List<Product>> getAsc(@Field("sube_id") Integer sube_id,@Field("old") Integer old);

    @FormUrlEncoded
    @POST("api/getAllAsc")
    Call<List<Product>> getAllAsc(@Field("sube_id") Integer sube_id);

    @FormUrlEncoded
    @POST("api/getSiparis")
    Call<List<Siparis>> getSiparis(@Field("sube_id") Integer sube_id, @Field("device") String device);

    @FormUrlEncoded
    @POST("api/search")
    Call<List<Product>> search(@Field("search") String search,@Field("old") Integer old,@Field("sube_id") Integer sube_id);

    @FormUrlEncoded
    @POST("api/postSiparis")
    Call<Code> postSiparis(@Field("onesignal") String onesignal,@Field("device_id") String device_id, @Field("telefon") String telefon, @Field("username") String username, @Field("address") String address, @Field("title") String title,@Field("sepet") String sepet,@Field("odeme_tipi") Integer odeme_tipi,@Field("sube_id") Integer sube_id);

}
