package com.cenk.marketsmi.Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cenk.marketsmi.AddressActivity;
import com.cenk.marketsmi.Models.InfoModel;
import com.cenk.marketsmi.R;
import com.cenk.marketsmi.database.InfoDatabase;
import com.github.pinball83.maskededittext.MaskedEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddFragment extends Fragment {


    EditText username, number, address;
    Button save;
    View view;
    int myInt;
    List<InfoModel> basketList=new ArrayList<>();
    MaskedEditText maskedEditText;
    Boolean defaultValue=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        save = view.findViewById(R.id.save);
        username = view.findViewById(R.id.username);
        number = view.findViewById(R.id.number);
        address = view.findViewById(R.id.address);
        maskedEditText = view.findViewById(R.id.masked_edit_text);
        basketList=getProducts();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            defaultValue=true;
            myInt = bundle.getInt("key", -1);
            username.setText(basketList.get(myInt).getUsername(), TextView.BufferType.EDITABLE);;
            maskedEditText.setMaskedText(basketList.get(myInt).getNumber());
            address.setText(basketList.get(myInt).getAddress(), TextView.BufferType.EDITABLE);

        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoDatabase infoDatabase=new InfoDatabase(getContext());
                if(username.getText().length()>0  &&  maskedEditText.getUnmaskedText().length()>9   && address.getText().length()>0){
                  if(defaultValue){
                      infoDatabase.addressEdit(username.getText().toString(),address.getText().toString(),maskedEditText.getUnmaskedText().toString(),Integer.parseInt(basketList.get(myInt).getId()));
                      Toast.makeText(getContext(),"Başarıyla Güncellendi",Toast.LENGTH_SHORT).show();
                      ((AddressActivity)requireActivity()).change();

                  }else {
                      infoDatabase.addressAdd(username.getText().toString(),address.getText().toString(),maskedEditText.getUnmaskedText().toString());
                      Toast.makeText(getContext(),"Başarıyla Eklendi",Toast.LENGTH_SHORT).show();
                      ((AddressActivity)requireActivity()).change();
                  }

                }else {
                    Toast.makeText(getContext(),"Alanları boş bırakmayınız",Toast.LENGTH_SHORT).show();
                }
                infoDatabase.close();

            }
        });
        return view;

    }


    public List<InfoModel> getProducts(){
        List<InfoModel> basketList=new ArrayList<>();
        ArrayList<HashMap<String, String>> products;
        InfoDatabase  db = new InfoDatabase(getContext());
        products = db.getAddress();
        if (products.size() != 0) {
            for(int i=0;i<products.size();i++){
                InfoModel infoModel=new InfoModel();
                infoModel.setUsername(products.get(i).get("name"));
                infoModel.setId(products.get(i).get("id"));
                infoModel.setAddress(products.get(i).get("address"));
                infoModel.setNumber(products.get(i).get("number"));
                basketList.add(infoModel);
            }
        }
        db.close();
        return basketList;
    }
}