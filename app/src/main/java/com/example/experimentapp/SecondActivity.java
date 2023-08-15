package com.example.experimentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private AppCompatEditText etName;
    private AppCompatButton btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        etName = findViewById(R.id.name);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                if (name.isEmpty())
                {
                    etName.setError(getString(R.string.name_error));
                    etName.requestFocus();
                }
                else {
                    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper(SecondActivity.this);
                    sharedPrefrenceHelper.saveString("name",name);
                    Toast.makeText(SecondActivity.this,"Name : "+name,Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }





}