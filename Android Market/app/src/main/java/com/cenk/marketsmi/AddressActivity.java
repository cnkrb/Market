package com.cenk.marketsmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.Activities.AddFragment;
import com.cenk.marketsmi.Activities.AddresFragment;
import com.cenk.marketsmi.Activities.InfoActivity;
import com.cenk.marketsmi.Activities.OrderFragment;

public class AddressActivity extends AppCompatActivity {
    ImageView back,add;
    TextView info;
   public Boolean aBoolean=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        moveToFragment(new AddresFragment());
        back=findViewById(R.id.back);
        add=findViewById(R.id.add);
        info=findViewById(R.id.info);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String addLoca = bundle.getString("add");

            if (addLoca != null) {
                aBoolean=true;
                add.setVisibility(View.GONE);
                info.setText("Yeni bilgi ekle");
                moveToFragment(new AddFragment());
            }
        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aBoolean=true;
                add.setVisibility(View.GONE);

                info.setText("Yeni bilgi ekle");
                moveToFragment(new AddFragment());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
    }

    public void change(){
        info.setText("Bilgilerim");
        aBoolean=false;
        add.setVisibility(View.VISIBLE);

        moveToFragment(new AddresFragment());
    }

    public void changeScreen(Integer position){
        aBoolean=true;
        add.setVisibility(View.GONE);
        info.setText("Bilgileri Düzenle");
        Fragment fragment=new AddFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", position);
        fragment.setArguments(bundle);
        moveToFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if(aBoolean){
            info.setText("Bilgilerim");
            add.setVisibility(View.VISIBLE);
            aBoolean=false;
            moveToFragment(new AddresFragment());
        }else {
            finish();
        }
        Animatoo.animateFade(AddressActivity.this); //fire the slide left animation
    }

    public void moveToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_address, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}