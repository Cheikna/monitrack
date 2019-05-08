package com.monitrack.mock.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.monitrack.util.JsonUtil;

public class JsonIndenterDialog extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea inputJsonArea;
	private JTextArea outputJsonArea;
	private JButton indentButton;
	private final Font font = new Font("Calibri", Font.PLAIN, 17);

	public JsonIndenterDialog(Frame owner) {
		super(owner, "Json Indenter", true);
		setLayout(new BorderLayout());
		setSize(1200,500);
		setLocationRelativeTo(null);
		inputJsonArea = new JTextArea(30,20);
		inputJsonArea.setFont(font);
		JScrollPane scroll1 = new JScrollPane(inputJsonArea);
		
		outputJsonArea = new JTextArea(30,60);
		outputJsonArea.setEditable(false);
		outputJsonArea.setFont(font);
		JScrollPane scroll2 = new JScrollPane(outputJsonArea);
		add(scroll1, BorderLayout.WEST);
		add(scroll2, BorderLayout.EAST);
		indentButton = new JButton("Indent now !");
		indentButton.addActionListener(this);
		add(indentButton, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == indentButton) {
			outputJsonArea.setText(JsonUtil.indentJsonOutput(inputJsonArea.getText()));
		}
		
	}
	
	

}
