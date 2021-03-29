package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;
import com.cenkkaraboa.marketynetimuygulamas.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {
    public List<User> itemList;
    public Context context;
    public List<Sube> subeList;
    public Boolean value=false;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public UserAdapter(List<User> itemList,List<Sube> subeList, Context context) {
        this.itemList = itemList;
        this.subeList = subeList;
        this.context = context;
    }

    public UserAdapter(List<User> itemList,List<Sube> subeList, Context context,Boolean value) {
        this.itemList = itemList;
        this.subeList = subeList;
        this.context = context;
        this.value = value;
    }

    @Override
    public UserAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(value){
            return new UserAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasiyer, parent, false));

        }else {
            return new UserAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(UserAdapter.Holder holder, int position) {
        try {

            holder.name.setText(itemList.get(position).getName());

           if(!value){
               holder.role.setText(itemList.get(position).getGetRoles().get(0).getRolesName());
           }
            if(subeList.size()>0){
                for(int i=0;i<subeList.size();i++){
                    if(itemList.get(position).getSubeId().toString().equals(subeList.get(i).getId().toString())){
                        holder.sube.setText(subeList.get(i).getSubeAdi());
                    }
                }
            }else {
                holder.sube.setText("Geçerli Şube yok");
            }
            holder.mail.setText(itemList.get(position).getEmail());

        }catch (Exception E){

        }


        //     holder.date.setText(itemList.get(itemList.size()-(position+1)).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView sube,name,role,mail;

        public Holder(final View itemView) {
            super(itemView);
            if(!value){
                role = (TextView) itemView.findViewById(R.id.rol);
            }
            sube = (TextView) itemView.findViewById(R.id.sube);
            name = (TextView) itemView.findViewById(R.id.name);
            mail = (TextView) itemView.findViewById(R.id.mail);

        }
    }


}