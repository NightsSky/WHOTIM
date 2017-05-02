package com.nightssky.whotim.View.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.nightssky.whotim.R;

/**
 * Created by user on 2017/5/2.
 */

public class DataGenerator {
   public static final int []mTabRes = new int[]{R.mipmap.message,R.mipmap.contact,R.mipmap.me};
    public static final int []mTabResPressed = new int[]{R.mipmap.message_down,R.mipmap.contact_down,R.mipmap.me_down};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
//        fragments[0] = HomeFragment.newInstance(from);
//        fragments[1] = DiscoveryFragment.newInstance(from);
//        fragments[2] = AttentionFragment.newInstance(from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);

        return view;
    }
}
