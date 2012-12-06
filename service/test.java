package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Contact;
import model.Conversation;
import model.Message;
import model.Phone;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Service service = new Service();
		Phone phone = new Phone("+4529927189");
		// service.createConversation(phone, "+4529927189",))
		
		Contact henrik = service.createContact(phone, "Henrik B. N.", "+4529927189");
		Contact benjamin = service.createContact(phone, "Benjamin", "+4513371337");
		Contact christian = service.createContact(phone, "Christian Liisberg", "+4590019001");

		Conversation conversation = service.createConversation(phone, "+4529927189");
		Message message = conversation.createMessage("Test", "+4529927189");
		System.out.println("\"" + message.getContent() + "\", from " + message.fromName() + " (" + message.fromNumber() + ") on " + message.getDateString());
		message = conversation.createMessage("Test2", henrik);
		System.out.println("\"" + message.getContent() + "\", from " + message.fromName() + " (" + message.fromNumber() + ") on " + message.getDateString());
		
		System.out.println(service.findContact(phone, "Christian Liisberg").getName());
		
	
		System.out.println("\nFinding contact...\n" + service.findContact(phone, "Christian Liisberg").getName());
		
		System.out.println("\nPrinting contacts...");
		
		for (Contact contact: phone.getContacts())
			System.out.println(contact.getName());
		System.out.println("\nSearching...");
		
		ArrayList<Contact> searchResults = service.searchContacts(phone, "");
		if (searchResults == null)
			System.out.println("No contacts found.");
		else
			for (Contact contact: searchResults)
				System.out.println(contact.getName());
		
		System.out.println("\nAdding messages...");
		
		service.sendMessage(phone, "+4529927189", "Hej dig, hvordan g�r det? Hilsen mig!");
		service.sendMessage(phone, "+4529927189", "Det er mig igen. Virker det?");
		service.sendMessage(phone, "12345678", "<font face=\"comic sans\">BUY VIAGRA CHEAP!</font>");
		
		System.out.println("\nNumber of conversations: " + phone.getConversations().size());
		
		System.out.println("\nPrinting messages...");
		for (Conversation thisConversation: phone.getConversations()) {
			System.out.println("\nConversation with " + thisConversation.getPhoneNumber());
			for (Message thisMessage: thisConversation.getInbox()) {
				System.out.println("From: " + thisMessage.fromName());
				System.out.println("Message: " + thisMessage.getContent());
			}
		}
	}

}
