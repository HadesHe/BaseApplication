package com.avenging.hades.baselibrary.loading;

import android.view.View;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/15.
 */
public class VaryViewHelperController {
    private IVaryViewHelper helper;
    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }
}
