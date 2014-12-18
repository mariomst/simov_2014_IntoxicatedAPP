package pt.isep.intoxicatedapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	
	private int _id;
	private int contactsSize;
	private TextView c1, c2, c3;
	private ImageButton a1, a2, a3, i1, i2, i3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		/** Criar base de dados de contactos caso ainda não foi criada **/
		DBAdapter adapter = new DBAdapter(getApplicationContext());

		/** Buscar contactos e inseri-los num array **/
		ArrayList<Contact> contacts = adapter.getContacts();
		
		/** Associar as TextViews **/
		c1 = (TextView) findViewById(R.id.contact_1);
		c2 = (TextView) findViewById(R.id.contact_2);
		c3 = (TextView) findViewById(R.id.contact_3);
		
		/** Associas aos botões **/
		a1 = (ImageButton) findViewById(R.id.add_contact_1);
		a2 = (ImageButton) findViewById(R.id.add_contact_2);
		a3 = (ImageButton) findViewById(R.id.add_contact_3);
		i1 = (ImageButton) findViewById(R.id.info_contact_1);
		i2 = (ImageButton) findViewById(R.id.info_contact_2);
		i3 = (ImageButton) findViewById(R.id.info_contact_3);
		
		/** Obter dimensão do array **/
		contactsSize = contacts.size();
		
		if(contactsSize == 0){
			String text = getString(R.string.Contact_1);
			c1.setText(text);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.INVISIBLE);
		} else if(contactsSize == 1){
			String text = getString(R.string.Contact_2);
			c2.setText(text);
			c3.setVisibility(View.INVISIBLE);
		} else if(contactsSize == 2){
			String text = getString(R.string.Contact_3);
			c3.setText(text);
		}		

		/** Adicionar onClickListeners nos botões relacionados com os contactos **/
		refreshContacts();
		addContact();
		infoContact();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}	

	@Override
	protected void onResume() {		
		super.onResume();
		refreshContacts();		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.i(getString(R.string.app_name), "Option Back was called.");
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/** Acções para os botões apresentados na secção contactos - Adicionar **/
	public void addContact() {
		a1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_id = 1;
				registerForContextMenu(a1);
				openContextMenu(a1);
			}
		});

		a2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_id = 2;
				registerForContextMenu(a2);
				openContextMenu(a2);
			}
		});

		a3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_id = 3;
				registerForContextMenu(a3);
				openContextMenu(a3);
			}
		});
	}

	/** Criar menu de contexto **/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.option_contacts, menu);
	}

	/** Acções para os itens do menu de contexto **/
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.manual_add_contact:
			Intent i = new Intent(this, AddContactActivity.class);
			i.putExtra("contactID", _id);
			startActivity(i);
			refreshContacts();
			return true;
		case R.id.select_contact:
			// Abrir lista de contactos
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);			
			startActivityForResult(intent, 1);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			Uri uri = data.getData();
			if (uri != null) {
				Cursor c = null;
				try {
					c = getContentResolver()
							.query(uri,
									new String[] {
											ContactsContract.CommonDataKinds.Phone.NUMBER,
											ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },
									null, null, null);

					if (c != null && c.moveToFirst()) {
						String number = c.getString(0);
						String name = c.getString(1);
						addContact(name, number);
						refreshContacts();
					}
				} finally {
					if (c != null) {
						c.close();
					}
				}
			}
		}
	}

	private void addContact(String name, String number) {
		DBAdapter adapter = new DBAdapter(getApplicationContext());
		try{
			adapter.insertContact(_id, name, number);
			Toast.makeText(this, "Info: Contact added", Toast.LENGTH_SHORT).show();
		} catch (Exception e){
			Toast.makeText(this, "Error: Failed to add contact", Toast.LENGTH_SHORT).show();
		}
	}

	/** Acções para os botões apresentados na secção contactos - Informações **/
	private void infoContact() {
		
		i1.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				DBAdapter adapter = new DBAdapter(getApplicationContext());
				ArrayList<Contact> contacts = adapter.getContacts();
				
				_id = contacts.get(0).getID();
				String m = contacts.get(0).getID() + ": " + contacts.get(0).getName();
				Log.i(getString(R.string.app_name), m);
				Intent intent = new Intent(SettingsActivity.this, EditContactActivity.class);
				intent.putExtra("contactID", _id);
				startActivity(intent);
			}
		});

		i2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DBAdapter adapter = new DBAdapter(getApplicationContext());
				ArrayList<Contact> contacts = adapter.getContacts();
				
				_id = contacts.get(1).getID();
				String m = contacts.get(1).getID() + ": " + contacts.get(1).getName();
				Log.i(getString(R.string.app_name), m);
				Intent intent = new Intent(SettingsActivity.this, EditContactActivity.class);
				intent.putExtra("contactID", _id);
				startActivity(intent);
			}
		});

		i3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DBAdapter adapter = new DBAdapter(getApplicationContext());
				ArrayList<Contact> contacts = adapter.getContacts();
				
				_id = contacts.get(2).getID();
				String m = contacts.get(2).getID() + ": " + contacts.get(2).getName();
				Log.i(getString(R.string.app_name), m);
				Intent intent = new Intent(SettingsActivity.this, EditContactActivity.class);				
				intent.putExtra("contactID", _id);
				startActivity(intent);
			}
		});
	}

	/** Atualização das TextViews relacionadas com os contactos **/
	private void refreshContacts(){
		DBAdapter adapter = new DBAdapter(getApplicationContext());
		ArrayList<Contact> contacts = adapter.getContacts();
		contactsSize = contacts.size();
		
		Toast.makeText(getApplicationContext(), "Contacts: " + contacts.size(),
				Toast.LENGTH_SHORT).show();
						
		if(contactsSize == 0){
			String text1 = getString(R.string.Contact_1);
			
			c1.setText(text1); c2.setVisibility(View.INVISIBLE); c3.setVisibility(View.INVISIBLE);
			a1.setVisibility(View.VISIBLE); a2.setVisibility(View.INVISIBLE); a3.setVisibility(View.INVISIBLE);
			i1.setVisibility(View.INVISIBLE); i2.setVisibility(View.INVISIBLE); i3.setVisibility(View.INVISIBLE);
		} else if(contactsSize == 1){
			String text1 = contacts.get(0).getName();
			c1.setText(text1);
			String text2 = getString(R.string.Contact_2);
			c2.setText(text2);
			
			c1.setVisibility(View.VISIBLE); c2.setVisibility(View.VISIBLE); c3.setVisibility(View.INVISIBLE);
			a1.setVisibility(View.INVISIBLE); a2.setVisibility(View.VISIBLE); a3.setVisibility(View.INVISIBLE);
			i1.setVisibility(View.VISIBLE);	i2.setVisibility(View.INVISIBLE); i3.setVisibility(View.INVISIBLE);
		} else if(contactsSize == 2){
			String text1 = contacts.get(0).getName();
			c1.setText(text1);
			String text2 = contacts.get(1).getName();
			c2.setText(text2);
			String text3 = getString(R.string.Contact_3);
			c3.setText(text3);
			
			c1.setVisibility(View.VISIBLE); c2.setVisibility(View.VISIBLE); c3.setVisibility(View.VISIBLE);
			a1.setVisibility(View.INVISIBLE); a2.setVisibility(View.INVISIBLE); a3.setVisibility(View.VISIBLE);
			i1.setVisibility(View.VISIBLE); i2.setVisibility(View.VISIBLE); i3.setVisibility(View.INVISIBLE);
		} else {
			String text1 = contacts.get(0).getName();
			c1.setText(text1);
			String text2 = contacts.get(1).getName();
			c2.setText(text2);
			String text3 = contacts.get(2).getName();
			c3.setText(text3);
			
			c1.setVisibility(View.VISIBLE); c2.setVisibility(View.VISIBLE); c3.setVisibility(View.VISIBLE);
			a1.setVisibility(View.INVISIBLE); a2.setVisibility(View.INVISIBLE); a3.setVisibility(View.INVISIBLE);
			i1.setVisibility(View.VISIBLE); i2.setVisibility(View.VISIBLE); i3.setVisibility(View.VISIBLE);
		}
	}
}
