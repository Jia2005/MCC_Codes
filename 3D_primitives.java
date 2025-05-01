//3D_primitives
package com.example.lab2; 
 
import android.graphics.Canvas; 
import android.graphics.Color; 
import android.graphics.Paint; 
import android.os.Bundle; 
import android.view.View; 
import androidx.appcompat.app.AppCompatActivity; 
 
public class MainActivity extends AppCompatActivity { 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(new CustomView(this)); 
    } 
 
    // Custom View class to handle the drawing 
    private class CustomView extends View { 
        public CustomView(MainActivity context) { 
            super(context); 
        } 
 
        @Override 
        protected void onDraw(Canvas canvas) { 
            super.onDraw(canvas); 
            canvas.drawColor(Color.WHITE); 
 
            Paint paint = new Paint(); 
            paint.setColor(Color.BLACK); 
            paint.setStrokeWidth(5); 
 
 
 
            // Cube 
            canvas.drawLine(100, 300, 400, 300, paint); // Top edge 
            canvas.drawLine(100, 700, 400, 700, paint); // Bottom edge 
            canvas.drawLine(100, 300, 100, 700, paint); // Left edge 
            canvas.drawLine(400, 300, 400, 700, paint); // Right edge 
             
            canvas.drawLine(150, 350, 450, 350, paint); // Top edge of back face 
            canvas.drawLine(150, 750, 450, 750, paint); // Bottom edge of back face 
            canvas.drawLine(150, 350, 150, 750, paint); // Left edge of back face 
            canvas.drawLine(450, 350, 450, 750, paint); // Right edge of back face 
             
            canvas.drawLine(100, 300, 150, 350, paint); // Connect top-left 
            canvas.drawLine(100, 700, 150, 750, paint); // Connect bottom-left 
            canvas.drawLine(400, 300, 450, 350, paint); // Connect top-right 
            canvas.drawLine(400, 700, 450, 750, paint); // Connect bottom-right 
             
            // Cone 
            Paint conePaint = new Paint(); 
            conePaint.setColor(Color.BLACK); 
            conePaint.setStyle(Paint.Style.FILL); 
            canvas.drawOval(600, 570, 1000, 750, paint); // Base of the cone 
            canvas.drawLine(600, 650, 800, 200, paint); // Left side 
            canvas.drawLine(800, 200, 1000, 650, paint); // Right side 
            canvas.drawLine(1000, 650, 600, 650, paint); // Bottom side 
 
            // Cylinder 
            canvas.drawOval(100, 850, 500, 950, paint); // Top ellipse of the cylinder 
            canvas.drawOval(100, 1100, 500, 1200, paint); 
            canvas.drawLine(100, 900, 100, 1150, paint); // Left vertical line 
            canvas.drawLine(500, 900, 500, 1150, paint); // Right vertical line 
        } 
    } 
}
