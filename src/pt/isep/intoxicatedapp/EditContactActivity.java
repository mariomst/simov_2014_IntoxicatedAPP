package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditContactActivity extends Activity {
	
	protected EditText contactName;
	protected EditText contactNumber;
	protected int _id;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editcontact);		
		
		/**
		 * Obter informações do contacto através do seu ID
		 */
		DBAdapter adapter = new DBAdapter(getApplicationContext());
		_id = getIntent().getIntExtra("contactID", 0);
		Contact c = adapter.getContact(_id);				
		
		/**
		 * Variáveis vão obter as informações inseridas nas respectivas caixas
		 * de texto
		 **/
		contactName = (EditText) findViewById(R.id.contactName);
		contactNumber = (EditText) findViewById(R.id.contactNumber);
		
		/**
		 * Definir texto nas caixas de texto
		 */
		contactName.setText(c.getName());
		contactNumber.setText(c.getPhoneNumber());
		
		/**
		 * Intruções para o botão Edit
		 */
		Button buttonEDIT = (Button) findViewById(R.id.editButton);
		buttonEDIT.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	/** Criar novo objecto Contact **/
            	Contact c = new Contact(_id, contactName.getText().toString(), contactNumber.getText().toString());
            	/** Atualizar BD **/
                DBAdapter adapter = new DBAdapter(getApplicationContext());
                adapter.updateContact(c.getID(), c.getName(), c.getPhoneNumber());
                finish();
            }
        });
		
		/**
		 * Intruções para o botão Remove
		 */
		Button buttonREMOVE = (Button) findViewById(R.id.removeButton);
		buttonREMOVE.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	DBAdapter adapter = new DBAdapter(getApplicationContext());
            	adapter.deleteContact(_id);
                finish();
            }
        });
		
		/**
		 * Intruções para o botão Cancel
		 */
		Button buttonCANCEL = (Button) findViewById(R.id.cancelButton);
		buttonCANCEL.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {            	
                finish();
            }
        });
	}
}
