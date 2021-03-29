package com.cenkkaraboa.marketynetimuygulamas.Interfaces;


import com.cenkkaraboa.marketynetimuygulamas.Models.AdminSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Gecmis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Kurye;
import com.cenkkaraboa.marketynetimuygulamas.Models.Marka;
import com.cenkkaraboa.marketynetimuygulamas.Models.Odeme;
import com.cenkkaraboa.marketynetimuygulamas.Models.Product;
import com.cenkkaraboa.marketynetimuygulamas.Models.Siparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeAltKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Urun;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface Interfaces {


    @FormUrlEncoded
    @POST("api/getSiparis")
    Call<List<Siparis>> getSiparis(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("api/subeurun/urunaltkatagori")
    Call<List<Product>> urunaltkatagori(@Field("sube_id") Integer sube_id, @Field("altkatagori_id") Integer altkatagori_id);

    @FormUrlEncoded
    @POST("api/subeurun/urunkatagori")
    Call<List<Product>> urunkatagori(@Field("sube_id") Integer sube_id, @Field("katagori_id") Integer katagori_id);

    @FormUrlEncoded
    @POST("api/subeurun/urunMarka")
    Call<List<Product>> urunMarka(@Field("sube_id") Integer sube_id, @Field("marka_id") Integer marka_id);



    @GET("api/sube")
    Call<List<Sube>> sube();

    @GET("api/hakkimda/{id}")
    Call<Sube> hakkimda(@Path(value = "id", encoded = true) String id);


    @GET("api/subesiparis/{id}")
    Call<SubeSiparis> subesiparis(@Path(value = "id", encoded = true) String id);

    @GET("api/subealtkatagori/{id}")
    Call<List<SubeAltKategori>> subealtkatagori(@Path(value = "id", encoded = true) String id);

    @GET("api/subemarka/{id}")
    Call<List<Marka>> subemarka(@Path(value = "id", encoded = true) String id);

    @GET("api/subeurun/{id}")
    Call<List<Urun>> subeurun(@Path(value = "id", encoded = true) String id);

    @GET("api/subekatagori/{id}")
    Call<SubeKategori> subekatagori(@Path(value = "id", encoded = true) String id);

   @GET("api/urungecmisi/{id}")
    Call<List<Gecmis>> urungecmisi(@Path(value = "id", encoded = true) String id);


    @GET("api/user")
    Call<List<User>> user();

    @GET("api/kasiyer")
    Call<List<User>> kasiyer();

    @GET("api/odeme")
    Call<List<Odeme>> odeme();

    @GET("api/kurye")
    Call<List<Kurye>> kurye();

    @GET("api/adminsiparis")
    Call<List<AdminSiparis>> adminsiparis();

    @GET("api/muhasebe")
    Call<List<AdminSiparis>> muhasebe();


}
