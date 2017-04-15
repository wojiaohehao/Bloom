package com.xzzb.bloom.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张扬 on 2017/1/2.
 */

public class VpAdapter extends FragmentPagerAdapter {
    private List<String> titles;
    private List<Fragment> fragments;

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public VpAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titles) {
        super(fm);
        this.titles=titles;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
