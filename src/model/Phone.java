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
			for (Message message: conversation.getMessagesReceived())
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
	/*public void addContact(Contact contact) {
		if (contact != null)
			this.contacts.add(contact);
		else
			System.out.println("Contact points to null.");
	}*/
	
	/**
	 * Adds new contact to alphabetically sorted position in contacts
	 * @param contacts to add
	 */
	public void addContact(Contact newContact)
	{
		if (contacts == null) {
			contacts.add(newContact);
			return;
		}
		
		// If newContact is first...
		if (newContact.compareTo(contacts.get(0)) < 0) {
			ArrayList<Contact> tempArray = new ArrayList<Contact>();
			tempArray.add(newContact);
			for (Contact tempContact: contacts)
				tempArray.add(tempContact);
			contacts = tempArray;
		}
		// If newContact is last...
		else if (newContact.compareTo(contacts.get(contacts.size()-1)) > 0)
			contacts.add(newContact);
		// And otherwise...
		else
			for (int i = 1; i < contacts.size(); i++)
				if (newContact.compareTo(contacts.get(i)) < 0 && newContact.compareTo(contacts.get(i-1)) > 0)
					contacts.add(i, newContact);
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
