package com.xzzb.bloom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.xzzb.bloom.R;
import com.xzzb.bloom.utils.TimesUtil;
import com.xzzb.bloom.weight.XListView;

import java.util.Date;

/**
 * Created by 张扬 on 2017/1/2.
 */

public class FmVpFx extends Fragment implements XListView.IXListViewListener{
    private XListView xListView;
    private Handler handler=new Handler();
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_vp_fx,null);
        context=container.getContext();
        initView(view);
        return view;
    }
    private ArrayAdapter adapter;
    private void initView(View view) {
        xListView= (XListView) view.findViewById(R.id.lv_view);
        adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,new String[]{"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
                ,"1","1"
        });
        xListView.setAdapter(adapter);
        xListView.setXListViewListener(this);
    }
    @Override
    public void onRefresh() {
        //刷新
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                xListView.setRefreshTime(TimesUtil.getTimes());
                xListView.stopRefresh();
            }
        },2000);
    }
    @Override
    public void onLoadMore() {
//加载
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                xListView.stopLoadMore();
            }
        },2000);
    }
}
