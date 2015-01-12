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
public class Game2Score extends Activity{
	
	Button game2back;
	Button game2sms;
	Button game2call;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		game2back = (Button) findViewById(R.id.button_game1_score);
		game2sms = (Button) findViewById(R.id.button_game1_sms);
		game2call = (Button) findViewById(R.id.button_game1_call);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    //float falhas = extras.getFloat("falhas");
		    float certas = extras.getFloat("certas");
		    float certas1 = extras.getFloat("certas1");
		    TextView tv = (TextView) findViewById(R.id.tv_game1_score);
		    if (certas >= 29 || certas1 >= 19){
			    tv.setText("You re doing\n      great!");
			    game2sms.setVisibility(4);
			    game2call.setVisibility(4);
		    }else if (certas >= 10 || certas1 >= 9){
			    tv.setText("No more happy\n   hour for you!\n     Be careful!");
		    }else{
		    	tv.setText("   No way you're\ndoing anything in\n      that state!\n   Get some help!");
		    }
		}
		optionSelected();
	}
	
	public void optionSelected(){
		game2back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Back no main menu.");

            	finish();
            }
        });
		
		game2sms.setOnClickListener(new View.OnClickListener() {
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
		
		game2call.setOnClickListener(new View.OnClickListener() {
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