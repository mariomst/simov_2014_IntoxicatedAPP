package pt.isep.intoxicatedapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

public class GetAdvicesHTTP {	
	
	private final Context c;
	
	public GetAdvicesHTTP(Context context, String url) {
		this.c = context;
		getAdvices(url);
	}
	
	public void getAdvices(String url) {
		
		//Verificar se o url é válido
		if(URLUtil.isValidUrl(url)){
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				
				if(statusCode == 200){
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					while((line = reader.readLine()) != null){
						builder.append(line);
					}				
					try{
						insertAdvices(builder.toString());
					} catch (Exception e){
						Toast.makeText(c,
								"Info: An error occured while trying to retrieve information", Toast.LENGTH_SHORT)
								.show();
						Log.i("IntoxicatedApp", "Error");
					}					
				} else {
					Log.i("IntoxicatedApp", "Failed to download file");
					Toast.makeText(c,
							"Info: Failed to download information", Toast.LENGTH_SHORT)
							.show();
				}
			} catch (ClientProtocolException e) {
				Toast.makeText(c,
						"Info: An error occured while trying to retrieve information", Toast.LENGTH_SHORT)
						.show();
			} catch (IOException e) {
				Toast.makeText(c,
						"Info: An error occured while trying to retrieve information", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(c,
					"Info: Invalid URL", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	private void insertAdvices(String advices) throws JSONException{
		
		JSONArray jArray = new JSONArray(advices);
		
		if(jArray.length() > 0){
			for(int i = 0; i < jArray.length(); i++){			
				JSONObject json_data = jArray.getJSONObject(i);
				String category = json_data.getString("category");
				String advice = json_data.getString("advice_text");
				
				DBAdapter adapter = new DBAdapter(c.getApplicationContext());
				try{
					adapter.insertAdvice(i, category, advice);
					Log.i("IntoxicatedApp", "Info: Adviced added");
				} catch (Exception e){
					Log.i("IntoxicatedApp", "Info: Failed to add advice");
				}
			}
			Toast.makeText(c,
					"Info: Advices were added", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(c,
					"Info: No advices to add", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
