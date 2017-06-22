package com.avenging.hades.baselibrary.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/22.
 */

public class XViewPager extends ViewPager {

    private boolean scrollEnabled =true;

    public void setScrollEnabled(boolean isEnableScroll){
        this.scrollEnabled=isEnableScroll;
    }
    public XViewPager(Context context) {
        super(context);
    }

    public XViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!scrollEnabled){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!scrollEnabled){
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
