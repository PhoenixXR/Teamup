package com.example.view.test_mo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/*
移动小球/方块
 */

public class MyView extends View {

    Paint mPaint; //画笔，包含了画几何图形、文本等的样式和颜色信息
    public float currentX = 20;
    public float currentY = 20 ;
    public MyView( Context context) {
        super(context);
        mPaint = new Paint();
    }
    public MyView( Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

    }
    public void onDraw( Canvas canvas ) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL); //设置填充
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(currentX-25, currentY-25, currentX+25, currentY+25,mPaint);
    }
    //为该组件的触碰事件重写事件处理方法

    public boolean onTouchEvent(MotionEvent event ) {
        currentX = event.getX();
        currentY = event.getY();
        this.invalidate();
        return true;
    }


}
