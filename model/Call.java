package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Call implements Comparable<Call>
{
	private DateFormat timeFormat;
	private DateFormat dateFormat;
	private DateFormat dateTimeFormat;
	private Date dateTime;
	private int duration;
	private String number;
	private Contact contact;
	
	public Call(String number)
	{
		this.timeFormat = new SimpleDateFormat("HH:mm:ss");
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTimeFormat = new SimpleDateFormat("dd/MM HH:mm");
		this.dateTime = new Date();
		this.number = number;
		this.duration = 0;
	}
	public Call(Contact contact)
	{
		this.timeFormat = new SimpleDateFormat("HH:mm:ss");
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTimeFormat = new SimpleDateFormat("dd/MM HH:mm");
		this.dateTime = new Date();
		this.number = contact.getPhoneNumber();
		this.contact = contact;
		this.duration = 0;
	}

	public String getDateTimeString()
	{
		return this.dateTimeFormat.format(this.dateTime);
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
	public String getDuration() 
	{
		int hrs = this.duration / 60 / 60;
		int mins = (this.duration - (hrs*60*60))/60;
		int secs = (this.duration - (hrs*60*60) - (mins*60));
		
		String hours = ""+hrs;
		String minutes = ""+mins;
		String seconds = ""+secs;
		if (hrs < 10) { hours = "0"+hours; } 
		if (mins < 10) { minutes = "0"+minutes; } 
		if (secs < 10) { seconds = "0"+seconds; } 
		return hours+":"+minutes+":"+seconds;
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
			return 0;
		}
		return this.dateTime.compareTo(call.getDateTime());
	}
}