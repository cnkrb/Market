package com.cenkkaraboa.marketynetimuygulamas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ExpandableListView;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.ExpandableListAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.AdminSiparisFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.KasiyerFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.KuryeFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.OdemeFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.SubeFragment;
import com.cenkkaraboa.marketynetimuygulamas.Fragments.UserFragment;
import com.cenkkaraboa.marketynetimuygulamas.Models.MenuModel;
import com.cenkkaraboa.marketynetimuygulamas.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter expandableListAdapter;
    DrawerLayout drawer;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    int lastExpandedPosition = -1;
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.createLoginAPI();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandableListView);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        prepareMenuData();
        populateExpandableList();

    }

    private void prepareMenuData() {

        String myDrawable = null;

        //myDrawable = getResources().getDrawable(R.drawable.changestore);

        MenuModel   menuModel = new MenuModel("Menü", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);


   /*      menuModel = new MenuModel("Kategoriler", true, true, "", myDrawable); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Kategori", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Alt Kategori", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Marka", false, false, "", null);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Ürünler", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

*/


        menuModel = new MenuModel("Siparişler", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }




     /*   menuModel = new MenuModel("Grafikler", true, true, "", myDrawable); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel>  childModelsList = new ArrayList<>();
        MenuModel  childModel = new MenuModel("Ürün Grafikleri", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Kurye Grafikleri", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Kasiyer Grafikleri", false, false, "", null);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
*/

        menuModel = new MenuModel("Kullanıcı Bilgileri", true, true, "", myDrawable); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel>   childModelsList = new ArrayList<>();
        MenuModel  childModel = new MenuModel("Tüm Kullanıcılar", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Kasiyer", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Kurye", false, false, "", null);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }


        menuModel = new MenuModel("Muhasebe Bilgileri", true, true, "", myDrawable); //Menu of Java Tutorials
        headerList.add(menuModel);
         childModelsList = new ArrayList<>();
         childModel = new MenuModel("Muhasebe", false, false, "", null);
        childModelsList.add(childModel);

        childModel = new MenuModel("Ödeme Tipi", false, false, "", null);
        childModelsList.add(childModel);



        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }


        menuModel = new MenuModel("Şubeler", true, false, "", myDrawable); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
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

                        if(groupPosition ==4){
                            moveToFragment(new SubeFragment());
                        }
                        if(groupPosition ==1){
                            moveToFragment(new AdminSiparisFragment());
                        }
                        onBackPressed();
                    }
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    if(groupPosition==2){
                        if(childPosition==0){
                            moveToFragment(new UserFragment());
                        }
                        if(childPosition==1){
                            moveToFragment(new KasiyerFragment());
                        }if(childPosition==2){
                            moveToFragment(new KuryeFragment());
                        }
                    }
                    if(groupPosition==3){
                        if(childPosition==0){
                        }
                        if(childPosition==1){
                            moveToFragment(new OdemeFragment());
                        }
                    }
                    onBackPressed();
                }

                return false;
            }
        });
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

    private void moveToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}