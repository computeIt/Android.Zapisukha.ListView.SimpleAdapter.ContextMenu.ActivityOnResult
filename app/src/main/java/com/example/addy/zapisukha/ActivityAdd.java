package com.example.addy.zapisukha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.addy.zapisukha.MainActivity.ATTR_NAME;
import static com.example.addy.zapisukha.MainActivity.ATTR_PHONE;

public class ActivityAdd extends AppCompatActivity implements View.OnClickListener {
    Button btnSend;
    EditText inputName, inputPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
        inputName = findViewById(R.id.new_name);
        inputPhone = findViewById(R.id.new_phone);
    }

    @Override
    public void onClick(View v) {
        if (inputName != null && inputPhone != null) {
            Intent intent = new Intent();
            intent.putExtra(ATTR_NAME, inputName.getText().toString());
            intent.putExtra(ATTR_PHONE, inputPhone.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
        else//it doesnot work... why?
            Toast.makeText(this, "data cannot be null, input something please", Toast.LENGTH_LONG).show();
    }
}
