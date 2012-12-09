package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message>
{
	private DateFormat timeFormat;
	private DateFormat dateFormat;
	private DateFormat dateTimeFormat;
	private Date dateTime;
	private String fromName;
	private String fromNumber;
	private String content;
	private boolean read;
	
	public Message(String content, String fromNumber) 
	{
		this.timeFormat = new SimpleDateFormat("HH:mm");
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTimeFormat = new SimpleDateFormat("dd/MM HH:mm");
		this.dateTime = new Date();
		this.content = content;
		this.fromName = "Unknown <" + fromNumber + ">";
		this.fromNumber = fromNumber;
		this.read = false;
	}
	public Message(String content, Contact contact)
	{		
		this.timeFormat = new SimpleDateFormat("HH:mm");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateTimeFormat = new SimpleDateFormat("dd/MM HH:mm");
		this.dateTime = new Date();
		this.content = content;
		this.fromName = contact.getName();
		this.fromNumber = contact.getPhoneNumber();
		this.read = false;
	}
	
	public String fromName()
	{
		return this.fromName;
	}
	public String fromNumber()
	{
		return this.fromNumber;
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
	
	public String getDateTimeFormat()
	{
		return this.dateTimeFormat.format(this.dateTime);
	}
	
	public void setContent(String content) 
	{
		this.content = content;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setRead(boolean read) 
	{
		this.read = read;
	}
	
	public boolean getRead() 
	{
		return read;
	}
	

	@Override
	public int compareTo(Message message) {
		if (message == null) {
			return 0;
		}
		return this.getDateTime().compareTo(message.getDateTime());
	}
}
