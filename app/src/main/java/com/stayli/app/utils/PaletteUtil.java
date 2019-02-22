package com.stayli.app.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.graphics.Palette;

/**
 * Created by yhgao on 2018/2/28.
 */

public class PaletteUtil implements Palette.PaletteAsyncListener {


    //1.活力颜色
    public static final String VIBRANT_SWATCH = "getVibrantSwatch";
    // 亮的活力颜色
    public static final String LIGHT_VIBRANT_SWATCH = "getLightVibrantSwatch";
    // 暗的活力颜色
    public static final String DARK_VIBRANT_SWATCH = "getDarkVibrantSwatch";
    //柔色
    public static final String MUTED_SWATCH = "getMutedSwatch";
    //亮的柔色
    public static final String LIGHT_MUTED_SWATCH = "getLightMutedSwatch";
    //暗的柔色
    public static final String DARK_MUTED_SWATCH = "getDarkMutedSwatch";
    // 优势样本
    public static final String DOMINANT_SWATCH = "getDominantSwatch";

    private static PaletteUtil instance;

    private PatternCallBack patternCallBack;
    private PaletteCallBack paletteCallBack;


    public static PaletteUtil getInstance() {
        if (instance == null) {
            instance = new PaletteUtil();
        }
        return instance;
    }

    public synchronized void init(Bitmap bitmap, PatternCallBack patternCallBack) {
        Palette.from(bitmap).generate(this);
        this.patternCallBack = patternCallBack;
    }

    public synchronized void init(Bitmap bitmap, PaletteCallBack patternCallBack) {
        Palette.from(bitmap).generate(this);
        this.paletteCallBack = patternCallBack;
    }


    public synchronized void init(Resources resources, int resourceId, PatternCallBack patternCallBack) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);
        Palette.from(bitmap).generate(this);
        this.patternCallBack = patternCallBack;
    }


    @Override
    public synchronized void onGenerated(Palette palette) {
        Palette.Swatch a = palette.getVibrantSwatch();
        Palette.Swatch b = palette.getLightVibrantSwatch();

        if (paletteCallBack != null) {
            String s = paletteCallBack.PaletteType();
            switch (s) {
                case DOMINANT_SWATCH:
                    Palette.Swatch g = palette.getDominantSwatch();
                    if (g != null) {
                        paletteCallBack.onCallBack(palette,g, g.getRgb());
                        break;
                    }
                case VIBRANT_SWATCH:
                    if (a != null) {
                        paletteCallBack.onCallBack(palette,a, a.getRgb());
                        break;
                    }
                case LIGHT_VIBRANT_SWATCH:
                    if (b != null) {
                        paletteCallBack.onCallBack(palette,b, b.getRgb());
                        break;
                    }
                case DARK_VIBRANT_SWATCH:
                    Palette.Swatch c = palette.getDarkVibrantSwatch();
                    if (c != null) {
                        paletteCallBack.onCallBack(palette,c, c.getRgb());
                        break;
                    }
                case MUTED_SWATCH:
                    Palette.Swatch d = palette.getMutedSwatch();
                    if (d != null) {
                        paletteCallBack.onCallBack(palette,d, d.getRgb());
                        break;
                    }
                case LIGHT_MUTED_SWATCH:
                    Palette.Swatch e = palette.getLightMutedSwatch();
                    if (e != null) {
                        paletteCallBack.onCallBack(palette,e, e.getRgb());
                        break;
                    }
                case DARK_MUTED_SWATCH:
                    Palette.Swatch f = palette.getDarkMutedSwatch();
                    if (f != null) {
                        paletteCallBack.onCallBack(palette,f, f.getRgb());
                        break;
                    }
                default:
                    Palette.Swatch swatch = palette.getSwatches().get(0);
                    paletteCallBack.onCallBack(palette,swatch, swatch.getRgb());

            }
        }


        int coloraEasy = 0;
        int colorbEasy = 0;

        if (a != null) {
            coloraEasy = a.getRgb();
        }
        if (b != null) {
            colorbEasy = b.getRgb();
        }
        if (patternCallBack != null)
            patternCallBack.onCallBack(changedImageViewShape(coloraEasy, colorbEasy)
                    , a.getTitleTextColor());


    }

    /**
     * 创建Drawable对象
     *
     * @param RGBValues
     * @param two
     * @return
     */
    private Drawable changedImageViewShape(int RGBValues, int two) {
        if (two == 0) {
            two = colorEasy(RGBValues);
        } else {
            two = colorBurn(two);
        }
        GradientDrawable shape = new GradientDrawable(GradientDrawable.Orientation.TL_BR
                , new int[]{RGBValues, two});
        shape.setShape(GradientDrawable.RECTANGLE);
        //设置渐变方式
        shape.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        //圆角
        shape.setCornerRadius(8);
        return shape;
    }


    /**
     * 颜色变浅处理
     *
     * @param RGBValues
     * @return
     */
    private int colorEasy(int RGBValues) {
        int red = RGBValues >> 16 & 0xff;
        int green = RGBValues >> 8 & 0xff;
        int blue = RGBValues & 0xff;
        if (red == 0) {
            red = 10;
        }
        if (green == 0) {
            green = 10;
        }
        if (blue == 0) {
            blue = 10;
        }
        red = (int) Math.floor(red * (1 + 0.1));
        green = (int) Math.floor(green * (1 + 0.1));
        blue = (int) Math.floor(blue * (1 + 0.1));
        return Color.rgb(red, green, blue);
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     * @return
     */
    private int colorBurn(int RGBValues) {
        int red = RGBValues >> 16 & 0xff;
        int green = RGBValues >> 8 & 0xff;
        int blue = RGBValues & 0xff;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }


    public interface PatternCallBack {
        void onCallBack(Drawable drawable, int titleColor);
    }

    public static interface PaletteCallBack {
        String PaletteType();

        void onCallBack(Palette palette,Palette.Swatch swatch, int rgb);
    }
}
