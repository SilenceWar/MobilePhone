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
	
	public Call(String number)
	{
		this.timeFormat = new SimpleDateFormat("HH:mm:ss");
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTime = new Date();
		this.number = number;
		this.duration = 0;
	}
	public Call(Contact contact)
	{
		this.timeFormat = new SimpleDateFormat("HH:mm:ss");
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTime = new Date();
		this.number = contact.getPhoneNumber();
		this.duration = 0;
	}

	public String getDateString()
	{
		return this.dateFormat.format(this.dateTime);
	}
	/**
	 * @return date & time in formatted string
	 */
	public Date getDateTime() 
	{
		return dateTime;
	}
	/**
	 * @return time in formatted string
	 */
	public String getTime()
	{
		return this.timeFormat.format(this.dateTime);
	}
	
	/**
	 * @return duration
	 */
	public int getDuration() 
	{
		return duration;
	}
	/**
	 * @param duration
	 */
	public void setDuration(int time)
	{
		this.duration = time;
	}

	public String getNumber() 
	{
		return number;
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