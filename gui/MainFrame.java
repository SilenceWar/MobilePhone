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
		    }
		});
		
	}
	
	public void showPage(String panel) {
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
			phonePanel.setVisible(true);
			phonePanel.clearScreen();
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
		case "logger":
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
			incommingCallPanel.setVisible(true); 
			break;
	}
	}
}
	 

