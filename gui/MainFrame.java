package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.JFrame;
import javax.swing.border.Border;

public class MainFrame extends JFrame {
	private HomePanel homePanel;
	private ContactsPanel contactsPanel;
	private MessagesPanel messagesPanel;
	private PhonePanel phonePanel;
	private SettingsPanel settingsPanel;
	private NewMessagePanel newMessagePanel;
	private CallPanel callPanel;
	
	public MainFrame() {
		homePanel = new HomePanel(this);
		contactsPanel = new ContactsPanel(this);
		messagesPanel = new MessagesPanel(this);
		phonePanel = new PhonePanel(this);
		settingsPanel = new SettingsPanel(this);
		newMessagePanel = new NewMessagePanel(this);
		callPanel = new CallPanel(this);
		
		this.setSize(350,650);
		this.setLocation(400,50);
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

		if (panel.equals("home")) homePanel.setVisible(true); // Her kan der også kaldes en refresh kode inde i homePanel! :-)
		if (panel.equals("contacts")) contactsPanel.setVisible(true);
		if (panel.equals("messages")) messagesPanel.setVisible(true);
		if (panel.equals("phone")) phonePanel.setVisible(true);
		if (panel.equals("settings")) settingsPanel.setVisible(true);
		if (panel.equals("newMessage")) newMessagePanel.setVisible(true);
		if (panel.equals("call")) callPanel.setVisible(true);
	}
	 
}
