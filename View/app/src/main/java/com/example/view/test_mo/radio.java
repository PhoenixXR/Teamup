package com.example.view.test_mo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.view.R;

/*
测试选择按钮的
 */
public class radio extends AppCompatActivity {
    private TextView mTvShow;
    private RadioGroup mRg;
    public RadioButton m1, m2,m3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        mTvShow = (TextView)findViewById(R.id.textView);
        mRg = (RadioGroup) findViewById(R.id.RadioGroup);
        m1 = (RadioButton) findViewById(R.id.radioButton01);
        m2 = (RadioButton) findViewById(R.id.radioButton02);
        m3 = (RadioButton) findViewById(R.id.radioButton03);
        mRg.setOnCheckedChangeListener(listenner);

    }

    private RadioGroup.OnCheckedChangeListener listenner = new RadioGroup.OnCheckedChangeListener() {

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==m1.getId())mTvShow.setText(m1.getText());
            if(checkedId==m2.getId())mTvShow.setText(m2.getText());
            if(checkedId==m3.getId())mTvShow.setText(m3.getText());

        }
    };
}