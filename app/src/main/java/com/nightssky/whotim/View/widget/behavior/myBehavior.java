package com.nightssky.whotim.View.widget.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by user on 2017/5/11.
 */

public class myBehavior extends CoordinatorLayout.Behavior<View> {
    public myBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myBehavior() {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        float scaleY = Math.abs(dependency.getY());
        float scaleY =dependency.getHeight()-dependency.getY();
//        child.setTranslationY(-scaleY);
        child.layout(0, (int)-scaleY,child.getMeasuredWidth(),child.getMeasuredHeight()+(int)scaleY);
        Log.d("msg", "onDependentViewChanged");
        return true;
    }
}
