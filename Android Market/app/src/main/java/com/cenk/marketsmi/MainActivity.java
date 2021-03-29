package com.cenk.marketsmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cenk.marketsmi.Activities.InfoActivity;
import com.cenk.marketsmi.Activities.OrderActivity;
import com.cenk.marketsmi.Activities.StoreActivity;
import com.cenk.marketsmi.Adapters.ExpandableListAdapter;
import com.cenk.marketsmi.Adapters.StoreAdapter;
import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.Categori;
import com.cenk.marketsmi.Models.CategoriModel;
import com.cenk.marketsmi.Models.Info;
import com.cenk.marketsmi.Models.MenuModel;
import com.cenk.marketsmi.Models.Sube;
import com.cenk.marketsmi.Utils.Utils;
import com.cenk.marketsmi.database.BasketDatabase;
import com.cenk.marketsmi.fragments.SelectMarketFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenk.marketsmi.Utils.Utils.interfaces;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter expandableListAdapter;
    DrawerLayout drawer;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    int lastExpandedPosition = -1;
    public static List<CategoriModel> menu = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    Fragment fragment;
    TextView fab_text;
    FloatingActionButton fab;
    AppBarLayout appBar;
    RelativeLayout fab_layout;
    Toolbar toolbar;

    Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab_text = findViewById(R.id.fab_text);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();



        fab = findViewById(R.id.fab);
        fab_layout = findViewById(R.id.fab_layout);
        appBar = findViewById(R.id.appBar);
        fab.setColorFilter(Color.WHITE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasketDatabase db = new BasketDatabase(getApplicationContext());
                if (db.products().size() > 0) {
                    startActivity(new Intent(getApplicationContext(), StoreActivity.class));
                    Animatoo.animateFade(MainActivity.this);
                } else {
                    Toast.makeText(getApplicationContext(), "Sepete ürün ekleyiniz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        drawer = findViewById(R.id.drawer_layout);

        expandableListView = findViewById(R.id.expandableListView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Utils.createLoginAPI();

            fragment = new SelectMarketFragment();
            moveToFragment(fragment);
    }


    @Override
    protected void onResume() {
        super.onResume();

        fabPlus();


    }

    public void prepare() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        position = preferences.getInt("position", 0);
        headerList.clear();
        childList.clear();
        menu.clear();
        getCategories();
        getStores();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Çıkmak istediğinizden emin misiniz?")
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
                            sweetAlertDialog.dismiss();
                            finish();
                        }
                    })
                    .show();
        }

    }

    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
        fab_layout.setVisibility(View.GONE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    public void visiToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        fab_layout.setVisibility(View.VISIBLE);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                fragment = new HomeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("store", "store");
                fragment.setArguments(bundle);
                moveToFragment(fragment);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchView.clearFocus();
                fragment = new HomeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("search", query);
                fragment.setArguments(bundle);
                moveToFragment(fragment);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fragment = new HomeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("search", newText);
                fragment.setArguments(bundle);
                moveToFragment(fragment);
                return false;
            }
        });
        return true;
    }

    public void getHakkimda() {
        Callback<List<Info>> listCallBack = new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
            }
        };
        interfaces.getHakkimda().enqueue(listCallBack);
    }

    public void getStores() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Integer position = preferences.getInt("pos", -1);
        Callback<List<Sube>> listCallBack = new Callback<List<Sube>>() {
            @Override
            public void onResponse(Call<List<Sube>> call, Response<List<Sube>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        if (position == -1) {

                        } else {
                            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                            View headerView = navigationView.getHeaderView(0);
                            TextView title = (TextView) headerView.findViewById(R.id.title);
                            title.setText(response.body().get(position).getSubeAdi());
                            CircleImageView header = headerView.findViewById(R.id.header);
                            Picasso.with(getApplicationContext())
                                    .load("https://market.cenkkaraboa.com/public/images/hakkimda/" + response.body().get(position).getGorselUrl())
                                    .into(header);
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


    public void getCategories() {
        Callback<List<CategoriModel>> listCallBack = new Callback<List<CategoriModel>>() {
            @Override
            public void onResponse(Call<List<CategoriModel>> call, Response<List<CategoriModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        menu=(response.body());

                    }
                    prepareMenuData();
                    populateExpandableList();
                }
            }

            @Override
            public void onFailure(Call<List<CategoriModel>> call, Throwable t) {
            }
        };
        interfaces.getCategories(position).enqueue(listCallBack);
    }


    private void prepareMenuData() {

        String myDrawable = null;

        //myDrawable = getResources().getDrawable(R.drawable.changestore);

        MenuModel menuModel = new MenuModel("İşlemler", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


        menuModel = new MenuModel("Mağaza Hakkında", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Siparişler", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


        menuModel = new MenuModel("Ana Sayfa", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


        menuModel = new MenuModel("Bilgilerim", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Şube Değiştir", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Kategoriler", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


            System.out.println(menu.size());
            if (menu.size() > 0) {
                for (int i = 0; i < menu.size(); i++) {
                    String url = "";
                    if (menu.get(i).getGetKatagori().getGorselUrl() != null) {
                        url = menu.get(i).getGetKatagori().getGorselUrl().toString();
                    }
                    menuModel = new MenuModel(menu.get(i).getGetKatagori().getKatagoriAdi(), true, true, "", url); //Menu of Java Tutorials
                    headerList.add(menuModel);

                    List<MenuModel> childModelsList3 = new ArrayList<>();

                    MenuModel childModel3 = new MenuModel("Tümünü Gör", false, false, menu.get(i).getGetKatagori().getId().toString(), null);
                    childModelsList3.add(childModel3);

                    for (int y = 0; y < menu.get(i).getGetSubealtkatagori().size(); y++) {
                        if (menu.get(i).getGetKatagori().getSelect().equals("1") && menu.get(i).getGetSubealtkatagori().get(y).getSecim().equals("1")) {
                            String url2 = "";
                            if (menu.get(i).getGetSubealtkatagori().get(y).getGetAlt().getGorselUrl() != null) {
                                url2 = menu.get(i).getGetSubealtkatagori().get(y).getGetAlt().getGorselUrl().toString();
                            }
                            childModel3 = new MenuModel(menu.get(i).getGetSubealtkatagori().get(y).getGetAlt().getAltkatagoriAdi(), false, false, menu.get(i).getGetSubealtkatagori().get(y).getGetAlt().getId().toString(), url2);
                            childModelsList3.add(childModel3);
                        }

                    }

                    if (menuModel.hasChildren) {
                        childList.put(menuModel, childModelsList3);
                    }
                }

            }



    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if(groupPosition ==0 || groupPosition ==6){

                        }else {
                            if (groupPosition == 1) {
                                startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                                Animatoo.animateSlideLeft(MainActivity.this);
                            } else if (groupPosition == 2) {
                                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                                Animatoo.animateSlideLeft(MainActivity.this);
                            } else if (groupPosition == 3) {
                                fragment = new HomeFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("store", "store");
                                fragment.setArguments(bundle);
                                moveToFragment(fragment);
                            } else if (groupPosition == 4) {
                                startActivity(new Intent(getApplicationContext(), AddressActivity.class));
                                Animatoo.animateFade(MainActivity.this);
                            } else if (groupPosition == 5) {
                                BasketDatabase db = new BasketDatabase(getApplicationContext());
                                db.resetTables();
                                db.close();
                                fabPlus();
                                moveToFragment(new SelectMarketFragment());
                            }
                            onBackPressed();
                        }

                    }
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {

                    if (childPosition == 0) {
                        fragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        System.out.println(menu.get(groupPosition - 7).getGetKatagori().getId());
                        bundle.putString("message", menu.get(groupPosition - 7).getGetKatagori().getId().toString());
                        fragment.setArguments(bundle);
                        moveToFragment(fragment);
                        onBackPressed();
                        //menu.get(i).getId().toString()
                        //menu.get(i).getGetAltkatagori().get(y).getId().toString()
                    } else {
                        fragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("alt", menu.get(groupPosition - 7).getGetSubealtkatagori().get(childPosition - 1).getGetAlt().getId().toString());
                        fragment.setArguments(bundle);
                        moveToFragment(fragment);
                        onBackPressed();
                    }
                }
                return false;
            }
        });
    }


    private void moveToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void fabPlus() {
        BasketDatabase db = new BasketDatabase(getApplicationContext());
        fab_text.setText(String.valueOf(db.products().size()));
    }


}