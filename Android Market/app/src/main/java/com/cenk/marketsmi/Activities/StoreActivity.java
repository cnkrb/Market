package com.cenk.marketsmi.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.Adapters.BasketAdapter;
import com.cenk.marketsmi.Adapters.ProductAdapter;
import com.cenk.marketsmi.HomeFragment;
import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.Code;
import com.cenk.marketsmi.Models.Product;
import com.cenk.marketsmi.R;
import com.cenk.marketsmi.database.BasketDatabase;

import android.provider.Settings.Secure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenk.marketsmi.Utils.Utils.interfaces;

public class StoreActivity extends AppCompatActivity implements BasketAdapter.OnItemClickListener ,BasketAdapter.OnItemAddClickListener  {
    RecyclerView recyclerView;
    TextView basketTotal, order;
    TextView deleteBasket;
    Integer position;
    ImageView delete, back;
    private String android_id = "cihaz";
    List<Product> productList;
    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        android_id = Secure.getString(getApplicationContext().getContentResolver(),
                Secure.ANDROID_ID);

        recyclerView = findViewById(R.id.recyclerView);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);
        order = findViewById(R.id.order);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String store = preferences.getString("store", "null");
        position = preferences.getInt("position", 0);

          getAsc(position,0);
        deleteBasket = findViewById(R.id.deleteBasket);
        basketTotal = findViewById(R.id.basketTotal);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getProducts().size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Sepete ürün ekleyiniz", Toast.LENGTH_SHORT).show();

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getProducts().size() > 0) {
                    new SweetAlertDialog(StoreActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Emin misiniz?")
                            .setContentText("Sepetinizdeki tüm ürünler silinecektir!!")
                            .setCancelText("Hayır")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .setConfirmText("Evet")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    new SweetAlertDialog(StoreActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Başarıyla Silindi!")
                                            .setContentText("Tamam")
                                            .show();
                                    BasketDatabase db = new BasketDatabase(getApplicationContext());
                                    db.resetTables();
                                    db.close();
                                    da();
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }else {
                    Toast.makeText(getApplicationContext(),"Sepette ürün yok",Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void getAsc(Integer sube,Integer data) {
        Callback<List<Product>> listCallBack = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        productList=response.body();
                        System.out.println("sdlalkdsaklmasdklmdsmkalaksmld"+productList.size());
                        da();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        };
        interfaces.getAllAsc(sube).enqueue(listCallBack);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
       finish();
        Animatoo.animateFade(StoreActivity.this); //fire the slide left animation
    }

    public void da() {
        List<Basket> basketList = new ArrayList<>();
        basketList = getProducts();
        BasketDatabase db = new BasketDatabase(getApplicationContext());

        for(int a=0;a<basketList.size();a++){
            Boolean boo=false;
            for(int x=0;x<productList.size();x++){
                if(basketList.get(a).getId().equals(String.valueOf(productList.get(x).getId()))){
                    if(productList.get(x).getUrunTuru().equals("1")){
                        db.productPrice(productList.get(x).getIndirimFiyat().toString(),basketList.get(a).getId());
                    }else {
                        db.productPrice(productList.get(x).getSatisFiyat().toString(),basketList.get(a).getId());
                    }
                    boo=true;
                }
            }
            if(!boo){
                ArrayList<HashMap<String, String>> productss;
                productss = db.products();
                db.productDelete(Integer.parseInt(Objects.requireNonNull(basketList.get(a).getId())));
                System.out.println("girdiye kla"+basketList.get(a).getId());
                db.close();
            }
        }
        basketList = getProducts();
        BasketAdapter productAdapter = new BasketAdapter(basketList, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(productAdapter);
        productAdapter.setOnItemClickListener(StoreActivity.this);
        productAdapter.setOnItemAddClickListener(StoreActivity.this);
        productAdapter.notifyDataSetChanged();
        double total = 0.0;
        for (int i = 0; i < basketList.size(); i++) {
            total = total + Double.parseDouble(basketList.get(i).getX()) * Double.parseDouble(basketList.get(i).getPrice());
        }
        basketTotal.setText("Sepet Toplam: " + round(total, 2) + " TL");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    public void postSiparis() {
        String data = "";
        List<Basket> basketList = new ArrayList<>();
        basketList = getProducts();

        for (int i = 0; i < basketList.size(); i++) {
            if (i == basketList.size() - 1) {
                data = data + basketList.get(i).getId() + "," + basketList.get(i).getX();
            } else {
                data = data + basketList.get(i).getId() + "," + basketList.get(i).getX() + "+";
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {

        BasketDatabase db = new BasketDatabase(getApplicationContext());
        ArrayList<HashMap<String, String>> products;
        products = db.products();
        if (Objects.equals(products.get(position).get("x"), "1")) {
            db.productDelete(Integer.parseInt(Objects.requireNonNull(products.get(position).get("ca"))));
            System.out.println("adkasdklmdasklmadslkmalkmalkm");
        } else {
            int id = Integer.parseInt(Objects.requireNonNull(products.get(position).get("id")));
            db.productEdit(String.valueOf((Integer.parseInt(Objects.requireNonNull(products.get(position).get("x"))) - 1)), id);
        }
        db.close();
        da();
    }

    public List<Basket> getProducts() {
        List<Basket> basketList = new ArrayList<>();
        ArrayList<HashMap<String, String>> products;
        BasketDatabase db = new BasketDatabase(getApplicationContext());
        products = db.products();
        if (products.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                Basket basket = new Basket();
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

    @Override
    public void onItemAddClick(int position) {
        BasketDatabase db = new BasketDatabase(getApplicationContext());
        ArrayList<HashMap<String, String>> products;
        products = db.products();
        int id = Integer.parseInt(Objects.requireNonNull(products.get(position).get("id")));
        db.productEdit(String.valueOf((Integer.parseInt(Objects.requireNonNull(products.get(position).get("x"))) + 1)), id);
        db.close();
        da();
    }
}