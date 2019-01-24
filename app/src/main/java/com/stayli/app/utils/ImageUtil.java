package com.stayli.app.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by  yahuigao
 * Date: 2019/1/24
 * Time: 16:56
 * Description:
 */
public class ImageUtil {

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle  当前角度
     * @param dis    目标角度
     * @param bitmap
     * @return
     */
    public static Bitmap rotaingImageView(int angle, int dis, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate((dis - angle));
        // 创建新的图片
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }


    /***
     * 将bitmap保存在SD jpeg格式
     *
     * @param bitmap
     *            图片bitmap
     * @param filePath
     *            要保存图片路径
     * @param quality
     *            压缩质量值
     */
    public static void saveImage(Bitmap bitmap, String filePath, int quality) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            // 如果图片还没有回收，强制回收
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
                System.gc();
            }
        } catch (Exception e) {
        }
    }
}
