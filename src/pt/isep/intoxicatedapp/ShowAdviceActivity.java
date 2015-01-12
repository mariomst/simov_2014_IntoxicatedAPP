package pt.isep.intoxicatedapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShowAdviceActivity extends Activity {
	
	private TextView adviceNumber, adviceOutput, adviceCounter;
	private ImageButton advicePrev, adviceNext;
	private String adviceCat;
	private ArrayList<Advice> advices;
	private int advicesSize, currentAdvice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showadvice);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		/** Associar os objectos criados com os objectos do layout **/
		adviceNumber = (TextView) findViewById(R.id.tvAdviceNumber);
		adviceOutput = (TextView) findViewById(R.id.tvAdviceOutput);
		adviceCounter = (TextView) findViewById(R.id.tvAdviceCounter);
		
		advicePrev = (ImageButton) findViewById(R.id.btnAdvicePrev);
		adviceNext = (ImageButton) findViewById(R.id.btnAdviceNext);
		
		/** Atribuir a categoria desejada **/
		adviceCat = getIntent().getStringExtra("category");
		
		/** Obter todos os conselhos da categoria **/
		DBAdapter adapter = new DBAdapter(getApplicationContext());
		advices = adapter.getAdvicesbyCategory(adviceCat);
		advicesSize = advices.size();
		
		/** Caso não existem conselhos da categoria **/
		if(advicesSize == 0){
			/** Ocultar os botões **/
			advicePrev.setVisibility(View.INVISIBLE);
			adviceNext.setVisibility(View.INVISIBLE);
			
			/** Definir texto nas caixas de texto **/
			currentAdvice = 0;
			String number = adviceNumber.getText().toString() + currentAdvice;
			String advice = getString(R.string.error_message4) + " \"" + adviceCat + "\"";
			String counter = "0/0";
			adviceNumber.setText(number);
			adviceOutput.setText(advice);
			adviceCounter.setText(counter);
		} else {
			/** Mostrar o primeiro conselho no array **/
			currentAdvice = 1;
			showFirstAdvice();
		}
		
		/** Adicionar instruções para os botões **/
		advicePrev.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentAdvice--;				
				if(currentAdvice == 1){
					showFirstAdvice();
				} else {					
					showAdvice();
				}
			}
		});
		
		adviceNext.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentAdvice++;
				if(currentAdvice == advices.size()){
					showLastAdvice();
				} else {					
					showAdvice();
				}
			}
		});		
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
			Log.i(getString(R.string.app_name), "Option unknown.");
			return true;
		}
	}
	
	protected void showFirstAdvice(){
		/** Ocultar o botão de conselho anterior **/
		advicePrev.setVisibility(View.INVISIBLE);
		
		/** Dependendo da dimenão do array mostrar ou não o botão seguinte **/
		if(advicesSize > 1){
			adviceNext.setVisibility(View.VISIBLE);
		} else {
			adviceNext.setVisibility(View.INVISIBLE);
		}
		
		/** Mostrar conselho **/
		String number = getString(R.string.advice_number) + currentAdvice;
		String advice = advices.get((currentAdvice-1)).getText();
		String counter = currentAdvice + "/" + advicesSize;	
		adviceNumber.setText(number);
		adviceOutput.setText(advice);
		adviceCounter.setText(counter);		
	}
	
	protected void showAdvice(){
		/** Mostrar o botão de conselho anterior **/
		advicePrev.setVisibility(View.VISIBLE);
		/** Mostar o botão de conselho seguinte **/
		adviceNext.setVisibility(View.VISIBLE);		
		
		/** Mostrar conselho **/
		String number = getString(R.string.advice_number) + currentAdvice;
		String advice = advices.get((currentAdvice-1)).getText();
		String counter = currentAdvice + "/" + advicesSize;	
		adviceNumber.setText(number);
		adviceOutput.setText(advice);
		adviceCounter.setText(counter);	
	}
	
	protected void showLastAdvice(){
		/** Mostrar o botão de conselho anterior **/
		advicePrev.setVisibility(View.VISIBLE);
		/** Ocultar o botão de conselho seguinte **/
		adviceNext.setVisibility(View.INVISIBLE);		
		
		/** Mostrar conselho **/
		String number = getString(R.string.advice_number) + currentAdvice;
		String advice = advices.get((currentAdvice-1)).getText();
		String counter = currentAdvice + "/" + advicesSize;	
		adviceNumber.setText(number);
		adviceOutput.setText(advice);
		adviceCounter.setText(counter);	
	}
}
