package com.xzzb.bloom.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xzzb.bloom.R;
import com.xzzb.bloom.weight.XListView;

/**
 * Created by 张扬 on 2017/1/2.
 */

public class FmVpGz extends Fragment{
    private XListView xListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_vp_gz,null);
        xListView= (XListView) view.findViewById(R.id.lv_view);
        return view;
    }
}
