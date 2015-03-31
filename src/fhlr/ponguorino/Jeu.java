package fhlr.ponguorino;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

public class Jeu extends View implements SensorEventListener {

	private BitmapDrawable balle;
	private BitmapDrawable barre; 
	
	private int START_X = -1;
	private int START_Y_PLAYER = -1;
	private int START_Y_AI = -1;
	
	private boolean init;
	
	private SensorManager sensorManager;
	private Sensor sensor;
	
	private Barre ai;
	private Barre joueur;
	
	private Paint p;
	
	private float vx;

	public Jeu(Context context) {
		super(context);
		
		init = true;
		
		balle = (BitmapDrawable)getContext().getResources().getDrawable(R.drawable.favelasp);
		barre = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.paddle);
		
		sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		
		p = new Paint();
		p.setStrokeWidth(10);
		p.setTextSize(20);
		p.setColor(Color.WHITE);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		if(init) {
			START_X = getWidth()/2;
			START_Y_PLAYER = getHeight()-barre.getBitmap().getHeight();
			START_Y_AI = barre.getBitmap().getHeight()/2;
			
			ai = new Barre(START_X, START_Y_AI);
			joueur = new Barre(START_X, START_Y_PLAYER);
			init = false;
		}
		
		//On bouge la barre
		joueur.setX((int)(joueur.getX() - vx*2.5));
		
		//Arrière-plan en noir
		canvas.drawARGB(255, 0, 0, 0);

		//TODO: Mouvement de la balle
		canvas.drawBitmap(balle.getBitmap(), getWidth()/2, getHeight()/2, p);
		canvas.drawBitmap(barre.getBitmap(), joueur.getX()-joueur.getWidth()/2 , joueur.getY()-joueur.getHeight()/2, p);
		canvas.drawBitmap(barre.getBitmap(), ai.getX() - ai.getWidth()/2, ai.getY() - ai.getHeight()/2, p);
		
		//Affichage de l'accéléromètre
		canvas.drawText(String.valueOf(vx), 10, 25, p);
		
		
		invalidate();
	}
	
	public void resume() {
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public void pause() {
		sensorManager.unregisterListener(this, sensor);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		vx = values[0];
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
