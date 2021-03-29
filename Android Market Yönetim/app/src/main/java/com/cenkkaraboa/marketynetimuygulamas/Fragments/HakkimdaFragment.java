package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.UserAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class HakkimdaFragment extends Fragment {

    View view;
    LinearLayout recyclerView;
    ProgressBar progressBar;
    ImageView imageView,back;
    TextView name,address,number,time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_hakkimda, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);


        imageView=view.findViewById(R.id.image);
        name=view.findViewById(R.id.name);
        address=view.findViewById(R.id.address);
        number=view.findViewById(R.id.number);
        time=view.findViewById(R.id.time);
        back=view.findViewById(R.id.back);

        hakkimda();
        return view;

    }



    public void hakkimda() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position=preferences.getInt("position",-1);
        Callback<Sube> listCallBack = new Callback<Sube>() {
            @Override
            public void onResponse(Call<Sube> call, Response<Sube> response) {
                if (response.isSuccessful()) {

                        recyclerView.setVisibility(View.VISIBLE);
                        try {
                            name.setText(response.body().getSubeAdi());
                            address.setText(response.body().getAdres().toString());
                            number.setText(response.body().getTelefon().toString());
                            time.setText(response.body().getServis().toString());
                            Picasso.with(getContext())
                                    .load("https://market.cenkkaraboa.com/public/images/hakkimda/"+response.body().getGorselUrl())
                                    .into(imageView);
                        }catch (Exception e){

                        }


                }                    progressBar.setVisibility(GONE);

            }

            @Override
            public void onFailure(Call<Sube> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        interfaces.hakkimda(String.valueOf(position)).enqueue(listCallBack);
    }
}