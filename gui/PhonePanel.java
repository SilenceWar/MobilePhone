package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Phone;

public class PhonePanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage, pressedNumber;
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
		
		topBarClock = Ccollection.drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, 0, this);
		Ccollection.drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage = Ccollection.drawJLabel("topNewMessage.png",2,-3,20,25,true, Color.gray, 0, 0, this);
		topBarNewMessage.setVisible(false);
		
		pressedNumber = Ccollection.drawJLabel("", 0, 100, 261, 50, false, Color.white, 32, 1, this);
		
		Ccollection.drawJLabel("PhoneTopBar.png",1,20,262,47,true, Color.gray, 0, 0, this).addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
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
			numPad[i-1] = Ccollection.drawJButtonImage(i+".png",3+((i-1)%3)*86, 197+(line*51), 84, 49, buttonPress, this);
			if (i%3==0) { line++; }
		}
		this.setVisible(true);
	}
	
	public String getNumber() {
		return pressedNumber.getText();
	}
	
	public void setNumber(String number) {
		pressedNumber.setText(number);
	}
	
	public void clearScreen() {
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
		pressedNumber.setText("");
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

			else if (ae.getSource() == numPad[13]) {
				parent.showPage("call");				
			}
		
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
