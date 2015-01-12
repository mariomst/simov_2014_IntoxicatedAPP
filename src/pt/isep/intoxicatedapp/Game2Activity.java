package pt.isep.intoxicatedapp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Debug;
import android.util.AttributeSet;
import android.util.Log;
import android.util.EventLog.Event;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Game2Activity extends Activity {

	/*public Game2Activity(){
		
	}*/	

	
	private Paint paint = new Paint();
	private Paint paint2 = new Paint();
	private Paint paint3 = new Paint();
	private Paint paint4 = new Paint();
	private Path path = new Path();
	//private Path path2 = new Path();
	float x = -50;
	float y = 0;
	int x1, y1, x2, y2;
	int z1,z2;
	int radius1, radius2, radius3, radius4;
	
	boolean mode1;
	boolean rdm = true;
	int i1 = 0;
	  
	private static final long UPDATE_INTERVAL = 20000;
	private Timer timer = new Timer();
	float height, width;
	float count = 0; //erradas
	float right = 0;
	float right1 = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));

	}
	
	protected void onFinish(){

		
		System.out.println("certas "+right);
		System.out.println("certas1 "+right1);
		System.out.println("falhas "+count);
		Intent i = new Intent(Game2Activity.this, Game2Score.class);
		//i.putExtra("falhas",count);
		i.putExtra("certas",right);
		i.putExtra("certas1",right1);
		
		startActivity(i);
		Log.i(getString(R.string.app_name),"Game2Score created.");

		finish();
		
	}
	
	public Game2Activity() {
	    paint.setAntiAlias(true);
	    paint.setStrokeWidth(6f);
	    paint.setColor(Color.BLACK);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setStrokeJoin(Paint.Join.ROUND);
	    
	    paint2.setAntiAlias(true);
	    paint2.setStrokeWidth(6f);
	    paint2.setColor(Color.RED);
	    paint2.setStyle(Paint.Style.STROKE);
	    paint2.setStrokeJoin(Paint.Join.ROUND);
	    
	    paint3.setAntiAlias(true);
	    paint3.setStrokeWidth(6f);
	    paint3.setColor(Color.GREEN);
	    paint3.setStyle(Paint.Style.STROKE);
	    paint3.setStrokeJoin(Paint.Join.ROUND);
	    
	    paint4.setAntiAlias(true);
	    paint4.setStrokeWidth(6f);
	    paint4.setColor(Color.RED);
	    paint4.setStyle(Paint.Style.STROKE);
	    paint4.setStrokeJoin(Paint.Join.ROUND);
	   
	  }
	  
	  Paint curves = new Paint() {
		    {
		        setStyle(Paint.Style.STROKE);
		        setStrokeCap(Paint.Cap.ROUND);
		        setStrokeWidth(3.0f);
		        setAntiAlias(true);
		    }
		};
		
		@Override
		protected void onDestroy() {
			super.onDestroy();
		}
		
		@Override
	    protected void onResume() {
		    super.onResume();
		}
		
		@Override
	    protected void onPause() {
		    super.onPause();
	    }
		
		public class MyView extends View 
	    {
		
	        public MyView(Context context) 
	        {
	             super(context);

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
			      
			      if(mode1 == true){
			    	int transx = (int)x - x1;
			    	int transy = (int)y - y1; 
			    	
			    	 if((Math.sqrt(transx * transx + transy * transy) <= radius1 && 
				    	Math.sqrt(transx * transx + transy * transy) >= radius2) && mode1 == true){
				    	  
			    		  right++;
				    	   
				      }else
				      {
				    	  count++;
				      }
			    	
			      }else{
			    	  int transx = (int)x - x1;
			    	  int transy = (int)y - y1; 
			    	  int transx1 = (int)x - x2;
			    	  int transy1 = (int)y - y2; 
			    	  
			    	  if((Math.sqrt(transx * transx + transy * transy) <= radius1 && 
				    	 Math.sqrt(transx * transx + transy * transy) >= radius2 || (Math.sqrt(transx1 * transx1 + transy1 * transy1) <= radius3 && 
						 Math.sqrt(transx * transx + transy * transy) >= radius4))){
			    		 
			    		  right1++;
			    		  
				      }else
				      {
				    	  count++;
				      }
			      }
			      
			      break;
			    case MotionEvent.ACTION_UP:
			    //enviar count para activity
			    	System.out.println("count" + count);
			    	onFinish();
			    	
			      break;
			    default:
			      return false;
			    }

			    // Schedules a repaint.
			    invalidate();
			    return true;
			  }
			
			
			@Override
			protected void onDraw(Canvas canvas) {
				 
					canvas.drawPath(path, paint);
					randomNumber();
				    drawCurves(canvas);
				    canvas.drawCircle(x, y, 25, paint2);
				  }
			
			void randomNumber(){
				
				if ( rdm == true){
					Random r = new Random();
					i1 = r.nextInt(3 - 1) + 1;
					
					rdm = false;
				}
			}
			
			
			void drawCurves(Canvas canvas){

				int width = canvas.getWidth(); //320 x
				int height = canvas.getHeight(); //480 y
				
				
				/**
				}**/
				
				if( i1 == 1 ){
					mode1 = true;
					
					x1 = width - width; //0
					y1 = height/2; //280
					
					radius1 = height/2 + 20;
					radius2 = radius1 - 50;
					
					Paint paint_test1 = new Paint(),
						  paint_test2 = new Paint();
					paint_test1.setStyle(Paint.Style.FILL);
					paint_test1.setColor(Color.BLUE);
					paint_test1.setAntiAlias(true);
					paint_test2.setStyle(Paint.Style.FILL);
					paint_test2.setColor(Color.WHITE);
					paint_test2.setAntiAlias(true);
					
					canvas.drawCircle(x1, y1, radius1, paint_test1);
					canvas.drawCircle(x1, y1, radius2, paint_test2);
					
					
				}else{
					mode1 = false;
					//320 480
					x1 = width - 50;   //240
					y1 = height - 50; //300
					x2 = width/7 - 20; //20 --
					y2 = height/5; //50
		
					radius1 = height/2 + 20; //220;
					radius2 = radius1 - 50;
					radius3 = 200;
					radius4 = radius3 - 50;
					
					Paint paint_test1 = new Paint(),
						  paint_test2 = new Paint();
					paint_test1.setStyle(Paint.Style.FILL);
					paint_test1.setColor(Color.BLUE);
					paint_test1.setAntiAlias(true);
					paint_test2.setStyle(Paint.Style.FILL);
					paint_test2.setColor(Color.WHITE);
					paint_test2.setAntiAlias(true);
					
					canvas.drawCircle(x1, y1, radius1, paint_test1);
					canvas.drawCircle(x1, y1, radius2, paint_test2); //mais pequeno
					canvas.drawCircle(x2, y2, radius3, paint_test1);
					canvas.drawCircle(x2, y2, radius4, paint_test2); //mais pequeno
					
				}
			}        
			
	    }
		
		
		
	}
	
	
   
