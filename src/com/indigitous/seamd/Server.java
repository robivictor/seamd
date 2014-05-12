package com.indigitous.seamd;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class Server {
	
	public static int  read;
	public static Activity ma;

	public static void downloadRate() { // download the rates as a string from the url

		new Thread(new Runnable() { // start a new thread so it wont stuck 
			URL connectURL;

			public void run() {
				read = 0;
				StringBuffer b = new StringBuffer();
				InputStreamReader inputreader;
						try {
					int ch;
						connectURL = new URL( 
								"https://api.crowdmap.com/v1/posts?apikey=AFHQNLeWxHZUMvXrT42a64a1696d240d1297de4c5e47aefd203a7d307");// download xml
				

					HttpURLConnection conn = (HttpURLConnection) connectURL
							.openConnection();

					showToast("Update Started ...");

					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setUseCaches(false);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Connection", "Keep-Alive");
					
					InputStream is = conn.getInputStream();
					inputreader = new InputStreamReader(is, "UTF-8");
					
					while ((ch = inputreader.read()) != -1) {
						b.append((char) ch);
					}

					is.close();
					inputreader.close();
					conn.disconnect();
					conn = null;
					System.gc();
	
				
					PreferenceManager.getDefaultSharedPreferences(ma).edit().putString("storage", b.substring(0, b.length()-1)).commit(); 
					
					
				} catch (final Exception e) {
					showToast("Update Failed");
				}

			}

		}).start();
		
	}
	
	private static void showToast(final String text) {
		ma.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(ma, text, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public static void setContext(Activity con) { // show toast on specific activity
		ma = con;
	}

}
