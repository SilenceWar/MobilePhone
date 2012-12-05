package model;

import java.util.Date;
import java.util.ArrayList;
//skal muligvis ikke bruges her ^^

public class Call 
{
	
	private Date dateTime;
	private int duration;
	private String number;
	private Contact contact;

	public void setDateTime() 
	{
		this.dateTime = dateTime;
	}

	public Date getDateTime() 
	{
		return dateTime;
	}

	public void setDuration() 
	{
		this.duration = duration;	
	}

	public int getDuration() 
	{
		return duration;
	}

	public void setNumber()	
	{
		this.number = number;
	}

	public String getNumber() 
	{
		return number;
	}
	
	public void setContact(Contact contact)
	{
		this.contact = contact;
		contacts.add
	}
	
	public Contact getContact()
	{
		return contact;
	}

}