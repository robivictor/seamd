package com.indigitous.seamd;


import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ShareActivity extends Activity implements OnClickListener{

	WebView webviewMap;
	EditText description_et;
	//radio stuff
	RadioButton church_rb;
	RadioButton desipleship_rb;
	RadioButton training_rb;
	
	Button place_bt;
	Button capture_bt;
	Button reset_bt;
	Button submit_bt;
	
	// data to be sent
	String Description_string;
	String catagory_string;
	String Location_string;
	
	
	int TAKE_PHOTO_CODE = 0;
	public static int count=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_layout);
	
		place_bt = (Button) findViewById(R.id.button_place);
		capture_bt = (Button) findViewById(R.id.button_capture);
		reset_bt = (Button) findViewById(R.id.button_reset);
		submit_bt = (Button) findViewById(R.id.button_submit);
		
		webviewMap = (WebView) findViewById(R.id.webView_map);
		
		description_et = (EditText) findViewById(R.id.editText_description);
	
		 church_rb = (RadioButton) findViewById(R.id.radioButton_church);
		 desipleship_rb = (RadioButton) findViewById(R.id.radioButton_disipleship);
		 training_rb = (RadioButton) findViewById(R.id.radioButton_training);
		
		description_et.setOnClickListener(this);
		place_bt.setOnClickListener(this);
		capture_bt.setOnClickListener(this);
		reset_bt.setOnClickListener(this);
		submit_bt.setOnClickListener(this);
		
		church_rb.setOnClickListener(this);
		desipleship_rb.setOnClickListener(this);
		training_rb.setOnClickListener(this);

		webviewMap.getSettings().setJavaScriptEnabled(true);
		webviewMap.setWebViewClient(new WebViewClient());
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == place_bt.getId()){ // get the place on the map
			
			webviewMap.loadUrl("http://maps.google.com/?ll=567,79879q=hilton");

		}
		else if (arg0.getId() == capture_bt.getId()){ // capture image
			//here,we are making a folder named picFolder to store pics taken by the camera using this application
            final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/SEAMD/"; 
            File newdir = new File(dir); 
            newdir.mkdirs();
            // here,counter will be incremented each time,and the picture taken by camera will be stored as 1.jpg,2.jpg and likewise.
            count++;
            String file = dir+count+".jpg";
            File newfile = new File(file);
            try {
                newfile.createNewFile();
            } catch (IOException e) {}       

            Uri outputFileUri = Uri.fromFile(newfile);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
		}
		else if (arg0.getId() == reset_bt.getId()){ // reset everything
			 Description_string = "";
			 catagory_string = "";
			 Location_string = "";
			 
			 description_et.setText("Description");
			 
			 church_rb.setChecked(false);
			 desipleship_rb.setChecked(false);
			 training_rb.setChecked(false);
		}
		else if (arg0.getId() == submit_bt.getId()){ // submit location
			
		}
		else if (arg0.getId() == description_et.getId()){ // write description
				
			 final AlertDialog.Builder alert = new AlertDialog.Builder(this);
			 
			 
			 
			    final EditText input = new EditText(this);
			    input.setLines(6);
			    input.setText(Description_string);
			    alert.setView(input);
			    alert.setTitle("Description");
			    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int whichButton) {
			        	Description_string = input.getText().toString().trim();
			             description_et.setText("Description :  "+ Description_string);
			        }
			    });

			    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int whichButton) {
			            dialog.cancel();
			        }
			    });
			    alert.show(); 
			    
		}
		else if (arg0.getId() == church_rb.getId()){ // submit location
			desipleship_rb.setChecked(false);
			training_rb.setChecked(false);
		}
		else if (arg0.getId() == desipleship_rb.getId()){ // submit location
			church_rb.setChecked(false);
			training_rb.setChecked(false);	
		}
		else if (arg0.getId() == training_rb.getId()){ // submit location
			church_rb.setChecked(false);
			desipleship_rb.setChecked(false);	
		}
		
	}
}
