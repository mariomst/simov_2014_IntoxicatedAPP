package pt.isep.intoxicatedapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.EventLog.Event;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class SingleTouchEventView extends View {
  private Paint paint = new Paint();
  private Paint paint2 = new Paint();
  private Path path = new Path();
  //private Path path2 = new Path();
  float x = 0;
  float y = 0;

  public SingleTouchEventView(Context context, AttributeSet attrs) {
    super(context, attrs);

    paint.setAntiAlias(true);
    paint.setStrokeWidth(6f);
    paint.setColor(Color.BLACK);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeJoin(Paint.Join.ROUND);
    
    paint2.setAntiAlias(true);
    paint2.setStrokeWidth(6f);
    paint2.setColor(Color.MAGENTA);
    paint2.setStyle(Paint.Style.STROKE);
    paint2.setStrokeJoin(Paint.Join.ROUND);
  }

  
  @Override
  protected void onDraw(Canvas canvas) {
	drawLine();
    canvas.drawPath(path, paint);
    canvas.drawCircle(x, y, 25, paint2);
    
  }

  void drawLine(){
	  //desenhar DUAS LINHASSSSSS ! Dedo tem que passar no meio, se tocar count++
  }
  
  
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float eventX = event.getX();
    float eventY = event.getY();

    switch (event.getAction()) {
    case MotionEvent.ACTION_DOWN:
      path.moveTo(eventX, eventY);
      return true;
    case MotionEvent.ACTION_MOVE:
      x = event.getX();
      y = event.getY();
      path.moveTo(x, y);
      //comparar valores

      break;
    case MotionEvent.ACTION_UP:
    //enviar count para activity
      break;
    default:
      return false;
    }

    // Schedules a repaint.
    invalidate();
    return true;
  }
} 