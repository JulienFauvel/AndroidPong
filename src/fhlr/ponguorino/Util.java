package fhlr.ponguorino;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

public abstract class Util {

public static String lireFichier(Activity a) {
		
		String externalStorageState = Environment.getExternalStorageState();
		StringBuilder sb = new StringBuilder();
		
		if(Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
			try {
				File f = new File(a.getExternalFilesDir("ponguorino"), "pong.data");
				if(f.exists()) {
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
					int c;
					
					while((c = in.read()) != -1) {
						sb.append(Character.toString((char)c));
					}
					in.close();
					
				} else {
					f.createNewFile();
				}
			} catch (IOException e) {
				Toast.makeText(a, "Impossible de lire sur le périphérique externe", Toast.LENGTH_LONG).show();
			}
		}
		
		return sb.toString();
	}
	
	public static void ecrireFichier(Activity a, String ligne) {
		String externalStorageState = Environment.getExternalStorageState();
		StringBuilder sb = new StringBuilder();
		
		if(Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
			try {
				File file = new File(a.getExternalFilesDir("ponguorino"), "pong.data");
				
				if(file.exists()) {
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, true));
					
					sb.append(ligne+"\n");
					
					out.write(sb.toString().getBytes());
					
					out.close();
					
				}
			} catch (IOException e) {
				Toast.makeText(a, "Impossible d'écrire sur le périphérique externe", Toast.LENGTH_LONG).show();
			}
		}
	}
	
}
