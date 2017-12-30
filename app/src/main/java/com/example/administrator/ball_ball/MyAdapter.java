package com.example.administrator.ball_ball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

class MyAdapter extends BaseAdapter {
    private List<Data> mData;
    private Context mContext;
    private ImageView imageView;

    public MyAdapter(List<Data> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_two,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.txt_item_title_two = (TextView) convertView.findViewById(R.id.txt_item_title_two);
            viewHolder.imageView_two = (ImageView) convertView.findViewById(R.id.imageView_two);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_item_title_two.setText(mData.get(position).getTitle());
        viewHolder.time.setText(mData.get(position).getTime());
        Picasso.with(mContext).load("http://www.ccsolo.top/icon/"+mData.get(position).getIcon()+".jpg").into(viewHolder.imageView_two);

        return convertView;
    }

    private class ViewHolder{
        TextView txt_item_title_two , time;
        ImageView imageView_two;

    }

}
