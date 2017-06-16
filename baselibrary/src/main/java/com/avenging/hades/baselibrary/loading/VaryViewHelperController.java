package com.avenging.hades.baselibrary.loading;

import android.view.View;

import com.avenging.hades.baselibrary.R;

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

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper=helper;
    }

    public void showNetworkError(View.OnClickListener onClickListener){
        View layout=helper.inflate(R.layout.message);
    }
}
