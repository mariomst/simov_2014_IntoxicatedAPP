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
import android.widget.TextView;
import android.widget.ToggleButton;

public class TestsActivity extends Activity implements SensorEventListener {

	private SensorManager sm = null;
	private TextView tv;
	private long lastUpdate = 0;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tests);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Obter o TextView do ficheiro layout
		tv = (TextView) findViewById(R.id.textView_output);

		// Obter o serviço dos sensores
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
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
				tv.setText("Orientation X: "
						+ Float.toString(x) + "\n"
						+ "Orientation Y: "
						+ Float.toString(y) + "\n"
						+ "Orientation Z: "
						+ Float.toString(z));
			}

		} else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			// escrever no ecrã os valores
			tv.setText("Orientation X (Roll) :"
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
}
