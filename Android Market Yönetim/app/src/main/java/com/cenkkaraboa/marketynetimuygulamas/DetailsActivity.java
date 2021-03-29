package com.cenkkaraboa.marketynetimuygulamas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.AdminSiparisAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.BasketAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.AdminSiparisFragment;
import com.cenkkaraboa.marketynetimuygulamas.Models.AdminSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.GetSepet;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;

public class DetailsActivity extends AppCompatActivity {

    View view;
    Integer myInt;
    private String android_id="cihaz";
    TextView number,date,price,address,mobile,statement,totalPrice;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        number=findViewById(R.id.number);
        date=findViewById(R.id.date);
        price=findViewById(R.id.price);
        address=findViewById(R.id.address);
        mobile=findViewById(R.id.mobile);
        totalPrice=findViewById(R.id.total);
        statement=findViewById(R.id.statament);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            myInt = bundle.getInt("position");
        }
        adminsiparis();
    }

    public void adminsiparis() {
        Callback<List<AdminSiparis>> listCallBack = new Callback<List<AdminSiparis>>() {
            @Override
            public void onResponse(Call<List<AdminSiparis>> call, Response<List<AdminSiparis>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        try {
                            List<GetSepet> basketList ;
                            basketList = response.body().get(myInt).getGetSepet();
                            number.setText("Sipariş numaraıs gelevek");
                            date.setText(response.body().get(myInt).getCreatedAt());
                            price.setText(response.body().get(myInt).getOdemeId().toString());
                            address.setText(response.body().get(myInt).getAdres());
                            mobile.setText(response.body().get(myInt).getTelefon());
                            statement.setText(response.body().get(myInt).getSiparisAciklama());

                            BasketAdapter productAdapter = new BasketAdapter(basketList, getApplicationContext(),true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(productAdapter);
                            productAdapter.notifyDataSetChanged();
                            double total = 0.0;
                            for (int i = 0; i < basketList.size(); i++) {
                                total = total + Double.parseDouble(basketList.get(i).getAdet()) * Double.parseDouble(basketList.get(i).getSatisFiyat());
                            }
                            totalPrice.setText(round(total,2)+" TL");
                        }catch (Exception e){

                        }


                    }

                }
            }

            @Override
            public void onFailure(Call<List<AdminSiparis>> call, Throwable t) {
            }
        };
        interfaces.adminsiparis().enqueue(listCallBack);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}