package com.example.hype.iTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hype.it_v2.R;

public class Tab2 extends AppCompatActivity implements View.OnClickListener {

    Switch sw;
    ImageView iv;

    TextView title;
    TextView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        title = (TextView) findViewById(R.id.title);
        bottom = (TextView) findViewById(R.id.bottomtxt);


        iv = (ImageView) findViewById(R.id.pic);
        sw = (Switch) findViewById(R.id.tyk);

        sw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tyk:
                if(sw.isChecked()){
                    title.setText("Моё=)");
                    bottom.setText("Жду Новый год с тобой!");
                    iv.setImageResource(R.drawable.malaya2);
                }else{
                    title.setText("Это моя Сашка)");
                    bottom.setText("Спасибо тебе за твою поддержку, веру и любовь)");
                    iv.setImageResource(R.drawable.malaya);
                }
        }
    }
}
