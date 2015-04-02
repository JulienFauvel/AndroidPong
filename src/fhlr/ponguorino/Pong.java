package fhlr.ponguorino;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

public class Pong extends Activity implements OnClickListener {

	private Jeu jeu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pong);
		
		// Permet d'empêcher le téléphone/tablette de se mettre en veille si on
		// ne touche pas l'écran
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		TextView text = (TextView) findViewById(R.id.appuyer_jouer);
		text.setOnClickListener(this);
		
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

	@Override
	public void onClick(View v) {
		jeu = new Jeu(this);
		setContentView(jeu);
	}

}
