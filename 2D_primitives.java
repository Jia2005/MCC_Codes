package com.example.shapes;  
import android.content.Context;  
import android.graphics.Canvas;  
import android.graphics.Color;  
import android.graphics.Paint;  
import android.graphics.Path;  
import android.util.AttributeSet;  
import android.view.View;  
  
public class CustomView extends View {  
  
    private Paint drawPaint;  
    private Canvas canvas;  
  
    public CustomView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        setupPaint();  
    }  
  
    private void setupPaint() {  
        drawPaint = new Paint();  
    }  
  
    @Override  
    protected void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        this.canvas = canvas;  
  
        drawPaint.setColor(Color.BLUE);  
        canvas.drawRect(100, 100, 300, 300, drawPaint);  
  
        drawPaint.setColor(Color.GREEN);  
        canvas.drawCircle(200, 500, 100, drawPaint);  
  
        drawPaint.setColor(Color.MAGENTA);  
        Path path = new Path();  
path.moveTo(200, 700);  
path.lineTo(300, 900);  
path.lineTo(100, 900);  
path.close();  
canvas.drawPath(path, drawPaint);  
}  
} 
