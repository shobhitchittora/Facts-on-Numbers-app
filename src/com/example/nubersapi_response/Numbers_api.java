package com.example.nubersapi_response;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Numbers_api extends Activity {
	static String number ;
	Button send;
	TextView textview;
	EditText edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numbers_api);
		
		send=(Button)findViewById(R.id.button1);
		textview=(TextView)findViewById(R.id.textView1);
		edit=(EditText)findViewById(R.id.editText1);
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				number = edit.getText().toString();
				new HttpGetTask().execute();
			}
		});
		
	}
	
	private class HttpGetTask extends AsyncTask<Void, Void, String>{

		
		private final  String URL = "http://numbersapi.com/"+ number ; 
		AndroidHttpClient mclient = AndroidHttpClient.newInstance(""); 
		
	@Override
		protected String doInBackground(Void... v) {
			// TODO Auto-generated method stub
		
		 HttpGet request= new HttpGet(URL);
		 ResponseHandler<String> responsehandler = new BasicResponseHandler();
		
		 try {
			return mclient.execute(request, responsehandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(null != mclient)
				mclient.close();
			
			textview.setText(result);	
			
			
		}
	
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_numbers_api, menu);
		return true;
	}	
}
