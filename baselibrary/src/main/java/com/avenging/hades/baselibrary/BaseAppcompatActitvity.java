package com.avenging.hades.baselibrary;

import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class BaseAppcompatActitvity extends AppCompatActivity {

    public static final int TRANSITION_MODE_LEFT=0;
    public static final int TRANSITION_MODE_RIGHT=1;
    public static final int TRANSITION_MODE_TOP=2;
    public static final int TRANSITION_MODE_BOTTOM=3;
    public static final int TRANSITION_MODE_SCALE=4;
    public static final int TRANSITION_MODE_FADE=5;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TRANSITION_MODE_LEFT,TRANSITION_MODE_RIGHT,TRANSITION_MODE_TOP,TRANSITION_MODE_BOTTOM,TRANSITION_MODE_SCALE,TRANSITION_MODE_FADE})
    public @interface TransitionMode{}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
