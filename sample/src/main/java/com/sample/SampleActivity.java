package com.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.caladbolg.Caladbolg;
import com.caladbolg.Caladbolg.OnCancelPickColorListener;
import com.caladbolg.Caladbolg.OnPickedColorListener;


public class SampleActivity extends ActionBarActivity implements OnPickedColorListener, OnCancelPickColorListener {

    Caladbolg mCaladbolg;
    RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_sample, null);
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mCaladbolg.show(getSupportFragmentManager(), "caladbolg");
                return false;
            }
        });
        setContentView(mLayout);

        if (getSupportFragmentManager().findFragmentByTag("caladbolg") == null) {
            mCaladbolg = Caladbolg.getInstance(Color.BLACK);
            mCaladbolg.show(getSupportFragmentManager(), "caladbolg");
        }
    }

    @Override
    public void onCancelPickColor() {
        Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPickedColor(int rgb, int alpha) {
        Log.v(SampleActivity.class.getSimpleName(), "RGB:" + rgb + " Alpha:" + alpha);
        //mCaladbolg = Caladbolg.getInstance(rgb);
        mLayout.setBackgroundColor(rgb);
        mLayout.setAlpha((float) alpha);
    }
}
