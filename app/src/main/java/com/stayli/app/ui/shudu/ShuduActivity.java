package com.stayli.app.ui.shudu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stayli.app.R;
import com.stayli.shudu.ShuduTest;

public class ShuduActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shudu);
        ShuduTest.main(null);
    }
}
