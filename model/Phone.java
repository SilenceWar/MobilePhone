package model;

import java.util.ArrayList;

public class Phone
{
	private String number;
	private ArrayList<Call> incoming;
	private ArrayList<Call> outgoing;
	private ArrayList<Contact> contacts;
	private ArrayList<Conversation> conversations;
	
	public Phone (String number)
	{
		this.number = number;
		this.incoming = new ArrayList<Call>();
		this.outgoing = new ArrayList<Call>();
		this.contacts = new ArrayList<Contact>();
		this.conversations = new ArrayList<Conversation>();
	}
	
	/**
	 * If a conversation exists with the given number, the conversation is returned
	 * @param number
	 * @return matching conversation
	 */
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
	 * If a contact exists with the given number, the contact is returned
	 * @param number
	 * @return matching contact
	 */
	public Contact contactExists(String number)
	{
		if (this.contacts == null)
			return null;
		
		for (Contact item: contacts)
			if (item.getPhoneNumber().equals(number))
				return item;
		
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
	 * @return calls received
	 */
	public ArrayList<Call> getIncoming() {
		return new ArrayList<Call>(incoming);
	}
	/**
	 * @return calls made
	 */
	public ArrayList<Call> getOutgoing() {
		return new ArrayList<Call>(outgoing);
	}
	
	/**
	 * Adds new call to chronically sorted position in incoming calls
	 * @param call to add
	 */
	private void addIncomingCall(Call call)
	{
		this.incoming.add(0, call);
		/*boolean caught = false;
		for (int i = 0; i < this.incoming.size(); i++) {
			if (call.compareTo(this.incoming.get(i)) < 0) {
				this.incoming.add(i, call);
				caught = true;
				break;
			}
		}
		if (!caught)
			this.outgoing.add(call);*/
	}
	/**
	 * Adds new call to chronically sorted position in incoming calls
	 * @param call to add
	 */
	private void addOutgoingCall(Call call)
	{
		this.outgoing.add(0, call);
		/*boolean caught = false;
		for (int i = 0; i < this.outgoing.size(); i++) {
			if (call.compareTo(this.outgoing.get(i)) < 0) {
				this.outgoing.add(i, call);
				caught = true;
				break;
			}
		}
		if (!caught)
			this.outgoing.add(call);*/
	}
	public Call createCall(String toNumber, boolean outgoingCall) 
	{
		Call newCall;
		if (this.contactExists(toNumber) != null) 
			newCall = new Call(this.contactExists(toNumber));
		else
			newCall = new Call(toNumber);
		
		if (outgoingCall)
			addOutgoingCall(newCall);
		else 
			addIncomingCall(newCall);
		return newCall;
	}
	/**
	 * Adds a call to the phone
	 * @param contact
	 * @param outgoingCall if true, incoming call if false
	 * @return created call
	 */
	public Call createCall(Contact contact, boolean outgoingCall)
	{
		if (contact == null) {
			System.out.println("Contact points to null.");
			return null;
		}
		Call newCall = new Call(contact);
		if (outgoingCall)
			addOutgoingCall(newCall);
		else
			addIncomingCall(newCall);
		return newCall;
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
		for (int i = 0; i < contacts.size(); i++)
			if (contact.compareTo(contacts.get(i)) < 0) {
				contacts.add(i, contact);
				caught = true;
				break;
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
		for (int i = 0; i < conversations.size(); i++)
			if (conversation.compareTo(conversations.get(i)) < 0) {
				conversations.add(i, conversation);
				caught = true;
				break;
			}
		if (!caught)
			conversations.add(conversation);
	}
	
	/**
	 * Removes given conversation from the ArrayList
	 * @param conversation to add
	 */
	public void deleteConversation(Conversation conversation)
	{
		if (conversations.contains(conversation)) 
			conversations.remove(conversation);
	}
	
	/**
	 * Merges list of incoming and outgoing calls
	 * @return calls
	 */
	public ArrayList<Call> getCalls() {
		ArrayList<Call> newArray = new ArrayList<>();
		int i1 = 0;
		int i2 = 0;
		while(i1<this.incoming.size() && i2<this.outgoing.size()) {
			if (this.incoming.get(i1).compareTo(this.outgoing.get(i2)) <= 0) {
				newArray.add(this.incoming.get(i1));
				i1++;
			}
			else {
				newArray.add(this.outgoing.get(i2));
				i2++;
			}
		}
		while (i1 < this.incoming.size()) { 
			newArray.add(this.incoming.get(i1));
			i1++;
		}
		while (i2 < this.outgoing.size()) { 
			newArray.add(this.outgoing.get(i2));
			i2++;
		}
		return newArray;
	}
}
