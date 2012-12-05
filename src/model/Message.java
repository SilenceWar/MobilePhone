package model;

import java.util.Date;

public class Message implements Comparable<Message>
{
	private Date dateTime;
	private String content;
	private boolean read;
	
	public Message(Date dateTime, String content, boolean read) 
	{
		this.dateTime = dateTime;
		this.content = content;
		this.read = read;
	}
	
	public void setDateTime(Date dateTime) 
	{
		this.dateTime = dateTime;
	}

	public Date getDateTime() 
	{
		return dateTime;
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
			System.out.println("compareTo(): Message points to null.");
			return 0;
		}
		return this.getDateTime().compareTo(message.getDateTime());
	}
}
