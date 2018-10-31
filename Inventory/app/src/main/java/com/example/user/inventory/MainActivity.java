package com.example.user.inventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ViewPager viewPager;
    BottomNavigationView navigation;//底部导航菜单
    List<Fragment> listFragment;//存储页面对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.view_paper);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        //向ViewPager添加各页面
        listFragment = new ArrayList<>();
        listFragment.add(new Fragment1());
        listFragment.add(new Fragment2());
        MyFragAdapter myAdapter = new MyFragAdapter(getSupportFragmentManager(),this,listFragment);
        viewPager.setAdapter(myAdapter);
        Log.v("Tag","2");
        //导航栏点击时间和viewpager滑动事件，让两个空间互相关联
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(1);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();//要去掉这句，否则会结束当前Activity，无法起到屏蔽的作用
        //处理自己的逻辑
    }
}
