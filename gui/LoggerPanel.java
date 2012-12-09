package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.Border;

import service.Service;

import model.Call;
import model.Contact;
import model.Conversation;
import model.Message;
import model.Phone;

public class LoggerPanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, loggerTopBar;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime;
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
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0, this);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0,0,this);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0,0,this);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0,0,this);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0,0,this);
		topBarNewMessage = drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
		
		loggerTopBar = drawJLabel("LoggerTopBar.png",1,20,261,47,true, Color.gray, 0,0,this);
				
		loggerTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
//		    	System.out.println(evt.getX()+"|"+evt.getY());
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
						
			drawJLabel("contactImage.png",2,2,47,48,true,Color.gray,0,0,newPanel);

			if (thisPhone.contactExists(calls.get(i).getNumber()) != null)
				drawJLabel(thisPhone.contactExists(calls.get(i).getNumber()).getName(),55,5,160,25,false,Color.white,16,0,newPanel);
			else 
				drawJLabel(calls.get(i).getNumber(),55,5,160,25,false,Color.white,16,0,newPanel);
			
			if (thisPhone.getOutgoing().contains(calls.get(i))) 
				drawJLabel("outgoingCall.png",55,29,27,17,true,Color.gray,0,0,newPanel);
			else 
				drawJLabel("incomingCallIcon.png",55,29,27,17,true,Color.gray,0,0,newPanel);
			
			reCall[i] = drawJButtonImage("MakeCall.png",230, 5, 23, 23, newPanel);
			
			
			drawJLabel(calls.get(i).getDuration(),85,25,160,25,false,Color.gray,12,0,newPanel);
			drawJLabel(calls.get(i).getDateTimeString(),185,25,160,25,false,Color.cyan,12,0,newPanel);
	
			drawJLabel("______________________________________",0,35,340,25,false,Color.gray,0,0,newPanel);
			
			newPanel.setVisible(true);
		}
		
	}
		
	public void clearArrayList() {
		
		for (JPanel item : formattedCalls) {
			item.setVisible(false);
		}
		
		formattedCalls.clear();
	}
	
	public JButton drawJButtonImage(String path,int x, int y, int width, int height, JPanel panel) {
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
	    panel.add(newButton);
	    
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
