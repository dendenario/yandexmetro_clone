//package com.example.drudnitskiy.yandexmetro;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.View;
//
//public class MetroMapView extends View {
//
//    private int height, width = 0;
//    private int padding =0;
//    private int fontSize = 0;
//    private int numeralSpacing = 0;
//    private Paint paint;
//    private boolean isInit;
//
//
//    public MetroMapView(Context context) {
//        super(context);
//    }
//
//    public MetroMapView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MetroMapView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    private void InitMetroMap()
//    {
//        height = getHeight();
//        width = getWidth();
//        padding = numeralSpacing + 50;
//        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,13,getResources().getDisplayMetrics());
//        paint = new Paint();
//        isInit = true;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        if(!isInit)
//        {
//            InitMetroMap();
//        }
//    }
//}
