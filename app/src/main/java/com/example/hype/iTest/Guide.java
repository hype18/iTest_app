package com.example.hype.iTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.hype.it_v2.R;

public class Guide extends AppCompatActivity implements View.OnTouchListener {

    TextView txt;

    double x;
    double y;

    String Down;
    String Move;
    String Up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        setTitle("Guide");

        txt = (TextView) findViewById(R.id.touch);
        txt.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Down = "Down: " + x + "," + y;
                Move = "";
                Up = "";
                break;
            case MotionEvent.ACTION_MOVE:
                Move = "Move: " + x + "," + y;
                break;

            case MotionEvent.ACTION_CANCEL:
                Move = "";
                Up = "Up: " + x + "," + y;
                break;

        }
        txt.setText(Down + "\n" + Move + "\n" + Up);

        return true;
    }

    public void back(View v){
        onBackPressed();
    }
}
