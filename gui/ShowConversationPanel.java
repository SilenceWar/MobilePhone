package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.Timer;

import service.Service;

import model.Conversation;
import model.Message;
import model.Phone;

public class ShowConversationPanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, messagesTopBar, writeMessage, writeMessageBig;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime, lblConversationTopbar;
	private Controller buttonPress;
	private final MainFrame parent;
	private final Phone thisPhone;
	private ArrayList<JPanel> conPanels;
	
	private JButton[] keyboard;
	private JButton[] numKeys;
	private JButton[] specialKeys;
	private JLabel keyboardBackground;
	
	private int field;
	private boolean shift = true;
	private int screen = 1;
	
	private JTextField content;
	private JEditorPane content2;
	
	private FocusController boxFocus;
	
	private TimeController timeController;
	private Timer clockTimer;
	
	public ShowConversationPanel(MainFrame theParent, Phone thePhone) {
		this.thisPhone = thePhone;
		this.parent = theParent;
		buttonPress = new Controller();
		boxFocus = new FocusController();
		
		timeController = new TimeController();
		clockTimer = new Timer(1000, timeController);
		clockTimer.start();
		
		keyboard = new JButton[29];
		specialKeys = new JButton[7];
		numKeys = new JButton[27];
		
		conPanels = new ArrayList<>();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0, this);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0, this);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0, this);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0, this);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0, this);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0, this);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0, this);
		
		lblConversationTopbar = drawJLabel("",55,20,262,43,false, Color.WHITE, 16, this);
		messagesTopBar = drawJLabel("InConversationTopBar.png",1,20,262,43,true, Color.gray, 0, this);
		messagesTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	        	if (evt.getX()>=231 && evt.getX()<=251 && evt.getY()>=10 && evt.getY()<=30) {
	        		Service.deleteConversation(thisPhone,parent.chosenConversation);
	        		parent.showPage("messages");
	        	}
		    	if (evt.getX()>=4 && evt.getX()<=16 && evt.getY()>=9 && evt.getY()<=30) {
		        	parent.showPage("messages");
		        }
		    }
		});
		
		content = drawJTextField("Skriv din besked",15,422,190,23);
		content2 = drawJTextArea("Skriv din besked",15,217,190,71);
		content2.setVisible(false);
		
		writeMessage = drawJLabel("WriteMessageBar.png",1,413,262,40,true, Color.gray, 0, this);
		
		writeMessageBig = drawJLabel("WriteMessageBarBig.png",0,207,262,92,true, Color.gray, 0, this);
		writeMessageBig.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=216 && evt.getX()<=256 && evt.getY()>=59 && evt.getY()<=85) {
		        		Service.sendMessage(thisPhone, parent.chosenConversation.getPhoneNumber(), content2.getText(),true);
		        		parent.showPage("showConversation"); 
		        	}
		    }
		});
		writeMessageBig.setVisible(false);
		
		showConversation(parent.chosenConversation);
		
		drawKeyboard();
		hideKeyboard();
		
		this.setVisible(true);
	}
	
	public void clearAll() {
		this.requestFocusInWindow(false);
		content.setVisible(true);
		content2.setVisible(false);
		content2.setText("");
		hideKeyboard();
	}
	
	public void showConversation(Conversation conversation) {	
		if (conversation == null) return;
		
		removePanels();
		
		String receiverString = (conversation.getContact()!=null) ? conversation.getContact().getName() : conversation.getPhoneNumber(); 
		lblConversationTopbar.setText(receiverString);
		ArrayList<Message> messages = conversation.getMessages();
		for (int i=0;i<messages.size();i++) {
			JPanel newPanel = new JPanel();
			newPanel.setLayout(null);
			newPanel.setSize(340, 0);
			newPanel.setLocation(1,(63+(i*55)));
			if (!conPanels.isEmpty())
			newPanel.setLocation(1,(63+(i*(conPanels.get(conPanels.size()-1).getHeight()+5))));
			
			newPanel.setOpaque(false);
			this.add(newPanel);
			conPanels.add(newPanel);
			
			if (conversation.getOutbox().contains(messages.get(i)))
				drawJEditorPane(messages.get(i).getContent(), 71, 2, 188, Color.black, 16, newPanel);	
			else 
				drawJEditorPane(messages.get(i).getContent(), 1, 2, 188, Color.black, 16, newPanel);
			newPanel.setVisible(true);
		}
		
	}
	
	public void removePanels() {
		for (JPanel item:conPanels) 
			item.setVisible(false);
		conPanels.clear();
	}
	
	public JTextField drawJTextField(String text, int x, int y, int width, int height) {
		JTextField newTextfield = new JTextField(text);
		newTextfield.setLocation(x, y);
		newTextfield.setSize(width, height);
		newTextfield.setOpaque(false);
		newTextfield.setBorder(null);
		newTextfield.addFocusListener(boxFocus);
		newTextfield.setForeground(Color.white);
		this.add(newTextfield);
		return newTextfield;
	}
	
	public JEditorPane drawJTextArea(String text, int x, int y, int width, int height) {
		JEditorPane newTextArea = new JEditorPane();
		newTextArea.setText(text);
		newTextArea.setLocation(x, y);
		newTextArea.setSize(width, height);
		newTextArea.setOpaque(false);
		newTextArea.setBorder(null);
		newTextArea.addFocusListener(boxFocus);
		newTextArea.setForeground(Color.white);
		this.add(newTextArea);
		return newTextArea;
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
	
	public JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size, JPanel panel) {
		JLabel newLabel;
		if (image) { 
			java.net.URL newImageURL = MainFrame.class.getResource("/images/"+text);
			ImageIcon newImage = new ImageIcon(newImageURL);
			newLabel = new JLabel(newImage);
		} else { 
			newLabel = new JLabel(text);
		}
		newLabel.setLocation(x,y);
		newLabel.setSize(width, height);
		newLabel.setForeground(color);
		if (size != 0 && size != 50) newLabel.setFont(new Font(newLabel.getName(), Font.PLAIN, size));
		else if (size == 50) newLabel.setFont(new Font(newLabel.getName(), Font.BOLD, size));
		
		panel.add(newLabel);
		return newLabel;
	}
	
	public JEditorPane drawJEditorPane(String text, int x, int y, int width, Color color, int size, JPanel panel) {
		JEditorPane newPane;
		newPane = new JEditorPane();
		newPane.setText(text);
		newPane.setEditable(false);
		newPane.setLocation(x,y);
		
		int height = 30;
		int count=0;
		String[] result = text.split("\\n");
		for (int i=0; i<result.length; i++)
			if (result[i].length()>20) { count += result[i].length()/26; }
		height += (count * 22);
		height += numberOfApearences(text,"\\n") * 22;
		
		panel.setSize(panel.getWidth(), panel.getHeight()+height);
		
		newPane.setSize(width, height);
		newPane.setForeground(color);
		newPane.setBackground(Color.LIGHT_GRAY);
		if (size != 0 && size != 50) newPane.setFont(new Font(newPane.getName(), Font.PLAIN, size));
		else if (size == 50) newPane.setFont(new Font(newPane.getName(), Font.BOLD, size));
		
		panel.add(newPane);
		return newPane;
	}
	
	public int numberOfApearences(String theString, String searchString) {
		String str = theString;
		String findStr = searchString;
		return str.split(findStr).length-1;
	}

	public void drawKeyboard() {
		int line = 0;
		for (int i=1;i<30;i++) {
			int xtra = (line%2==0 && line!=0) ? 46 : 0;
			keyboard[i-1] = drawJButtonImage("keyAlpha"+(i-1)+".png",xtra+6+((i-1)%11)*23, 310+(line*36), 19, 30);
			if (i%11 == 0) line++;
		}
		line = 0;
		for (int i=1;i<=27;i++) {
			int xtra = (line%2==0 && line!=0) ? 37 : 0;
			numKeys[i-1] = drawJButtonImage("keyNum"+(i-1)+".png",xtra+7+((i-1)%10)*25, 310+(line*36), 24, 32);
			if (i%10 == 0) line++;
		}
		
		specialKeys[0] = drawJButtonImage("keyAlphaShift.png",6, 381, 32, 31);
		specialKeys[1] = drawJButtonImage("keyAlphaBackspace.png",224, 382, 32, 31);
		specialKeys[2] = drawJButtonImage("keyAlphaSymbols.png",4, 418, 40, 31);
		specialKeys[3] = drawJButtonImage("keyAlphaComma.png",36, 418, 40, 31);
		specialKeys[4] = drawJButtonImage("keyAlphaSpace.png",72, 418, 120, 31);
		specialKeys[5] = drawJButtonImage("keyAlphaPunct.png",187, 417, 40, 31);
		specialKeys[6] = drawJButtonImage("keyEnter.png",218, 417, 40, 31);
		
		keyboardBackground = drawJLabel("keyBackground.png", 1, 300, 261, 180, true, Color.gray, 0, this);
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
		writeMessage.setVisible(false);
		writeMessageBig.setVisible(true);
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
		writeMessage.setVisible(true);
		writeMessageBig.setVisible(false);
		keyboardBackground.setVisible(false);
	}
	
	
	public String getAlpha(int i) {
		if (i==0) return "q"; if (i==1) return "w"; if (i==2) return "e"; if (i==3) return "r";
		if (i==4) return "t"; if (i==5) return "y"; if (i==6) return "u"; if (i==7) return "i";
		if (i==8) return "o"; if (i==9) return "p"; if (i==10) return "�"; if (i==11) return "a";
		if (i==12) return "s"; if (i==13) return "d"; if (i==14) return "f"; if (i==15) return "g";
		if (i==16) return "h"; if (i==17) return "j"; if (i==18) return "k"; if (i==19) return "l";
		if (i==20) return "�"; if (i==21) return "�"; if (i==22) return "z"; if (i==23) return "x";
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
						content2.setText(content2.getText()+value);
					shift = false;
				}
			}
			for (int i=0;i<numKeys.length;i++) {
				if (ae.getSource()==numKeys[i]) {
					String value = getNumeric(i);
						content2.setText(content2.getText()+value);
					shift = false;
				}
			}
			
			if (ae.getSource()==specialKeys[0]) {
				shift = (shift) ? false : true;
			}
			else if (ae.getSource()==specialKeys[1]) {
				if (!content2.getText().equals("")) 
					content2.setText(content2.getText().substring(0, content2.getText().length()-1));
			}
			else if (ae.getSource()==specialKeys[2]) {
				hideKeyboard();
				if (screen == 1) { showKeyboard(2); screen = 2; } else { showKeyboard(1); screen = 1; } 
			}
			else if (ae.getSource()==specialKeys[3]) {
					content2.setText(content2.getText()+",");
			}
			else if (ae.getSource()==specialKeys[4]) {
					content2.setText(content2.getText()+" ");
			}
			else if (ae.getSource()==specialKeys[5]) {
				content2.setText(content2.getText()+".");
				shift = true;
			}
			else if (ae.getSource()==specialKeys[6]) {
				content2.setText(content2.getText()+"\n"); 
				shift = true;
			}
		}
	}
	
	private class FocusController implements FocusListener {
		public void focusLost(FocusEvent ae) {}
		public void focusGained(FocusEvent ae) {
			if (ae.getSource() == content) {
				if (content2.getText().equals("Skriv din besked")) 
					content2.setText("");
				
				showKeyboard(1);
				content2.setVisible(true);
				content.setVisible(false);
				content2.requestFocusInWindow();
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
