package com.cenk.marketsmi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.R;

public class OrderActivity extends AppCompatActivity {
    ImageView back,refresh;
    Integer position;
    private String android_id="cihaz";
    Boolean aBoolean=false;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String store=preferences.getString("store","null");
        position=preferences.getInt("position",0);
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        back=findViewById(R.id.back);
        refresh=findViewById(R.id.refresh);
        info=findViewById(R.id.editSearch);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToFragment(new OrderFragment());
            }
        });
        moveToFragment(new OrderFragment());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void  changeScreen(Integer position){
        refresh.setVisibility(View.GONE);
        Fragment fragment=new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", position);
        fragment.setArguments(bundle);
        info.setText("Sipariş Detayı");
        aBoolean=true;
        moveToFragment(fragment);
    }


    @Override
    public void onBackPressed() {
        if(aBoolean){
            info.setText("Siparişlerim");
            aBoolean=false;
            refresh.setVisibility(View.VISIBLE);
            moveToFragment(new OrderFragment());
        }else {
            finish();
        }
        Animatoo.animateFade(OrderActivity.this);
    }

    public void moveToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}