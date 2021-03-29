package com.cenk.marketsmi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.Adapters.InfoAdapter;
import com.cenk.marketsmi.AddressActivity;
import com.cenk.marketsmi.MainActivity;
import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.Code;
import com.cenk.marketsmi.Models.Info;
import com.cenk.marketsmi.Models.InfoModel;
import com.cenk.marketsmi.Models.Price;
import com.cenk.marketsmi.R;
import com.cenk.marketsmi.database.BasketDatabase;
import com.cenk.marketsmi.database.InfoDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.onesignal.OneSignal;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenk.marketsmi.Utils.Utils.interfaces;

public class PaymentActivity extends AppCompatActivity implements InfoAdapter.OnItemClickListener {
    Button send;
    ImageView back;
    RecyclerView recyclerView;

    Integer value=0;
    EditText statement;
    private String android_id="cihaz";

    String username="",address="",number="";
    Integer position;
    InfoAdapter infoAdapter;
    CardView add;
    TextView danger;
    Button addStore;
    MaterialSpinner spinner;
    List<Price> priceList;
    Button adAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String store=preferences.getString("store","null");
        position=preferences.getInt("position",0);
        back=findViewById(R.id.back);
        send=findViewById(R.id.send);
        adAddress=findViewById(R.id.adAddress);
        adAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("add", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        add=findViewById(R.id.add);
        addStore=findViewById(R.id.addStore);
        addStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("add", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        danger=findViewById(R.id.danger);
        statement=findViewById(R.id.statement);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value!=0 &&  statement.getText().length()>0 && username.length()>0  && address.length()>0  && number.length()>0 ){
                    postSiparis();
                }else {
                    Toast.makeText(getApplicationContext(),"Boş yerleri doldurun",Toast.LENGTH_SHORT).show();
                }
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        ;
        getOdeme();
        spinner = (MaterialSpinner) findViewById(R.id.spinner);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(), StoreActivity.class));
                Animatoo.animateFade(PaymentActivity.this);
                finish();
            }
        });
    }


    public void getOdeme() {
        Callback<List<Price>> listCallBack = new Callback<List<Price>>() {
            @Override
            public void onResponse(Call<List<Price>> call, Response<List<Price>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        System.out.println(response.body().size()+"sakmsamkldsakldmlşkömsadfklşsadlşksadsamklfklmsadlklmSALK");
                        String[] price =new String[response.body().size()+1];
                        priceList=response.body();
                        price[0]="Ödeme Tipi Seçiniz";
                        for(int i=0;i<response.body().size();i++){
                            price[i+1]=response.body().get(i).getOdemeTipi();
                        }
                        System.out.println(price.length);
                        spinner.setItems(price);
                        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                                  if(price[position].equals("Ödeme Tipi Seçiniz")){
                                      value=0;
                                  }else {
                                      value=priceList.get(position-1).getId();
                                  }

                                System.out.println(value);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Price>> call, Throwable t) {
            }
        };
        interfaces.getOdeme().enqueue(listCallBack);
    }


    public void postSiparis() {
        String data="";
        List<Basket> basketList=new ArrayList<>();
        basketList=products();

        for(int i=0;i<basketList.size();i++){
            if(i==basketList.size()-1){
                data=data+basketList.get(i).getId() + "," +basketList.get(i).getX();
            }else{
                data=data+basketList.get(i).getId() + "," +basketList.get(i).getX()+"+";
            }
        }
        String UUID = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();

        Callback<Code> listCallBack = new Callback<Code>() {
            @Override
            public void onResponse(Call<Code> call, Response<Code> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.body().getStatus_code(),Toast.LENGTH_SHORT).show();
                    new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Sipariş Başarılı!")
                            .setConfirmText("Tamam")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    finish();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                    BasketDatabase db = new BasketDatabase(getApplicationContext());
                    db.resetTables();
                    db.close();

                }
            }
            @Override
            public void onFailure(Call<Code> call, Throwable t) {
                System.out.println(t.getCause());
                System.out.println(t.getMessage()+"cececece");
            }
        };
        interfaces.postSiparis(UUID,android_id,number,username,address,statement.getText().toString(),
                data,(value),position).enqueue(listCallBack);
    }


    @Override
    protected void onResume() {
        super.onResume();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        if (getProducts().size()==0) {
            add.setVisibility(View.VISIBLE);
        }else {
            add.setVisibility(View.GONE);

            infoAdapter = new InfoAdapter(getProducts(), this,true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(infoAdapter);
            infoAdapter.setOnItemClickListener(PaymentActivity.this);
            infoAdapter.notifyDataSetChanged();
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);

        }
    }

    public List<InfoModel> getProducts(){
        List<InfoModel> basketList=new ArrayList<>();
        ArrayList<HashMap<String, String>> products;
        InfoDatabase db = new InfoDatabase(getApplicationContext());
        products = db.getAddress();
        if (products.size() != 0) {
            for(int i=0;i<products.size();i++){
                InfoModel infoModel=new InfoModel();
                infoModel.setUsername(products.get(i).get("name"));
                infoModel.setAddress(products.get(i).get("address"));
                infoModel.setNumber(products.get(i).get("number"));
                basketList.add(infoModel);
            }
        }
        return basketList;
    }

    @Override
    public void onItemClick(int position) {
        List<InfoModel> infoModels=getProducts();
        if(username.equals(infoModels.get(position).getUsername())){
            infoAdapter.notifyDataSetChanged();
        }
        username=infoModels.get(position).getUsername();
        number=infoModels.get(position).getNumber();
        address=infoModels.get(position).getAddress();

    }

    public List<Basket> products(){
        List<Basket> basketList=new ArrayList<>();
        ArrayList<HashMap<String, String>> products;
        BasketDatabase db = new BasketDatabase(getApplicationContext());
        products = db.products();
        if (products.size() != 0) {
            for(int i=0;i<products.size();i++){
                Basket basket=new Basket();
                basket.setName(products.get(i).get("name"));
                basket.setX(products.get(i).get("x"));
                basket.setId(products.get(i).get("ca"));
                basket.setPrice(products.get(i).get("price"));
                basket.setPatch(products.get(i).get("patch"));
                basketList.add(basket);
            }
        }
        return basketList;
    }
}