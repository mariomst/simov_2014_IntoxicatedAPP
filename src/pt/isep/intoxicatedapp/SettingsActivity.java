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
import android.widget.Toast;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		/*
		 * Criar base de dados de contactos caso ainda não foi criada
		 */
		DBAdapter adapter = new DBAdapter(getApplicationContext());

		/*
		 * Buscar contactos e inseri-los num array
		 */
		ArrayList<Contact> contacts = adapter.getContacts();

		Toast.makeText(getApplicationContext(), "Contacts: " + contacts.size(),
				Toast.LENGTH_SHORT).show();

		/*
		 * Adicionar onClickListeners nos botões relacionados com os contactos.
		 */
		addContact();
		removeContact();
		infoContact();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
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

	/*
	 * Acções para os botões apresentados na secção contactos - Adicionar
	 */
	public void addContact() {
		final ImageButton contact1 = (ImageButton) findViewById(R.id.add_contact_1);
		final ImageButton contact2 = (ImageButton) findViewById(R.id.add_contact_2);
		final ImageButton contact3 = (ImageButton) findViewById(R.id.add_contact_3);

		contact1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				registerForContextMenu(contact1);
				openContextMenu(contact1);
			}
		});

		contact2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				registerForContextMenu(contact2);
				openContextMenu(contact2);
			}
		});

		contact3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				registerForContextMenu(contact3);
				openContextMenu(contact3);
			}
		});
	}

	/*
	 * Criar menu de contexto
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.option_contacts, menu);
	}

	/*
	 * Acções para os itens do menu de contexto
	 */
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.manual_add_contact:
			// Intent i = new Intent(this, AddContactActivity.class);
			// startActivity(i);
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
						showSelectedNumber(name, number);
					}
				} finally {
					if (c != null) {
						c.close();
					}
				}
			}
		}
	}

	public void showSelectedNumber(String type, String number) {
		Toast.makeText(this, type + ": " + number, Toast.LENGTH_LONG).show();
	}

	/*
	 * Acções para os botões apresentados na secção contactos - Remover
	 */
	public void removeContact() {
		ImageButton r_contact1 = (ImageButton) findViewById(R.id.remove_contact_1);
		ImageButton r_contact2 = (ImageButton) findViewById(R.id.remove_contact_2);
		ImageButton r_contact3 = (ImageButton) findViewById(R.id.remove_contact_3);

		final DBAdapter adapter = new DBAdapter(getApplicationContext());

		r_contact1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int contactID = 1;
				adapter.deleteContact(contactID);
				Toast.makeText(getApplicationContext(),
						"Removing contact 1...", Toast.LENGTH_SHORT).show();
			}
		});

		r_contact2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int contactID = 2;
				adapter.deleteContact(contactID);
				Toast.makeText(getApplicationContext(),
						"Removing contact 2...", Toast.LENGTH_SHORT).show();
			}
		});

		r_contact3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int contactID = 3;
				adapter.deleteContact(contactID);
				Toast.makeText(getApplicationContext(),
						"Removing contact 3...", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/*
	 * Acções para os botões apresentados na secção contactos - Informações
	 */
	public void infoContact() {
		ImageButton i_contact1 = (ImageButton) findViewById(R.id.info_contact_1);
		ImageButton i_contact2 = (ImageButton) findViewById(R.id.info_contact_2);
		ImageButton i_contact3 = (ImageButton) findViewById(R.id.info_contact_3);

		final DBAdapter adapter = new DBAdapter(getApplicationContext());

		i_contact1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int contactID = 1;
				// Intent intent = new Intent(this,
				// ContactDetailsActivity.class);
				// intent.putExtra("CONTACT_ID", contactID);
				// startActivity(intent);
			}
		});

		i_contact2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int contactID = 2;
				// Intent intent = new Intent(this,
				// ContactDetailsActivity.class);
				// intent.putExtra("CONTACT_ID", contactID);
				// startActivity(intent);
			}
		});

		i_contact3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int contactID = 3;
				// Intent intent = new Intent(this,
				// ContactDetailsActivity.class);
				// intent.putExtra("CONTACT_ID", contactID);
				// startActivity(intent);
			}
		});
	}
}
