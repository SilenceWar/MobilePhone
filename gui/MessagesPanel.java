package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.Service;

import model.Conversation;
import model.Message;
import model.Phone;

public class MessagesPanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, messagesTopBar;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime;
	private Controller buttonPress;
	private final MainFrame parent;
	private Phone thisPhone;
	private ArrayList<JPanel> formattedConversations;
	
	public MessagesPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		buttonPress = new Controller();
		
		formattedConversations = new ArrayList<>();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, this);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, this);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, this);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, this);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, this);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0, this);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0, this);
		
		messagesTopBar = drawJLabel("MessagesTopBar.png",1,20,262,43,true, Color.gray, 0, this);
		messagesTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=231 && evt.getX()<=251 && evt.getY()>=10 && evt.getY()<=30) {
		        		parent.showPage("newMessage");
		        	}
		    }
		});
		
		printFormattedConversations();
		
		this.setVisible(true);
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
			
			drawJLabel("contactImage.png", 2, 2, 47, 48, true, Color.gray, 0,newPanel);
			
			if (Service.searchContactsWithNumber(thisPhone, conversations.get(i).getPhoneNumber()) == null)
				drawJLabel(conversations.get(i).getPhoneNumber(), 55, 5, 160, 25, false, Color.white, 16, newPanel);	
			else 
				drawJLabel(conversations.get(i).getContact().getName(), 55, 5, 160, 25, false, Color.white, 16, newPanel);
			
			Message latestMessage = conversations.get(i).getLatestMessage();
			
			String newestMessage = latestMessage.getContent();
			newestMessage = (newestMessage.length()>15) ? newestMessage.substring(0, 15)+"..." : newestMessage ;
			drawJLabel(newestMessage, 55, 30, 160, 25, false, Color.gray, 0, newPanel);	
			
			drawJLabel(latestMessage.getDateTimeFormat(), 185, 30, 160, 25, false, Color.gray, 0, newPanel);	
			
			drawJLabel("______________________________________", 0, 35, 340, 25, false, Color.gray, 0, newPanel);
			
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
	
	public JButton drawJButtonImage(String path,int x, int y, int width, int height) {
		java.net.URL newImageURL = MainFrame.class.getResource("/images/"+path);
		ImageIcon newImage = new ImageIcon(newImageURL);
	    JButton newButton = new JButton(newImage);
	    newButton.setSize(width,height);
	    newButton.setLocation(x,y);
	    newButton.setOpaque(false);
	    newButton.setContentAreaFilled(false);
	    newButton.setBorderPainted(false);
	    newButton.setFocusPainted(false);
	    newButton.addActionListener(buttonPress);
	    this.add(newButton);
	    
		return newButton;
	}
	
	public JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size, JPanel panel) {
		JLabel newLabel;
		if (image) { 
			java.net.URL newImageURL = MainFrame.class.getResource("/images/"+text);
			ImageIcon newImage = new ImageIcon(newImageURL);
			newLabel = new JLabel(newImage);
		} else { 
			newLabel = new JLabel(text);
		}
		newLabel.setLocation(x,y);
		newLabel.setSize(width, height);
		newLabel.setForeground(color);
		if (size != 0 && size != 16) newLabel.setFont(new Font(newLabel.getName(), Font.PLAIN, size));
		else if (size == 16) newLabel.setFont(new Font(newLabel.getName(), Font.BOLD, size));
		
		panel.add(newLabel);
		return newLabel;
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
		}
	}
}
