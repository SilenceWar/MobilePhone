package service;

import java.util.ArrayList;

import model.Contact;
import model.Conversation;
import model.Message;
import model.Phone;

public abstract class Service
{
	public static Conversation createConversation (Phone phone, String phoneNumber)
	{
		Conversation conversation = new Conversation(phoneNumber);
		phone.addConversation(conversation);
		return conversation;
	}
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
	 * Searches specified phone's contact list for name given
	 * @param phone
	 * @param name
	 * @return
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
	public static ArrayList<Contact> searchContacts(Phone phone, String searchPhrase)
	{
		ArrayList<Contact> searchVolume = phone.getContacts();
		ArrayList<Contact> result = new ArrayList<Contact>();
		
		for (Contact contact: searchVolume)
			if (contact.getName().contains(searchPhrase))
				result.add(contact);
		return result;
	}
	
	public static void sendMessage (Phone phone, String number, String content)
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
		conversation.createMessage(content, number);
	}
	public static void changeScreenLock (boolean status)
	{
		// TODO Ikke helt sikker på om den her metode skal bruges? -- Henrik
	}
	public static void callNumber (String number)
	{
		// TODO Heller ikke helt sikker her -- Henrik
	}
}
