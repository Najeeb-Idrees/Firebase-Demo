package com.example.najeeb.firebase.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by 123 on 07-Sep-17.
 */

@IgnoreExtraProperties
public class User
{
	public String name;
	public String email;

	// Default constructor required for calls to
	public User()
	{
	}

	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
}
