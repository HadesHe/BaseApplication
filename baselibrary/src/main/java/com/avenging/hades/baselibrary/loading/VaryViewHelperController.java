package com.avenging.hades.baselibrary.loading;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenging.hades.baselibrary.R;
import com.avenging.hades.baselibrary.utils.CommonUtils;

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
        TextView textView= (TextView) layout.findViewById(R.id.tvMessageInfo);
        textView.setText(helper.getContext().getResources().getString(R.string.common_no_network_message));
        ImageView imageView= (ImageView) layout.findViewById(R.id.ivMessageIcon);
        imageView.setImageResource(R.drawable.ic_exception);

        if(onClickListener != null){
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showError(String errorMessage,View.OnClickListener onClickListener){
        View layout=helper.inflate(R.layout.message);
        TextView textView= (TextView) layout.findViewById(R.id.tvMessageInfo);
        if(!CommonUtils.isEmpty(errorMessage)){
            textView.setText(errorMessage);
        }else{
            textView.setText(helper.getContext().getResources().getString(R.string.common_error_message));
        }

        ImageView imageView= (ImageView) layout.findViewById(R.id.ivMessageIcon);
        imageView.setImageResource(R.drawable.ic_error);
        if(null != onClickListener){
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }
}
