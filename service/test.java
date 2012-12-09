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
		/*//Service service = new Service();
		Phone phone = new Phone("+4529927189");
		//Service.createConversation(phone, "+4529927189",))
		
		Contact henrik = Service.createContact(phone, "Henrik B. N.", "+4529927189");
		Contact benjamin = Service.createContact(phone, "Benjamin", "+4513371337");
		Contact christian = Service.createContact(phone, "Christian Liisberg", "+4590019001");

		Conversation conversation = new Conversation("+4529927189");
		phone.addConversation(conversation);
		Message message = conversation.createMessage("Test", "+4529927189", true);
		System.out.println("\"" + message.getContent() + "\", from " + message.fromName() + " (" + message.fromNumber() + ") on " + message.getDateString());
		message = conversation.createMessage("Test2", henrik, true);
		System.out.println("\"" + message.getContent() + "\", from " + message.fromName() + " (" + message.fromNumber() + ") on " + message.getDateString());
		
		System.out.println(Service.findContact(phone, "Christian Liisberg").getName());
		
	
		System.out.println("\nFinding contact...\n" + Service.findContact(phone, "Christian Liisberg").getName());
		
		System.out.println("\nPrinting contacts...");
		
		for (Contact contact: phone.getContacts())
			System.out.println(contact.getName());
		System.out.println("\nSearching...");
		
		ArrayList<Contact> searchResults = Service.searchContacts(phone, "liis");
		if (searchResults == null)
			System.out.println("No contacts found.");
		else
			for (Contact contact: searchResults)
				System.out.println(contact.getName());
		
		System.out.println("\nAdding messages...");
		
		Service.sendMessage(phone, "+4529927189", "Hej dig, hvordan går det? Hilsen mig!", true);
		Service.sendMessage(phone, "+4529927189", "Det er mig igen. Virker det?", false);
		Service.sendMessage(phone, "12345678", "<font face=\"comic sans\">BUY VIAGRA CHEAP!</font>", true);
		Service.sendMessage(phone, "+4529927180", "Testbesked << hvis den lægger i sin egen Conversation, så duer det!", true);
		
		System.out.println("\nNumber of conversations: " + phone.getConversations().size());
		
		System.out.println("\nPrinting messages...");
		for (Conversation thisConversation: phone.getConversations()) {
			System.out.println("\nConversation with " + thisConversation.getPhoneNumber());
			for (Message thisMessage: thisConversation.getMessages()) {
				System.out.println("From: " + thisMessage.fromName());
				System.out.println("Message: " + thisMessage.getContent());
			}
		}
		
		System.out.println("\n\nPrinting inbox...");
		for (Conversation thisConversation: phone.getConversations()) {
			System.out.println("\nConversation with " + thisConversation.getPhoneNumber());
			for (Message thisMessage: thisConversation.getInbox()) {
				System.out.println("From: " + thisMessage.fromName());
				System.out.println("Message: " + thisMessage.getContent());
			}
		}
		
		System.out.println("\n\nPrinting outbox...");
		for (Conversation thisConversation: phone.getConversations()) {
			System.out.println("\nConversation with " + thisConversation.getPhoneNumber());
			for (Message thisMessage: thisConversation.getOutbox()) {
				System.out.println("From: " + thisMessage.fromName());
				System.out.println("Message: " + thisMessage.getContent());
			}
		}*/
	}

}
