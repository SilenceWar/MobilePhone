package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Phone;

public class HomePanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarNewMessage, newMessage, topBarClock, lblWeatherTime;
	private Controller buttonPress;
	private MainFrame parent;
	private Phone thisPhone;
	private TimeController timeController;
	private Timer clockTimer;
	
	public HomePanel(MainFrame parent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = parent;
		buttonPress = new Controller();
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setOpaque(false);
		
		contacts = Ccollection.drawJButtonImage("Contacts.png",15,385,46,45,buttonPress,this);
		call = Ccollection.drawJButtonImage("Call.png",76,385,46,45,buttonPress,this);
		
		newMessage = Ccollection.drawJLabel("newMessageSmallHome.png",170,380,18,19,true, Color.gray, 0, 0, this);
		newMessage.setVisible(false);
		
		messages = Ccollection.drawJButtonImage("Messages.png",139,385,46,45,buttonPress,this);
		settings = Ccollection.drawJButtonImage("Settings.png",203,385,46,45,buttonPress,this);
		
		Ccollection.drawJLabel("Kontakter",15,425,100,25,false, Color.WHITE, 10, 0, this);
		Ccollection.drawJLabel("Telefon",80,425,100,25,false, Color.WHITE, 10, 0, this);
		Ccollection.drawJLabel("Meddelelser",133,425,100,25,false, Color.WHITE, 10, 0, this);
		Ccollection.drawJLabel("Indstillinger",199,425,100,25,false, Color.WHITE, 10, 0, this);
		
		topBarClock = Ccollection.drawJLabel("",225,-4,100,25,false, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage = Ccollection.drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
		
		lblWeatherTime = Ccollection.drawJLabel("",65,65,155,40,false, Color.BLACK, 50, 0, this);
		Ccollection.drawJLabel("Weather.png",3,40,255,153,true, Color.gray, 0, 0, this);

		this.setVisible(true);
		
	}	

	public void checkNew() {
		if (thisPhone.unReadConversation()) {
			newMessage.setVisible(true);
			topBarNewMessage.setVisible(true);
		}
		else {
			newMessage.setVisible(false);
			topBarNewMessage.setVisible(false);
		}
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == contacts) {
				parent.showPage("contacts");
			}
			else if (ae.getSource() == call) {
				parent.showPage("phone");
			}
			else if (ae.getSource() == messages) {
				parent.showPage("messages");
			}
			else if (ae.getSource() == settings) {
				parent.showPage("incommingCall");
			}
		}
	}
	
	private class TimeController implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == clockTimer) {
				SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
				topBarClock.setText(""+stf.format(System.currentTimeMillis()));
				lblWeatherTime.setText(""+stf.format(System.currentTimeMillis()));
			}
		}
	}
}
