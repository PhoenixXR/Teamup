package com.example.view.teamup;

import android.app.Dialog;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Handler;

/**
 * P：public
 * l:listview
 */
public class PlService {
    public static boolean Pub(String kook, String lv,String num) {
        //MyThread2 myThread2 = new MyThread2("http://192.168.0.115/Teamup_web_war_exploded/Pub",kook,lv,num);
        MyThread2 myThread2 = new MyThread2("http://121.40.96.127:8080/Teamup_web_war_exploded/Pub",kook,lv,num);
        try
        {
            myThread2.start();
            myThread2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return myThread2.getResult();
    }

    public static Stack listv() throws MalformedURLException {
        //URL url = new URL("http://192.168.0.115/Teamup_web_war_exploded/listv");
        URL url = new URL("http://121.40.96.127:8080/Teamup_web_war_exploded/listv");
        Stack stack = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("type", "text/html");
            conn.setRequestProperty("Charset", "utf-8");
            //传递请求参数
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoOutput(true);// 允许输出
            String requestBody = "1";
            try (OutputStream outputStream = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            conn.connect();
            stack = new Stack();
            System.out.println(conn.getResponseCode());

            //用BufferedReader接受server数据
            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                //readline是读到/n，空格，为止
                while ((line = reader.readLine()) != null) {
                    //stringBuilder.append(line);
                    //System.out.println(line);
                    stack.push(line);
                }
                // 获取响应内容,连起来
                //data = stringBuilder.toString();
                reader.close();
            } else {
                Log.e("Response", "Error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回存储了info的栈stack
        return stack;
    }
}

class MyThread2 extends Thread
{
    private String path;
    private String kook;
    private String lv;
    private String num;
    private boolean result = false;

    public MyThread2(String path,String kook,String lv,String num)
    {
        this.path = path;
        this.kook = kook;
        this.lv = lv;
        this.num = num;
    }
    @Override
    public void run()
    {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(8000);//设置连接超时时间
            httpURLConnection.setReadTimeout(8000);//设置读取超时时间
            httpURLConnection.setRequestMethod("POST");//设置请求方法,post

            String data = "kook=" + URLEncoder.encode(kook, "utf-8") +
                    "&lv=" + URLEncoder.encode(lv, "utf-8") + "&num=" +
                    URLEncoder.encode(num, "utf-8") ;//设置数据
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置响应类型
            httpURLConnection.setRequestProperty("Content-Length", data.length() + "");//设置内容长度
            httpURLConnection.setDoOutput(true);//允许输出
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data.getBytes("utf-8"));//写入数据
            result = (httpURLConnection.getResponseCode() == 200);
            System.out.println(httpURLConnection.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getResult()
    {
        return result;
    }
}