package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.os.Bundle;

public class AdvicesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advices);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
