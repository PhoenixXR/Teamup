package com.example.view.teamup;

import static com.example.view.teamup.PlService.listv;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import java.net.MalformedURLException;
import java.util.Stack;

import com.example.view.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.logging.Handler;

/*
显示组队信息
 */
public class listview extends TabActivity {
    Button pu,sort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        // 获取TabHost实例
        TabHost tabHost = getTabHost();

        // 添加标签页1
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("组队");
        tab1.setContent(R.id.tab1_content);
        tabHost.addTab(tab1);

        // 添加标签页2
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("图鉴(未实现)");
        tab2.setContent(R.id.tab2_content);
        tabHost.addTab(tab2);

        // 添加标签页3
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("战绩查询(未实现)");
        tab3.setContent(R.id.tab3_content);
        tabHost.addTab(tab3);

        // 设置默认选中的标签
        tabHost.setCurrentTab(0);


        pu = (Button) this.findViewById(R.id.publish);
        sort = (Button) this.findViewById(R.id.sort);
        //如果将来添加或删除按钮，只需要更新 buttons 数组的大小，而不需要修改循环体内的代码
        Button[] buttons = new Button[9];
        buttons[1] = (Button) findViewById(R.id.b1);
        buttons[2] = (Button) findViewById(R.id.b2);
        buttons[3] = (Button) findViewById(R.id.b3);
        buttons[4] = (Button) findViewById(R.id.b4);
        buttons[5] = (Button) findViewById(R.id.b5);
        buttons[6] = (Button) findViewById(R.id.b6);
        buttons[7] = (Button) findViewById(R.id.b7);
        buttons[8] = (Button) findViewById(R.id.b8);

        //传输id
        Intent intent = getIntent();
        String id = intent.getStringExtra("data");


        //  跳转发布页面
        pu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(listview.this, pubulic.class);
                startActivity(intent);
            }
        });

        //显示
        try {
            post(buttons);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        //筛选lv，num（临时改成刷新，指跳本页面）
        sort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                //测试listv模块
//                try {
//                    listv();
//                } catch (MalformedURLException e) {
//                    throw new RuntimeException(e);
//                }
                Snackbar.make(v, "刷新成功", Snackbar.LENGTH_SHORT).show();
                refresh();
            }
        });

    }

    //刷新
    public void refresh() {
        onCreate(null);
    }

    //查询,组队信息上传到button，实现新发布的排第一
    @SuppressLint("SetTextI18n")
    public void post(Button[] buttons) throws MalformedURLException {
        Stack info = null;
        info = listv();
        //循环输入更简洁,强循环buttons，因为没有b[0]，所以要避免.NullPointerException
        //for (Button button : buttons)
        for (int i = 1; i < buttons.length; i++) {
            Button button = buttons[i];
            if (!info.isEmpty()) {
                //拆分info后再组装，减少信息传输，也可以做到页面？
                String str = info.pop().toString();
                int t,kook,lv;
                String time;
                t = str.indexOf('|');
                kook = str.indexOf('|',t+1);
                lv = str.indexOf('|',kook+1);
                //>10min的显示大于
                if(Integer.parseInt(str.substring(0,t))<600){
                    time = str.substring(0,t);
                }
                else{
                    time = "超过10分钟";
                }
                button.setText("已经发布:"+time+
                        "s|kook:"+str.substring(t+1,kook)+
                        "|"+str.substring(kook+1,lv)+
                        "⭐|"+str.substring(lv+1));
            }
        }
    }

    //实现点击复制
    public void copyText(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        //只取kook号
        int endIndex = buttonText.indexOf('|');
        String kook = null;
        if(endIndex!=-1){
            kook = buttonText.substring(5,endIndex);
        }

        // 复制按钮文本到剪贴板
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("kook_text",kook);
        clipboardManager.setPrimaryClip(clipData);

        // 可选：显示复制成功的提示
        Snackbar.make(view, "复制成功", Snackbar.LENGTH_SHORT).show();
    }

}
