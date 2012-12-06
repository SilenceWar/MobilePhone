package model;

import java.util.ArrayList;

public class Phone
{
	private String number;
	private ArrayList<Call> calls;
	private ArrayList<Contact> contacts;
	private ArrayList<Conversation> conversations;
	
	public Phone (String number)
	{
		this.number = number;
		this.calls = new ArrayList<Call>();
		this.contacts = new ArrayList<Contact>();
		this.conversations = new ArrayList<Conversation>();
	}
	
	public Conversation conversationExists(String number)
	{
		if (this.conversations == null)
			return null;
		
		for (Conversation conversation: conversations)
			if (conversation.getPhoneNumber().equals(number))
				return conversation;
		
		return null;
	}
	
	/**
	 * @return count of unread messages
	 */
	public int unreadMessages()
	{
		int unreadMessages = 0;
		if (this.conversations == null) return unreadMessages;
		
		for (Conversation conversation: this.conversations)
			for (Message message: conversation.getInbox())
				if (message.getRead())
					unreadMessages++;
		return unreadMessages;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return the calls
	 */
	public ArrayList<Call> getCalls() {
		return calls;
	}
	
	/**
	 * Adds new call to chronically sorted position in calls
	 * @param call to add
	 */
	public void addCall(Call call)
	{
		boolean caught = false;
		for (int i = 0; i < calls.size(); i++) {
			if (call.compareTo(calls.get(i)) < 0) {
				calls.add(i, call);
				caught = true;
				break;
			}
		}
		if (!caught)
			calls.add(call);
	}
	
	
	/**
	 * @return the contacts
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	
	/**
	 * Adds new contact to alphabetically sorted position in contacts
	 * @param contacts to add
	 */
	public void addContact(Contact contact)
	{
		boolean caught = false;
		for (int i = 0; i < contacts.size(); i++) {
			if (contact.compareTo(contacts.get(i)) < 0) {
				contacts.add(i, contact);
				caught = true;
				break;
			}
		}
		if (!caught)
			contacts.add(contact);
	}
	
	/**
	 * @return the conversations
	 */
	public ArrayList<Conversation> getConversations() {
		return conversations;
	}
	
	/**
	 * Adds new conversation to chronically sorted position in conversations
	 * @param conversation to add
	 */
	public void addConversation(Conversation conversation)
	{
		boolean caught = false;
		for (int i = 0; i < conversations.size(); i++) {
			if (conversation.compareTo(conversations.get(i)) < 0) {
				conversations.add(i, conversation);
				caught = true;
				break;
			}
		}
		if (!caught)
			conversations.add(conversation);
	}
	
	public Conversation hasConversation(Message message)
	{
		// TODO metode som hvis der allerede er en Conversation fra modtageren, returnerer denne Conversation
		return null;
	}
}
