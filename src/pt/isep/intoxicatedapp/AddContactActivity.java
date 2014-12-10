package pt.isep.intoxicatedapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends Activity {

	protected EditText contactName;
	protected EditText contactNumber;
	protected int _id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcontact);

		/**
		 * Variáveis vão obter as informações inseridas nas respectivas caixas
		 * de texto
		 **/
		contactName = (EditText) findViewById(R.id.contactName);
		contactNumber = (EditText) findViewById(R.id.contactNumber);
		_id = getIntent().getIntExtra("contactID", 0);

		/** Instruções para o botão Add **/
		Button buttonADD = (Button) findViewById(R.id.addButton);
		buttonADD.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				DBAdapter adapter = new DBAdapter(getApplicationContext());
				try {
					adapter.insertContact(_id,
							contactName.getText().toString(), contactNumber
									.getText().toString());
					Toast.makeText(AddContactActivity.this,
							"Info: Contact added", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(AddContactActivity.this,
							"Error: Failed to add contact", Toast.LENGTH_SHORT)
							.show();
				}
				finish();
			}
		});
	}
}
