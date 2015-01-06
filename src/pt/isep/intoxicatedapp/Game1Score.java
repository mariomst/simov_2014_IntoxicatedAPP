package pt.isep.intoxicatedapp;

import pt.isep.intoxicatedapp.Game1Activity.MyView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Game1Score extends Activity{
	
	Button game1back;
	Button game1sms;
	Button game1call;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		game1back = (Button) findViewById(R.id.button_game1_score);
		game1sms = (Button) findViewById(R.id.button_game1_sms);
		game1call = (Button) findViewById(R.id.button_game1_call);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    float value = extras.getFloat("score");
		    float limite1 = extras.getFloat("circuloVerde");
		    float limite2 = extras.getFloat("circuloLaranja");
		    TextView tv = (TextView) findViewById(R.id.tv_game1_score);
		    if (value<=limite1){
			    tv.setText(getString(R.string.g1score_msg1));	/** podes escrever as mensagens no strings.xml so não pode ter o ' adiciona a versao pt **/
			    game1sms.setVisibility(4);
			    game1call.setVisibility(4);
			    //tv.setTextAlignment(4);
		    }else if (value<=limite2){
			    tv.setText(getString(R.string.g1score_msg2)); 	/** podes escrever as mensagens no strings.xml so não pode ter o ' adiciona a versao pt **/
		    }else{
		    	tv.setText(getString(R.string.g1score_msg3)); 	/** podes escrever as mensagens no strings.xml so não pode ter o ' adiciona a versao pt **/
		    }
		}
		optionSelected();
	}
	
	public void optionSelected(){
		game1back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Back no main menu.");
            	//Intent i = new Intent(Game1Score.this, MainActivity.class);
            	//startActivity(i);
            	//Log.i(getString(R.string.app_name),"MainActivity created.");
            	/** não é necessário recriares a main activity porque ela ainda existe **/
            	finish();
            }
        });
		
		game1sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Sending SMS.");            	
            	Log.i(getString(R.string.app_name),"SMS sended.");
            	
            	/** enviar as sms **/
            	SendSMS sms = new SendSMS();
        		sms.sendSMS(getApplicationContext());
            	
            	
            	Log.i(getString(R.string.app_name),"Back no main menu.");
            	//Intent i = new Intent(Game1Score.this, MainActivity.class);
            	//startActivity(i);
            	//Log.i(getString(R.string.app_name),"MainActivity created.");
            	
            	/** não é necessário recriares a main activity porque ela ainda existe **/
            	finish();
            }
        });
		
		game1call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Making call.");
            	
            	/** para fazer uma chamada (http://goo.gl/EnR17s) **/            	
            	Intent callIntent = new Intent(Intent.ACTION_CALL);
            	callIntent.setData(Uri.parse("tel:112"));
            	startActivity(callIntent);
            	
            	Log.i(getString(R.string.app_name),"Call done.");
            	Log.i(getString(R.string.app_name),"Back no main menu.");
            	//Intent i = new Intent(Game1Score.this, MainActivity.class);
            	//startActivity(i);
            	//Log.i(getString(R.string.app_name),"MainActivity created.");
            	/** não é necessário recriares a main activity porque ela ainda existe **/
            	finish();
            }
        });
	}
	
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

}
