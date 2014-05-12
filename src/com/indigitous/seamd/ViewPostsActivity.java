package com.indigitous.seamd;

import java.security.PublicKey;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.loopj.android.http.*;

public class ViewPostsActivity extends Activity {
	
	private ListView newsListView; // the list view
	private ArrayList<String> viewItems; // the items that goes in the list
	private ArrayAdapter<String> adapter; // adapter to populate the list
		
	String jsonFile;
	
	JSONObject[] PostsJSONObject; // the rate data in array form
	String[] LocationText; // the rate data in array form
	
	//String jUrl = "https://api.crowdmap.com/v1/posts?apikey=AFHQNLeWxHZUMvXrT42a64a1696d240d1297de4c5e47aefd203a7d307";
	String jUrl = "https://api.crowdmap.com/v1/maps/5398/posts/";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviews);
		newsListView = (ListView) findViewById(R.id.NewListView);
		
		viewItems = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, R.layout.list_item, viewItems);
		newsListView.setAdapter(adapter);
		
		newsListView.setOnItemClickListener(new OnItemClickListener() { // listens for the list touch event
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Intent intent = new Intent(ViewPostsActivity.this, ViewDetailsActivity.class);
				intent.putExtra("description", (viewItems.get(arg2))); // pass the country name to RateCalculatorActivity
				startActivity(intent);
						
			}
        });
		
		// if there is a stored data
		if(PreferenceManager.getDefaultSharedPreferences(this).getString("storage", "nothing")!="nothing")
		{
			try {
				PopulateList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	
	
	private void PopulateList() throws Exception {
   //  while(){
    	  
   //   }
        adapter.notifyDataSetChanged();
}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) // add options button for updating 
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.layout.optionsmenu, menu);
	        return true;
	    }
	 
	    @Override
	    public boolean onOptionsItemSelected (MenuItem item)
	    {
	    	    	
	    	AsyncHttpClient client = new AsyncHttpClient();
	    	RequestParams params = new RequestParams();
	    	params.add("map_id", "5398");
	    	client.get(jUrl,params,new AsyncHttpResponseHandler() {
	    	    @Override
	    	    public void onSuccess(String response) {
	    	    
	    	    	
	    	    	try {
						JSONObject myj = new JSONObject(response);
						JSONArray postsArray = myj.getJSONArray("posts");
					   
					    for (int i = 0; i < 3; i++) {
					    	JSONObject p =(JSONObject) postsArray.get(i); 
						    viewItems.add(p.getString("message"));
						   adapter.notifyDataSetChanged();
					    }
					    
											
						
											
						
						JSONObject firstpost = (JSONObject) postsArray.get(0);
						
					 
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    	
	    	    	
	    	    	
	    	    }
	    	    @Override
	    	    public void onFailure(int arg0, Header[] arg1, byte[] arg2,
	    	    		Throwable arg3) {
	    	    	Toast.makeText(getApplicationContext(), 
	                        "Failed", Toast.LENGTH_LONG).show();
	    	    }
	    	
	    	});
	       
			
	             return true;
	    }
	
	
}