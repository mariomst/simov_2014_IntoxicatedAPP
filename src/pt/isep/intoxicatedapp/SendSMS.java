package pt.isep.intoxicatedapp;

import java.util.ArrayList;
import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SendSMS {

	public void sendSMS(Context context, String lat, String lon){
		/** Obter contactos da base de dados **/
		DBAdapter adapter = new DBAdapter(context);
		ArrayList<Contact> contacts = adapter.getContacts();
		
		/** Obter dimensão do array de contactos **/
		int contactsSize = contacts.size();
		
		if(contactsSize == 0){
			String errorMsg = context.getString(R.string.error_message1);
			Log.i(context.getString(R.string.app_name), errorMsg);
			Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
		} else {
			/** Obter nome do primeiro contacto **/
			String contactName = contacts.get(0).getName();
			/** Obter número do primeiro contacto **/
			String phoneNum = contacts.get(0).getPhoneNumber();
			/** Mensgem de texto a ser enviada **/
			String inicio = context.getString(R.string.sms_txt1);
			String corpo = context.getString(R.string.sms_txt2);
			
			String message = inicio + " " + contactName + ". " + corpo + " http://maps.google.com/?q=" + lat + "," + lon;
			
			try{
				Log.i(context.getString(R.string.app_name), "Sending SMS to " + contactName + " with the phone number " + phoneNum);
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNum, null, message, null, null);
			} catch (Exception err) {
				String errorMsg = context.getString(R.string.error_message2);
				Log.i(context.getString(R.string.app_name), errorMsg);
				Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
