package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Call implements Comparable<Call>
{
	private DateFormat timeFormat;
	private DateFormat dateFormat;
	private Date dateTime;
	private int duration;
	private String number;
	private Contact contact;
	
	public Call(Date dateTime, int duration, String number) 
	{
		this.timeFormat = new SimpleDateFormat("HH:mm:ss");
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTime = dateTime;
		this.number = number;
		this.duration = duration;
	}

	public String getDateString()
	{
		return this.dateFormat.format(this.dateTime);
	}
	public Date getDateTime() 
	{
		return dateTime;
	}
	public String getTime()
	{
		return this.timeFormat.format(this.dateTime);
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