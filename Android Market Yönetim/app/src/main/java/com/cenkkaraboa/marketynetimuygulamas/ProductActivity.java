package com.cenkkaraboa.marketynetimuygulamas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.ProductAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Product;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;

public class ProductActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView back;
    TextView sube;
    int subee;
    List<Product> products;
    ProductAdapter adminSiparisAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sube=findViewById(R.id.sube);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        subee=preferences.getInt("position",0);

        EditText search = findViewById(R.id.search); // inititate a search view
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    adminSiparisAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String value;
        int val;
        if(bundle != null){
            value = bundle.getString("value","boş");
            String name = bundle.getString("name","boş");
            val = bundle.getInt("val");
            switch (value) {
                case "kat":
                    urunkatagori(val);
                    sube.setText(name);

                    break;
                case "altkat":
                    urunaltkatagori(val);
                    sube.setText(name);

                    break;
                case "marka":
                    urunMarka(val);
                    sube.setText(name);

                    break;
            }
        }

    }


    public void urunaltkatagori(int id) {
        Callback<List<Product>> listCallBack = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                    products=response.body();
                    adminSiparisAdapter = new ProductAdapter(response.body(),getApplicationContext(),true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }                    progressBar.setVisibility(GONE);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        };
        interfaces.urunaltkatagori((subee),id).enqueue(listCallBack);
    }
    public void urunkatagori(int id) {
        Callback<List<Product>> listCallBack = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                    products=response.body();

                    adminSiparisAdapter = new ProductAdapter(response.body(),getApplicationContext(),true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }                    progressBar.setVisibility(GONE);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        };
        interfaces.urunkatagori((subee),id).enqueue(listCallBack);
    }


    public void urunMarka(int id) {
        Callback<List<Product>> listCallBack = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                    products=response.body();

                    adminSiparisAdapter = new ProductAdapter(response.body(),getApplicationContext(),true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }                    progressBar.setVisibility(GONE);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        };
        interfaces.urunMarka((subee),id).enqueue(listCallBack);
    }

}