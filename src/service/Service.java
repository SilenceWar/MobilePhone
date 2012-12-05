package service;

import model.Contact;
import model.Phone;

public class Service
{
	public void createContact (Phone phone, String name, String phoneNumber)
	{
		if (phone == null) {
			System.out.println("Phone points to null.");
			return;
		}
		if (name.length() < 1 || phoneNumber.length() < 8) {
			System.out.println("Name is empty or number is too short.");
			return; 
		}
		Contact contact = new Contact(name, phoneNumber);
		phone.addContact(contact);
	}
	public Contact findContact (String number)
	{
		// TODO
		return null;
	}
	public void sendMessage (String number, String content)
	{
		if (number.length() > 7 && content.length() > 0) {
			System.out.println("Number must have at least 8 digits.");
			return;
		}
		if (content.length() > 0) {
			System.out.println("Message can't be empty.");
			return;
		}
		
		// TODO
	}
	public void changeScreenLock (boolean status)
	{
		// TODO
	}
	public void callNumber (String number)
	{
		// TODO
	}
}
