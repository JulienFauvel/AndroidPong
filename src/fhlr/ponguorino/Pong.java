package fhlr.ponguorino;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
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
		
		// Permet d'emp�cher le t�l�phone/tablette de se mettre en veille si on
		// ne touche pas l'�cran
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		TextView text = (TextView) findViewById(R.id.appuyer_jouer);
		text.setOnClickListener(this);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	Intent intent = new Intent();
	    	String scores = jeu.getScore()[0] + " - " + jeu.getScore()[1];
	    	intent.setData(Uri.parse(scores));
	    	Util.ecrireFichier(this, String.valueOf(jeu.getMeilleurNbCoup()));
	    	setResult(RESULT_OK, intent);			
	    	finish();
	    	
	    	return true;
	    }
	    return super.onKeyDown(keyCode, event);
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
