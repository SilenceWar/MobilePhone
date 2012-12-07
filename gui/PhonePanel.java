package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Call;
import model.Phone;

public class PhonePanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, phoneTopBar;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime, lblPressedNumber;
	private Controller buttonPress;
	private final MainFrame parent;
	private JButton[] numPad;
	private Phone thisPhone;
	
	public PhonePanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		buttonPress = new Controller();
		
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
		
		lblPressedNumber = drawJLabel("", 0, 100, 261, 50, false, Color.white, 32, 1);
		
		phoneTopBar = drawJLabel("PhoneTopBar.png",1,20,262,47,true, Color.gray, 0, 0);
		phoneTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
//		    	System.out.println(evt.getX()+"|"+evt.getY());
		        	if (evt.getX()>=198 && evt.getX()<=260 && evt.getY()>=0 && evt.getY()<=49) {
		        		System.out.println("clicked!");
		        		parent.showPage("contacts");
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
//	Creates a JLabel
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
				lblPressedNumber.setText(lblPressedNumber.getText()+"1");
			}
			
			else if (ae.getSource() == numPad[1]) { 
				lblPressedNumber.setText(lblPressedNumber.getText()+"2");
			}
			
			else if (ae.getSource() == numPad[2]) { 
				lblPressedNumber.setText(lblPressedNumber.getText()+"3");
			}
			
			else if (ae.getSource() == numPad[3]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"4");
			}
			
			else if (ae.getSource() == numPad[4]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"5");
			}
			
			else if (ae.getSource() == numPad[5]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"6");
			}
			
			else if (ae.getSource() == numPad[6]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"7");
			}
			
			else if (ae.getSource() == numPad[7]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"8");
			}
			
			else if (ae.getSource() == numPad[8]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"9");
			}
			
			else if (ae.getSource() == numPad[9]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"9");
			}
			
			else if (ae.getSource() == numPad[10]) {
				lblPressedNumber.setText(lblPressedNumber.getText()+"0");
			}
			
			else if (ae.getSource() == numPad[11])	{
				lblPressedNumber.setText(lblPressedNumber.getText()+"#");
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
				if (!lblPressedNumber.getText().equals(""))
				lblPressedNumber.setText(lblPressedNumber.getText().substring(0, lblPressedNumber.getText().length()-1));
			}
		}
	}
}
