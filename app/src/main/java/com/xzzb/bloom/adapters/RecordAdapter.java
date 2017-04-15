package com.xzzb.bloom.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.xzzb.bloom.R;
import com.xzzb.bloom.model.CurLiveInfo;
import com.xzzb.bloom.model.RecordInfo;
import com.xzzb.bloom.views.ActivityReplay;

import java.util.ArrayList;


/**
 * 点播列表的Adapter
 */
public class RecordAdapter extends ArrayAdapter<RecordInfo> {
    private static String TAG = "RecordAdapter";
    private int resourceId;
    private Activity mActivity;
    private class ViewHolder{
        TextView tvName;
        TextView tvHost;
        TextView tvTime;
    }

    public RecordAdapter(Activity activity, int resource, ArrayList<RecordInfo> objects) {
        super(activity, resource, objects);
        resourceId = resource;
        mActivity = activity;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder)convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);

            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvHost = (TextView) convertView.findViewById(R.id.tv_host);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);

            convertView.setTag(holder);
        }

        final RecordInfo data = getItem(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurLiveInfo.setRecordInfo(data);
                mActivity.startActivity(new Intent(mActivity.getApplicationContext(), ActivityReplay.class));
            }
        });

        holder.tvName.setText(data.getStrName());
        holder.tvHost.setText( data.getStrUser());
        holder.tvTime.setText(data.getStrCreateTime());

        return convertView;
    }
}
