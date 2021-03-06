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
import android.view.View;

public class Jeu extends View implements SensorEventListener {

	private BitmapDrawable balleImg;
	private BitmapDrawable barreImg; 
	
	private int START_X = -1;
	private int START_Y_PLAYER = -1;
	private int START_Y_AI = -1;
	
	private boolean init;
	
	private SensorManager sensorManager;
	private Sensor sensor;
	
	//Barres
	private Barre ai;
	private Barre joueur;
	
	//Balle
	private Balle balle;
	
	private int[] score;
	
	
	private Paint p;
	
	private float vx;
	
	private int nbCoupsMax;
	private int nbCoups;

	public Jeu(Context context) {
		super(context);
		
		init = true;
		
		balleImg = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.ball);
		barreImg = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.paddle);
		
		sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		
		p = new Paint();
		p.setStrokeWidth(20);
		p.setTextSize(60);
		p.setColor(Color.WHITE);
		
		score = new int[2];
		score[1] = -1;
		nbCoupsMax = 0;
		nbCoups = 0;
		
		boucleJeu();
	}
	
	public void init() {
		
		START_X = getWidth()/2;
		START_Y_PLAYER = getHeight()-barreImg.getBitmap().getHeight();
		START_Y_AI = barreImg.getBitmap().getHeight()/2;
		
		ai = new Barre(barreImg.getBitmap().getWidth(), barreImg.getBitmap().getHeight(), START_X, START_Y_AI);
		joueur = new Barre(barreImg.getBitmap().getWidth(), barreImg.getBitmap().getHeight(), START_X, START_Y_PLAYER);
		balle = new Balle(balleImg.getBitmap().getWidth(), balleImg.getBitmap().getHeight(), getWidth()/2, getHeight()/2);
		
		init = false;
		
		demarrerBalle();

	}
	
	public void boucleJeu() {
		if(init) {
			init();
		}
		
		invalidate();
		
		//On bouge la barre
		joueur.setX((int)(joueur.getX() - vx*3.0));
		
		balle.bouger();
		
		
		if(balle.getVy()<0) {
			ai.bougerVers(getWidth()/2);
		} else {
			ai.bougerVers(balle.getX());
		}
		
		//On check la position des barres pour qu'elles ne sortent pas de l'�cran
		checkBarres();
		checkBalle();
		
		int m;
		if((m=mancheFinie())>0) {
			if(m == 1) {
				score[0]++;
			} else if(m == 2) {
				score[1]++;
			}
			
			if(nbCoups > nbCoupsMax) {
				nbCoupsMax = nbCoups;
			}
			nbCoups = 0;
			
			reset();
		}
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
			
		//Arri�re-plan en noir
		canvas.drawARGB(255, 0, 0, 0);
		
		//Barre en pointill�s du milieu
		int nbTraits = 0;
		for(int i=0; i<getWidth(); i+=getWidth()/21) {
			if(nbTraits%2==0) {
				canvas.drawLine(i, getHeight()/2, i + getWidth()/21, getHeight()/2, p);
			}
			nbTraits++;
		}
		
		//Dessin des deux barres et de la balle
		canvas.drawBitmap(balleImg.getBitmap(), balle.getRX(), balle.getRY(), p);
		canvas.drawBitmap(barreImg.getBitmap(), joueur.getRX(), joueur.getRY(), p);
		canvas.drawBitmap(barreImg.getBitmap(), ai.getRX(), ai.getRY(), p);
		
		//Affichage des scores et du nombre de coups
		canvas.drawText("Score : " + score[0] + " - " + score[1], 10, 55, p);
		canvas.drawText("Nombre de coup : " + nbCoups, 10, 110, p);
		
		//Le jeu avance
		boucleJeu();
	}
	
	public void demarrerBalle() {
		int vx = 0;
		int vy = -7; // Vers le joueur
		
		balle.setVx(vx);
		balle.setVy(vy);
	}
	
	public void checkBalle() {
		
		//Si la balle tappe le c�t� de la barre du joueur
		if(balle.getX()+balle.getWidth()/2 >= joueur.getX()-joueur.getWidth()/2
			&& balle.getY()-joueur.getHeight()/2 >= joueur.getY()-joueur.getHeight()/2
			&& balle.getY()+joueur.getHeight()/2 <= joueur.getY()+joueur.getHeight()/2) {
			balle.setVx(- balle.getVx());
			return;
		}
		
		//Si le joueur touche la balle
		if(balle.getY() + balle.getHeight()/2 >= joueur.getY() - joueur.getHeight()/2
			&& balle.getX() + balle.getWidth()/3 >= joueur.getX() - joueur.getWidth()/2 
			&& balle.getX() - balle.getWidth()/3 <= joueur.getX() + joueur.getWidth()/2){
			
			nbCoups++;
			
			float xBalle = balle.getX();
			float xJoueur = joueur.getX();
			
			float newVx = (xJoueur - xBalle) * 0.10f;
			
			balle.setVx(newVx);
			balle.setVy(- balle.getVy());
			balle.accelerer();
			
		//Si l'ia touche la balle
		} else if ((balle.getY() - balle.getHeight()/2 <= ai.getY() + ai.getHeight()/2
			&& balle.getX() + balle.getWidth()/3 >= ai.getX() - ai.getWidth()/2 
			&& balle.getX() - balle.getWidth()/3 <= ai.getX() + ai.getWidth()/2)) {
			
			float xBalle = balle.getX();
			float xAi = ai.getX();
			
			float newVx = (xAi - xBalle) * 0.10f;
			
			balle.setVx(newVx);
			
			balle.setVy(- balle.getVy());
			balle.accelerer();
		}
		
		//Collision sur les murs
		if(balle.getX() - balle.getWidth()/2 <= 0 
			|| balle.getX() + balle.getWidth()/2 >= getWidth()) {
			balle.setVx(- balle.getVx());
			
			if(balle.getX() - balle.getWidth()/2 <= 0) {
				balle.setX(balle.getWidth()/2);
			}
		}
		
	}
		
	
	/**
	 * 
	 * @return 0 si la balle n'est pas sortie
	 * 		1 si le joueur a gagn� la manche
	 * 		2 si l'ia a gagn�
	 */
	public int mancheFinie() {
		if(balle.getY()-balle.getHeight()/4 <= 0) {
			return 2;
		} else if(balle.getY()+balle.getHeight()/4 >= getHeight()) {
			return 1;
		}
		
		return 0;
	}
	
	public void checkBarres() {
		checkBarre(joueur);
		checkBarre(ai);
	}
	
	public void checkBarre(Barre b) {
		if(b.getX() <= b.getWidth()/2) {
			b.setX(b.getWidth()/2);
		} else if(b.getX() >= getWidth()-b.getWidth()/2) {
			b.setX(getWidth()-b.getWidth()/2);
		}
	}
	
	public void reset() {
		init = true;
	}
	
	public void resume() {
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public void pause() {
		sensorManager.unregisterListener(this, sensor);
	}
	
	public int[] getScore() {
		return score;
	}
	
	public int getMeilleurNbCoup() {
		return nbCoupsMax;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		vx = values[0];
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
