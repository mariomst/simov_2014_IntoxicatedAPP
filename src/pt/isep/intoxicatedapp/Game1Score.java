package pt.isep.intoxicatedapp;

import pt.isep.intoxicatedapp.Game1Activity.MyView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
			    tv.setText("You're doing\n      great!");
			    game1sms.setVisibility(4);
			    game1call.setVisibility(4);
			    //tv.setTextAlignment(4);
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
            	Intent i = new Intent(Game1Score.this, MainActivity.class);
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"MainActivity created.");
            }
        });
		
		game1sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Sending SMS.");
            	
            	Log.i(getString(R.string.app_name),"SMS sended.");
            	Log.i(getString(R.string.app_name),"Back no main menu.");
            	Intent i = new Intent(Game1Score.this, MainActivity.class);
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"MainActivity created.");
            }
        });
		
		game1call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Making call.");
            	
            	Log.i(getString(R.string.app_name),"Call done.");
            	Log.i(getString(R.string.app_name),"Back no main menu.");
            	Intent i = new Intent(Game1Score.this, MainActivity.class);
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"MainActivity created.");
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
