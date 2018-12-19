package com.example.hype.iTest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hype.it_v2.R;

import java.util.concurrent.TimeUnit;

public class Munsterberg extends AppCompatActivity implements View.OnTouchListener {

    int secLeft = 0;
    int founded = 0;
    int check = 0;

    LinearLayout mainll;

    TextView maintxt;
    TextView timing;

    final int DIALOG_WARN = 1;
    final int DIALOG_STOP = 1;

    TextView found;

    Button btncount;

    //Создание
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_munsterberg);
        setTitle("Тест Мюнстерберга");

        found = (TextView) findViewById(R.id.found);

        maintxt = (TextView) findViewById(R.id.mainText);
        timing = (TextView) findViewById(R.id.timing);

        mainll = (LinearLayout) findViewById(R.id.mainll);
        mainll.setOnTouchListener(this);

        btncount = (Button) findViewById(R.id.btncount);
    }

    //добавляем подсчет по нажатию кнопки
    public void onCount(View v){
        Toast.makeText(this, "+1", Toast.LENGTH_SHORT).show();
        founded++;
        found.setText("Найдено: "+founded);
    }

    //Выход из теста
    public void rulesG(View v){
        showDialog(DIALOG_WARN);
    }

    //Начать тест
    @SuppressLint("ResourceAsColor")
    public void startTest(View v){
        Button start = (Button) findViewById(R.id.btnstart);
        start.setEnabled(false);
        maintxt.setTextColor(R.color.colorPrimaryDark);
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 120; i >= 0; i--) {
                    updateTime();
                    // обновляем TextView
                    timing.setText("Осталось " + i + " секунд");
                    // пишем лог
                    if(i == 0)
                        check = 1;
                    Log.d("time", "i = " + i);
                }
            }
        });
        t.start();
    }
    public void check(int i){
        if(i==1){
            maintxt.setText("Время вышло");
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("touch", "down");
                check(check);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("touch", "move");
                check(check);
            break;
            case MotionEvent.ACTION_UP:
                check(check);
                break;
            case MotionEvent.ACTION_CANCEL:
                check(check);
                break;
        }
        return true;
    }

    public void updateTime(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    //для ДИАЛОГОВ
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        if (id == DIALOG_WARN) {
            // заголовок
            adb.setTitle("Правило!");
            // сообщение
            adb.setMessage("Среди буквенного текста имеются слова. Ваша задача - как можно быстрее найти" +
                    " их! \n" +
                    "Если слова входят друг в друга, то считаем только самое длинное слово.\n" +
                    "Жми кнопку +1, если нашел - поможет посчитать найденные слова\n"+
                    "Время выполнения задания — 1 минута");
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            adb.setPositiveButton(R.string.yes, myClickListener);
            adb.setCancelable(false);
            return adb.create();
        }
        if (id == 10) {
            // заголовок
            adb.setTitle("Результат");
            // сообщение
            if(founded>25)
                adb.setMessage("От натыкал! Здесь всего 25 слов... От кадр;)");
            else
                adb.setMessage("Слов найдено: "+founded+".\nВ тексте содержится 25 слов! \n" +
                    "Хорошим считается результат – 20 и более баллов. Низкие показатели - " +
                    "18 и менее баллов.\n");
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            adb.setPositiveButton(R.string.yes, myClickListener2);
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

    //Тут сделаем все для кнопки СТОП
    public void end(View v){
        showDialog(10);
    }

        DialogInterface.OnClickListener myClickListener2 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        onBackPressed();
                        break;
                }
            }
        };

}
