package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.view.sqlite.DBOpenHelper;
import com.google.android.material.snackbar.Snackbar;
/**
 * 注册界面
 */
public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button r = (Button) findViewById(R.id.register);
        //注册
        r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String e1 = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
                String e2 = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();
                //判断是否为空
                System.out.println("id:"+e1+"\n密码："+e2);
                if(e1.equals("")){
                    Snackbar.make(v, "请输入账号", Snackbar.LENGTH_SHORT).show();
                }
                else if(e2.equals("")){
                    Snackbar.make(v, "请输入密码", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    if (UserService.signUp(e1, e2))
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(v, "注册成功,请返回", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(v, "注册失败"+UserService.signUp(e1, e2), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}