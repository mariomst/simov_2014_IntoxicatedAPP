package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button games;
	Button advices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        OptionSelected();
    }
    
    public void OptionSelected(){
    	
    	games = (Button) findViewById(R.id.button1);
    	advices = (Button) findViewById(R.id.button2);
    	
    	games.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent i = new Intent(MainActivity.this, GamesActivity.class);
            	startActivity(i);
            }
        });
    	
    	advices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent i = new Intent(MainActivity.this, AdvicesActivity.class);
            	startActivity(i);            	
            }
        });

    }
    
}
