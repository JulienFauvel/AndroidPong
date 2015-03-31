package fhlr.ponguorino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Accueil extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
		if( getIntent().getBooleanExtra("EXIT", false)){
		    finish();
		}
	}
	
	/**
	 * Lance le jeu
	 * 
	 * @param button Bouton originaire de l'événement
	 */
	public void jouer(Button button) {
		Intent intent = new Intent(this, Jeu.class);
		startActivity(intent);
	}
	
	/**
	 * Affiche les scores
	 * 
	 * @param button Bouton originaire de l'événement
	 */
	public void scores(Button button) {
		
	}
	
	/**
	 * Quitte l'application
	 * 
	 * @param button Bouton originaire de l'événement
	 */
	public void quitter(Button button) {
		Intent intent = new Intent(this, Accueil.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
		finish();
	}

}
