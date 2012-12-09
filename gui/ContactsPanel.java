package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import service.Service;

import model.Contact;
import model.Phone;

public class ContactsPanel extends JPanel {
	private JLabel topBarClock, topBarNewMessage, contactsTopBar, contactSearchBar;
	private Controller buttonPress;
	private TextFieldController textfieldEnter;
	private final MainFrame parent;
	private Phone thisPhone;
	private ArrayList<JPanel> formattedContacts;
	
	private JButton[] keyboard;
	private JButton[] numKeys;
	private JButton[] specialKeys;
	private JLabel keyboardBackground;
	
	private boolean shift = true;
	private int screen = 1;
	
	private JTextField name;
	
	private FocusController boxFocus;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public ContactsPanel(MainFrame theParent, Phone thePhone) {
		this.formattedContacts = new ArrayList<JPanel>();
		
		this.thisPhone = thePhone;
		this.parent = theParent;
		buttonPress = new Controller();
		textfieldEnter = new TextFieldController();
		boxFocus = new FocusController();
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		keyboard = new JButton[29];
		specialKeys = new JButton[7];
		numKeys = new JButton[27];
		
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
		
		
		contactsTopBar = Ccollection.drawJLabel("ContactsTopBar.png",1,20,262,47,true, Color.gray, 0, 0, this);
		contactsTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=0 && evt.getX()<=65 && evt.getY()>=0 && evt.getY()<=49) {
		        		parent.showPage("phone");
		        	}
		    }
		});
		
		name = Ccollection.drawJTextField("Søg",44,75,175,23,boxFocus,this);
		name.addActionListener(textfieldEnter);

		contactSearchBar = Ccollection.drawJLabel("ContactSearch.png", 1, 67, 261, 40, true, Color.gray, 0, 0,this);
		contactSearchBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=227 && evt.getX()<=254 && evt.getY()>=6 && evt.getY()<=32) {
		        		parent.showPage("createContact");
		        	}
		    }
		});
		
		drawKeyboard();
		hideKeyboard();
		
		this.setVisible(true);
	}
	
	public void clearAll() {
		if (thisPhone.unReadConversation()) 
			topBarNewMessage.setVisible(true);
		else 
			topBarNewMessage.setVisible(false);
		name.setText("Søg");
	}
	
	public void printContacts(ArrayList<Contact> theContacts) {
		clearArrayList();
		
		ArrayList<Contact> contacts = theContacts;
		for (int i=0;i<contacts.size();i++) {
			JPanel newPanel = new JPanel();
			newPanel.setLayout(null);
			newPanel.setSize(340, 55);
			newPanel.setLocation(1,(108+(i*55)));
			newPanel.setOpaque(false);
			
			formattedContacts.add(newPanel);
			
			this.add(newPanel);
			
			Ccollection.drawJLabel(contacts.get(i).getName(), 55, 12, 160, 25, false, Color.white, 16, 0, newPanel);	
			
			Ccollection.drawJLabel("contactImage.png", 2, 2, 47, 48, true, Color.gray, 0, 0, newPanel);
			Ccollection.drawJLabel("______________________________________", 0, 35, 340, 25, false, Color.gray, 0, 0, newPanel);

			newPanel.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent evt) {
			    	parent.chosenViewContact = thisPhone.getContacts().get(formattedContacts.indexOf(evt.getSource()));
			    	parent.showPage("showContact");
			    }
			});
			newPanel.setVisible(true);
		}
	}
	
	public void clearArrayList() {
		
		for (JPanel item : formattedContacts) {
			item.setVisible(false);
		}
		
		formattedContacts.clear();
	}
	
	public void search(String name) {
		this.requestFocusInWindow(false);
		hideKeyboard();
		ArrayList<Contact> contacts = Service.searchContacts(thisPhone, name);
		printContacts(contacts);
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
		specialKeys[6] = Ccollection.drawJButtonImage("keySearch.png",218, 417, 40, 31, buttonPress, this);
		
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
		for (JButton item: specialKeys) 
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
		for (JButton item: specialKeys) 
			item.setVisible(false);
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
						name.setText(name.getText()+value);
					shift = false;
				}
			}
			for (int i=0;i<numKeys.length;i++) {
				if (ae.getSource()==numKeys[i]) {
					String value = getNumeric(i);
						name.setText(name.getText()+value);
					shift = false;
				}
			}
			
			if (ae.getSource()==specialKeys[0]) {
				shift = (shift) ? false : true;
			}
			else if (ae.getSource()==specialKeys[1]) {
				if (!name.getText().equals("")) 
					name.setText(name.getText().substring(0, name.getText().length()-1));
			}
			else if (ae.getSource()==specialKeys[2]) {
				hideKeyboard();
				if (screen == 1) { showKeyboard(2); screen = 2; } else { showKeyboard(1); screen = 1; } 
			}
			else if (ae.getSource()==specialKeys[3]) {
					name.setText(name.getText()+",");
			}
			else if (ae.getSource()==specialKeys[4]) {
					name.setText(name.getText()+" ");
			}
			else if (ae.getSource()==specialKeys[5]) {
				name.setText(name.getText()+".");
				shift = true;
			}
			else if (ae.getSource()==specialKeys[6]) {
				search(name.getText());
			}
		}
	}
	
	private class FocusController implements FocusListener {
		public void focusLost(FocusEvent ae) {}
		public void focusGained(FocusEvent ae) {
			if (ae.getSource() == name) {
				if (name.getText().equals("Søg")) 
					name.setText("");
				showKeyboard(1);
			}
		}
	}
	private class TextFieldController implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == name) {
				search(name.getText());
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
