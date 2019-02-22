package com.stayli.app.tools.helper;

import com.bumptech.glide.Glide;
import com.stayli.app.utils.Util;

import java.io.File;

/**
 * Created by yhgao on 2018/2/27.
 */

public class ImageLoaderUtil {



    private ImageLoaderUtil() {

        File glide = Glide.getPhotoCacheDir(Util.getApplication(), "glide");

        Glide.get(Util.getApplication());
    }


}
