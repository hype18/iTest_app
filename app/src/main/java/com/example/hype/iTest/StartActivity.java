package com.example.hype.iTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hype.it_v2.R;

import java.util.concurrent.TimeUnit;

public class StartActivity extends AppCompatActivity {
    TextView itest;
    TextView dev;
    TextView hy;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mainAnim();
    }

    private void closeActivity() {
        finish();
    }
    public void mainAnim() {
        Animation anim = null;
        itest = (TextView) findViewById(R.id.itest);
        dev = (TextView) findViewById(R.id.dev);
        hy = (TextView) findViewById(R.id.hy);
        pb = (ProgressBar) findViewById(R.id.pb);
        //anim for iTest
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        itest.startAnimation(anim);
        //anim for bottomTitle
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha700);
        dev.startAnimation(anim);
        hy.startAnimation(anim);
        //anim for progressBar
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha2000);
        pb.startAnimation(anim);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        t.start();
    }
}