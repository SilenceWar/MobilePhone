package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Call;
import model.Phone;

public class PhonePanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, phoneTopBar, pressedNumber, clockDisplay;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime;
	private Controller buttonPress;
	private final MainFrame parent;
	private JButton[] numPad;
	
	private Phone thisPhone;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public PhonePanel(MainFrame theParent, Phone thePhone) {
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
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, 0);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, 0);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, 0);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, 0);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0, 0);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0, 0);
		
		pressedNumber = drawJLabel("", 0, 100, 261, 50, false, Color.white, 32, 1);
		
		phoneTopBar = drawJLabel("PhoneTopBar.png",1,20,262,47,true, Color.gray, 0, 0);
		phoneTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
//		    	System.out.println(evt.getX()+"|"+evt.getY());
		        	if (evt.getX()>=198 && evt.getX()<=260 && evt.getY()>=0 && evt.getY()<=49) {
		        		parent.showPage("contacts");
		        	}
		        	else if (evt.getX()>=69 && evt.getX()<=127 && evt.getY()>=0 && evt.getY()<=49) {
		        		parent.showPage("logger");
		        	}
		    }
		});
		
		numPad = new JButton[15];
		int line = 0;
		for (int i=1;i<16;i++) {
			numPad[i-1] = drawJButtonImage(i+".png",3+((i-1)%3)*86, 197+(line*51), 84, 49);
			if (i%3==0) { line++; }
		}
		
		this.setVisible(true);
	}
	
	public String getNumber() {
		return pressedNumber.getText();
	}
	
	public void clearScreen() {
		pressedNumber.setText("");
	}
	
//	Draws a JButton
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
// Draws a JLabel
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
			
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == numPad[0]) {
				pressedNumber.setText(pressedNumber.getText()+"1");
			}
			
			else if (ae.getSource() == numPad[1]) { 
				pressedNumber.setText(pressedNumber.getText()+"2");
			}
			
			else if (ae.getSource() == numPad[2]) { 
				pressedNumber.setText(pressedNumber.getText()+"3");
			}
			
			else if (ae.getSource() == numPad[3]) {
				pressedNumber.setText(pressedNumber.getText()+"4");
			}
			
			else if (ae.getSource() == numPad[4]) {
				pressedNumber.setText(pressedNumber.getText()+"5");
			}
			
			else if (ae.getSource() == numPad[5]) {
				pressedNumber.setText(pressedNumber.getText()+"6");
			}
			
			else if (ae.getSource() == numPad[6]) {
				pressedNumber.setText(pressedNumber.getText()+"7");
			}
			
			else if (ae.getSource() == numPad[7]) {
				pressedNumber.setText(pressedNumber.getText()+"8");
			}
			
			else if (ae.getSource() == numPad[8]) {
				pressedNumber.setText(pressedNumber.getText()+"9");
			}
			
			else if (ae.getSource() == numPad[9]) {
				pressedNumber.setText(pressedNumber.getText()+"9");
			}
			
			else if (ae.getSource() == numPad[10]) {
				pressedNumber.setText(pressedNumber.getText()+"0");
			}
			
			else if (ae.getSource() == numPad[11])	{
				pressedNumber.setText(pressedNumber.getText()+"#");
			}
			
			else if (ae.getSource() == numPad[12])	{
//				VIDEO CALL - NO FUNCTION
			}
		
//			Call a number
			else if (ae.getSource() == numPad[13]) {
				parent.showPage("call");				
			}
		
//			Removes last pressed
			else if (ae.getSource() == numPad[14]) {
				if (!pressedNumber.getText().equals(""))
				pressedNumber.setText(pressedNumber.getText().substring(0, pressedNumber.getText().length()-1));
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
