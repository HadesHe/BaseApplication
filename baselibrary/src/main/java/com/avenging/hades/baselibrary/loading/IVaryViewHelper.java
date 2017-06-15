package com.avenging.hades.baselibrary.loading;

import android.content.Context;
import android.view.View;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/15.
 */
public interface IVaryViewHelper {

    public abstract View getCurrentLayout();

    public abstract void restoreView();

    public abstract void showLayout(View view);

    public abstract View inflate(int layoutId);

    public abstract Context getContext();

    public abstract View getView();
}
