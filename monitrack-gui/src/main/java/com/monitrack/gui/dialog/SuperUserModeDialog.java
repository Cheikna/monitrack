package com.monitrack.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.monitrack.enumeration.Images;
import com.monitrack.listener.SuperUserModeDialogListener;

@SuppressWarnings("serial")
public class SuperUserModeDialog extends JDialog {

	private SuperUserModeDialogListener listener;
	
	private JPanel northPanel;
	private JPanel centerPanel;
		
	private JLabel entityLabel;
	private String[] comboboxValues;
	@SuppressWarnings("rawtypes")
	private JComboBox entitiesCombobox;
	
	private JLabel actionLabel;	
	private ButtonGroup buttonGroup;
	private JRadioButton fillRadioButton;
	private JRadioButton emptyRadioButton;
	
	private JButton validateChoicesButton;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SuperUserModeDialog(JFrame owner) {
		super(owner, true);
		//setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setLayout(new BorderLayout());
		setTitle("Super mode");
		setIconImage(Images.SUPER.getImage());
		setSize(500, 200);
		setLocationRelativeTo(owner);
		
		listener = new SuperUserModeDialogListener(this);
		
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		entityLabel = new JLabel("Choisissez une entité : ");
		comboboxValues = new String[]{"Personnes", "Emplacements"};
		entitiesCombobox = new JComboBox(comboboxValues);
		entitiesCombobox.addActionListener(listener);
		northPanel.add(entityLabel);
		northPanel.add(entitiesCombobox, BorderLayout.NORTH);
		

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		actionLabel = new JLabel("Choisissez une action :");
		
		fillRadioButton = new JRadioButton("Remplir");
		fillRadioButton.setSelected(true);
		emptyRadioButton = new JRadioButton("Vider");
		buttonGroup = new ButtonGroup();
		
		buttonGroup.add(fillRadioButton);
		buttonGroup.add(emptyRadioButton);

		centerPanel.add(actionLabel);
		centerPanel.add(fillRadioButton);
		centerPanel.add(emptyRadioButton);
		
		validateChoicesButton = new JButton("Valider");
		validateChoicesButton.addActionListener(listener);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(validateChoicesButton, BorderLayout.SOUTH);
		
	}

	/**
	 * @return the entitiesCombobox
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getEntitiesCombobox() {
		return entitiesCombobox;
	}

	/**
	 * @return the fillRadioButton
	 */
	public JRadioButton getFillRadioButton() {
		return fillRadioButton;
	}

	/**
	 * @return the emptyRadioButton
	 */
	public JRadioButton getEmptyRadioButton() {
		return emptyRadioButton;
	}

	/**
	 * @return the validateChoicesButton
	 */
	public JButton getValidateChoicesButton() {
		return validateChoicesButton;
	}
	
	
}
