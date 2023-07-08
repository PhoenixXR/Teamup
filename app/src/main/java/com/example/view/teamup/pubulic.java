package com.example.view.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.view.R;
import com.example.view.teamup.PlService;
import com.google.android.material.snackbar.Snackbar;
/**
 * 发布界面
 */
public class pubulic extends AppCompatActivity {
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubulic);

        b = (Button) this.findViewById(R.id.button2);

        //发布
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                //数据库链接
//                DBOpenHelper mySQLiteHelper = new DBOpenHelper(pubulic.this,"test.db",null,1);
//                SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
                //获取输入
                String kook = ((EditText) findViewById(R.id.editTextText)).getText().toString();
                String lv = ((EditText) findViewById(R.id.editTextText2)).getText().toString();
                String num = ((EditText) findViewById(R.id.editTextText3)).getText().toString();
                //kook,lv,num不为空
                if(kook.equals("")){
                    Snackbar.make(v, "请kook号", Snackbar.LENGTH_SHORT).show();
                }
                else if(lv.equals("")){
                    Snackbar.make(v, "请输入对局等级", Snackbar.LENGTH_SHORT).show();
                }
                else if(num.equals("")){
                    Snackbar.make(v, "请输入人数", Snackbar.LENGTH_SHORT).show();
                }

                else {
                    if (PlService.Pub(kook, lv, num))
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(v, "发布成功,请返回", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(v, "发布失败"+PlService.Pub(kook, lv, num), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}