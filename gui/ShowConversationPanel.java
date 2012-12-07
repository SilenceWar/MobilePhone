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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.Service;

import model.Conversation;
import model.Message;
import model.Phone;

public class ShowConversationPanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, messagesTopBar;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime, lblConversationTopbar;
	private Controller buttonPress;
	private final MainFrame parent;
	private final Phone thisPhone;
	private ArrayList<JPanel> conPanels;
	
	public ShowConversationPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		buttonPress = new Controller();
		
		conPanels = new ArrayList<>();
		
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
		
		lblConversationTopbar = drawJLabel("",55,20,262,43,false, Color.WHITE, 16, this);
		messagesTopBar = drawJLabel("InConversationTopBar.png",1,20,262,43,true, Color.gray, 0, this);
		messagesTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	        	if (evt.getX()>=231 && evt.getX()<=251 && evt.getY()>=10 && evt.getY()<=30) {
	        		Service.deleteConversation(thisPhone,parent.chosenConversation);
	        		parent.showPage("messages");
	        	}
		    	if (evt.getX()>=4 && evt.getX()<=16 && evt.getY()>=9 && evt.getY()<=30) {
		        	parent.showPage("messages");
		        }
		    }
		});
		
		
		showConversation(parent.chosenConversation);
		
		this.setVisible(true);
	}
	
	
	public void showConversation(Conversation conversation) {	
		if (conversation == null) return;
		
		removePanels();
		
		String receiverString = (conversation.getContact()!=null) ? conversation.getContact().getName() : conversation.getPhoneNumber(); 
		lblConversationTopbar.setText(receiverString);
		ArrayList<Message> messages = conversation.getMessages();
		for (int i=0;i<messages.size();i++) {
			JPanel newPanel = new JPanel();
			newPanel.setLayout(null);
			newPanel.setSize(340, 0);
			newPanel.setLocation(1,(63+(i*55)));
			newPanel.setOpaque(false);
			System.out.println("HEJ!");
			this.add(newPanel);
			conPanels.add(newPanel);
			
			if (conversation.getOutbox().contains(messages.get(i)))
				drawJEditorPane(messages.get(i).getContent(), 71, 2, 188, Color.black, 16, newPanel);	
			else 
				drawJEditorPane(messages.get(i).getContent(), 1, 2, 188, Color.black, 16, newPanel);
			newPanel.setVisible(true);
		}
		
	}
	
	public void removePanels() {
		for (JPanel item:conPanels) 
			item.setVisible(false);
		conPanels.clear();
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
		if (size != 0 && size != 50) newLabel.setFont(new Font(newLabel.getName(), Font.PLAIN, size));
		else if (size == 50) newLabel.setFont(new Font(newLabel.getName(), Font.BOLD, size));
		
		panel.add(newLabel);
		return newLabel;
	}
	
	public JEditorPane drawJEditorPane(String text, int x, int y, int width, Color color, int size, JPanel panel) {
		JEditorPane newPane;
		newPane = new JEditorPane();
		newPane.setText(text);
		newPane.setEditable(false);
		newPane.setLocation(x,y);
		
		int height = 25+((text.length() / 26) *17);
		System.out.println(height);
		height += numberOfApearences(text,"\\n") * 16;
		System.out.println(height);
		
		panel.setSize(panel.getWidth(), panel.getHeight()+height);
		
		newPane.setSize(width, height);
		newPane.setForeground(color);
		newPane.setBackground(Color.LIGHT_GRAY);
		if (size != 0 && size != 50) newPane.setFont(new Font(newPane.getName(), Font.PLAIN, size));
		else if (size == 50) newPane.setFont(new Font(newPane.getName(), Font.BOLD, size));
		
		panel.add(newPane);
		return newPane;
	}
	
	public int numberOfApearences(String theString, String searchString) {
		
		String str = theString;
		String findStr = searchString;
		
		return str.split(findStr).length-1;
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
		}
	}
}
