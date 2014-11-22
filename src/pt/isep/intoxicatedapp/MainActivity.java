package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    
}
