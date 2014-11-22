package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class AdvicesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advices);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
}
