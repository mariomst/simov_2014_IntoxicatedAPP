/*
 * Referências
 * Accelerometer - http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125
 * Gyroscope - http://www.41post.com/3745/programming/android-acessing-the-gyroscope-sensor-for-simple-applications
 */

package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TestsActivity extends Activity implements SensorEventListener {

	private SensorManager sm = null;
	private TextView tv_out;
	private Button smsBTN, gpsBTN;
	private long lastUpdate = 0;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tests);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		/** Obter o TextView do ficheiro layout **/
		tv_out = (TextView) findViewById(R.id.textView_output);

		/** Obter o serviço dos sensores **/
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		/** Associar botão button_testSMS **/
		smsBTN = (Button) findViewById(R.id.button_testSMS);
		
		smsBTN.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				sendSMSMessage_test();			
			}
		});			
		
		/** Associar botão button_testGPS **/
		gpsBTN = (Button) findViewById(R.id.button_testGPS);
		
		gpsBTN.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				getCurrentLocation_test();				
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
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

	/** Iniciar/Parar testes do Gyroscope **/
	public void onToggleClicked_gyro(View view) {
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			Log.i(getString(R.string.app_name), "Gyroscope test started.");
			sm.registerListener(this,
					sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
					SensorManager.SENSOR_DELAY_GAME);
		} else {
			Log.i(getString(R.string.app_name), "Gyroscope test stoped.");
			sm.unregisterListener(this);
		}
	}

	/** Iniciar/Parar testes do Accelerometer **/
	public void onToggleClicked_accel(View view) {
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			Log.i(getString(R.string.app_name), "Accelerometer test started.");
			sm.registerListener(this,
					sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL);
		} else {
			Log.i(getString(R.string.app_name), "Accelerometer test stoped.");
			sm.unregisterListener(this);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;

		if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			
			long curTime = System.currentTimeMillis();			
			
			if((curTime - lastUpdate) > 100){				
				lastUpdate = curTime;
				
				//escrever no ecrã os valores
				tv_out.setText("Orientation X: "
						+ Float.toString(x) + "\n"
						+ "Orientation Y: "
						+ Float.toString(y) + "\n"
						+ "Orientation Z: "
						+ Float.toString(z));
			}

		} else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			// escrever no ecrã os valores
			tv_out.setText("Orientation X (Roll) :"
					+ Float.toString(event.values[2]) + "\n"
					+ "Orientation Y (Pitch) :"
					+ Float.toString(event.values[1]) + "\n"
					+ "Orientation Z (Yaw) :" + Float.toString(event.values[0]));
		} else {
			Log.i(getString(R.string.app_name), "Sensor Error.");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	
	/** Método para enviar SMS **/
	protected void sendSMSMessage_test(){
		Log.i(getString(R.string.app_name), "SMS test started.");
		SendSMS sms = new SendSMS();
		sms.sendSMS(getApplicationContext(), "41.1777702","-8.6084523");
	}
	
	/** Método para obter coordenadas GPS **/
	protected void getCurrentLocation_test(){
		Log.i(getString(R.string.app_name), "GPS test started.");
		
		GPS gps = new GPS(this);
		
		if(gps.canGetLocation()){
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			String strLat = String.valueOf(latitude);
			String strLon = String.valueOf(longitude);
			
			//escrever no ecrã os valores
			tv_out.setText("Latitude: " + strLat  + "\n" + "Longitude: " + strLon);		
		} else {
			String errMsg = getString(R.string.error_message3);
			tv_out.setText(errMsg);		
		}
	}
}
