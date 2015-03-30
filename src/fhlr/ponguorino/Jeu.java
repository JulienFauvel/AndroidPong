package fhlr.ponguorino;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

public class Jeu extends View implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor sensor;

	public Jeu(Context context) {
		super(context);

		sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public void resume() {
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public void pause() {
		sensorManager.unregisterListener(this, sensor);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
