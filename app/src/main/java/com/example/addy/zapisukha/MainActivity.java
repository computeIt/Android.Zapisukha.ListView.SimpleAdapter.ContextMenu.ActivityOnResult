package com.example.addy.zapisukha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String ATTR_NAME = "name";
    public static final String ATTR_PHONE = "phone";
    public final String ATTR_IMAGE = "image";

    private static final int CONTEXT_MENU_DELETE_ID = 1;
    
    ListView listView;
    SimpleAdapter adapter;
    Map<String, Object> map;
    ArrayList<Map<String, Object>> data;
    Button btnDelete, btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] phones = {"11111", "22222", "33333", "44444", "555555", "11111", "22222", "33333", "44444", "555555", "11111", "22222", "33333", "44444", "555555"};

        data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 15; i++) {
            map = new HashMap<>();
            map.put(ATTR_NAME, "name" + i);
            map.put(ATTR_PHONE, phones[i]);
            map.put(ATTR_IMAGE, R.drawable.smile);
            data.add(map);
        }

        String[] from = {ATTR_NAME, ATTR_PHONE, ATTR_IMAGE};
        int[] to = {R.id.name, R.id.phone, R.drawable.smile};

        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        btnAdd = findViewById(R.id.btn_add);
//        btnDelete = findViewById(R.id.btn_delete);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CONTEXT_MENU_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == CONTEXT_MENU_DELETE_ID){
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(adapterContextMenuInfo.position);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(this, ActivityAdd.class);
                startActivityForResult(intent, 1);
                break;
//            case R.id.btn_delete:
//
//                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data1) {
        if(data1 == null)
            return;
        String name1;
        String phone1;

        if(requestCode == 1 && resultCode == RESULT_OK){
            name1 = data1.getStringExtra(ATTR_NAME);
            phone1 = data1.getStringExtra(ATTR_PHONE);
            map = new HashMap<String, Object>();
            map.put(ATTR_NAME, name1);
            map.put(ATTR_PHONE, phone1);
            map.put(ATTR_IMAGE, R.drawable.smile);
            data.add(map);
            adapter.notifyDataSetChanged();
        }
    }
}
