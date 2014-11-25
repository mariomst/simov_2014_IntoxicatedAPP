package pt.isep.intoxicatedapp;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button games;
	Button advices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Acções definidas para os botões.
        optionSelected();
        
        //Forçar overflow menu (em androids com o botão menu fisico)
        forceOverflowMenu();
    }       
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.option_menu, menu);
    	return true;
	}  

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_settings: 
				// Criar activity "settings"
				Intent is = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(is);
				Log.i(getString(R.string.app_name),"SettingsActivity created.");
				return true;
			case R.id.action_about:
				// Criar activity "about"
            	Intent ia = new Intent(MainActivity.this, AboutActivity.class);
            	startActivity(ia);
				Log.i(getString(R.string.app_name),"AboutActivity created.");
				return true;
			default:
				return super.onOptionsItemSelected(item);	
		}		
	}

	public void optionSelected(){
    	
    	games = (Button) findViewById(R.id.button1);
    	advices = (Button) findViewById(R.id.button2);
    	
    	games.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Criar nova activity com o menu de jogos.
            	Intent i = new Intent(MainActivity.this, GamesActivity.class);
            	startActivity(i);
            	Log.i(getString(R.string.app_name),"GamesActivity created.");
            }
        });
    	
    	advices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// Criar nova activity com os conselhos.
            	Intent i = new Intent(MainActivity.this, AdvicesActivity.class);
            	startActivity(i);            	
            	Log.i(getString(R.string.app_name),"AdvicesActivity created.");
            }
        });

    }
    
	public void forceOverflowMenu(){
		try{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			if(menuKeyField != null){
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config,false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
