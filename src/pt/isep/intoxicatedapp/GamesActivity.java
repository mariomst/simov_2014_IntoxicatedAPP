

package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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

	/**
	 * Acções para os botões apresentados na activity "GamesActivity"
	 */
	public void optionSelected(){
		ImageButton game1 = (ImageButton) findViewById(R.id.button_game1);
    	ImageButton game2 = (ImageButton) findViewById(R.id.button_game2);
    	
    	game1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Abrir minijogo 1
            	//Toast.makeText(getApplicationContext(), "Not yet implemented... Sorry",
            			//Toast.LENGTH_SHORT).show();
            	Log.i(getString(R.string.app_name),"Starting minigame 1.");
            	
            	// Criar nova activity com o menu de jogos.
            	Intent i = new Intent(GamesActivity.this, Game1Activity.class);
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"Game1Activity created.");
            }
        });
    	
    	game2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Abrir minijogo 2     
            	Toast.makeText(getApplicationContext(), "Not yet implemented... Sorry",
            	Toast.LENGTH_SHORT).show();
            	Log.i(getString(R.string.app_name),"Starting minigame 2.");
            }
        });
	}
}

