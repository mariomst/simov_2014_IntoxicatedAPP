package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class AdvicesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advices);
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
	
	/** Acções para os botões apresentados na activity "AdvicesActivity" **/
	public void optionSelected(){
		ImageButton advice1 = (ImageButton) findViewById(R.id.button_drinking);
    	ImageButton advice2 = (ImageButton) findViewById(R.id.button_hangover);
    	ImageButton advice3 = (ImageButton) findViewById(R.id.button_alchool);    	
    	
    	advice1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(AdvicesActivity.this, ShowAdviceActivity.class);
            	i.putExtra("category", "drinking");
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"Showing drinking advices.");
            }
        });
    	
    	advice2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { 
            	Intent i = new Intent(AdvicesActivity.this, ShowAdviceActivity.class);
            	i.putExtra("category", "hangover");
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"Showing hangover advices.");
            }
        });
    	
    	advice3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { 
            	Intent i = new Intent(AdvicesActivity.this, ShowAdviceActivity.class);
            	i.putExtra("category", "alchool");
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"Showing % of alcohol.");
            }
        });
	}
}
