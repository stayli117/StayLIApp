package com.stayli.app.ui.face;

import android.media.ImageReader;
import android.os.Handler;

/**
 * Created by  yahuigao
 * Date: 2019/1/24
 * Time: 13:55
 * Description:
 */
public abstract class FaceOnImageAvailableListener implements ImageReader.OnImageAvailableListener {

    private Handler mBackgroundHandler;

    public FaceOnImageAvailableListener() {

    }

    public void onSaveHandler(Handler backgroundHandler) {
        mBackgroundHandler = backgroundHandler;
    }

    public Handler getHandler() {
        return mBackgroundHandler;
    }

    @Override
    public abstract void onImageAvailable(ImageReader reader);

    public abstract void onCaptureCompleted();


}
