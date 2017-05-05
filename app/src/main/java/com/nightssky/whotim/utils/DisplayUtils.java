package com.nightssky.whotim.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 手机像素转换适配工具
 * @author Administrator
 *
 */
public class DisplayUtils {
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 *   （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，
	 * 							isHeight=false则为测量该控件的宽度
	 * @param view
	 * @param isHeight
	 * @return
	 */
	public static int getViewHeight(View view, boolean isHeight){
		int result;
		if(view==null)return 0;
		if(isHeight){
			int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
			view.measure(h,0);
			result =view.getMeasuredHeight();
		}else{
			int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
			view.measure(0,w);
			result =view.getMeasuredWidth();
		}
		return result;
	}

	/**
	 * 获取状态栏高度
	 */
	public static int getStatusBarHight(Context context) {
		int statusBarHeight = -1;
		//获取status_bar_height资源的ID

		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

		if (resourceId > 0) {
			//根据资源ID获取响应的尺寸值
			statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);

		}
		return statusBarHeight;
	}

	public static void setToolbarHeight(Toolbar toolbar,Context context) {
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
		layoutParams.height += DisplayUtils.getStatusBarHight(context) / 2;
		toolbar.setLayoutParams(layoutParams);
	}
}