package com.cenk.marketsmi;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cenk.marketsmi.Adapters.ProductAdapter;
import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.Product;
import com.cenk.marketsmi.Models.SearchAltCategori;
import com.cenk.marketsmi.Models.SearchCategori;
import com.cenk.marketsmi.database.BasketDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenk.marketsmi.Utils.Utils.interfaces;


public class HomeFragment extends Fragment implements ProductAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    List<SearchCategori> products=new ArrayList<>();
    List<Product> productList=new ArrayList<>();
    List<SearchAltCategori> altCategoris=new ArrayList<>();
    ProductAdapter productAdapter;
    String strtext="";
    NestedScrollView nestedScrollView;
    int data=0;
    Bundle args;
    Boolean loadedAllItems=false;
    int position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        String store=preferences.getString("store","null");
        position=preferences.getInt("position",0);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView=view.findViewById(R.id.recyclerView);
        nestedScrollView=view.findViewById(R.id.scrool);
        ((MainActivity) requireActivity()).visiToolbar();

        recyclerView.setVisibility(GONE);
        args = getArguments();
        if (args != null) {
            strtext=args.getString("message");
            if(strtext != null){
                productAdapter = new ProductAdapter(products, getContext());
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                searchKategori(strtext,data);
            }
            strtext=args.getString("store");
            if(strtext != null){
                productAdapter = new ProductAdapter(productList, getContext(),7);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                getAsc(position,data);
            }
            strtext=args.getString("alt");
            if(strtext != null){
                productAdapter = new ProductAdapter(altCategoris, getContext(),true);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                searchAltKategori(strtext,data);
            }
            strtext=args.getString("home");
            if(strtext != null){
                productAdapter = new ProductAdapter(productList, getContext(),7);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            //    getAsc(data);
            }
            strtext=args.getString("search");
            if(strtext != null){
                productAdapter = new ProductAdapter(productList, getContext(),7);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                search(strtext,data,position);
            }
        }
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                        if (!loadedAllItems) {
                            loadedAllItems=true;
                            progressBar.setVisibility(View.VISIBLE);
                            data=data+20;
                            strtext=args.getString("message");
                            if(strtext != null) {
                                searchKategori(strtext, data);
                            }
                            strtext=args.getString("alt");
                            if(strtext != null){
                                searchAltKategori(strtext,data);
                            }
                            strtext=args.getString("home");
                            if(strtext != null){
                               // getAsc(data);
                            }
                            strtext=args.getString("search");
                            if(strtext != null){
                                search(strtext,data,position);
                            }
                            strtext=args.getString("store");
                            if(strtext != null){
                                getAsc(position,data);

                            }
                        }
                }
            }
        });
        return view;
    }

    public void getAsc(Integer sube,Integer data) {
        Callback<List<Product>> listCallBack = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        productList.addAll(response.body());
                        productAdapter = new ProductAdapter(productList, getContext(),7);
                        recyclerView.setAdapter(productAdapter);
                        productAdapter.setOnItemClickListener(HomeFragment.this);
                        productAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(GONE);
                    loadedAllItems=false;

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        };
        interfaces.getAsc(sube,data).enqueue(listCallBack);
    }



    public void search(String data,Integer old,Integer sube) {
        Callback<List<Product>> listCallBack = new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        productList.addAll(response.body());
                        productAdapter = new ProductAdapter(productList, getContext(),7);
                        recyclerView.setAdapter(productAdapter);
                        productAdapter.setOnItemClickListener(HomeFragment.this);
                        productAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(GONE);
                    loadedAllItems=false;

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        };
        interfaces.search(data,old,sube).enqueue(listCallBack);
    }



    public void searchKategori(String id,Integer data) {
        Callback<List<SearchCategori>> listCallBack = new Callback<List<SearchCategori>>() {
            @Override
            public void onResponse(Call<List<SearchCategori>> call, Response<List<SearchCategori>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        products.addAll(response.body());
                        productAdapter = new ProductAdapter(products, getContext());
                        recyclerView.setAdapter(productAdapter);
                        productAdapter.setOnItemClickListener(HomeFragment.this);
                        productAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(GONE);
                    loadedAllItems=false;

                }
            }

            @Override
            public void onFailure(Call<List<SearchCategori>> call, Throwable t) {

            }
        };
        interfaces.searchKategori(position,id,data).enqueue(listCallBack);
    }



    public void searchAltKategori(String id,Integer data) {
        Callback<List<SearchAltCategori>> listCallBack = new Callback<List<SearchAltCategori>>() {
            @Override
            public void onResponse(Call<List<SearchAltCategori>> call, Response<List<SearchAltCategori>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        altCategoris.addAll(response.body());
                        productAdapter = new ProductAdapter(altCategoris, getContext(),true);
                        recyclerView.setAdapter(productAdapter);
                        productAdapter.setOnItemClickListener(HomeFragment.this);
                        productAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(GONE);
                    loadedAllItems=false;
                }
            }

            @Override
            public void onFailure(Call<List<SearchAltCategori>> call, Throwable t) {

            }
        };
        interfaces.searchAltKategori(position,id,data).enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position) {

        try {
            List<Basket> basketList=getProducts();
            BasketDatabase db = new BasketDatabase(getContext());
            boolean value=true;

            strtext=args.getString("message");
            if(strtext != null){
                for(int i=0;i<basketList.size();i++){
                    if(basketList.get(i).getName().equals(products.get(position).getUrunAdi())){
                        int id=Integer.parseInt(Objects.requireNonNull(db.products().get(i).get("id")));
                        db.productEdit(String.valueOf((Integer.parseInt(basketList.get(i).getX())+1)),id);
                        Toast.makeText(getContext(),products.get(position).getUrunAdi()+" adet sayısı "+(Integer.parseInt(basketList.get(i).getX())+1)+" oldu",Toast.LENGTH_SHORT).show();
                        value=false;
                        return;
                    }
                }
            }
            strtext=args.getString("alt");
            if(strtext != null){
                for(int i=0;i<basketList.size();i++){
                    if(basketList.get(i).getName().equals(altCategoris.get(position).getUrunAdi())){
                        int id=Integer.parseInt(Objects.requireNonNull(db.products().get(i).get("id")));
                        db.productEdit(String.valueOf((Integer.parseInt(basketList.get(i).getX())+1)),id);
                        Toast.makeText(getContext(),altCategoris.get(position).getUrunAdi()+" adet sayısı "+(Integer.parseInt(basketList.get(i).getX())+1)+" oldu",Toast.LENGTH_SHORT).show();

                        value=false;
                        return;
                    }
                }
            }
            strtext=args.getString("store");
            if(strtext != null){
                for(int i=0;i<basketList.size();i++){
                    if(basketList.get(i).getName().equals(productList.get(position).getUrunAdi())){
                        int id=Integer.parseInt(Objects.requireNonNull(db.products().get(i).get("id")));
                        db.productEdit(String.valueOf((Integer.parseInt(basketList.get(i).getX())+1)),id);
                        Toast.makeText(getContext(),productList.get(position).getUrunAdi()+" adet sayısı "+(Integer.parseInt(basketList.get(i).getX())+1)+" oldu",Toast.LENGTH_SHORT).show();

                        value=false;
                        return;
                    }
                }
            }
            strtext=args.getString("search");
            if(strtext != null){
                for(int i=0;i<basketList.size();i++){
                    if(basketList.get(i).getName().equals(productList.get(position).getUrunAdi())){
                        int id=Integer.parseInt(Objects.requireNonNull(db.products().get(i).get("id")));
                        db.productEdit(String.valueOf((Integer.parseInt(basketList.get(i).getX())+1)),id);
                        Toast.makeText(getContext(),productList.get(position).getUrunAdi()+" adet sayısı "+(Integer.parseInt(basketList.get(i).getX())+1)+" oldu",Toast.LENGTH_SHORT).show();

                        value=false;
                        return;
                    }
                }
            }
            if(value) {
                String url = "";
                strtext = args.getString("message");
                if (strtext != null) {
                    if(products.get(position).getGorselUrl()  != null){
                        url = products.get(position).getGorselUrl().toString();
                    }
                    if (products.get(position).getUrunTuru().equals("1")) {
                        db.productAdd(products.get(position).getId().toString(), products.get(position).getUrunAdi(), "1", products.get(position).getIndirimFiyat().toString(), url);
                    }else {
                        db.productAdd(products.get(position).getId().toString(), products.get(position).getUrunAdi(), "1", products.get(position).getSatisFiyat(), url);
                    }
                    Toast.makeText(getContext(),products.get(position).getUrunAdi()+" sepete eklendi.",Toast.LENGTH_SHORT).show();

                }
                strtext = args.getString("alt");
                if (strtext != null) {

                    if(altCategoris.get(position).getGorselUrl()  != null){
                        url = altCategoris.get(position).getGorselUrl().toString();
                    }
                    if (altCategoris.get(position).getUrunTuru().equals("1")) {
                        db.productAdd(altCategoris.get(position).getId().toString(), altCategoris.get(position).getUrunAdi(), "1", altCategoris.get(position).getIndirimFiyat().toString(), url);
                    }else {
                        db.productAdd(altCategoris.get(position).getId().toString(), altCategoris.get(position).getUrunAdi(), "1", altCategoris.get(position).getSatisFiyat(), url);
                    }
                    Toast.makeText(getContext(),altCategoris.get(position).getUrunAdi()+" sepete eklendi.",Toast.LENGTH_SHORT).show();

                }
                strtext = args.getString("store");
                if (strtext != null) {
                    if(productList.get(position).getGorselUrl()  != null){
                        url = productList.get(position).getGorselUrl().toString();
                    }
                    if (productList.get(position).getUrunTuru().equals("1")) {

                        db.productAdd(productList.get(position).getId().toString(), productList.get(position).getUrunAdi(), "1", productList.get(position).getIndirimFiyat().toString(), url);

                    }else {
                        db.productAdd(productList.get(position).getId().toString(), productList.get(position).getUrunAdi(), "1", productList.get(position).getSatisFiyat().toString(), url);

                    }
                    Toast.makeText(getContext(),productList.get(position).getUrunAdi()+" sepete eklendi.",Toast.LENGTH_SHORT).show();

                }
                strtext = args.getString("search");
                if (strtext != null) {
                    if(productList.get(position).getGorselUrl()  != null){
                        url = productList.get(position).getGorselUrl().toString();
                    }
                    if (productList.get(position).getUrunTuru().equals("1")) {
                        db.productAdd(productList.get(position).getId().toString(), productList.get(position).getUrunAdi(), "1", productList.get(position).getIndirimFiyat().toString(), url);


                    }else {
                        db.productAdd(productList.get(position).getId().toString(), productList.get(position).getUrunAdi(), "1", productList.get(position).getSatisFiyat().toString(), url);

                    }
                    Toast.makeText(getContext(),productList.get(position).getUrunAdi()+" sepete eklendi.",Toast.LENGTH_SHORT).show();

                }




         /*   if(products.get(position).getIndirim().equals("1")){
                db.productAdd(products.get(position).getUrunAdi(),"1",products.get(position).getIndirimFiyat().toString(),products.get(position).getFotograf());
            }else {
                db.productAdd(products.get(position).getUrunAdi(),"1",products.get(position).getFiyat(),products.get(position).getFotograf());
            }*/
                db.close();

            }
        }catch (Exception e){

        }

        ((MainActivity) requireActivity()).fabPlus();
    }

    public List<Basket>  getProducts(){
        List<Basket> basketList=new ArrayList<>();
        ArrayList<HashMap<String, String>> products;
        BasketDatabase db = new BasketDatabase(getContext());
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