package com.stayli.app.utils;

import android.content.Context;

/**
 * Created by yhgao on 2018/2/8.
 */

public class SizeUtil {

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        Context context = Util.getApplication();
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        Context context = Util.getApplication();
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue) {
        Context context = Util.getApplication();
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */

    public static int px2sp(float pxValue) {
        Context context = Util.getApplication();
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 主要参考stackoverflow的这篇问答：http://stackoverflow.com/questions/3407256/height-of-status-bar-in-android
     * <p>
     * 状态栏高度=取大于其的最小整数（25*上下文_获取应用包的资源实例_获取当前屏幕尺寸_屏幕密度比例）
     * <p>
     * 其中density并不是真实的屏幕密度，而是一个相对密度，基准密度为160dpi，比如我测试的手机为HTC one m8，
     * 查的屏幕密度为441dpi，相对160为2.75，density就取为3。各分辨率的density取值为：
     * <p>
     * ldpi (dpi=120，density=0.75)
     * mdpi (dpi=160，density=1)
     * hdpi (dpi=240，density=1.5)
     * xhdpi (dpi=320，density=2)
     * xxhdpi (dpi=480，density=3)
     * 所以得到的状态栏高度为25*3=75
     * <p>
     * 由这种方法得到的状态栏高度具有较大局限性，比如因为某种需要去掉状态栏或本身没有状态栏，
     * 此时状态栏高度应为0，但是该方法依然能够得到一个非零的状态栏高度。
     *
     * @return 状态栏高度
     */
    public static double getStatusBarHeight() {
        Context context = Util.getApplication();
        double statusBarHeight = Math.ceil(25 * context.getResources().getDisplayMetrics().density);
        if (statusBarHeight > 0) return statusBarHeight;

        return getStatusBarHeight(context);

    }

    /**
     * 主要参考stackoverflow的这篇问答：http://stackoverflow.com/questions/3407256/height-of-status-bar-in-android
     * <p>
     * 状态栏获取补充 主要参考
     *
     * @param context 上下文
     * @return 状态栏
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
