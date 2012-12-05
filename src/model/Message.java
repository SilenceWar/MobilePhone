package model;

import java.util.Date;

public class Message 
{
	
	private Date dateTime;
	private String content;
	private boolean read;
	
	public void setDateTime() 
	{
		this.dateTime = dateTime;
	}

	public Date getDateTime() 
	{
		return dateTime;
	}
	
	public void setContent() 
	{
		this.content = content;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setRead() 
	{
		this.read = read;
	}
	
	public boolean getRead() 
	{
		return read;
	}
}
