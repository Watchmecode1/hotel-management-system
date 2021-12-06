package it.faggiorosso.exception;

import java.io.Serial;

import javax.swing.JOptionPane;

public class GuiInputFieldValueException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -169112313346470853L;

	public GuiInputFieldValueException(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage);
	}
}