package com.xzzb.bloom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xzzb.bloom.R;

/**
 * Created by 张扬 on 2017/1/2.
 */

public class DL_Adapter extends BaseAdapter {
    private Context mContext;
    public DL_Adapter(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.drawlayout_cd,null);
        return convertView;
    }
}
