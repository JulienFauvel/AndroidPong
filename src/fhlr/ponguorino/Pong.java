package fhlr.ponguorino;

import android.app.Activity;
import android.os.Bundle;

public class Pong extends Activity {

	private Jeu jeu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pong);
		jeu = new Jeu(this);
		setContentView(jeu);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

}
