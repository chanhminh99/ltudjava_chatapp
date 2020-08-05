package chanhchung.vn.ChatApp.GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TexturePaint;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import chanhchung.vn.ChatApp.model.Message;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;

public class MessageRenderer extends JPanel implements ListCellRenderer<Message> {
	
	private JLabel lbSender = new JLabel();
    private JPanel mess_group;
    private String sender;
    private JTextPane textPane = null;
    
    
    public MessageRenderer(String sender) {
    	setLayout(new BorderLayout(5, 5));
    	
    	mess_group = new JPanel(new GridLayout(0, 1));
    	
    	textPane = new JTextPane();
    	textPane.setBackground(Color.LIGHT_GRAY);
    	mess_group.add(lbSender);
    	mess_group.add(textPane);
    	this.sender=sender;
    }
 

	@Override
	public Component getListCellRendererComponent(JList<? extends Message> arg0, Message arg1, int arg2, boolean arg3,
			boolean arg4) {
		// TODO Auto-generated method stub
		Font fieldFont = new Font("Arial", Font.PLAIN, 20);
		if (arg1.getFromUser().equalsIgnoreCase(sender)) {
    		add(mess_group, BorderLayout.EAST);
    		lbSender.setFont(fieldFont);
    		lbSender.setHorizontalAlignment(SwingConstants.RIGHT);
    		
    		lbSender.setText(arg1.getFromUser());
    		lbSender.setForeground(Color.RED);
    		
    		String text = arg1.getContent();
    		renderMessage(text);
    		
    		
    	} else {
    		add(mess_group, BorderLayout.CENTER);
    		lbSender.setText(arg1.getFromUser());
    		lbSender.setFont(fieldFont);
    		lbSender.setHorizontalAlignment(SwingConstants.LEFT);
    		lbSender.setForeground(Color.GRAY);
    		String text = arg1.getContent();
    		renderMessage(text);
    	}

		lbSender.setOpaque(true);
		textPane.setOpaque(true);
		
		

        return this;
		
	}


	private void renderMessage(String text) {
		StyledDocument doc = textPane.getStyledDocument();
		
		String msg = "";
		textPane.setText("");
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == ':' && i+1 < text.length() && text.charAt(i+1) == 'v') { // Haha
				try {
					doc.insertString(doc.getLength(), msg, null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textPane.setCaretPosition(textPane.getDocument().getLength());
				Image img_haha = new ImageIcon(this.getClass().getResource("/happy.png")).getImage();
	    		img_haha = img_haha.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    		textPane.insertIcon(new ImageIcon(img_haha));
	    		
	    		msg = "";
	    		i++;
			} else if(text.charAt(i) == ':' && i+1 < text.length() && text.charAt(i+1) == '(') { // Sad
					try {
						doc.insertString(doc.getLength(), msg, null);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					textPane.setCaretPosition(textPane.getDocument().getLength());
    				Image img_sad = new ImageIcon(this.getClass().getResource("/unhappy.png")).getImage();
    				img_sad = img_sad.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    	    		textPane.insertIcon(new ImageIcon(img_sad));
    	    		
    	    		msg = "";
    	    		i++;
			} else if (text.charAt(i) == ':' && i+1 < text.length() && text.charAt(i+1) == 'x') { //Angry
				try {
					doc.insertString(doc.getLength(), msg, null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textPane.setCaretPosition(textPane.getDocument().getLength());
				Image img_angry = new ImageIcon(this.getClass().getResource("/mad.png")).getImage();
				img_angry = img_angry.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    		textPane.insertIcon(new ImageIcon(img_angry));
	    		
	    		msg = "";
	    		i++;
			} else if (text.charAt(i) == ':' && i+1 < text.length() && text.charAt(i+1) == 'o') { //Wow
				try {
					doc.insertString(doc.getLength(), msg, null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textPane.setCaretPosition(textPane.getDocument().getLength());
				Image img_wow = new ImageIcon(this.getClass().getResource("/surprised.png")).getImage();
				img_wow = img_wow.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    		textPane.insertIcon(new ImageIcon(img_wow));
	    		
	    		msg = "";
	    		i++;
			} else if (text.charAt(i) == ':' && i+1 < text.length() && text.charAt(i+1) == '3') { // Heart
				try {
					doc.insertString(doc.getLength(), msg, null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textPane.setCaretPosition(textPane.getDocument().getLength());
				Image img_heart= new ImageIcon(this.getClass().getResource("/heart.png")).getImage();
				img_heart = img_heart.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    		textPane.insertIcon(new ImageIcon(img_heart));
	    		
	    		msg = "";
	    		i++;
			}
			else {
				msg = msg + text.charAt(i);
				try {
					doc.insertString(doc.getLength(), msg, null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg = "";
				}
			}
		
	}
}
