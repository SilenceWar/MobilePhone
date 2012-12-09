package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ccollection {
	
	public static JLabel drawJLabel(String text, int x, int y, int width, int height, boolean image, Color color, int size, int alignment, JPanel panel) {
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
	
	public static JTextField drawJTextField(String text, int x, int y, int width, int height, FocusListener focus, JPanel panel) {
		JTextField newTextfield = new JTextField(text);
		newTextfield.setLocation(x, y);
		newTextfield.setSize(width, height);
		newTextfield.setOpaque(false);
		newTextfield.setBorder(null);
		newTextfield.addFocusListener(focus);
		newTextfield.setForeground(Color.white);
		panel.add(newTextfield);
		return newTextfield;
	}
	
	public static JButton drawJButtonImage(String path,int x, int y, int width, int height, ActionListener action, JPanel panel) {
		java.net.URL newImageURL = MainFrame.class.getResource("/images/"+path);
		ImageIcon newImage = new ImageIcon(newImageURL);
	    JButton newButton = new JButton(newImage);
	    newButton.setSize(width,height);
	    newButton.setLocation(x,y);
	    newButton.setOpaque(false);
	    newButton.setContentAreaFilled(false);
	    newButton.setBorderPainted(false);
	    newButton.setFocusPainted(false);
	    newButton.addActionListener(action);
	    panel.add(newButton);
		return newButton;
	}
	
	public static JEditorPane drawJEditorPane(String text, int x, int y, int width, int height, FocusListener focus, JPanel panel) {
		JEditorPane newTextArea = new JEditorPane();
		newTextArea.setText(text);
		newTextArea.setLocation(x, y);
		newTextArea.setSize(width, height);
		newTextArea.setOpaque(false);
		newTextArea.setBorder(null);
		newTextArea.addFocusListener(focus);
		newTextArea.setForeground(Color.white);
		panel.add(newTextArea);
		return newTextArea;
	}
	
}
