package com.example.owner.myapp00;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

//自定义的水波纹

public class WaterView extends View {
    private Paint mPaintTop,mPaintBottom;
    private Path mTopPath,mBottomPath;
    //φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
    private float a;

    public WaterView(Context context) {
        super(context);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //创建画笔
        mPaintTop =  new Paint();
        mPaintTop.setColor(Color.BLUE);//设置画笔颜色
        mPaintTop.setAntiAlias(true);//设置抗锯齿

        mPaintBottom =  new Paint();
        mPaintBottom.setColor(Color.BLUE);//设置画笔颜色
        mPaintBottom.setAntiAlias(true);//设置抗锯齿
        mPaintBottom.setAlpha(60);//设置颜色透明度 因为是波浪效果 所以需要用到

        //设置路径
        mTopPath = new Path();
        mBottomPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTopPath.reset();
        mBottomPath.reset();


        //设置波浪开始的位置
        mTopPath.moveTo(getLeft(),getBottom());
        mBottomPath.moveTo(getLeft(),getBottom());

        //获取每个宽度值所占的宽度
        double mY = Math.PI * 2 / getWidth();

        a-=0.1f;

        //路劲移动的坐标
        for (float x = 0;x<= getWidth();x+=20){
            float y = (float) (10*Math.cos(mY*x+a)+10);
            mTopPath.lineTo(x,y);
            mBottomPath.lineTo(x,(float) (10*Math.sin(mY*x+a)));
            listener.animation(y);
        }

        //路径终止的位置
        mTopPath.lineTo(getRight(),getBottom());
        mBottomPath.lineTo(getRight(),getBottom());

        //
        canvas.drawPath(mTopPath,mPaintTop);
        canvas.drawPath(mBottomPath,mPaintBottom);
        //刷新
        postInvalidateDelayed(20);

    }
    /*

    接口回调获取
     */
    private AnimationListener listener;

    //传递接口
    public void animation(AnimationListener listener){
        this.listener=listener;
    }

    public interface AnimationListener{
        void animation(float y);
    }
}
