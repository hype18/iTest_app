package com.example.hype.iTest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hype.it_v2.R;

import java.util.concurrent.TimeUnit;

public class ForBel extends AppCompatActivity {

    int score = 0;
    int count = 1;

    String[] queses = {"Любице бульбу?","А картошку?",
            "Белоруссия или Беларусь?","Ведаеце Коласа?","Па пяццот?",
            "Саладуху?","Пс, хлопец, можа крыху БРСМу?", " "};

    TextView ques;
    TextView quesCount;

    Button yes;
    Button no;
    Button btnback;

    ProgressBar pb;

    String s7 = "7/";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_bel);

        setTitle("Тест на беларуса");

        ques = (TextView) findViewById(R.id.ques);
        ques.setText(queses[0]);

        quesCount = (TextView) findViewById(R.id.quesCount);

        yes = (Button) findViewById(R.id.btnYes);
        no = (Button) findViewById(R.id.btnNo);

        btnback = (Button) findViewById(R.id.btnback);
        btnback.setEnabled(false);

        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(7);
        pb.setProgress(0);

    }

    public void onclick(View v){
        switch (v.getId()){
            case R.id.btnNo:
                if(count==7){
                    score -= 100;
                }
                score++;
                update_ques();
                break;
            case R.id.btnYes:
                score++;
                update_ques();
                break;
        }
    }

    void update_ques(){
        Log.d("count", "COUNT "+queses.length+" TR"+count);
        if(count == 7){
            showDialog(1);
            yes.setEnabled(false);
            no.setEnabled(false);
            btnback.setEnabled(true);
        }
        if(count == 2){
            yes.setText("Беларусь");
            no.setText("Беларусь");
        }
        if(count == 3){
            yes.setText("Ведаю");
            no.setText("И вершы яго");
        }
        if(count == 4){
            yes.setText("...");
            no.setText("...");
        }
        if(count == 5){
            yes.setText("Так!");
            no.setText("Не");
            no.setEnabled(false);
        }
        if(count == 6){
            yes.setText("Ужо лячу!");
            no.setText("Не");
            no.setEnabled(true);
        }
        ques.setText(queses[count]);
        pb.setProgress(count);
        quesCount.setText(s7+count);
        count++;

    }

    public void backAct(View v){
        onBackPressed();
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        if (id == 1) {
            // заголовок
            adb.setTitle("Заключение");
            // сообщение
            if(score>0)
                adb.setMessage("По результатам теста вы набрали "+score+" очков\n Вы сапраудны Беларус =)");
            else
                adb.setMessage("По результатам теста вы набрали "+score+" очков\n Не беларус!..");
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            adb.setPositiveButton(R.string.yes, myClickListener);
            adb.setCancelable(false);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    break;
            }
        }
    };
}
