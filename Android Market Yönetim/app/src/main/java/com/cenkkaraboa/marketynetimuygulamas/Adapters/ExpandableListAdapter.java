package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cenkkaraboa.marketynetimuygulamas.Models.MenuModel;
import com.cenkkaraboa.marketynetimuygulamas.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;


public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MenuModel> listDataHeader;
    private HashMap<MenuModel, List<MenuModel>> listDataChild;

    public  int a=1;

    public ExpandableListAdapter(Context context, List<MenuModel> listDataHeader,
                                 HashMap<MenuModel, List<MenuModel>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public MenuModel getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }
        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);

        ImageView lblListHeader_image=convertView.findViewById(R.id.lblListItem_image);

          //  Picasso.with(context).load("http://market.cenkkaraboa.com/public/images/altkatagori/"+getChild(groupPosition, childPosition)).error(R.drawable.more).into(lblListHeader_image);
                System.out.println("http://market.cenkkaraboa.com/public/images/altkatagori/"+getChild(groupPosition, childPosition).image+"dadadadada");


                Picasso.with(context).load("http://market.cenkkaraboa.com/public/images/altkatagori/"+getChild(groupPosition, childPosition).image).into(lblListHeader_image);



        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        try {
            String headerTitle = getGroup(groupPosition).menuName;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group_header, null);
            }
            ImageView lblListHeader_image=convertView.findViewById(R.id.lblListHeader_image);

            ImageView go = convertView.findViewById(R.id.go);
            if(getGroup(groupPosition).menuName.equals("Şube Değiştir")){
                go.setImageResource(R.drawable.go);
                go.setPadding(15,15,15,15);
            }


            if(getGroup(groupPosition).menuName.equals("Siparişler")){
                go.setImageResource(R.drawable.go);
                go.setPadding(15,15,15,15);
            }
            if(getGroup(groupPosition).menuName.equals("Ürünler")){
                go.setImageResource(R.drawable.go);
                go.setPadding(15,15,15,15);
            }
            if(getGroup(groupPosition).menuName.equals("Şubeler")){
                go.setImageResource(R.drawable.go);
                go.setPadding(15,15,15,15);
            }
            if(getGroup(groupPosition).menuName.equals("Bilgilerim")){
                go.setImageResource(R.drawable.go);
                go.setPadding(15,15,15,15);
            }

            // Picasso.with(context).load("http://market.cenkkaraboa.com/public/images/altkatagori/"+getGroup(groupPosition).image).into(lblListHeader_image);
            TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            LinearLayout  linear=convertView.findViewById(R.id.linear);


            if(getGroup(groupPosition).menuName.equals("İşlemler")){
                go.setVisibility(View.GONE);
                lblListHeader_image.setVisibility(View.GONE);
                lblListHeader.setTextSize(18);
                linear.setBackgroundColor(context.getResources().getColor(R.color.light_color));
                int paddingDp = 25;
                float density = context.getResources().getDisplayMetrics().density;
                int paddingPixel = (int)(paddingDp * density);
                lblListHeader.setPadding(paddingPixel,0,0,0);
            }

            if(getGroup(groupPosition).menuName.equals("Menü")){
                go.setVisibility(View.GONE);
                lblListHeader_image.setVisibility(View.GONE);
                lblListHeader.setTextSize(18);
                linear.setBackgroundColor(context.getResources().getColor(R.color.light_color));
                int paddingDp = 25;
                float density = context.getResources().getDisplayMetrics().density;
                int paddingPixel = (int)(paddingDp * density);
                lblListHeader.setPadding(paddingPixel,0,0,0);
            }
            lblListHeader.setText(headerTitle);





        }catch (Exception e){

        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}