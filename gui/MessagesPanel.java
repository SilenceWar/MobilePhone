package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import service.Service;

import model.Conversation;
import model.Message;
import model.Phone;

public class MessagesPanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage;
	private final MainFrame parent;
	private Phone thisPhone;
	private ArrayList<JPanel> formattedConversations;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public MessagesPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		formattedConversations = new ArrayList<>();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = Ccollection.drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage = Ccollection.drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
		
		Ccollection.drawJLabel("MessagesTopBar.png",1,20,262,43,true, Color.gray, 0, 0, this).addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=231 && evt.getX()<=251 && evt.getY()>=10 && evt.getY()<=30) {
		        		parent.showPage("newMessage");
		        	}
		    }
		});
		
		printFormattedConversations();
		
		this.setVisible(true);
	}
	
	public void clearAll() {
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
	}
	
	public void printFormattedConversations() {
		clearArrayList();
		
		ArrayList<Conversation> conversations = this.thisPhone.getConversations();
		for (int i=0;i<conversations.size();i++) {
			JPanel newPanel = new JPanel();
			newPanel.setLayout(null);
			newPanel.setSize(340, 55);
			newPanel.setLocation(1,(63+(i*55)));
			newPanel.setOpaque(false);
			
			formattedConversations.add(newPanel);
			
			this.add(newPanel);
			
			Ccollection.drawJLabel("contactImage.png", 2, 2, 47, 48, true, Color.gray, 0, 0,newPanel);
			
			if (Service.searchContactsWithNumber(thisPhone, conversations.get(i).getPhoneNumber()) == null)
				Ccollection.drawJLabel(conversations.get(i).getPhoneNumber(), 55, 5, 160, 25, false, Color.white, 16, 0, newPanel);	
			else 
				Ccollection.drawJLabel(conversations.get(i).getContact().getName(), 55, 5, 160, 25, false, Color.white, 16, 0, newPanel);
			
			if (!conversations.get(i).isRead()) 
				Ccollection.drawJLabel("newMessageSmall.png", 230, 5, 20, 20, true, Color.gray, 0, 0, newPanel);
			
			Message latestMessage = conversations.get(i).getLatestMessage();
			
			String newestMessage = latestMessage.getContent();
			newestMessage = (newestMessage.length()>15) ? newestMessage.substring(0, 15)+"..." : newestMessage ;
			Ccollection.drawJLabel(newestMessage, 55, 30, 160, 25, false, Color.gray, 0, 0, newPanel);	
			
			Ccollection.drawJLabel(latestMessage.getDateTimeFormat(), 185, 30, 160, 25, false, Color.gray, 0, 0, newPanel);	
			
			Ccollection.drawJLabel("______________________________________", 0, 35, 340, 25, false, Color.gray, 0, 0, newPanel);
			
			newPanel.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent evt) {
			    	parent.chosenConversation = thisPhone.getConversations().get(formattedConversations.indexOf(evt.getSource()));
			    	parent.showPage("showConversation");
			    }
			});
			
			newPanel.setVisible(true);
		}
		
	}
	public void clearArrayList() {
		
		for (JPanel item : formattedConversations) {
			item.setVisible(false);
		}
		
		formattedConversations.clear();
	}
	
	private class TimeController implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == clockTimer) {
				SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
				topBarClock.setText(""+stf.format(System.currentTimeMillis()));
			}
		}
	}
}
