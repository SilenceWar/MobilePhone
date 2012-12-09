package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Phone;

public class ShowContactPanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage, lblContactName, lblContactNumber;
	private final MainFrame parent;
	private Phone thisPhone;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public ShowContactPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		lblContactName = Ccollection.drawJLabel("",30,15,262,43,false, Color.WHITE, 16, 0, this);
		lblContactNumber = Ccollection.drawJLabel("",9,177,262,43,false, Color.WHITE, 14, 0, this);
		
		topBarClock = Ccollection.drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage = Ccollection.drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
		
		Ccollection.drawJLabel("showContact.png",0,20,262,192,true, Color.gray, 0, 0, this).addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	        	if (evt.getX()>=5 && evt.getX()<=18 && evt.getY()>=8 && evt.getY()<=25) {
	        		parent.showPage("contacts");
	        	}
		    	else if (evt.getX()>=191 && evt.getX()<=221 && evt.getY()>=155 && evt.getY()<=189) {
		    		parent.showPage("callContact");
	        	}
		    	else if (evt.getX()>=223 && evt.getX()<=259 && evt.getY()>=155 && evt.getY()<=189) {
	        		parent.showPage("newMessageContact");
	        	}
		    }
		});
		
		this.setVisible(true);
	}
	
	public void refreshPanel() {
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
		lblContactName.setText(parent.chosenViewContact.getName());
		lblContactNumber.setText(parent.chosenViewContact.getPhoneNumber());
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
