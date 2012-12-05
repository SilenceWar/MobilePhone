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
	
	public void receiveMessage (String number, String content)
	{
		
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
	 * @param call to add
	 */
	public void addCall(Call call) {
		if (call != null)
			this.calls.add(call);
		else
			System.out.println("Call points to null.");
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
	 * @param conversation to add
	 */
	public void addConversation(Conversation conversation) {
		if (conversation != null)
			this.conversations.add(conversation);
		else
			System.out.println("Conversation points to null.");
	}
}
