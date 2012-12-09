package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Call;
import model.Phone;

public class LoggerPanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage;
	private Phone thisPhone;
	private Controller buttonPress;
	private ArrayList<JPanel> formattedCalls;
	private final MainFrame parent;
	
	private JButton[] reCall;
	
	private TimeController timeController;
	private Timer clockTimer;
		
	public LoggerPanel(MainFrame theParent, Phone thePhone) {
		this.parent = theParent;
		buttonPress = new Controller();
		this.thisPhone = thePhone;
		
		formattedCalls = new ArrayList<>();
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = Ccollection.drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0,0,this);
		Ccollection.drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0,0,this);
		Ccollection.drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0,0,this);
		Ccollection.drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0,0,this);
		topBarNewMessage = Ccollection.drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
						
		Ccollection.drawJLabel("LoggerTopBar.png",1,20,261,47,true, Color.gray, 0,0,this).addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	        	if (evt.getX()>=198 && evt.getX()<=260 && evt.getY()>=0 && evt.getY()<=49) {
	        		parent.showPage("contacts");
	        	}
	        	else if (evt.getX()>=1 && evt.getX()<=61 && evt.getY()>=0 && evt.getY()<=49) {
	        		parent.showPage("phone");
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

	public void printFormattedCalls() {
		clearArrayList();
		
		ArrayList<Call> calls = this.thisPhone.getCalls();
		reCall = new JButton[calls.size()];
		for (int i=0;i<calls.size();i++) {
			JPanel newPanel = new JPanel();
			newPanel.setLayout(null);
			newPanel.setSize(340, 55);
			newPanel.setLocation(1,68+(i*55));
			newPanel.setOpaque(false);
			
			formattedCalls.add(newPanel);
			
			this.add(newPanel);
						
			Ccollection.drawJLabel("contactImage.png",2,2,47,48,true,Color.gray,0,0,newPanel);

			if (thisPhone.contactExists(calls.get(i).getNumber()) != null)
				Ccollection.drawJLabel(thisPhone.contactExists(calls.get(i).getNumber()).getName(),55,5,160,25,false,Color.white,16,0,newPanel);
			else 
				Ccollection.drawJLabel(calls.get(i).getNumber(),55,5,160,25,false,Color.white,16,0,newPanel);
			
			if (thisPhone.getOutgoing().contains(calls.get(i))) 
				Ccollection.drawJLabel("outgoingCall.png",55,29,27,17,true,Color.gray,0,0,newPanel);
			else 
				Ccollection.drawJLabel("incomingCallIcon.png",55,29,27,17,true,Color.gray,0,0,newPanel);
			
			reCall[i] = Ccollection.drawJButtonImage("MakeCall.png",230, 5, 23, 23, buttonPress,newPanel);
			
			
			Ccollection.drawJLabel(calls.get(i).getDuration(),85,25,160,25,false,Color.gray,12,0,newPanel);
			Ccollection.drawJLabel(calls.get(i).getDateTimeString(),185,25,160,25,false,Color.cyan,12,0,newPanel);
	
			Ccollection.drawJLabel("______________________________________",0,35,340,25,false,Color.gray,0,0,newPanel);
			
			newPanel.setVisible(true);
		}
		
	}
		
	public void clearArrayList() {
		
		for (JPanel item : formattedCalls) {
			item.setVisible(false);
		}
		
		formattedCalls.clear();
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			for (int i=0;i<reCall.length;i++) {
				if (ae.getSource() == reCall[i]) {
					parent.chosenRecallNumber = thisPhone.getCalls().get(i).getNumber();
					parent.showPage("reCallNumber");
				}
			}
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
