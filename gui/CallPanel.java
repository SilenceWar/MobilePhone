package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Call;
import model.Phone;
import service.Service;

public class CallPanel extends JPanel {
	private JButton contacts, call, messages, settings, hangUp, logger;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, weatherImg, newCall, getNumber, clockDisplay;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime;
	private Controller buttonPress;
	
	private Timer clock;
	private int secs=0, hrs=0, mins=0;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private final MainFrame parent;
	private final Phone phone;
	private Call theCall;
	
	public CallPanel(MainFrame theParent, Phone thePhone) {
		this.parent = theParent;
		this.phone = thePhone;
		buttonPress = new Controller();
		
		theCall = null;
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, 0);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, 0);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, 0);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, 0);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0, 0);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0, 0);
		getNumber = drawJLabel("",3,10,261,50,false,Color.white,20,0);
		clockDisplay = drawJLabel("", 200, 30, 100, 100, false, Color.white,14,0);

//		Background
		newCall = drawJLabel("CallGUI.png",1,20,261,436,true, Color.gray, 0, 0);
	
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
	
//	Starts a call
	public void startCall(String number) {  
		countUpClock();
		theCall = Service.recieveCall(this.phone,number, true); 
		getNumber.setText(number);
	}
			
	
//	Ends a call
	public void endCall() {
		theCall.setDuration(((hrs*60)+(mins*60)+(secs))); 
		hrs = 0;
		mins = 0;
		secs = 0;
		clock.stop();
		clockDisplay.setText("00:00:00");
	} 
	
	
// Draws a JButton	
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
	
//	Draws a JLabel
	public JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size, int alignment) {
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
		
		this.add(newLabel);
		return newLabel;
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
}
