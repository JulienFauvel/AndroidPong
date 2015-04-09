package fhlr.ponguorino;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Scores extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
	
		charger();
	}
	
	private void charger() {
		
		String meilleurNbCoup = Util.lireFichier(this);
		TextView tv = (TextView) findViewById(R.id.nbCoup);
		tv.setText(meilleurNbCoup);
		
	}
	
	
}
