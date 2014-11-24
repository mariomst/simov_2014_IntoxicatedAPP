package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GamesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_games);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		optionSelected();
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

	public void optionSelected(){
		Button game1 = (Button) findViewById(R.id.button1);
    	Button game2 = (Button) findViewById(R.id.button2);
    	
    	game1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Abrir minijogo 1            	
            	Log.i(getString(R.string.app_name),"Starting minigame 1.");
            }
        });
    	
    	game2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Abrir minijogo 2            	
            	Log.i(getString(R.string.app_name),"Starting minigame 2.");
            }
        });
	}
}
