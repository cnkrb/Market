package com.cenkkaraboa.marketynetimuygulamas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cenkkaraboa.marketynetimuygulamas.Fragments.GecmisFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.HakkimdaFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.MarkaFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.StokFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.SubeAltKategoriFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.SubeKategoriFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.SubeSiparisFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.UrunFragment;

public class SubeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back,search;
    LinearLayout home_view;
    public Boolean aBoolean=false;
    String subee;
    TextView hakkimda,sube,kategori,altkategori,marka,stok,urun,gecmis,rapor,siparis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sube);
        back=findViewById(R.id.back);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        subee=preferences.getString("store","Şube");
        hakkimda=findViewById(R.id.hakkimda);
        hakkimda.setOnClickListener(this);
        kategori=findViewById(R.id.kategori);
        search=findViewById(R.id.search);
        siparis=findViewById(R.id.siparis);
        siparis.setOnClickListener(this);
        kategori.setOnClickListener(this);
        altkategori=findViewById(R.id.altkategori);
        altkategori.setOnClickListener(this);
        marka=findViewById(R.id.marka);
        marka.setOnClickListener(this);
        stok=findViewById(R.id.stok);
        stok.setOnClickListener(this);
        urun=findViewById(R.id.urun);
        urun.setOnClickListener(this);
        gecmis=findViewById(R.id.gecmis);
        gecmis.setOnClickListener(this);
        rapor=findViewById(R.id.rapor);
        rapor.setOnClickListener(this);
        sube=findViewById(R.id.sube);
        sube.setText(subee);
        home_view=findViewById(R.id.home_view);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        hakkimda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_view.setVisibility(View.GONE);
                sube.setText(subee+ " Hakkımızda");
                aBoolean=true;
                moveToFragment(new HakkimdaFragment());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(aBoolean){
            sube.setText(subee);

            aBoolean=false;
            home_view.setVisibility(View.VISIBLE);
            super.onBackPressed();

        }else {
            finish();
        }
    }

    private void moveToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //    TextView hakkimda,sube,kategori,altkategori,marka,stok,urun,gecmis,rapor;
    @Override
    public void onClick(View view) {
        int id= view.getId();

        switch (id){

            case  R.id.hakkimda:
                sube.setText(subee+ " Hakkımızda");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new HakkimdaFragment());
                break;
            case  R.id.siparis:
                sube.setText(subee+ " Siparişler");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new SubeSiparisFragment());
                break;
            case  R.id.kategori:
                sube.setText(subee+ " Kategoriler");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new SubeKategoriFragment());
                break;
            case  R.id.altkategori:
                sube.setText(subee+ " AltKategoriler");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new SubeAltKategoriFragment());
                break;
            case  R.id.marka:
                sube.setText(subee+ " Markalar");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new MarkaFragment());
                //MarkaFragment
                break;
            case  R.id.stok:
                sube.setText(subee+ " Stok");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new StokFragment());
                break;
            case  R.id.urun:
                sube.setText(subee+ " Ürünler");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new UrunFragment());
                //UrunFragment
                break;
            case  R.id.gecmis:
                sube.setText(subee+ " Geçmiş");
                home_view.setVisibility(View.GONE);
                aBoolean=true;
                moveToFragment(new GecmisFragment());
                break;
            case  R.id.rapor:
                break;

        }
    }
}