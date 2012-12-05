package model;

import java.util.ArrayList;
import java.util.Date;

public class Conversation 
{
	private String phoneNumber;
	private ArrayList<Message> outbox;
	private ArrayList<Message> inbox;
	private Contact contact;
	private String content;
	private Date date;
	
	public Conversation(String phoneNumber, Date date) 
	{
		this.phoneNumber = phoneNumber;
		this.date = date;
		this.inbox = new ArrayList<Message>();
		this.outbox = new ArrayList<Message>();
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	public Date getDate()
	{
		return date;
	}

	public ArrayList<Message> getInbox()
	{
		return new ArrayList<Message>(inbox);
	}

	public ArrayList<Message> getOutbox() 
	{
		return new ArrayList<Message>(outbox);	
	}
	
	public Contact getContact() 
	{
		return contact;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void addMessage(boolean sent, Message theMessage) {
		if (sent) {
			if (!this.outbox.contains(theMessage))
			this.outbox.add(theMessage);
		} else {
			if (!this.inbox.contains(theMessage))
				this.inbox.add(theMessage);			
		}
	}
			
	public void createMessage(String content, boolean sent) 
	{
		Message NewMessage = new Message(new Date(),"hej",false);
		addMessage(sent, NewMessage);
	}
}
