package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.JFrame;

import model.Contact;
import model.Conversation;
import model.Phone;

import service.Service;
import java.util.Random;

public class MainFrame extends JFrame {
	private HomePanel homePanel;
	private ContactsPanel contactsPanel;
	private MessagesPanel messagesPanel;
	private PhonePanel phonePanel;
	private SettingsPanel settingsPanel;
	private NewMessagePanel newMessagePanel;
	private CallPanel callPanel;
	private LoggerPanel loggerPanel;
	private ShowConversationPanel showConversationPanel;
	private CreateContactPanel createContactPanel;
	private ShowContactPanel showContactPanel;
	private IncommingCallPanel incommingCallPanel;
	private Phone thisPhone;
	public Conversation chosenConversation;
	public Contact chosenViewContact;
	public String chosenRecallNumber;
	private String currentPage;	
	
	public MainFrame() {
		this.thisPhone = Service.createPhone("25798315");
		chosenConversation = null;
		chosenViewContact = null;
		
		homePanel = new HomePanel(this, this.thisPhone);
		contactsPanel = new ContactsPanel(this, this.thisPhone);
		messagesPanel = new MessagesPanel(this, this.thisPhone);
		phonePanel = new PhonePanel(this, this.thisPhone);
		settingsPanel = new SettingsPanel(this, this.thisPhone);
		newMessagePanel = new NewMessagePanel(this, this.thisPhone);
		callPanel = new CallPanel(this, this.thisPhone);	
		loggerPanel = new LoggerPanel(this, this.thisPhone);

		showConversationPanel = new ShowConversationPanel(this, this.thisPhone);
		createContactPanel = new CreateContactPanel(this, this.thisPhone);
		showContactPanel = new ShowContactPanel(this, this.thisPhone);
		incommingCallPanel = new IncommingCallPanel(this, this.thisPhone);

		
		this.setSize(350,650);
		this.setLocation(400,50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		java.net.URL imageURL = MainFrame.class.getResource("/images/Phone.png");
		ImageIcon image = new ImageIcon(imageURL);
		JLabel imageLabel = new JLabel(image);
		imageLabel.setSize(350,600);
		
		homePanel.setVisible(false);
		this.add(homePanel);
		this.add(contactsPanel);
		this.add(messagesPanel);
		this.add(phonePanel);
		this.add(settingsPanel);
		this.add(newMessagePanel);
		this.add(callPanel);
		this.add(loggerPanel);
		this.add(showConversationPanel);
		this.add(createContactPanel);
		this.add(showContactPanel);
		this.add(incommingCallPanel);
		
		showPage("home");
		
		this.add(imageLabel);
		this.setVisible(true);
		
		
		this.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=148 && evt.getX()<=215 && evt.getY()>=577 && evt.getY()<=600) {
		        		showPage("home"); // If clicked within the right coordinates of the Home-button of our phone.
		        	}
		        	else if (evt.getX()>=88 && evt.getX()<=111 && evt.getY()>=577 && evt.getY()<=593) {
			        	recieveMessage();
			        }
		    }
		});
		
	}
	
	public void recieveMessage() {
		Random generator = new Random();
		int randMessage = generator.nextInt(5)+1;
		int randPhoneNumber = generator.nextInt(5)+1;
		  
		String message = "";
		String number = "";
		
		switch(randMessage) {
		case 1:
			message = "Hvad skal du i dag?";
			break;
		case 2:
			message = "Det lyder godt nok mærkeligt! \nDet virker her ved mig :-(";
			break;
		case 3:
			message = "Hvad siger du? Er du syg? \nDet er der jo ikke noget at gøre ved desværre";
			break;
		case 4: 
			message = "Er du nu for sent på den igen? \nDet går da snart ikke med mere fravær? \nHvad har du? 25% allerede?";
			break;
		case 5:
			message = "Det er sku godt :-D \nGlæder mig sku også til jul :-P";
			break;
		}
		
		switch(randPhoneNumber) {
		case 1:
			number = "52305249";
			break;
		case 2:
			number = "61785495";
			break;
		case 3:
			number = "27507013";
			break;
		case 4: 
			number = "61206125";
			break;
		case 5:
			number = "65758595";
			break;
		}
		Service.sendMessage(thisPhone, number, message, false); // Dummy button which sends a message to yourself :-)
		showPage(currentPage);
	}
	
	public void showPage(String panel) {
		currentPage = panel;
		
		homePanel.setVisible(false);
		contactsPanel.setVisible(false);
		messagesPanel.setVisible(false);
		phonePanel.setVisible(false);
		settingsPanel.setVisible(false);
		newMessagePanel.setVisible(false);
		callPanel.setVisible(false);
		loggerPanel.setVisible(false);
		showConversationPanel.setVisible(false);
		createContactPanel.setVisible(false);
		showContactPanel.setVisible(false);
		incommingCallPanel.setVisible(false);
				
	switch (panel){
		case "home":
			homePanel.checkNew();
			homePanel.setVisible(true);
			break;
		case "settings":
			settingsPanel.setVisible(true);
			break;
		case "contacts":
			contactsPanel.clearAll();
			contactsPanel.printContacts(this.thisPhone.getContacts());
			contactsPanel.setVisible(true);
			break;
		case "messages":
			messagesPanel.clearAll();
			messagesPanel.printFormattedConversations();
			messagesPanel.setVisible(true);
			break;
		case "newMessage":
			newMessagePanel.clearAll();
			newMessagePanel.setVisible(true);
			break;
		case "newMessageContact":
			newMessagePanel.clearAll();
			newMessagePanel.toContact(chosenViewContact);
			newMessagePanel.setVisible(true); 
			break;
		case "phone":
			phonePanel.clearScreen();
			phonePanel.setVisible(true);
			break;
		case "showConversation":
			showConversationPanel.clearAll();
			showConversationPanel.showConversation(chosenConversation);
			showConversationPanel.setVisible(true);
			break;
		case "call":
			callPanel.startCall(phonePanel.getNumber());
			callPanel.setVisible(true);
			break;
		case "callContact":
			callPanel.startCall(chosenViewContact.getPhoneNumber());
			callPanel.setVisible(true);
			break;
		case "reCallNumber":
			callPanel.startCall(chosenRecallNumber);
			callPanel.setVisible(true);
			break;
		case "callNumber":
			callPanel.startCallIncomming(chosenRecallNumber);
			callPanel.setVisible(true);
			break;
		case "logger":
			loggerPanel.clearAll();
			loggerPanel.printFormattedCalls();
			loggerPanel.setVisible(true);
			break;
		case "createContact":
			createContactPanel.clearAll();
			createContactPanel.setVisible(true);
			break;
		case "showContact":
			showContactPanel.refreshPanel();
			showContactPanel.setVisible(true);
			break;
		case "incommingCall":
			incommingCallPanel.clearAll();
			incommingCallPanel.receiveCall();
			incommingCallPanel.setVisible(true); 
			break;
	}
	}
}
	 

