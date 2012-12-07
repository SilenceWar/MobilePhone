package service;

import java.util.ArrayList;

import model.Call;
import model.Contact;
import model.Conversation;
import model.Phone;

public abstract class Service
{
	public static Call makeCall(Phone phone, boolean incoming)
	{
		if (phone == null) {
			System.out.println("Phone points to null.");
			return null;
		}
		
		if (incoming) {
			// TODO
		}
		return null;
	}
	/**
	 * Creates and adds new contact to the address book of the phone
	 * @param phone
	 * @param name
	 * @param phoneNumber
	 * @return created contact
	 */
	public static Contact createContact (Phone phone, String name, String phoneNumber)
	{
		if (phone == null) {
			System.out.println("Phone points to null.");
			return null;
		}
		if (name.length() < 1 || phoneNumber.length() < 8) {
			System.out.println("Name is empty or number is too short.");
			return null; 
		}
		Contact contact = new Contact(name, phoneNumber);
		phone.addContact(contact);
		return contact;
	}
	
	/**
	 * Searches contact list for a given name
	 * @param phone
	 * @param name
	 * @return match if found, null if not found
	 */
	public static Contact findContact (Phone phone, String name)
	{
		ArrayList<Contact> contacts = phone.getContacts();
		int left = 0;
		int middle = -1;
		int right = contacts.size()-1;
		
		while (left <= right)
		{
			middle = (left + right) / 2;
			Contact candidate = contacts.get(middle);
			if (candidate.getName().compareTo(name) == 0)
				return contacts.get(middle);
			else if (candidate.getName().compareTo(name) > 0)
				right = middle -1;
			else if (candidate.getName().compareTo(name) < 0)
				left = middle+1;
		}
		System.out.println("Contact not found.");
		return null;
	}
	/**
	 * Searches contact list of the phone for a given search phrase
	 * @param phone to search
	 * @param searchPhrase to search for
	 * @return list of matches found
	 */
	public static ArrayList<Contact> searchContacts(Phone phone, String searchPhrase)
	{
		ArrayList<Contact> searchVolume = phone.getContacts();
		ArrayList<Contact> result = new ArrayList<Contact>();
		
		for (Contact contact: searchVolume)
			if (contact.getName().contains(searchPhrase) || contact.getName().toLowerCase().contains(searchPhrase))
				result.add(contact);
		return result;
	}
	
	/**
	 * Sends message to phone without contact association
	 * @param phone
	 * @param number
	 * @param content message
	 * @param outgoing if true, incoming if false
	 */
	public static void sendMessage (Phone phone, String number, String content, boolean outgoing)
	{
		if (number.length() < 8) {
			System.out.println("Number must have at least 8 digits.");
			return;
		}
		if (content.length() < 1) {
			System.out.println("Message can't be empty.");
			return;
		}
		Conversation conversation = phone.conversationExists(number);
		
		if (!(conversation instanceof Conversation)) {
			conversation = new Conversation(number);
			phone.addConversation(conversation);
		}
		conversation.createMessage(content, number, outgoing);
	}
	/**
	 * Sends message to phone with contact association
	 * @param phone
	 * @param contact
	 * @param content message
	 * @param outgoing if true, incoming if false
	 */
	public static void sendMessage (Phone phone, Contact contact, String content, boolean outgoing)
	{
		if (contact.getPhoneNumber().length() < 8) {
			System.out.println("Number must have at least 8 digits.");
			return;
		}
		if (content.length() < 1) {
			System.out.println("Message can't be empty.");
			return;
		}
		Conversation conversation = phone.conversationExists(contact.getPhoneNumber());
		
		if (!(conversation instanceof Conversation)) {
			conversation = new Conversation(contact);
			phone.addConversation(conversation);
		}
		conversation.createMessage(content, contact.getPhoneNumber(), outgoing);
	}
	
	public static void changeScreenLock (boolean status)
	{
		// TODO Ikke helt sikker p� om den her metode skal bruges? -- Henrik
	}
	public static void callNumber (String number)
	{
		// TODO Heller ikke helt sikker her -- Henrik
	}
}
