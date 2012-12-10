package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import service.Service;
import model.*;

public class NewMessagePanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage;
	private Controller buttonPress;
	private FocusController boxFocus;
	private final MainFrame parent;
	
	private JButton[] keyboard;
	private JButton[] numKeys;
	private JButton[] specialKeys;
	private JLabel keyboardBackground;
	
	private JTextField receiver;
	private JEditorPane content;
	
	private int field;
	private boolean shift = true;
	private int screen = 1;
	private Phone thisPhone;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public NewMessagePanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		keyboard = new JButton[29];
		specialKeys = new JButton[7];
		numKeys = new JButton[27];
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		this.parent = theParent;
		buttonPress = new Controller();
		boxFocus = new FocusController();
		
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
		
		
		Ccollection.drawJLabel("Skriv ny besked",55,20,262,43,false, Color.WHITE, 16, 0, this);
		Ccollection.drawJLabel("newMessageTopBar.png",1,20,262,43,true, Color.gray, 0, 0, this).addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	if (evt.getX()>=4 && evt.getX()<=16 && evt.getY()>=9 && evt.getY()<=30) {
		        	parent.showPage("messages");
		        }
		    }
		});
		
		receiver = Ccollection.drawJTextField("Modtager",16,68,188,23, boxFocus, this);
		content = Ccollection.drawJEditorPane("Skriv din besked",16,103,188,190, boxFocus, this);
		
		Ccollection.drawJLabel("newMessageScreen.png",1,58,261,249,true, Color.gray, 0, 0, this).addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=214 && evt.getX()<=254 && evt.getY()>=211 && evt.getY()<=238) {
		        		if (receiver.getText().length()>7 && !content.equals("")) {
		        		Service.sendMessage(thisPhone, receiver.getText(), content.getText(),true);
		        		parent.chosenConversation = thisPhone.getConversations().get(thisPhone.getConversations().size()-1);
		        		parent.showPage("showConversation");
		        		}
		        	}
		        	else if (evt.getX()>=214 && evt.getX()<=254 && evt.getY()>=8 && evt.getY()<=33) {
		        		parent.showPage("contacts");
		        	}
		    }
		});
		
		
		drawKeyboard();
		showKeyboard(1);
		
		this.setVisible(true);
	}
	
	public void clearAll() {
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
		receiver.setText("Modtager");
		content.setText("Skriv din besked");
	}
	
	public void toContact(Contact contact) {
		receiver.setText(contact.getPhoneNumber());
	}
	
	
	public void drawKeyboard() {
		int line = 0;
		for (int i=1;i<30;i++) {
			int xtra = (line%2==0 && line!=0) ? 46 : 0;
			keyboard[i-1] = Ccollection.drawJButtonImage("keyAlpha"+(i-1)+".png",xtra+6+((i-1)%11)*23, 310+(line*36), 19, 30, buttonPress, this);
			if (i%11 == 0) line++;
		}
		line = 0;
		for (int i=1;i<=27;i++) {
			int xtra = (line%2==0 && line!=0) ? 37 : 0;
			numKeys[i-1] = Ccollection.drawJButtonImage("keyNum"+(i-1)+".png",xtra+7+((i-1)%10)*25, 310+(line*36), 24, 32, buttonPress, this);
			if (i%10 == 0) line++;
		}
		
		specialKeys[0] = Ccollection.drawJButtonImage("keyAlphaShift.png",6, 381, 32, 31, buttonPress, this);
		specialKeys[1] = Ccollection.drawJButtonImage("keyAlphaBackspace.png",224, 382, 32, 31, buttonPress, this);
		specialKeys[2] = Ccollection.drawJButtonImage("keyAlphaSymbols.png",4, 418, 40, 31, buttonPress, this);
		specialKeys[3] = Ccollection.drawJButtonImage("keyAlphaComma.png",36, 418, 40, 31, buttonPress, this);
		specialKeys[4] = Ccollection.drawJButtonImage("keyAlphaSpace.png",72, 418, 120, 31, buttonPress, this);
		specialKeys[5] = Ccollection.drawJButtonImage("keyAlphaPunct.png",187, 417, 40, 31, buttonPress, this);
		specialKeys[6] = Ccollection.drawJButtonImage("keyEnter.png",218, 417, 40, 31, buttonPress, this);
		
		keyboardBackground = Ccollection.drawJLabel("keyBackground.png", 1, 300, 261, 180, true, Color.gray, 0, 0, this);
		hideKeyboard();
	}
	
	public void showKeyboard(int show) {
		hideKeyboard();
		if (show == 1)
		for (JButton item: keyboard) 
			item.setVisible(true);
		if (show ==2)
		for (JButton item: numKeys) 
			item.setVisible(true);
		keyboardBackground.setVisible(true);
	}
	
	public void hideKeyboard() {
		for (JButton item: keyboard) {
			item.setVisible(false);
		}
		for (JButton item: numKeys) {
			item.setVisible(false);
		}
		keyboardBackground.setVisible(false);
	}
	
	public String getAlpha(int i) {
		if (i==0) return "q"; if (i==1) return "w"; if (i==2) return "e"; if (i==3) return "r";
		if (i==4) return "t"; if (i==5) return "y"; if (i==6) return "u"; if (i==7) return "i";
		if (i==8) return "o"; if (i==9) return "p"; if (i==10) return "å"; if (i==11) return "a";
		if (i==12) return "s"; if (i==13) return "d"; if (i==14) return "f"; if (i==15) return "g";
		if (i==16) return "h"; if (i==17) return "j"; if (i==18) return "k"; if (i==19) return "l";
		if (i==20) return "æ"; if (i==21) return "ø"; if (i==22) return "z"; if (i==23) return "x";
		if (i==24) return "c"; if (i==25) return "v"; if (i==26) return "b"; if (i==27) return "n";
		if (i==28) return "m";
		return "Error";
	}
	
	public String getNumeric(int i) {
		if (i==0) return "1"; if (i==1) return "2"; if (i==2) return "3"; if (i==3) return "4";
		if (i==4) return "5"; if (i==5) return "6"; if (i==6) return "7"; if (i==7) return "8";
		if (i==8) return "9"; if (i==9) return "0"; if (i==10) return "!"; if (i==11) return "@";
		if (i==12) return "#"; if (i==13) return "$"; if (i==14) return "/"; if (i==15) return "^";
		if (i==16) return "&"; if (i==17) return "*"; if (i==18) return "("; if (i==19) return ")";
		if (i==20) return "-"; if (i==21) return "'"; if (i==22) return "\""; if (i==23) return ":";
		if (i==24) return ";"; if (i==25) return ","; if (i==26) return "?";
		return "Error";
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			for (int i=0;i<keyboard.length;i++) {
				if (ae.getSource()==keyboard[i]) {
					String value = getAlpha(i);
					if (shift) value = value.toUpperCase();
					if (field == 1) {
						receiver.setText(receiver.getText()+value);
					}
					else if (field == 2) {
						content.setText(content.getText()+value);
					}
					shift = false;
				}
			}
			for (int i=0;i<numKeys.length;i++) {
				if (ae.getSource()==numKeys[i]) {
					String value = getNumeric(i);
					if (field == 1) {
						receiver.setText(receiver.getText()+value);
					}
					else if (field == 2) {
						content.setText(content.getText()+value);
					}
					shift = false;
				}
			}
			
			if (ae.getSource()==specialKeys[0]) {
				shift = (shift) ? false : true;
			}
			else if (ae.getSource()==specialKeys[1]) {
				if (field == 1 && !receiver.getText().equals("")) 
					receiver.setText(receiver.getText().substring(0, receiver.getText().length()-1));
				else if (field == 2 && !content.getText().equals("")) 
					content.setText(content.getText().substring(0, content.getText().length()-1));
			}
			else if (ae.getSource()==specialKeys[2]) {
				hideKeyboard();
				if (screen == 1) { showKeyboard(2); screen = 2; } else { showKeyboard(1); screen = 1; } 
			}
			else if (ae.getSource()==specialKeys[3]) {
				if (field == 1) 
					receiver.setText(receiver.getText()+",");
				else if (field == 2) 
					content.setText(content.getText()+",");
			}
			else if (ae.getSource()==specialKeys[4]) {
				if (field == 1) 
					receiver.setText(receiver.getText()+" ");
				else if (field == 2) 
					content.setText(content.getText()+" ");
			}
			else if (ae.getSource()==specialKeys[5]) {
				if (field == 1) 
					receiver.setText(receiver.getText()+".");
				else if (field == 2) 
					content.setText(content.getText()+".");
				shift = true;
			}
			else if (ae.getSource()==specialKeys[6]) {
				if (field == 1) 
					receiver.setText(receiver.getText()+"\n");
				else if (field == 2) 
					content.setText(content.getText()+"\n"); 
				shift = true;
			}
			
		}
	}
	
	private class FocusController implements FocusListener {
		public void focusLost(FocusEvent ae) {}
		public void focusGained(FocusEvent ae) {
			if (ae.getSource() == receiver) {
				if (receiver.getText().equals("Modtager")) 
					receiver.setText("");
				field = 1;
			}
			else if (ae.getSource() == content) {
				if (content.getText().equals("Skriv din besked"))
					content.setText("");
				field = 2;
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
