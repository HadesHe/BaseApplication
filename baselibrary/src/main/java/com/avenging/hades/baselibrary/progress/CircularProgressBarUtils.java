package com.avenging.hades.baselibrary.progress;

import android.animation.ValueAnimator;

import static java.lang.Math.min;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/19.
 */

class CircularProgressBarUtils {

    private CircularProgressBarUtils(){}

    public static void checkColors(int[] colors) {
        if(colors==null||colors.length==0){
            throw new IllegalArgumentException(" You must provide at least 1 color");
        }
    }

    public static void checkSpeed(float speed) {
        if(speed <= 0f){
            throw new IllegalArgumentException("Speed must be >=0");
        }

    }

    public static void checkAngle(int angle) {
        if(angle<0||angle>360){
            throw new IllegalArgumentException(String.format("Illegal angle %d: must be >=0 and <=360",angle));
        }

    }

    public static void checkPositiveOrZero(float number, String name) {
        if(number<0){
            throw new IllegalArgumentException(String.format("%s %d must be positive",name,name));
        }
    }

    static void checkPositive(int number, String name) {
        if (number <= 0)
            throw new IllegalArgumentException(String.format("%s must not be null", name));
    }



    public static void checkNotNull(Object o, String name) {
        if (o==null){
            throw new IllegalArgumentException(String.format("%s must be not null",name));
        }

    }

    static float getAnimatedFraction(ValueAnimator animator) {
        float fraction = animator.getDuration() > 0 ? ((float) animator.getCurrentPlayTime()) / animator.getDuration() : 0f;

        fraction = min(fraction, 1f);
        fraction = animator.getInterpolator().getInterpolation(fraction);
        return fraction;
    }
}
