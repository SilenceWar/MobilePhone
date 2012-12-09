package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Phone;

public class IncommingCallPanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage, incCall, getNumber;
	private Phone thisPhone;
	private final MainFrame parent;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public IncommingCallPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);

		topBarClock = Ccollection.drawJLabel("12:45",225,-4,100,25,false, Color.gray,0,0,this);
		Ccollection.drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0,0,this);
		Ccollection.drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0,0,this);
		Ccollection.drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0,0,this);
		Ccollection.drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0,0,this);
		topBarNewMessage = Ccollection.drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
		
		getNumber = Ccollection.drawJLabel("11223344", 20, 15, 100, 100, false, Color.white, 0,0,this);
		

		incCall = Ccollection.drawJLabel("IncomingCall.png",1,20,261,436,true, Color.gray, 0,0,this);
		
		incCall.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	        	if (evt.getX()>=18 && evt.getX()<=62 && evt.getY()>=311 && evt.getY()<=363) {
	        		parent.chosenRecallNumber = getNumber.getText();
	        		parent.showPage("callNumber");
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
	
	private class TimeController implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == clockTimer) {
				SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
				topBarClock.setText(""+stf.format(System.currentTimeMillis()));
			}
		}
	}
}

