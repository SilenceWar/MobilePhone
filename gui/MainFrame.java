package gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
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
	
	private Point start_drag;
	private Point start_loc;
	
	public MainFrame() {
		this.thisPhone = Service.createPhone("25798315");
		chosenConversation = null;
		chosenViewContact = null;
		
		homePanel = new HomePanel(this, this.thisPhone);
		contactsPanel = new ContactsPanel(this, this.thisPhone);
		messagesPanel = new MessagesPanel(this, this.thisPhone);
		phonePanel = new PhonePanel(this, this.thisPhone);
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
		
		
		this.setResizable(false);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        
		java.net.URL imageURL = MainFrame.class.getResource("/images/Phone.png");
		ImageIcon image = new ImageIcon(imageURL);
		JLabel imageLabel = new JLabel(image);
		imageLabel.setSize(350,600);
		
		imageLabel.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=139 && evt.getX()<=208 && evt.getY()>=546 && evt.getY()<=566) {
		        		showPage("home"); // If clicked within the right coordinates of the Home-button of our phone.
		        	}
		        	else if (evt.getX()>=79 && evt.getX()<=104 && evt.getY()>=546 && evt.getY()<=566) {
			        	recieveMessage();
			        }
		        	else if (evt.getX()>=253 && evt.getX()<=272 && evt.getY()>=35 && evt.getY()<=55) {
			        	if (evt.getClickCount() == 2) {
			        		System.exit(0); // If clicked twice on the frontCamera in the top-right-corner.
			        	}
			        }
		    }
		});
		
		imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
		imageLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
		
		homePanel.setVisible(false);
		this.add(homePanel);
		this.add(contactsPanel);
		this.add(messagesPanel);
		this.add(phonePanel);
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
	}
	
	private void formMousePressed(java.awt.event.MouseEvent evt) {
        this.start_drag = this.getScreenLocation(evt);
        this.start_loc = this.getLocation();
    }
	
	private void formMouseDragged(java.awt.event.MouseEvent evt) {
        Point current = this.getScreenLocation(evt);
		Point offset = new Point((int) current.getX() - (int) start_drag.getX(),(int) current.getY() - (int) start_drag.getY());
		Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc.getY() + offset.getY()));
	    this.setLocation(new_location);
	}
	
	private Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint();
        Point target_location = this.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),(int) (target_location.getY() + cursor.getY()));
      }
	
	public void recieveMessage() {
		Random generator = new Random();
		int randMessage = generator.nextInt(6)+1;
		int randPhoneNumber = generator.nextInt(5)+1;
		  
		String message = "";
		String number = "";
		
		switch(randMessage) {
		case 1:
			message = "Hvad skal du i dag?";
			break;
		case 2:
			message = "Det lyder godt nok m�rkeligt! \nDet virker her ved mig :-(";
			break;
		case 3:
			message = "Hvad siger du? Er du syg? \nDet er der jo ikke noget at g�re ved desv�rre";
			break;
		case 4: 
			message = "Er du nu for sent p� den igen? \nDet g�r da snart ikke med mere frav�r? \nHvad har du? 25% allerede?";
			break;
		case 5:
			message = "Det er sku godt :-D \nGl�der mig sku ogs� til jul :-P";
			break;
		case 6:
			message = "HA GAAAAAAAAAAAAAAYYYYY!!!";
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
	 

