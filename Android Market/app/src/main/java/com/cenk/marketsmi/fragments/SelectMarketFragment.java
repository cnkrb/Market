package com.cenk.marketsmi.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cenk.marketsmi.Adapters.StoreAdapter;
import com.cenk.marketsmi.HomeFragment;
import com.cenk.marketsmi.MainActivity;
import com.cenk.marketsmi.Models.Sube;
import com.cenk.marketsmi.R;


import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenk.marketsmi.Utils.Utils.interfaces;

public class SelectMarketFragment extends Fragment implements StoreAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    Fragment fragment=null;
    public List<Sube> itemList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_selec_market, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        getStores();
        ((MainActivity) requireActivity()).hideToolbar();

        return view;
    }

    private void moveToFragment(Fragment fragment) {
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        manager.popBackStack();
        final FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getStores() {
        Callback<List<Sube>> listCallBack = new Callback<List<Sube>>() {
            @Override
            public void onResponse(Call<List<Sube>> call, Response<List<Sube>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        itemList=response.body();
                        StoreAdapter storeAdapter = new StoreAdapter(response.body(),getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(storeAdapter);
                        storeAdapter.setOnItemClickListener(SelectMarketFragment.this);
                        storeAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getContext(),"Mağaza Yok", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sube>> call, Throwable t) {

            }
        };
        interfaces.getSubes().enqueue(listCallBack);
    }


    @Override
    public void onItemClick(int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("store", itemList.get(position).getSubeAdi());
        editor.putInt("position", itemList.get(position).getId());
        editor.putInt("pos", position);
        editor.apply();
        fragment=new HomeFragment();

        Bundle bundle=new Bundle();
        bundle.putString("store","store");
        fragment.setArguments(bundle);
        moveToFragment(fragment);

        ((MainActivity) requireActivity()).prepare();

    }

}