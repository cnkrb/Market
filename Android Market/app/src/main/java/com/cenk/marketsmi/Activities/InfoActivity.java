package com.cenk.marketsmi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.Models.Sube;
import com.cenk.marketsmi.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenk.marketsmi.Utils.Utils.interfaces;

public class InfoActivity extends AppCompatActivity {


    ImageView imageView,back;
    TextView name,address,number,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView=findViewById(R.id.image);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        number=findViewById(R.id.number);
        time=findViewById(R.id.time);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getStores();
    }


    public void getStores() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Integer position=preferences.getInt("pos",-1);
        Callback<List<Sube>> listCallBack = new Callback<List<Sube>>() {
            @Override
            public void onResponse(Call<List<Sube>> call, Response<List<Sube>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        if(position==-1){

                        }else {
                           try {
                               name.setText(response.body().get(position).getSubeAdi());
                               address.setText(response.body().get(position).getAdres().toString());
                               number.setText(response.body().get(position).getTelefon().toString());
                               time.setText(response.body().get(position).getServis().toString());
                               Picasso.with(getApplicationContext())
                                       .load("https://market.cenkkaraboa.com/public/images/hakkimda/"+response.body().get(position).getGorselUrl())
                                       .into(imageView);
                           }catch (Exception e){

                           }

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sube>> call, Throwable t) {

            }
        };
        interfaces.getSubes().enqueue(listCallBack);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateFade(InfoActivity.this); //fire the slide left animation

    }
}