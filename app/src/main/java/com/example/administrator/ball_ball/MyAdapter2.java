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

class MyAdapter2 extends BaseAdapter {
    private final Context mContext_two;
    private List<Data> mData;
    private Context mContext;

    public MyAdapter2(List<Data> mData, Context mContext_two) {
        this.mData = mData;
        this.mContext_two = mContext_two;
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
            convertView = LayoutInflater.from(mContext_two).inflate(R.layout.list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txt_item_title = (TextView) convertView.findViewById(R.id.txt_item_title);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_item_title.setText(mData.get(position).getTitle());
        Picasso.with(mContext_two).load("http://www.ccsolo.top/icon/"+mData.get(position).getIcon()+".ico").into(viewHolder.imageView);

        return convertView;
    }

    private class ViewHolder{
        TextView txt_item_title;
        public ImageView imageView;
    }

}
