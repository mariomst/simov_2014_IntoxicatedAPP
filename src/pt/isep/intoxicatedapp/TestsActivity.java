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

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tests);		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Obter o TextView do ficheiro layout
		tv = (TextView) findViewById(R.id.textView_output);
		
		//Obter o serviço dos sensores
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
				Log.i(getString(R.string.app_name),"Option Back was called.");
				finish();
				return true;
			default:
				Log.i(getString(R.string.app_name),"Option unknown.");
				return true;
			}
		}	
	
	public void onToggleClicked_gyro(View view){
		boolean on = ((ToggleButton) view).isChecked();
		
		if(on){
			Log.i(getString(R.string.app_name),"Gyroscope test started.");
			sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
					SensorManager.SENSOR_DELAY_FASTEST);
		} else {
			Log.i(getString(R.string.app_name),"Gyroscope test stoped.");
			sm.unregisterListener(this);
		}		
	}
	
	@Override  
    public void onSensorChanged(SensorEvent event)  
    {  
        //if sensor is unreliable, return void  
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)  
        {  
        	Log.i(getString(R.string.app_name),"Gyroscope Error: SENSOR_STATUS_UNRELIABLE");
            return;  
        }  
  
        //else it will output the Roll, Pitch and Yawn values  
        tv.setText("Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+  
                   "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+  
                   "Orientation Z (Yaw) :"+ Float.toString(event.values[0]));  
    }

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {				
	} 
}
