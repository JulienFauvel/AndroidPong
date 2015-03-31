package fhlr.ponguorino;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class Pong extends Activity {

	private Jeu jeu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pong);

		// Permet d'empêcher le téléphone/tablette de se mettre en veille si on
		// ne touche pas l'écran
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		jeu = new Jeu(this);
		setContentView(jeu);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

}
