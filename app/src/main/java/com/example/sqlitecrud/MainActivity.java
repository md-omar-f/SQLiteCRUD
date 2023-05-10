package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_employee_no, et_employee_name;
    private Button btn_insert, btn_update, btn_delete, btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);

        et_employee_no = findViewById(R.id.et_employee_no);
        et_employee_name = findViewById(R.id.et_employee_name);

        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_search = findViewById(R.id.btn_search);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.insert(et_employee_no.getText().toString(), et_employee_name.getText().toString());
                clear();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.delete(et_employee_no.getText().toString());
                clear();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.update(et_employee_no.getText().toString(),
                        et_employee_name.getText().toString());
                clear();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_employee_name.setText(helper.search(et_employee_no.getText().toString()));
            }
        });

    }

    void clear() {
        et_employee_no.setText("");
        et_employee_name.setText("");
    }
}