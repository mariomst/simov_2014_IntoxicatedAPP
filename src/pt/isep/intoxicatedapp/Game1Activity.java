

package pt.isep.intoxicatedapp;

import java.util.Timer;
import java.util.TimerTask;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Game1Activity extends Activity implements SensorEventListener {

	/*private SensorManager sm = null;*/
	private TextView tv;
	private long lastUpdate = 0;
	private SensorManagerSimulator sm = null;
	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 5000;
    //private ConnectionToSensorSimulator conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_game1);
		//setContentView(new Game1(this,100,100));
		setContentView(new MyView(this));
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Obter o TextView do ficheiro layout
		//tv = (TextView) findViewById(R.id.tv_game1_output);

		// Obter o serviço dos sensores
		//sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		/*sm = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
		conn = new ConnectionToSensorSimulator();
	    conn.execute();*/
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		sm = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
		sm.connectSimulator();
		
		//Timer para atualizar a bola
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				
			}
		}, 0, UPDATE_INTERVAL);
		
		//sm.connectSimulator();
		
		//create Sensor
		//Sensor Accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		//register this class as a listener for the orientation and accelerometer sensors
		//sm.registerListener((SensorEventListener) this, Accel, sm.SENSOR_DELAY_FASTEST);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//sm.unregisterListener(this);
		//sm.disconnectSimulator();
		if(timer!=null){
			timer.cancel();
		}
		
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

	//public void onToggleClicked_gyro(View view) {
		/*boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			Log.i(getString(R.string.app_name), "Gyroscope test started.");
			sm.registerListener(this,
					sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
					SensorManagerSimulator.SENSOR_DELAY_GAME);
			sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_FASTEST);
		} else {
			Log.i(getString(R.string.app_name), "Gyroscope test stoped.");
			sm.unregisterListener(this);
		}*/
	//}

	public void onToggleClicked_accel(View view) {
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			Log.i(getString(R.string.app_name), "Accelerometer test started.");
			/*sm.registerListener(this,
					sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManagerSimulator.SENSOR_DELAY_NORMAL);*/
			tv.setText("comecou");
			//sm.connectSimulator();
			
			
			
			


			/*if (!sm.isConnectedSimulator()) {
				Log.i(getString(R.string.app_name), "Not connected yet -> Connect");
				sm.connectSimulator();
			}

			if (sm.isConnectedSimulator()) {
				tv.setText("lalalala");
			}*/

			
			
			
			
			
			
			
		} else {
			Log.i(getString(R.string.app_name), "Accelerometer test stoped.");
			//sm.unregisterListener(this);
			//sm.disconnectSimulator();
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			int sensor = event.type;
			float[] values = event.values;
			
			switch (sensor) {
	        case Sensor.TYPE_ACCELEROMETER:
	        	long curTime = System.currentTimeMillis();			
				
				if((curTime - lastUpdate) > 100){				
					lastUpdate = curTime;
		        	float x = event.values[0];
					float y = event.values[1];
					float z = event.values[2];
					/*tv.setText("Orientation X: "
							+ Float.toString(x) + "\n"
							+ "Orientation Y: "
							+ Float.toString(y) + "\n"
							+ "Orientation Z: "
							+ Float.toString(z));*/
				}
	        	break;
	        default:
	            break;
			}
		}
		//Sensor sensor = event.type;

		//if (sensor.TYPE_ACCELEROMETER == 1) {
			/*float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			
			long curTime = System.currentTimeMillis();			
			
			if((curTime - lastUpdate) > 100){				
				lastUpdate = curTime;
				
				//escrever no ecrã os valores
				tv.setText("Orientation X: "
						+ Float.toString(x) + "\n"
						+ "Orientation Y: "
						+ Float.toString(y) + "\n"
						+ "Orientation Z: "
						+ Float.toString(z));
			}*/

		/*} else if (sensor == 4) {
			// escrever no ecrã os valores
			tv.setText("Orientation X (Roll) :"
					+ Float.toString(event.values[2]) + "\n"
					+ "Orientation Y (Pitch) :"
					+ Float.toString(event.values[1]) + "\n"
					+ "Orientation Z (Yaw) :" + Float.toString(event.values[0]));
		} else {
			Log.i(getString(R.string.app_name), "Sensor Error.");
		}*/
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	
	/*@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
	}*/
	
	@Override
    protected void onResume() {
	    super.onResume();
	    // SENSOR SIMULATOR
	    sm.registerListener(this,
	        sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	        SensorManagerSimulator.SENSOR_DELAY_NORMAL);
	}
	
	@Override
    protected void onPause() {
	    super.onPause();
	    sm.unregisterListener(this);
    }
	
	/*private class ConnectionToSensorSimulator extends
    	AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
		    Log.d("SENSOR", "CONNECTING TO SENSOR SIMULATOR");
		    sm.connectSimulator();
		    return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
		    super.onPostExecute(result);
		    if (result) {
		    Log.d("SENSOR", "CONNECTED TO SENSOR SIMULATOR");
		    } else {
		    Log.d("SENSOR", "NOT CONNECTED TO SENSOR SIMULATOR");
		    }
		}

	}*/
	public class MyView extends View
    {
        public MyView(Context context) 
        {
             super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) 
        {
           super.onDraw(canvas);
           int x = getWidth();
           int y = getHeight();
           int radius = 20;
           Paint paint = new Paint();
           paint.setAntiAlias(true);
           paint.setStyle(Paint.Style.FILL_AND_STROKE);
           paint.setColor(Color.RED);
           canvas.drawPaint(paint);
           //paint.setStyle(Paint.Style.STROKE);
           paint.setColor(Color.YELLOW);
           canvas.drawCircle(x / 2, y / 2, x-160, paint);
           paint.setColor(Color.GREEN);
           canvas.drawCircle(x / 2, y / 2, x-250, paint);
           // Use Color.parseColor to define HTML colors
           paint.setColor(Color.parseColor("#CD5C5C"));
           canvas.drawCircle(x / 2, y / 2, radius, paint);
       }
    }
}

