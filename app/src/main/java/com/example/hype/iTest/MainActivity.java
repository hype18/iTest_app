package com.example.hype.iTest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.example.hype.it_v2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String[] mGroupsArray = new String[] { "Для белорусов", "А тут байда какая-то" };

    private String[] mTestArray = new String[] { "Насколько Вы белорус" };
    private String[] mAcerArray = new String[] { "Матан", "Шпрехен зе дойтчь", "Мюнстерберга" };

    ExpandableListView elvMain;

    int check = 0;

    final String TAG = "States";

    final int DIALOG_WARN = 1;
    final int DIALOG_SER = 2;
    final int DIALOG_MATH = 3;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity: onCreatet()");

        setTitle("iTest");

            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            check++;

        //showDialog(DIALOG_SER);
        //showDialog(DIALOG_WARN);
        //убрано, потому что сказали, что не солидно

        main();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume()");
    }


    private void main(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setTitle("Locale Date");

        Map<String, String> map;
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();

        for (String group : mGroupsArray) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList.add(map);
        }

        String groupFrom[] = new String[] { "groupName" };
        int groupTo[] = new int[] { android.R.id.text1 };

        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
        for (String tests : mTestArray) {
            map = new HashMap<>();
            map.put("testName", tests);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        сhildDataItemList = new ArrayList<>();
        for (String acer : mAcerArray) {
            map = new HashMap<>();
            map.put("testName", acer);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        String childFrom[] = new String[] { "testName" };

        int childTo[] = new int[] { android.R.id.text1 };

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupDataList,
                R.layout.my_exp_list, groupFrom,
                groupTo, сhildDataList, R.layout.my_exp_list_child,
                childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expList);
        expandableListView.setAdapter(adapter);

        elvMain = (ExpandableListView) findViewById(R.id.expList);

        final int[] m = new int[1];
        final int[] n = new int[1];



        //Кликаем на чайлд и открывается активити с нужным тестом
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition +
                        " childPosition = " + childPosition +
                        " id = " + id);
                m[0] = groupPosition;
                n[0] = childPosition;

                int group = m[0];
                int child = n[0];

                startTest(group,child);
                return false;
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Здесь стартуем тест
    public void startTest(int groupPosition, int childPosition) {
        Intent intent;

        switch (groupPosition){
            case 0:
                switch (childPosition){
                    //для белорусов
                    case 0:
                        intent = new Intent(this, ForBel.class);
                        startActivity(intent);
                        break;
                        }
                break;
            case 1:
                switch (childPosition){
                    //Матан
                    case 0:
                        showDialog(DIALOG_MATH);
                        break;
                    //Шрехен зе дойтчь
                    case 1:
                        Toast.makeText(this, "Да я сам не дойчь",
                                Toast.LENGTH_SHORT).show();
                        break;
                    //Мюнстерберг
                    case 2:
                        intent = new Intent(this, Munsterberg.class);
                        startActivity(intent);
                        break;
                } break;
        }
    }



    //Тут всякая фигня для боковой менюшки
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //For Navigation Menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_podpis) {
            intent = new Intent(this, Podpis.class);
            startActivity(intent);
        } else if (id == R.id.nav_guide) {
            intent = new Intent(this, Guide.class);
            startActivity(intent);
        } else if (id == R.id.nav_donate) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/hype18"));
            startActivity(intent);
        } else if (id == R.id.nav_thank) {
            intent = new Intent(this, TabTest.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   ///Create for DIALOG///
   protected Dialog onCreateDialog(int id) {
       AlertDialog.Builder adb = new AlertDialog.Builder(this);
       /*if (id == DIALOG_WARN) {
           adb.setTitle(R.string.warn);
           adb.setMessage(R.string.mess);
           // иконка
           adb.setIcon(android.R.drawable.ic_dialog_info);
           adb.setPositiveButton(R.string.yes, myClickListener);
           adb.setNegativeButton(R.string.no, myClickListener);
           // создаем диалог
           adb.setCancelable(false);
           return adb.create();
       }
       if (id == DIALOG_SER) {
           // заголовок
           adb.setTitle(R.string.warn2);
           // сообщение
           adb.setMessage(R.string.mess2);
           // иконка
           adb.setIcon(android.R.drawable.ic_dialog_info);
           // кнопка положительного ответа
           adb.setPositiveButton(R.string.yes2, myClickListener);
           // кнопка отрицательного ответа
           adb.setNegativeButton(R.string.no2, myClickListener);
           // создаем диалог
           adb.setCancelable(false);
           return adb.create();
       }*/
       if (id == DIALOG_MATH) {
           // заголовок
           adb.setTitle("Короче");
           // сообщение
           adb.setMessage("Я не думаю, что интеграл в уме посчитать можно. А " +
                   "спрашивать типа \"чему равно 2+2*2?\" и ак далее Уже заезжано\nТыкай далей=) ");
           adb.setIcon(android.R.drawable.ic_dialog_info);
           adb.setPositiveButton("Оки", myClickListener);
           adb.setCancelable(false);
           return adb.create();
       }

       return super.onCreateDialog(id);
   }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    break;
                // негативная кнопка
                case Dialog.BUTTON_NEGATIVE:
                    finish();
                    break;
            }
        }
    };
}
