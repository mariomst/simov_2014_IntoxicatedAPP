/*
 * Referências
 * Accelerometer - http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125
 * Gyroscope - http://www.41post.com/3745/programming/android-acessing-the-gyroscope-sensor-for-simple-applications
 */

package pt.isep.intoxicatedapp;

import java.util.ArrayList;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TestsActivity extends Activity implements SensorEventListener {

	private SensorManager sm = null;
	private TextView tv_out;
	private Button smsBTN;
	private long lastUpdate = 0;	
	private int contactsSize = 0;

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
		/** Obter contactos da base de dados **/
		DBAdapter adapter = new DBAdapter(getApplicationContext());
		ArrayList<Contact> contacts = adapter.getContacts();
		
		/** Obter dimensão do Array de contactos **/
		contactsSize = contacts.size();		
		
		if(contactsSize == 0){
			String errorMsg = getString(R.string.error_message1);
			Log.i(getString(R.string.app_name), errorMsg);
			Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
		} else {
			/** Obter nome do primeiro contacto **/
			String contactName = contacts.get(0).getName();
			/** Obter número do primeiro contacto **/
			String phoneNum = contacts.get(0).getPhoneNumber();			
			/** Mensagem a ser enviada **/
			String textMsg = "Hello " + contactName + ", this is a SMS test for the IntoxicatedApp. Please ignore this message :)";
			
			try{
				Log.i(getString(R.string.app_name), "Sending SMS to " + contactName + " with the phone number " + phoneNum);
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNum, null, textMsg, null, null);
			} catch (Exception err) {
				String errorMsg = getString(R.string.error_message2);
				Log.i(getString(R.string.app_name), errorMsg);
				Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
			}
		}	
	}
}
