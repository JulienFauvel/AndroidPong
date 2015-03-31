package fhlr.ponguorino;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;

public class Jeu extends Activity {

	private Canvas canvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeu);
		canvas = new Canvas();
	}
	
	
}
