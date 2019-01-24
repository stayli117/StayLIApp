package com.stayli.app.ui.face;

import android.arch.lifecycle.Observer;
import android.media.Image;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by  yahuigao
 * Date: 2019/1/24
 * Time: 13:50
 * Description:
 * <p>
 * 将捕获到的图像保存到指定的文件中
 */
public abstract class ImageSaver implements Runnable, Observer<File> {

    private final Image mImage;
    private final File mFile;

    public ImageSaver(Image image, File file) {
        mImage = image;
        mFile = file;
    }

    @Override
    public void run() {
        ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(mFile);
            output.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mImage.close();
            if (null != output) {
                try {
                    output.close();
                    onChanged(mFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public abstract void onChanged(@Nullable File file);
}