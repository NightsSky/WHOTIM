package com.nightssky.whotim.View.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nightssky.whotim.R;
import com.nightssky.whotim.View.common.DataGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_container)
    FrameLayout mHomeContainer;
    @BindView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                onTabItemSelected(tab.getPosition());

                // Tab 选中之后，改变各个Tab的状态
                for (int i=0;i<mTabLayout.getTabCount();i++){
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    if(i == tab.getPosition()){ // 选中状态
                        icon.setImageResource(DataGenerator.mTabResPressed[i]);
                    }else{// 未选中状态
                        icon.setImageResource(DataGenerator.mTabRes[i]);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

// 提供自定义的布局添加Tab
        for(int i=0;i<3;i++){
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this,i)));
        }

    }
}
