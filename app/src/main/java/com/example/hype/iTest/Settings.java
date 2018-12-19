package com.example.hype.iTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hype.it_v2.R;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

    Button btntimer;
    TextView timeleft;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btntimer = (Button) findViewById(R.id.btnTimer);
        time = (EditText) findViewById(R.id.edtxt);
        timeleft = (TextView) findViewById(R.id.timecount);
    }


}