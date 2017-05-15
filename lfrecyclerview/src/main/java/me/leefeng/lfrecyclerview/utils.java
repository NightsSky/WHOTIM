package me.leefeng.lfrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by user on 2017/5/15.
 */

public class utils {
    public static int getHeight(Context context) {
        return getStatusBarHeight(context)+ getNavigationBarHeight((Activity)context);
    }
    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        Log.d("me", "bar:" + height);
        return height;
    }
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
