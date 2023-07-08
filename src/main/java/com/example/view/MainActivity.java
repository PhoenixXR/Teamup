package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.view.teamup.listview;
import com.example.view.test_mo.MainActivity2;
import com.google.android.material.snackbar.Snackbar;
/*
登录界面
 */
public class MainActivity extends AppCompatActivity {
    private Button d,r;//d==登录，r==注册
    private CheckBox c1;//记住密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //强制创建网络连接，不建议
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        d = (Button) findViewById(R.id.button);
        r = (Button) findViewById(R.id.register);
        c1 = (CheckBox) findViewById(R.id.checkBox1);

        //记住账号密码，保持checkbox状态
        // 获取 Shared Preferences 对象
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // 从 Shared Preferences 中检索账号和密码
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if(!username.equals("")){((EditText) findViewById(R.id.editTextTextPassword)).setText(username);}
        if(!password.equals("")){((EditText) findViewById(R.id.editTextTextPassword2)).setText(password);}

        boolean rememberChecked = sharedPreferences.getBoolean("rememberChecked", false);
        // 设置 CheckBox 的状态
        c1.setChecked(rememberChecked);
        // 在 CheckBox 的状态改变监听器中保存 CheckBox 的状态
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 获取 CheckBox 的选中状态
                boolean rememberChecked = c1.isChecked();
                // 保存 CheckBox 的状态到 Shared Preferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("rememberChecked", rememberChecked);
                editor.apply();
            }
        });


        //登录
        d.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //跳转组队界面
                Intent intent=new Intent(MainActivity.this, listview.class);
                //e1==username，e2==password
                String e1 = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
                String e2 = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();
                System.out.println("id:"+e1+"\n密码："+e2);
                if(e1.equals("")){
                    Snackbar.make(v, "请输入账号", Snackbar.LENGTH_SHORT).show();
                }
                else if(e2.equals("")){
                    Snackbar.make(v, "请输入密码", Snackbar.LENGTH_SHORT).show();
                }
                else{
                    if (UserService.signIn(e1,e2))
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(v, "登录成功", Snackbar.LENGTH_SHORT).show();
                                //记住账号密码
                                // 获取 CheckBox 的选中状态
                                boolean rememberChecked = c1.isChecked();
                                // 如果 CheckBox 被选中，保存账号和密码到 Shared Preferences
                                if (rememberChecked) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", e1);
                                    editor.putString("password", e2);
                                    editor.apply();
                                }
                                //没选就不记住
                                else{
                                    // 获取 Shared Preferences 对象
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    // 获取 SharedPreferences 的编辑器
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    // 清除账号和密码
                                    editor.remove("username");
                                    editor.remove("password");
                                    // 提交编辑器的更改
                                    editor.apply();
                                }
                                //传id，跳转
                                intent.putExtra("data", "id:" + e1 +"\n");
                                startActivity(intent);
                            }
                        });
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(v, "登录失败"+UserService.signIn(e1, e2), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

        //注册
        r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

    }

}