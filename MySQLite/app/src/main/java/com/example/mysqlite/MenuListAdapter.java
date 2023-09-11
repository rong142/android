package com.example.mysqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuListAdapter extends BaseAdapter {

    private LayoutInflater myInflater;
    private List<ItemList> list;

    public MenuListAdapter(Context context, List<ItemList> list) {
        myInflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public int getPosition(String title) {
        for(int i=0;i<list.size();i++) {
            ItemList list_item = list.get(i);
            if(list_item.getTitle().equals(title)) {
                return i;
            }
        }
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = myInflater.inflate(R.layout.item_row, null);
        TextView item_title = (TextView)convertView.findViewById(R.id.item_title);
        ImageView item_image = (ImageView)convertView.findViewById(R.id.item_image);
        ItemList list_item = list.get(position);

        item_title.setText(list_item.getTitle());
        item_image.setImageResource(list_item.getImage());

        return convertView;
    }
}

