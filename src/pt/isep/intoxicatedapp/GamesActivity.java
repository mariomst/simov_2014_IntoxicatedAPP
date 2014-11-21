package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.os.Bundle;

public class GamesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_games);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
