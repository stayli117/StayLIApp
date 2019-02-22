package com.stayli.app.ui.face;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stayli.app.R;
import com.stayli.app.base.BaseActivity;
import com.stayli.app.databinding.ActivityFaceBinding;
import com.stayli.app.model.domain.FaceInfo;
import com.stayli.app.ui.view.ocr.CameraPreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class FaceActivity extends BaseActivity {
    private static final String TAG = "FaceActivity";
    protected ActivityFaceBinding binding;
    private LinearLayout mLlCon;
    private FaceViewModel model;
    private CameraPreview mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_face);
        model = ViewModelProviders.of(this).get(FaceViewModel.class);
        mLlCon = binding.llCon;
        mCameraView = binding.cameraView;
        mCameraView.setLensFacing(true); // 使用前置
        final File mFile = new File(getExternalFilesDir(null), "pic.jpg");
//        binding.ivFace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                /* 开启Pictures画面Type设定为image */
//                intent.setType("image/*");
//                /* 使用Intent.ACTION_GET_CONTENT这个Action */
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                /* 取得相片后返回本画面 */
//                startActivityForResult(intent, 1);
//            }
//        });


        final LiveData<FaceInfo> faceInfo = model.getFaceInfo();
        faceInfo.observe(this, new Observer<FaceInfo>() {
            @Override
            public void onChanged(@Nullable FaceInfo faceInfo) {
                if (faceInfo != null) {
                    Log.e(TAG, "onChanged: " + faceInfo.toString());
                    Toast.makeText(mContext, "ret " + faceInfo.ret + " msg " + faceInfo.msg, Toast.LENGTH_SHORT).show();
                    showInfo(faceInfo);
                }
            }
        });

        mCameraView.setOnImageAvailableListener(new FaceOnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                Handler handler = getHandler();
                if (handler != null) {
                    Log.d(TAG, "onImageAvailable post ---> ");
                    ImageSaver imageSaver = new ImageSaver(reader.acquireNextImage(), mFile) {
                        @Override
                        public void onChanged(@Nullable File file) {
                            if (file != null) {
                                Toast.makeText(FaceActivity.this, "onImageAvailable: " + file, Toast.LENGTH_SHORT).show();
                                loadFaceInfo(file);
                            }
                        }
                    };
                    handler.post(imageSaver);
                }
            }

            @Override
            public void onCaptureCompleted() {
                Toast.makeText(FaceActivity.this, "Saved: ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCaptureCompleted 不一定文件写入完整");
            }
        });

    }

    private void loadFaceInfo(File file) {
        model.loadFaceInfo(file, new Observer<Bitmap>() {
            @Override
            public void onChanged(@Nullable final Bitmap bitmap) {
                final ImageView ivFace = binding.ivFace;
                ivFace.post(new Runnable() {
                    @Override
                    public void run() {
                        ivFace.setImageBitmap(bitmap);
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.onResume(this); // 必须放在这里
    }

    @Override
    protected void onPause() {
        mCameraView.onPause(); // 必须放在这里
        super.onPause();
    }

    // 拍摄按钮事件
    public void takePic(View view) {
        mCameraView.takePicture();
    }

    //获取本地图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                String img_url = uri.getPath();//这是本机的图片路径
                ContentResolver cr = this.getContentResolver();
                InputStream inputStream = cr.openInputStream(uri);


                model.loadFaceInfo(inputStream, new Observer<Bitmap>() {
                    @Override
                    public void onChanged(@Nullable final Bitmap bitmap) {
//                        final ImageView imageView = binding.ivFace;
//                        imageView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                imageView.setImageBitmap(bitmap);
//                            }
//                        });
                    }
                });

            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showInfo(FaceInfo faceInfo) {
        FaceInfo.DataBean data = faceInfo.data;

        List<FaceInfo.DataBean.FaceListBean> face_list = data.face_list;
        for (FaceInfo.DataBean.FaceListBean faceListBean : face_list) {
            int age = faceListBean.age;
            int beauty = faceListBean.beauty;
            int expression = faceListBean.expression;
            int glass = faceListBean.glass;
            int roll = faceListBean.roll;

            TextView textView = getTextView();
            String info = "" + "年龄 " + age + "\n魅力值 " + beauty
                    + "\n笑容 " + expression + "\n眼镜 " + glass + " 平面旋转 " + roll;
            textView.setText(info);
//            binding.llConInfo.addView(textView);
            Log.e(TAG, "showInfo: " + info);
        }


    }

    public TextView getTextView() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }
}
