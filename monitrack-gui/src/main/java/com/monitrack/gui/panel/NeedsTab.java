package com.monitrack.gui.panel;

import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.monitrack.listener.LocationsTabListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.CardLayout;

public class NeedsTab extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	//COMBOBOX
	private JComboBox<String> actionsCombobox;
	//NORTHPANEL (COMBOBOX + CREATE BUTTON)
	private JPanel northPanel;
	private JButton JBCreate;
	//LAYOUT FOR ACTIONLISTENER
	private CardLayout cards;
	//CENTERPANEL WITH CARDLAYOUT
	private JPanel cardPanel;
	private JPanel JPnewHome;
	private JPanel JPhomeGrowing;
	private JPanel JPaddSensor;
	
	private JLabel lblTest;
	private JLabel lblGrowing;
	private JLabel lblCapteurs;
	
	

	/**
	 * Create the panel.
	 */
	public NeedsTab() {
		
		northPanel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		cards = new CardLayout(0,0);
		
		cardPanel = new JPanel();
		
		JPnewHome = new JPanel();
		JPhomeGrowing = new JPanel();
		JPaddSensor = new JPanel();
		
		
		//Font Properties
		Font textAreaFont = new Font("Calibri", Font.PLAIN, 12);
		
		add(northPanel, BorderLayout.NORTH);
		
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Components of NorthPanel
		JLabel create = new JLabel("Créer un devis :");
		JBCreate = new JButton("Créer");
		JBCreate.addActionListener(this);
		actionsCombobox = new JComboBox<String>();
		actionsCombobox.setFont(textAreaFont);
		actionsCombobox.setModel(new DefaultComboBoxModel<String>(new String[] {"Création d'une nouvelle résidence", "Agrandissement", "Devis de capteurs simple"}));
		
		actionsCombobox.addActionListener(this);
		northPanel.add(actionsCombobox);
		create.setFont(textAreaFont);
		northPanel.add(create);
		northPanel.add(JBCreate);
		
		
		//Panel for CardLayout
		
		cardPanel.setLayout(cards);
		
		
		add(cardPanel, BorderLayout.CENTER);
	
		//CardPanel.panels are generate by actionPerformed
		
		//TESTFORPANELS
		lblTest = new JLabel("test1");
		JPnewHome.add(lblTest);
		lblGrowing = new JLabel("growing");
		JPhomeGrowing.add(lblGrowing);
		lblCapteurs = new JLabel("capteurs");
		JPaddSensor.add(lblCapteurs);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == JBCreate){
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Création d'une nouvelle résidence"))
			{
				cardPanel.add(JPnewHome, "1");
				cards.show(cardPanel, "1");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Agrandissement"))
			{
				cardPanel.add(JPhomeGrowing, "2");
				cards.show(cardPanel, "2");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Devis de capteurs simple"))
			{
				cardPanel.add(JPaddSensor, "3");
				cards.show(cardPanel, "3");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
		}
	}

	public void setActionsCombobox(JComboBox<String> actionsCombobox) {
		this.actionsCombobox = actionsCombobox;
	}

	public JComboBox<String> getActionsCombobox() {
		return actionsCombobox;
	}

	public JPanel getNorthPanel() {
		return northPanel;
	}

	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}

	/**
	 * @return the cards
	 */
	public CardLayout getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(CardLayout cards) {
		this.cards = cards;
	}

	/**
	 * @return the jPnewHome
	 */
	public JPanel getJPnewHome() {
		return JPnewHome;
	}

	/**
	 * @param jPnewHome the jPnewHome to set
	 */
	public void setJPnewHome(JPanel jPnewHome) {
		JPnewHome = jPnewHome;
	}

	/**
	 * @return the jPhomeGrowing
	 */
	public JPanel getJPhomeGrowing() {
		return JPhomeGrowing;
	}

	/**
	 * @param jPhomeGrowing the jPhomeGrowing to set
	 */
	public void setJPhomeGrowing(JPanel jPhomeGrowing) {
		JPhomeGrowing = jPhomeGrowing;
	}

	/**
	 * @return the jPaddSensor
	 */
	public JPanel getJPaddSensor() {
		return JPaddSensor;
	}

	/**
	 * @param jPaddSensor the jPaddSensor to set
	 */
	public void setJPaddSensor(JPanel jPaddSensor) {
		JPaddSensor = jPaddSensor;
	}

	/**
	 * @return the jBCreate
	 */
	public JButton getJBCreate() {
		return JBCreate;
	}

	/**
	 * @param jBCreate the jBCreate to set
	 */
	public void setJBCreate(JButton jBCreate) {
		JBCreate = jBCreate;
	}

	/**
	 * @return the cardPanel
	 */
	public JPanel getCardPanel() {
		return cardPanel;
	}

	/**
	 * @param cardPanel the cardPanel to set
	 */
	public void setCardPanel(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
}
