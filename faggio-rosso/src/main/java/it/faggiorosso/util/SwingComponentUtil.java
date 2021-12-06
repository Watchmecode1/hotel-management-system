package it.faggiorosso.util;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class SwingComponentUtil {
	public static PlainDocument financesPlainDocument() {
		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(new DocumentFilter() {
		    @Override
		    public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) 
		        throws BadLocationException {
		        fb.insertString(off, str.replaceAll("[^0-9.]++", ""), attr);  // remove non-digits
		    } 
		    @Override
		    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
		        throws BadLocationException {
		        fb.replace(off, len, str.replaceAll("[^0-9.]++", ""), attr);  // remove non-digits
		    }
		});
		return doc;
	}
	
	public static PlainDocument numberPlainDocument() {
		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(new DocumentFilter() {
		    @Override
		    public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) 
		        throws BadLocationException {
		        fb.insertString(off, str.replaceAll("[^0-9]++", ""), attr);  // remove non-digits
		    } 
		    @Override
		    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
		        throws BadLocationException {
		        fb.replace(off, len, str.replaceAll("[^0-9]++", ""), attr);  // remove non-digits
		    }
		});
		return doc;
	}
	
	public static JSpinner numberJSpinner() {
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);
		JSpinner spinner = new JSpinner(model);
		((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		return spinner;
	}
	
	public static void addHotelIcons(JFrame frame) {
		try {
			Image img = ImageIO.read(SwingComponentUtil.class.getClassLoader().getResourceAsStream("imgs/wallPaper.jpg"));
			frame.setIconImage(img);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}