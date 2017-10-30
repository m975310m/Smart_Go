package com.example.user.smart_go;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;


/**
 * Created by AndyChuo on 2016/4/5.
 * 建立自訂義清單:
 *      單一項目利用 list_item 介面元件來建立
 */
public class ListAdapter extends BaseAdapter
{

    private AppCompatActivity activity;
    private List<String> mList;
    DataCenter datacenter;
    JSONArray Menu;
    private static LayoutInflater inflater = null;

    public ListAdapter(AppCompatActivity a, JSONArray b)
    {
        Menu = b;
        activity = a;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        datacenter = MainActivity.datacenter;
    }

    public int getCount()
    {
        return Menu.length();
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        TextView off =(TextView) convertView.findViewById(R.id.list_discount);
        TextView point = (TextView) convertView.findViewById(R.id.list_point);
        TextView name = (TextView) convertView.findViewById(R.id.list_name);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.list_image);

        try
        {
            off.setText(Menu.getJSONObject(position).getString("off"));
            point.setText(Menu.getJSONObject(position).getString("bonus"));
            name.setText(Menu.getJSONObject(position).getString("name"));
            imageView.setImageDrawable(activity.getResources().getDrawable(Menu.getJSONObject(position).getInt("img")));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return convertView;

}

}
