package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Call;
import model.Phone;
import service.Service;

public class CallPanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage, newCall, getNumber, clockDisplay;
	private Controller buttonPress;
	
	private Timer clock;
	private int secs=0, hrs=0, mins=0;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private final MainFrame parent;
	private final Phone thisPhone;

	private TimeController timeController;
	private Timer clockTimer;

	private Call theCall;
	
	public CallPanel(MainFrame theParent, Phone thePhone) {
		this.parent = theParent;
		this.thisPhone = thePhone;
		buttonPress = new Controller();
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		theCall = null;
		
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
		
		getNumber = Ccollection.drawJLabel("",3,10,261,50,false,Color.white,20,0, this);
		clockDisplay = Ccollection.drawJLabel("", 200, 30, 100, 100, false, Color.white,14,0, this);

		newCall = Ccollection.drawJLabel("CallGUI.png",1,20,261,436,true, Color.gray, 0, 0, this);
	
		newCall.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	        	if (evt.getX()>=174 && evt.getX()<=258 && evt.getY()>=259 && evt.getY()<=347) {
	        		endCall();
	        		parent.showPage("logger");
	        	}
		    }
		});
		
	
		this.setVisible(true);
	}
	
	public void startCall(String number) {  
		
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
		
		countUpClock();
		theCall = Service.makeCall(this.thisPhone,number, true); 
		if (thisPhone.contactExists(number) != null) 
			getNumber.setText(thisPhone.contactExists(number).getName());
		else 
			getNumber.setText(number);
	}
	
	public void startCallIncomming(String number) {  
		
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
		
		countUpClock();
		theCall = Service.makeCall(this.thisPhone,number, false); 
		if (thisPhone.contactExists(number) != null) 
			getNumber.setText(thisPhone.contactExists(number).getName());
		else 
			getNumber.setText(number);
	}
			
	
	public void endCall() {
		theCall.setDuration(((hrs*60)+(mins*60)+(secs))); 
		hrs = 0;
		mins = 0;
		secs = 0;
		clock.stop();
		clockDisplay.setText("00:00:00");
	} 
	
	public void countUpClock() {
		clock = new Timer(1000, buttonPress);
		clock.start();
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == clock) 
			{
		      secs++;
		    }
		    if (secs == 60)
		    {
		      mins++;
		      secs = 0;
		    }
		 
		    if (mins == 60)
		    {
		      hrs++;
		      mins = 0;
		      secs = 0;
		    }
		 
		    if (hrs == 24)
		    {
		      hrs = 0;
		      mins = 0;
		      secs = 0;
		    }
		    clockDisplay.setText(
		        dFormat.format(hrs) + ":" + 
		        dFormat.format(mins) + ":" + 
		        dFormat.format(secs));	  
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
