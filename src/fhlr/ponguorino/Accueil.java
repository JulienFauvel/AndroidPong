package fhlr.ponguorino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Accueil extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getIntent().getBooleanExtra("EXIT", false)) {
			finish();
			return;
		}

		setContentView(R.layout.activity_accueil);
	}

	public void jouer(View view) {
		
	}

	public void afficherRegles(View view) {

	}

	public void quitter(View view) {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
	}

}
