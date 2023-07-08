package com.example.view.test_mo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;

import com.example.view.R;

/*
此页面是查看用户登录数据的，没有实装
 */
public class MainActivity2 extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        tv =(TextView) findViewById(R.id.textViewx);
        tv.setText(intent.getStringExtra("data"));
    }
}