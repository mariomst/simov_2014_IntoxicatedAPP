package pt.isep.intoxicatedapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
			    tv.setText("You're doing\n      great!");
			    game1sms.setVisibility(4);
			    game1call.setVisibility(4);
		    }else if (value<=limite2){
			    tv.setText("No more happy\n   hour for you!\n     Be careful!");
		    }else{
		    	tv.setText("   No way you're\ndoing anything in\n      that state!\n   Get some help!");
		    }
		}
		optionSelected();
	}
	
	public void optionSelected(){
		game1back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Back no main menu.");

            	finish();
            }
        });
		
		game1sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Sending SMS.");
            	
            	/** enviar as sms **/
            	SendSMS sms = new SendSMS();
        		sms.sendSMS(getApplicationContext());
        		
        		Log.i(getString(R.string.app_name),"SMS sended.");
            	
            	Log.i(getString(R.string.app_name),"Back no main menu.");
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
