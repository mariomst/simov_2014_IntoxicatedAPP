package pt.isep.intoxicatedapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPS extends Service implements LocationListener {

	private final Context c;

	/** flag para o estado do GPS **/
	boolean GPSEnabled = false;
	boolean canGetLocaion = false;

	/** flag para o estado da rede **/
	boolean NetworkEnabled = false;

	/** Variáveis **/
	Location location;
	double latitude;
	double longitude;

	/** Distancia minima para ocorrer atualização **/
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 metros

	/** Tempo entre atualizações em ms **/
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minuto

	/** Criar instância de LocationManager **/
	private LocationManager lm;

	public GPS(Context context) {
		this.c = context;
		getLocation();
	}

	public Location getLocation() {
		try {
			lm = (LocationManager) c.getSystemService(LOCATION_SERVICE);

			// Obter estado do GPS
			GPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// Obter estado da rede
			NetworkEnabled = lm
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!GPSEnabled && !NetworkEnabled) {
				// nenhum provider activo
				Log.i(c.getString(R.string.app_name),
						"Error: GPS and Network are disabled.");
			} else {
				this.canGetLocaion = true;

				// Obter localização através do Network Provider
				if (NetworkEnabled) {
					Log.i(c.getString(R.string.app_name),
							"Info: Network enabled, going to use if to obtain location.");
					lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					if (lm != null) {
						location = lm
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}

				// Obter lat/long usand os serviços GPS.
				if (GPSEnabled) {
					if (location == null) {
						Log.i(c.getString(R.string.app_name),
								"Info: GPS enabled, going to use if to obtain location.");
						lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						if (lm != null) {
							location = lm
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			/*Log.i(getString(R.string.app_name),
					getString(R.string.error_message3));*/
		}

		return location;
	}

	/** função para parar o GPS listener **/
	public void stopUsingGPS() {
		if (lm != null) {
			lm.removeUpdates(GPS.this);
		}
	}

	/** função para obter a latitude **/
	public double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}

		return latitude;
	}

	/** função para obter a longitude **/
	public double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}

		return longitude;
	}

	/** função para verificar se GPS/wifi esta ativo **/
	public boolean canGetLocation() {
		return this.canGetLocaion;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
