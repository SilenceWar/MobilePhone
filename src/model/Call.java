package model;

import java.util.Date;

public class Call implements Comparable<Call>
{
	private Date dateTime;
	private int duration;
	private String number;
	private Contact contact;
	
	public Call(Date dateTime, int duration, String number) 
	{
		this.dateTime = dateTime;
		this.number = number;
		this.duration = duration;
	}

	public void setDateTime(Date dateTime) 
	{
		this.dateTime = dateTime;
	}

	public Date getDateTime() 
	{
		return dateTime;
	}

	public void setDuration(int duration) 
	{
		this.duration = duration;	
	}

	public int getDuration() 
	{
		return duration;
	}

	public void setNumber(String number)	
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
	}
	
	public Contact getContact()
	{
		return contact;
	}

	@Override
	public int compareTo(Call call) {
		if (call == null) {
			System.out.println("compareTo(): Call points to null.");
			return 0;
		}
		return this.dateTime.compareTo(call.getDateTime());
	}
}