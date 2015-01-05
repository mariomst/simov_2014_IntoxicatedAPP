package pt.isep.intoxicatedapp;

import pt.isep.intoxicatedapp.Game1Activity.MyView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Game1Score extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    float value = extras.getFloat("score");
		    TextView tv = (TextView) findViewById(R.id.tv_game1_score);
		    tv.setText(Float.toString(value));
		}
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
