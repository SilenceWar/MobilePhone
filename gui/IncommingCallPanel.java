package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import service.Service;

import model.Call;
import model.Phone;
import gui.CallPanel;

public class IncommingCallPanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, incCall, getNumber;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime, lblIncCall;
	private Controller buttonPress;
	private Phone thisPhone;
	private Call theCall;
	private final MainFrame parent;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public IncommingCallPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		buttonPress = new Controller();
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);

		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray,0,0,this);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0,0,this);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0,0,this);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0,0,this);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0,0,this);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0,0,this);
		topBarNewMessage.setVisible(false);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0,0, this);
		
		getNumber = drawJLabel("11223344", 20, 15, 100, 100, false, Color.white, 0,0,this);
		

		incCall = drawJLabel("IncomingCall.png",1,20,261,436,true, Color.gray, 0,0,this);
		
		incCall.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
//		    	System.out.println(evt.getX()+"|"+evt.getY()); 
	        	if (evt.getX()>=18 && evt.getX()<=62 && evt.getY()>=311 && evt.getY()<=363) {
	        		parent.chosenRecallNumber = getNumber.getText();
	        		parent.showPage("reCallNumber");
	        	}
	        	else if (evt.getX()>=198 && evt.getX()<=240 && evt.getY()>=309 && evt.getY()<=363) {
	        		parent.showPage("home");
	        	}
		    }
		});	
							

		this.setVisible(true);
						
	}

	public void clearAll() {
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
	}

//	Method to receive a call from a randomly generated number
	public void receiveCall() {
		Random generator = new Random();
		int randPhoneNumber = generator.nextInt(6)+1;
		  
		String number = "";
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
		case 6:
			number = "88888888";
			break;
		case 7: 
			number = "70121416";
			break;
		
		}
		getNumber.setText(number);
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
	
	public JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size, int alignment, JPanel panel) {
		JLabel newLabel;
		if (image) { 
			java.net.URL newImageURL = MainFrame.class.getResource("/images/"+text);
			ImageIcon newImage = new ImageIcon(newImageURL);
			newLabel = new JLabel(newImage);
		} else { 
			newLabel = new JLabel(text);
		}
		
		if (alignment==1) newLabel.setHorizontalAlignment(JLabel.CENTER);
		
		newLabel.setLocation(x,y);
		newLabel.setSize(width, height);
		newLabel.setForeground(color);
		if (size != 0 && size != 50) newLabel.setFont(new Font(newLabel.getName(), Font.PLAIN, size));
		else if (size == 50) newLabel.setFont(new Font(newLabel.getName(), Font.BOLD, size));
		
		panel.add(newLabel);
		return newLabel;
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
		}
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

