package com.stayli.app.utils;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.stayli.app.utils.FileSizeUtil.SIZETYPE_KB;

/**
 * Created by  yahuigao
 * Date: 2019/1/23
 * Time: 15:37
 * Description:
 */
public class AessUtil {
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null)
                result.append(line);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFromAssetsImage(Context context, String fileName) throws IOException {
        InputStream in = context.getResources().getAssets().open(fileName);
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        byte[] bytes = out.toByteArray();
        byte[] encode = Base64.encode(bytes, Base64.NO_WRAP);
        return new String(encode);
    }

    public static byte[] getFromAssetsImage(InputStream in) throws IOException {
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        byte[] bytes = out.toByteArray();

        return bytes;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @param observer
     * @return
     */
    public static byte[] compressImage(Bitmap image, Observer<Bitmap> observer) {
        ByteArrayOutputStream baos = compressSampling(image, observer);
        byte[] bytes = baos.toByteArray();
        Log.d(TAG, "compressImage: " + bytes.length);
        return bytes;
    }

    private static final String TAG = "AessUtil";

    private static ByteArrayOutputStream compressSampling(Bitmap bitmap, Observer<Bitmap> observer) {

        int bitmapSize = getBitmapSize(bitmap);
        double fileSize = FileSizeUtil.FormetFileSize(bitmapSize, SIZETYPE_KB);
        Log.e(TAG, "compressSampling1: " + fileSize);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (fileSize <= 100) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            return baos;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap bitmap1 = null;
        while (fileSize > 100) {
            options.inSampleSize++;
            bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            bitmapSize = getBitmapSize(bitmap1);
            fileSize = FileSizeUtil.FormetFileSize(bitmapSize, SIZETYPE_KB);
        }
        observer.onChanged(bitmap1);
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        Log.e(TAG, "compressSampling: " + fileSize);
        if (bitmap1 != null) {
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos1);
        }
        return baos1;
    }

    /**
     * 得到bitmap的大小
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }
}
