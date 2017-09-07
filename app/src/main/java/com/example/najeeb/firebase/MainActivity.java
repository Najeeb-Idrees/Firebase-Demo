package com.example.najeeb.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.najeeb.firebase.firebase.FBAuth;
import com.example.najeeb.firebase.firebase.FBRealTimeDb;
import com.example.najeeb.firebase.firebase.FBStorage;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String token = FirebaseInstanceId.getInstance().getToken();

		// Log
		Log.d("FCM Token", token + " ");

		findViewById(R.id.btn_db).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startActivity(new Intent(getApplicationContext(), FBRealTimeDb.class));
			}
		});

		findViewById(R.id.btn_auth).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startActivity(new Intent(getApplicationContext(), FBAuth.class));
			}
		});

		findViewById(R.id.btn_storage).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startActivity(new Intent(getApplicationContext(), FBStorage.class));
			}
		});
	}
}
