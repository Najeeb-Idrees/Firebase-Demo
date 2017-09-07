package com.example.najeeb.firebase.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.najeeb.firebase.MainActivity;
import com.example.najeeb.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by 123 on 07-Sep-17.
 */

public class FBAuth extends AppCompatActivity
{

	private EditText inputEmail, inputPassword;
	private Button btnSignUp;
	private ProgressBar progressBar;
	private FirebaseAuth auth;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auth_fb);

		//Get Firebase auth instance
		auth = FirebaseAuth.getInstance();

		btnSignUp = (Button) findViewById(R.id.sign_up_button);
		inputEmail = (EditText) findViewById(R.id.email);
		inputPassword = (EditText) findViewById(R.id.password);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		btnSignUp.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				final String email = inputEmail.getText().toString().trim();
				String password = inputPassword.getText().toString().trim();

				if (TextUtils.isEmpty(email))
				{
					Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
					return;
				}

				if (TextUtils.isEmpty(password))
				{
					Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
					return;
				}

				if (password.length() < 6)
				{
					Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
					return;
				}

				progressBar.setVisibility(View.VISIBLE);
				//create user
				//				auth.createUserWithEmailAndPassword(email, password)
				//						.addOnCompleteListener(FBAuth.this, new OnCompleteListener<AuthResult>()
				//						{
				//							@Override
				//							public void onComplete(@NonNull Task<AuthResult> task)
				//							{
				//								Toast.makeText(FBAuth.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
				//								progressBar.setVisibility(View.GONE);
				//								// If sign in fails, display a message to the user. If sign in succeeds
				//								// the auth state listener will be notified and logic to handle the
				//								// signed in user can be handled in the listener.
				//								if (!task.isSuccessful())
				//								{
				//									Log.e("Error", task.getException().toString());
				//									Toast.makeText(FBAuth.this, "User creation failed." + task.getException(),
				//											Toast.LENGTH_SHORT).show();
				//								}
				//								else
				//								{
				//				inputEmail.setText("");
				//				inputPassword.setText("");
				//
				//									Toast.makeText(FBAuth.this, "User has been created successfully!",
				//											Toast.LENGTH_SHORT).show();
				//								}
				//							}
				//						});

				//Authenticate user
				auth.signInWithEmailAndPassword(email, password)
						.addOnCompleteListener(FBAuth.this, new OnCompleteListener<AuthResult>()
						{
							@Override
							public void onComplete(@NonNull Task<AuthResult> task)
							{
								progressBar.setVisibility(View.GONE);
								if (!task.isSuccessful())
								{
									Log.e("Error", task.getException().toString());
									Toast.makeText(FBAuth.this, "Authentication failed." + task.getException(),
											Toast.LENGTH_SHORT).show();
								}
								else
								{
									inputEmail.setText("");
									inputPassword.setText("");
									Toast.makeText(FBAuth.this, "User has been authorized successfully!.",
											Toast.LENGTH_SHORT).show();
								}
							}
						});


			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		progressBar.setVisibility(View.GONE);
	}
}
