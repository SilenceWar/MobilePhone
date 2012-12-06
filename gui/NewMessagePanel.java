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

public class NewMessagePanel extends JPanel {
	private JButton contacts, call, messages, settings;
	private JLabel topBarClock, topBarBattery, topBarSignal, topBarWifi, topBarMute, topBarNewMessage, topBarMissedCall, inConversationTopBar;
	private JLabel lblMessages, lblContacts, lblCall, lblSettings, lblWeatherTime;
	private Controller buttonPress;
	private final MainFrame parent;
	
	private JButton[] keyboard;
	private JButton[] numKeys;
	private JButton[] specialKeys;
	
	public NewMessagePanel(MainFrame theParent) {
		keyboard = new JButton[29];
		specialKeys = new JButton[7];
		numKeys = new JButton[26];
		
		this.parent = theParent;
		buttonPress = new Controller();
		
		this.setLayout(null);
		this.setSize(261,452);
		this.setLocation(43, 67);
		this.setBackground(Color.BLACK);
		
		topBarClock = drawJLabel("12:45",225,-4,100,25,false, Color.gray, 0);
		topBarBattery = drawJLabel("topBattery.png",200,-4,20,25,true, Color.gray, 0);
		topBarSignal = drawJLabel("topSignal.png",175,-5,20,25,true, Color.gray, 0);
		topBarWifi = drawJLabel("topWifi.png",150,-4,20,25,true, Color.gray, 0);
		topBarMute = drawJLabel("topMute.png",125,-4,20,25,true, Color.gray, 0);
		topBarNewMessage = drawJLabel("topNewMessage.png",25,-3,20,25,true, Color.gray, 0);
		topBarMissedCall = drawJLabel("topMissedCall.png",0,-3,20,25,true, Color.gray, 0);
		
		inConversationTopBar = drawJLabel("InConversationTopBar.png",1,20,262,43,true, Color.gray, 0);
		inConversationTopBar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        	if (evt.getX()>=231 && evt.getX()<=251 && evt.getY()>=10 && evt.getY()<=30) {
		        		//parent.showPage("DELETE");
		        		System.out.println("DELETE");
		        	}
		    }
		});
		
		drawKeyboard();
		showKeyboard(1);
//		hideKeyboard();
		
		this.setVisible(true);
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
	
	public JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size) {
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
		
		this.add(newLabel);
		return newLabel;
	}
	
	public void drawKeyboard() {
		int line = 0;
		for (int i=1;i<30;i++) {
			int xtra = (line%2==0 && line!=0) ? 46 : 0;
			keyboard[i-1] = drawJButtonImage("keyAlpha"+(i-1)+".png",xtra+5+((i-1)%11)*23, 300+(line*36), 19, 30);
			if (i%11 == 0) line++;
		}
		line = 0;
		for (int i=1;i<27;i++) {
			int xtra = (line%2==0 && line!=0) ? 50 : 0;
			numKeys[i-1] = drawJButtonImage("keyNum"+(i-1)+".png",xtra+6+((i-1)%10)*25, 300+(line*36), 24, 32);
			if (i%10 == 0) line++;
		}
		
		specialKeys[0] = drawJButtonImage("keyAlphaShift.png",5, 371, 32, 31);
		specialKeys[1] = drawJButtonImage("keyAlphaBackspace.png",223, 372, 32, 31);
		specialKeys[2] = drawJButtonImage("keyAlphaSymbols.png",3, 408, 40, 31);
		specialKeys[3] = drawJButtonImage("keyAlphaComma.png",35, 408, 40, 31);
		specialKeys[4] = drawJButtonImage("keyAlphaSpace.png",71, 408, 120, 31);
		specialKeys[5] = drawJButtonImage("keyAlphaPunct.png",186, 407, 40, 31);
		specialKeys[6] = drawJButtonImage("keyEnter.png",217, 407, 40, 31);
		
		drawJLabel("keyBackground.png", 0, 290, 261, 180, true, Color.gray, 0);
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
	}
	
	public void hideKeyboard() {
		for (JButton item: keyboard) {
			item.setVisible(false);
		}
		for (JButton item: numKeys) {
			item.setVisible(false);
		}
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
		}
	}
}
