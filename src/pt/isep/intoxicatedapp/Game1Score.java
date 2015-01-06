package pt.isep.intoxicatedapp;

import pt.isep.intoxicatedapp.Game1Activity.MyView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game1Score extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    float value = extras.getFloat("score");
		    TextView tv = (TextView) findViewById(R.id.tv_game1_score);
		    if (value<=70){
			    tv.setText("safe!");
		    }else if (value<=160){
			    tv.setText("be careful!");
		    }else if(value>160){
		    	tv.setText("no way!");
		    }
		}
		optionSelected();
	}
	
	public void optionSelected(){
		ImageButton game1back = (ImageButton) findViewById(R.id.button_game1_score);
		ImageButton game1sms = (ImageButton) findViewById(R.id.button_game1_sms);
    	
		game1back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Back no main menu.");
            	Intent i = new Intent(Game1Score.this, MainActivity.class);
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"MainActivity created.");
            }
        });
		
		game1back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.i(getString(R.string.app_name),"Sending SMS.");
            	
            	Log.i(getString(R.string.app_name),"SMS sended.");
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
