package fhlr.ponguorino;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;


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
					initFichier(f);
				}
			} catch (IOException e) {
				//Toast.m
			}
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

	public void jouer(View view) {
		Intent intent = new Intent(this, Pong.class);
		startActivity(intent);
		//TODO: Renvoyer le score 
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
	
	public void initFichier(File file) {
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			String str = "best:0\n";
			str += "last:0\n";
			str += "tenlast:0\n";
			out.write(str.getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
