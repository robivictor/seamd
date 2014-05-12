package com.indigitous.seamd;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class LandingPage extends TabActivity implements OnTabChangeListener{

	public static TabHost tabHost; // for navigation purpose
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		   setContentView(R.layout.tabs);
		   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		   
			tabHost = getTabHost(); 
	 
			// view tab
			Intent intentShare = new Intent().setClass(this, ShareActivity.class);
			 TabSpec share = tabHost
			  .newTabSpec("Share")
			  .setIndicator("Share")
			  .setContent(intentShare);
	 
			// share tab
			Intent intentView = new Intent().setClass(this, ViewPostsActivity.class);
			TabSpec view = tabHost
			  .newTabSpec("View")
			  .setIndicator("View")
			  .setContent(intentView);
	 
			// register tab
			/*Intent intentAdmin = new Intent().setClass(this, AdminActivity.class);
			TabSpec admin = tabHost
			  .newTabSpec("Admin")
			  .setIndicator("Admin")
			  .setContent(intentAdmin);*/
		
	 
			// add all tabs 
			tabHost.addTab(view);
			tabHost.addTab(share);
			//tabHost.addTab(admin);
			 
			//set Profile tab as default (zero based)
			tabHost.setCurrentTab(2);
			tabHost.setOnTabChangedListener(this);
	
		}

	@Override
	public void onTabChanged(String tabId) {
		int i = tabHost.getCurrentTab();
		
		if(i == 1) // 
		{

		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
    

}
