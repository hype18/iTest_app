package com.example.hype.iTest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.hype.it_v2.R;

public class Podpis extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    private static final int CM_DELETE_ID = 1;
    ListView lvData;
    DB db;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;

    Button btnAdd;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podpis);

        Toast.makeText(this, "Удержанием на записи " +
                "можно ее удалить", Toast.LENGTH_SHORT).show();

        setTitle("Подписи");


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.edText1);

        db = new DB(this);
        db.open();

        // получаем курсор
        cursor = db.getAllData();
        startManagingCursor(cursor);

        String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_TXT };
        int[] to = new int[] { R.id.ivImg, R.id.tvText };

        scAdapter = new SimpleCursorAdapter(this, R.layout.podpis_item, cursor, from, to);
        lvData = (ListView) findViewById(R.id.lvSimple);
        lvData.setAdapter(scAdapter);

        //контекстное меню к списку
        registerForContextMenu(lvData);
    }


    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            // извлекаем id записи и удаляем из БД
            db.delRec(acmi.id);
            //обновим курсор
            cursor.requery();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
        // закрываем подключение при выходе
        db.close();
    }

    //для выхода обратно
    public void back(View v){
        onBackPressed();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAdd:
                db.addRec((cursor.getCount() + 1)+". "+etName.getText().toString(), R.drawable.ic_human);
                // обновляем курсор
                cursor.requery();
                break;
        }
    }

}
