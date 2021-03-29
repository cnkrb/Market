package com.cenkkaraboa.marketynetimuygulamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GecmisDetailsActivity extends AppCompatActivity {

    TextView sube,kat,altkat,marka,urun,tur,adet,fiyat;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecmis_details);

        kat=findViewById(R.id.kat);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        altkat=findViewById(R.id.altkat);
        marka=findViewById(R.id.marka);
        urun=findViewById(R.id.urun);
        adet=findViewById(R.id.adet);
        tur=findViewById(R.id.tur);
        fiyat=findViewById(R.id.fiyat);
        try {


            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                kat.setText(bundle.getString("kat", "boş"));
                altkat.setText(bundle.getString("altkat", "boş"));
                marka.setText(bundle.getString("marka", "boş"));
                urun.setText(bundle.getString("urun", "boş"));
                tur.setText(bundle.getString("tur", "boş"));
                adet.setText(bundle.getString("adet", "boş"));
                fiyat.setText(bundle.getString("fiyat", "boş"));
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}