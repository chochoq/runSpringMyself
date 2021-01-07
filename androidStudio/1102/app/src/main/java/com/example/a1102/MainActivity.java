package com.example.a1102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    LinearLayout item_drawer;
    ViewPager pager;
    TabLayout tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        item_drawer = findViewById(R.id.item_drawer);
        pager = findViewById(R.id.pager);
        tab=findViewById(R.id.tab);

        getSupportActionBar().setTitle("네이버검색");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);



        tab.addTab(tab.newTab().setText("book"));
        tab.addTab(tab.newTab().setText("movie"));
        tab.addTab(tab.newTab().setText("shopping"));

        tab.getTabAt(0).setIcon(R.drawable.ic_book);
        tab.getTabAt(1).setIcon(R.drawable.ic_movie);
        tab.getTabAt(2).setIcon(R.drawable.ic_shop);

        //어댑터 생성
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);


        //페이지를 옮기면 탭이바뀜
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        //탭을 누르면 페이저가 바뀜
        tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //어댑터 정의
    class  PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return  new BookFragment();
                case 1 :
                    return new MovieFragment();
                case 2:
                    return new ShoppingFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(item_drawer);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}