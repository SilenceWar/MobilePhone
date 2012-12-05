package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, weatherImg;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime;
	private Controller buttonPress;
	private MainFrame parent;
	
	public HomePanel(MainFrame parent) {
		this.parent = parent;
		buttonPress = new Controller();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setOpaque(false);
		
		contacts = drawJButtonImage("Contacts.png",15,385,46,45);
		call = drawJButtonImage("Call.png",76,385,46,45);
		messages = drawJButtonImage("Messages.png",139,385,46,45);
		settings = drawJButtonImage("Settings.png",203,385,46,45);
		
		lblContacts = drawJLabel("Kontakter",15,425,100,25,false, Color.WHITE, 10);
		lblCall = drawJLabel("Telefon",80,425,100,25,false, Color.WHITE, 10);
		lblMessages = drawJLabel("Meddelelser",133,425,100,25,false, Color.WHITE, 10);
		lblSettings = drawJLabel("Indstillinger",199,425,100,25,false, Color.WHITE, 10);
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0);
		
		lblWeatherTime = drawJLabel("12:45",65,65,155,40,false, Color.BLACK, 50);
		weatherImg = drawJLabel("Weather.png",3,40,255,153,true, Color.gray, 0);
		
		this.setVisible(true);
		
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
	
	public JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size) {
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
		
		this.add(newLabel);
		return newLabel;
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == contacts) {
				parent.showPage("contacts");
			}
			else if (ae.getSource() == call) {
				System.out.println("Telefon");
			}
			else if (ae.getSource() == messages) {
				System.out.println("Meddelelser");
			}
			else if (ae.getSource() == settings) {
				System.out.println("Indstillinger");
			}
		}
	}
}
