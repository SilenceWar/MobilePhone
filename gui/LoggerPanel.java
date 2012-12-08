package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private final ArrayList<JPanel> formattedCalls;
	private final MainFrame parent;
	public LoggerPanel(MainFrame theParent, Phone thePhone) {
		this.parent = theParent;
		buttonPress = new Controller();
		this.thisPhone = thePhone;
		
		formattedCalls = new ArrayList<>();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0,0,this);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0,0,this);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0,0,this);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0,0,this);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0,0,this);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0,0,this);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0,0,this);
		
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
		
		printFormattedCalls();
		
		this.setVisible(true);
	}

	public void printFormattedCalls() {
		formattedCalls.clear();
		
		System.out.println("her1");
		
		ArrayList<Call> calls = this.thisPhone.getCalls();
		for (int i=0;i<calls.size();i++) {
			System.out.println("her2");
			JPanel newPanel = new JPanel();
			newPanel.setLayout(null);
			newPanel.setSize(340, 55);
			newPanel.setLocation(1,63+(i*55));
			newPanel.setOpaque(false);
			
			formattedCalls.add(newPanel);
			
			this.add(newPanel);
									
			drawJLabel("contactImage.png",2,2,47,48,true,Color.gray,0,0,newPanel);
			
			drawJLabel(calls.get(i).getNumber(),55,5,160,25,false,Color.white,16,0,newPanel);	
						
			drawJLabel("______________________________________",0,35,340,25,false,Color.gray,0,0,newPanel);
			
			newPanel.setVisible(true);
		}
		
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
}
