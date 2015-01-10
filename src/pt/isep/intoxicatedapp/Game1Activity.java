package pt.isep.intoxicatedapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

public class Game1Activity extends Activity implements SensorEventListener {

	private SensorManager sm = null;
	private long lastUpdate = 0;
	private static final long UPDATE_INTERVAL = 20000;
	float x=0, y=0 ,z=0;
	private static List<Float> listaX = new ArrayList<Float>();
	private static List<Float> listaY = new ArrayList<Float>();
	
	private Timer timer = new Timer();
	float height, width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
		
		// Obter o servico dos sensores
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        
		//Timer para acabar jogo
		timer.schedule(new TimerTask(){
			public void run(){
				
				//Media das posicoes da bola
				float tamanho = listaX.size();
				float total=0;
				float hipotenusa;

				for(int i=0; i<tamanho; i++){
					hipotenusa = (float) Math.hypot(listaY.get(i)-(height/2), listaX.get(i)-(width/2));
					total=total+hipotenusa;
				}

				float media = total/tamanho;
				Log.i("MEDIA", String.valueOf(media));
				
				// Criar nova activity com o score do jogo.
		    	Intent i = new Intent(Game1Activity.this, Game1Score.class);
		    	i.putExtra("score",media);
		    	i.putExtra("circuloVerde",(float)((width/2)*0.40));
		    	i.putExtra("circuloLaranja",(float)((width/2)*0.90));
		    	startActivity(i);
		    	Log.i(getString(R.string.app_name),"Game1Score created.");
		    	finish();
			}
		}, UPDATE_INTERVAL);
	}

	@Override
	protected void onDestroy() {
		sm.unregisterListener(this);
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

	public void onToggleClicked_accel(View view) {
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			Log.i(getString(R.string.app_name), "Accelerometer started.");
			sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		} else {
			Log.i(getString(R.string.app_name), "Accelerometer stoped.");
			sm.unregisterListener(this);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;

		if (sensor.TYPE_ACCELEROMETER == 1) {
			x = 0-(event.values[0]);
			y = event.values[1];
			z = event.values[2];
			
			long curTime = System.currentTimeMillis();			
			
			if((curTime - lastUpdate) > 100){				
				lastUpdate = curTime;
			}
		} else {
			Log.i(getString(R.string.app_name), "Sensor Error.");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	@Override
    protected void onPause() {
	    super.onPause();
	    sm.unregisterListener(this);
    }
	
	public class MyView extends View implements Runnable
    {
		private static final int INTERVAL = 100;
		private boolean running=true;
		private float yy=0;
		private float xx=0;
		boolean primeiraVez = true;
		
        public MyView(Context context) 
        {
             super(context);
             Thread t=new Thread(this);
             t.setPriority(Thread.MIN_PRIORITY);
             t.start();
        }

        @Override
        public void run(){
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
        	//Verificação para a bola não sair do ecrã
        	if((xx=xx+(3*x)) < width && (xx+(3*x)) > 0){
	        	xx=xx+(3*x);
        	}else if((xx=xx+(3*x)) > width){
        		xx = xx + (width - xx -20);
        	}else{
        		xx = 20;
        	}
        	
        	//Verificação para a bola não sair do ecrã
        	if((yy+(3*y)) < height && (yy+(3*y)) > 0){
        		yy=yy+(3*y);
        	}else if((yy+(3*y)) > height){
        		yy = yy + (height - yy -20);
        	}else{
        		yy = 20;
        	}
        	
        	//Adicionar coordenadas da bola à lista
        	listaX.add(xx);
        	listaY.add(yy);
        	
        	//Actualizar ecrã
        	postInvalidate();
        }
        
        public void draw(Canvas canvas){
        	super.draw(canvas);
        	
        	//Se for a primeira vez que estiver a executar, coloca a bola no centro de ecrã
        	if(primeiraVez){
         		yy=getHeight()/2;
         		xx=getWidth()/2;
         		
         		height=getHeight();
         		width=getWidth();
         		
         		primeiraVez=false;
         	}
        	
        	Paint paint = new Paint();
        	paint.setAntiAlias(true);
        	paint.setStyle(Paint.Style.FILL_AND_STROKE);
        	
        	//Fundo vermelho
        	paint.setColor(Color.RED);
        	canvas.drawPaint(paint);
        	
        	//Circulo amarelo
        	paint.setColor(Color.YELLOW);
        	canvas.drawCircle((getWidth() / 2), (getHeight() / 2), (float) ((getWidth()/2)*0.90), paint);
        	
        	//Circulo verde
        	paint.setColor(Color.GREEN);
        	canvas.drawCircle((getWidth() / 2), (getHeight() / 2), (float) ((getWidth()/2)*0.40), paint);
        	
        	//Bola
        	paint.setColor(Color.parseColor("#CD5C5C"));
           	canvas.drawCircle(xx, yy, 20, paint);
           	
           	Log.i(getString(R.string.app_name), "x:"+xx+" y:"+yy);
           	Log.i(getString(R.string.app_name), "Ciculo amarelo:"+(float) ((getWidth()/2)*0.90)+" Circulo verde:"+(float) ((getWidth()/2)*0.40));
        }
        
        public void release(){
        	running=false;
        }
    }
}