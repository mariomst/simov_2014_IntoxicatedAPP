package pt.isep.intoxicatedapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class Game2Activity extends Activity{
	
    private static List<Integer> listaColisoes = new ArrayList<Integer>();
    private static List<Integer> listaNaoColisoes = new ArrayList<Integer>();
    private float width, height;
    private float x=0, y=0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float tempX, tempY;
		boolean check1=false, check2=false;
		tempX=event.getX();
		tempY=event.getY()-75;
		
		if(x>tempX){
			if((x-tempX) < ((float)(width*0.1))){
				check1=true;
			}
		}else if(tempX>x){
			if((tempX-x) < ((float)(width*0.1))){
				check1=true;
			}
		}
		
		if(y>tempY){
			if((y-tempY) < ((float)(width*0.1))){
				check2=true;
			}
		}else if(tempY>y){
			if((tempY-y) < ((float)(width*0.1))){
				check2=true;
			}
		}
		
		if(check1 && check2){
			x = event.getX()/*.getAxisValue(0).getX()*/;
			y = event.getY()-75/*getAxisValue(1).getY()*/;
		}

		return true;
	}
	
	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.i(getString(R.string.app_name), "Option Back was called.");
			finish();
			return true;
		default:
			Log.i(getString(R.string.app_name), "Option unknown.");
			return true;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();

	}
	
	@Override
    protected void onPause() {
	    super.onPause();
    }
	
	public class MyView extends View implements Runnable
    {
		private static final int INTERVAL = 100;
		private boolean running=true;
		private CornerPathEffect effect;
		private Paint mPaint;
        private Path mPath;
        private int mColor;
        boolean primeiraVez=true;
		
		public MyView(Context context) 
        {
             super(context);
             Thread t=new Thread(this);
             t.setPriority(Thread.MIN_PRIORITY);
             t.start();
        }

		@Override
		public void run() {
			while(running){
        		try{
        			Thread.sleep(INTERVAL);
        		}catch(InterruptedException e){
        			Log.e("Jogo", "GameLoop Finalizado!");
        		}
        		update();
        	}
		}
		
		private void update(){
			
			if(x > width*0.95 && y > height*0.95){
				
				release();
				
				float tamanho1 = listaColisoes.size();
				float total = listaColisoes.size() + listaNaoColisoes.size();
				float media = tamanho1/total;
				
				Log.i(getString(R.string.app_name),"tamanho1:"+tamanho1+" total:"+total);
				
				// Criar nova activity com o score do jogo.
		    	Intent i = new Intent(Game2Activity.this, Game2Score.class);
		    	i.putExtra("score",(float)media);
		    	startActivity(i);
		    	Log.i(getString(R.string.app_name),"Game2Score created.");
		    	finish();
			}
			
			postInvalidate();
		}
		
		public void draw(Canvas canvas){
			super.draw(canvas);
			if(primeiraVez){
				width=getWidth();
				height=getHeight();
				primeiraVez=false;
				
	            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	            mPaint.setStyle(Paint.Style.STROKE);
	            mPaint.setStrokeWidth(10);

	            mPath = makeFollowPath();

	            effect = new CornerPathEffect(10);

	            mColor = Color.BLACK;
	            
	            x=(float) (width*0.01);
	            y=(float) (height*0.01);
			}
			
			
			
            Path path2 = new Path();
            path2.addCircle(x, y, (float)(width*0.04), Path.Direction.CW);
            
            Region clip = new Region(0, 0, (int)width, (int)height);
            
            Region region1 = new Region();
            region1.setPath(mPath, clip);
            
            Region region2 = new Region();
            region2.setPath(path2, clip);
            
            if (!region1.quickReject(region2) && region1.op(region2, Region.Op.INTERSECT)) {
                // Colisao!
            	Log.i(getString(R.string.app_name), "COLISAO");
            	listaColisoes.add(1);
            }else{
            	listaNaoColisoes.add(0);
            }
            
            Log.i(getString(R.string.app_name), "x:"+x+" y:"+y);
			
			canvas.drawColor(Color.WHITE);

            RectF bounds = new RectF();
            mPath.computeBounds(bounds, false);

            mPaint.setPathEffect(effect);
            mPaint.setColor(mColor);
            canvas.drawPath(mPath, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawPath(path2, mPaint);
		}

        private Path makeFollowPath() {
            Path p = new Path();
            p.moveTo((float) (width*0.01), (float) (height*0.01));
            List<Float> listaX = new ArrayList<Float>();
            List<Float> listaY = new ArrayList<Float>();
            
            listaX.add((float) (width*0.1));
            listaY.add((float) (height*0.2));
            
            listaX.add((float) (width*0.3));
            listaY.add((float) (height*0.3));
            
            listaX.add((float) (width*0.5));
            listaY.add((float) (height*0.4));
            
            listaX.add((float) (width*0.6));
            listaY.add((float) (height*0.4));
            
            listaX.add((float) (width*0.7));
            listaY.add((float) (height*0.3));
            
            listaX.add((float) (width*0.7));
            listaY.add((float) (height*0.2));
            
            listaX.add((float) (width*0.6));
            listaY.add((float) (height*0.1));
            
            listaX.add((float) (width*0.4));
            listaY.add((float) (height*0.2));
            
            listaX.add((float) (width*0.3));
            listaY.add((float) (height*0.5));
            
            listaX.add((float) (width*0.4));
            listaY.add((float) (height*0.7));
            
            listaX.add((float) (width*0.6));
            listaY.add((float) (height*0.7));
            
            listaX.add((float) (width*0.6));
            listaY.add((float) (height*0.9));
            
            listaX.add((float) (width*0.5));
            listaY.add((float) (height*0.9));
            
            listaX.add((float) (width*0.4));
            listaY.add((float) (height*0.8));
            
            listaX.add((float) (width*0.5));
            listaY.add((float) (height*0.6));
            
            listaX.add((float) (width*0.7));
            listaY.add((float) (height*0.5));
            
            listaX.add((float) (width*0.8));
            listaY.add((float) (height*0.5));
            
            listaX.add((float) (width*0.9));
            listaY.add((float) (height*0.6));
            
            listaX.add((float) (width*0.8));
            listaY.add((float) (height*0.8));
            
            listaX.add((float) (width));
            listaY.add((float) (height));
            for (int j = 0; j < listaX.size(); j++) {
            	p.lineTo(listaX.get(j), listaY.get(j));
            }
            return p;
        }
		
		public void release(){
        	running=false;
        }
    
    }
}
