package fhlr.ponguorino;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Accueil extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getIntent().getBooleanExtra("EXIT", false)) {
			finish();
			return;
		}

		setContentView(R.layout.activity_accueil);
		
		if( getIntent().getBooleanExtra("EXIT", false)){
		    finish();
		}
		

		String externalStorageState = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
			try {
				File f = new File(this.getExternalFilesDir("ponguorino"), "pong.data");
				if(!f.exists()) {
					f.createNewFile();
				}
			} catch (IOException e) {
				Toast.makeText(this, "Impossible d'accéder à la mémoire externe", Toast.LENGTH_LONG).show();		
			}
		}
		
		TextView tv = (TextView) findViewById(R.id.dernier_score);
		tv.setText(tv.getText()+"");
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

	public void jouer(View view) {
		Intent intent = new Intent(this, Pong.class);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode == 0 && resultCode == RESULT_OK) {
			TextView tv = (TextView) findViewById(R.id.dernier_score); 
			tv.setText("Dernier score : " + data.getData().toString());
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void afficherScores(View view) {
		Intent intent = new Intent(this, Scores.class);
		startActivity(intent);
	}

	public void quitter(View view) {
		Intent intent = new Intent(this, Accueil.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
	}
	
}
