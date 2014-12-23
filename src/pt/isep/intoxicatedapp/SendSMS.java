package pt.isep.intoxicatedapp;

import java.util.ArrayList;
import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SendSMS {
	
	public void sendSMS(Context context){		
		/** Obter contactos da base de dados **/
		DBAdapter adapter = new DBAdapter(context);
		ArrayList<Contact> contacts = adapter.getContacts();
		
		/** Obter dimensão do array de contactos **/
		int contactsSize = contacts.size();
		
		/** Obter coordenadas GPS **/
		String latlon = getCurrLocation(context);
		
		if(contactsSize == 0){
			String errorMsg = context.getString(R.string.error_message1);
			Log.i(context.getString(R.string.app_name), errorMsg);
			Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
		} else {
			/** Iniciar ciclo para percorrer todos os contactos definidos **/
			for(int i = 0; i < contactsSize; i++){			
				/** Obter nome do contacto **/
				String contactName = contacts.get(i).getName();
				/** Obter número do contacto **/
				String phoneNum = contacts.get(i).getPhoneNumber();
				/** Mensgem de texto a ser enviada **/
				String inicio = context.getString(R.string.sms_txt1);
				String corpo = context.getString(R.string.sms_txt2);
			
				/** Construir mensagem **/
				String message = inicio + " " + contactName + ". " + corpo + " http://maps.google.com/?q=" + latlon;
			
				try{
					Log.i(context.getString(R.string.app_name), "Sending SMS to " + contactName + " with the phone number " + phoneNum);
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNum, null, message, null, null);
					String infMsg = context.getString(R.string.sms_info1) + " " + (i+1) + " " + context.getString(R.string.sms_info2) + " " + contactsSize + " " + context.getString(R.string.sms_info3);
					Toast.makeText(context, infMsg, Toast.LENGTH_SHORT).show();
				} catch (Exception err) {
					String errorMsg = context.getString(R.string.error_message2);
					Log.i(context.getString(R.string.app_name), errorMsg);
					Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
	
	/** Método para obter coordenadas GPS **/
	private String getCurrLocation(Context context){
		Log.i(context.getString(R.string.app_name), "SMS: Adquiring GPS coordinates.");
		
		String latlon; //Latitude e longitude
		
		GPS gps = new GPS(context);
		
		if(gps.canGetLocation()){
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			String strLat = String.valueOf(latitude);
			String strLon = String.valueOf(longitude);
			
			latlon = strLat + "," + strLon;
		} else {
			latlon = "0,0";
		}
		
		return latlon;
	}
}
