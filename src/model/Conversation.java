package model;

import java.util.ArrayList;

public class Conversation 
{

	private String phoneNumber;
	private ArrayList<Message> messagesSent = new ArrayList<Message>();
	private ArrayList<Message> messagesReceived = new ArrayList<Message>();
	private Contact contact;

	public void setPhoneNumber() 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public ArrayList<Message> getMessagesReceived()
	{
		return new ArrayList<Message>(messagesReceived);
	}

	public ArrayList<Message> getMessagesSent() 
	{
		return new ArrayList<Message>(messagesSent);	
	}
	
	public Contact getContact() 
	{
		return contact;
	}
	
	public void setContact(Contact contact)
	{
		this.contact = contact;
		//ArrayList add her yaikz!
	}
	
	public void createMessage(content) 
	{
		
	}


}
