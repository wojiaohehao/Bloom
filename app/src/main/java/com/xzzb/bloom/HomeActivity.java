package com.xzzb.bloom;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xzzb.bloom.adapter.VpAdapter;
import com.xzzb.bloom.fragment.FmVpFx;
import com.xzzb.bloom.fragment.FmVpGz;
import com.xzzb.bloom.fragment.FmVpTc;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout lef_liear1;
    private ListView listView;
    private ImageView headimg;
    private ImageView imageView;//菜单
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_home);
        VpAdapter vpAdapter=new VpAdapter(getSupportFragmentManager(),getTestData(), getTestTitle());
        viewPager= (ViewPager) findViewById(R.id.view_nr);
        tabLayout= (TabLayout) findViewById(R.id.tab_top);
        lef_liear1= (LinearLayout) findViewById(R.id.lef_liear);
        viewPager.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(viewPager);
        imageView= (ImageView) findViewById(R.id.caidan_img);
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawLay);
               drawerLayout.openDrawer(lef_liear1);
           }
       });
        headimg= (ImageView) findViewById(R.id.top_img);
        headimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Me_info.class);
                startActivity(intent);
            }
        });
    }
    private List<Fragment> getTestData(){
        List<Fragment> returnList=new ArrayList<>();
        returnList.add(new FmVpGz());
        returnList.add(new FmVpFx());
        returnList.add(new FmVpTc());
        return returnList;
    }
    private List<String> getTestTitle(){
        List<String> arr=new ArrayList<>();
        arr.add("关注");
        arr.add("发现");
        arr.add("同城");
        return arr;
    }
}
