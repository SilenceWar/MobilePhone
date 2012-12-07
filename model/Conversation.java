package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Conversation implements Comparable<Conversation>
{
	private DateFormat dateFormat;
	private String phoneNumber;
	private ArrayList<Message> outbox;
	private ArrayList<Message> inbox;
	private Contact contact;
	private Date date;
	
	public Conversation(String phoneNumber) 
	{
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		this.phoneNumber = phoneNumber;
		this.date = new Date();
		this.inbox = new ArrayList<Message>();
		this.outbox = new ArrayList<Message>();
	}
	public Conversation(Contact contact)
	{
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		this.contact = contact;
		this.phoneNumber = this.contact.getPhoneNumber();
		this.date = new Date();
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
	public String getDateString()
	{
		return this.dateFormat.format(this.date);
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
	
	/**
	 * Adds message to inbox
	 * @param theMessage
	 */
	public void addToInbox(Message theMessage)
	{
		boolean caught = false;
		for (int i = 0; i < inbox.size(); i++)
			if (theMessage.compareTo(inbox.get(i)) < 0) {
				inbox.add(i, theMessage);
				caught = true;
				break;
			}
		if (!caught)
			inbox.add(theMessage);
	}
	/**
	 * Adds message to outbox
	 * @param theMessage
	 */
	public void addToOutbox(Message theMessage)
	{
		boolean caught = false;
		for (int i = 0; i < outbox.size(); i++) 
			if (theMessage.compareTo(outbox.get(i)) < 0) {
				inbox.add(i, theMessage);
				caught = true;
				break;
			}
		if (!caught)
			outbox.add(theMessage);
	}
	public void addMessage(Message theMessage) {
		if (this.outbox.size() < 1) {
			setPhoneNumber(theMessage.fromNumber());
			this.inbox.add(theMessage);
			return;
		} else if (!this.inbox.contains(theMessage))
			this.inbox.add(theMessage);
		else if (!this.outbox.contains(theMessage))
			this.outbox.add(theMessage);
		
	}
	
	public Message createMessage(String content, String fromNumber, boolean incomingMessage) 
	{
		Message newMessage = new Message(content, fromNumber);
		if (incomingMessage)
			addToInbox(newMessage);
		else
			addToOutbox(newMessage);
		return newMessage;
	}
	public Message createMessage(String content, Contact contact, boolean incomingMessage)
	{
		if (contact == null) {
			System.out.println("Contact points to null.");
			return null;
		}
		Message newMessage = new Message(content, contact);
		if (incomingMessage)
			addToInbox(newMessage);
		else
			addToOutbox(newMessage);
		return newMessage;
	}
	
	@Override
	public int compareTo(Conversation conversation) {
		if (conversation == null) {
			System.out.println("compareTo(): Conversation points to null.");
			return 0;
		}
		return this.getDate().compareTo(conversation.getDate());
	}
}
