package com.monitrack.gui.panel;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.monitrack.entity.SensorShop;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorType;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class NeedsTab extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//COMBOBOX
	private JComboBox<String> actionsCombobox;
	//NORTHPANEL (COMBOBOX + CREATE BUTTON)
	private JPanel northPanel;
	private JButton JBCreate;
	//LAYOUT FOR ACTIONLISTENER
	private CardLayout cards;
	//CENTERPANEL FOR CARDLAYOUT
	private JPanel cardPanel;



	//PANEL NEWHOME
	private JPanel JPnewHome;

	//TITLE NEWHOME
	private JLabel jlTitleNewHome;

	//LEFT PANEL PRODUCT NEWHOME
	private JPanel productPaneNewHome;

	//PARTS NEWHOME
	private JLabel jlPartsNameNewHome;
	private JTextField jtfPartsNameNewHome;

	private JLabel jlPartsSizeNewHome;
	private JTextField jtfPartsSizeNewHome;

	private JButton jbNewPartsButtonNewHome;



	//BASKETPANEL NEWHOME
	private JPanel basketPanelNewHome;

	//TITLE BASKETPANEL NEWHOME
	private JLabel jlListTitleNewHome;
	//BASKET SCROLL NEWHOME
	JScrollPane jspBasketScrollNewHome;
	//BASKET LIST AND INSTANCIATION FOR NEW HOME
	private BasketArc 					basketNewHome					= new BasketArc();
	private DefaultListModel<String>dlmBasketLinesNewHome 		= new DefaultListModel<String>();
	private JList<String>  			jlBasketLinesNewHome			= new JList<String>(dlmBasketLinesNewHome);





	//PANEL GROWING
	private JPanel JPhomeGrowing;

	//TITLE GROWING
	private JLabel jlTitleGrowing;

	//PRODUCT PANEL GROWING
	private JPanel productPaneGrowing;

	//CORRIDORS GROWING
	private JLabel jlPartsNameGrowing;
	private JTextField jtfPartsNameGrowing;

	private JLabel jlPartsSizeGrowing;
	private JTextField jtfPartsSizeGrowing;

	private JButton jbNewPartsButtonGrowing;



	//BASKET PANEL GROWING
	private JPanel basketPaneGrowing;
	//BASKET PANEL TITLE GROWING
	private JLabel jlListTitleGrowing;
	//BASKET SCROLL GROWING
	private JScrollPane jspBasketScrollGrowing;
	//BASKET LIST AND INSTANCIATION FOR NEW HOME
	private BasketArc 					basketGrowing					= new BasketArc();
	private DefaultListModel<String>dlmBasketLinesGrowing  			= new DefaultListModel<String>();
	private JList<String>  			jlBasketLinesGrowing			= new JList<String>(dlmBasketLinesGrowing);




	//PANEL ADDSENSOR
	private JLabel jlTitleAddSensor;
	private JPanel JPaddSensor;
	private JPanel jpProductPanelAddSensor;
	private JScrollPane jspProductPanelAddSensor;
	private JPanel jpBasketPanelAddSensor;
	private JScrollPane jspBasketPanelAddSensor;
	private JButton jbAddSensorToBasket;
	private JLabel lblTotalPriceAddSensor;
	private JLabel lblTotalInterviewPriceAddSensor;
	private JButton jbOneMoreAddSensor;
	private JButton jbOneLessAddSensor;
	private JButton jbDeleteAddSensor;

	private ArrayList<SensorShop> 		alListSensorsAddSensor	 = new ArrayList<SensorShop>();
	private DefaultListModel<String>dlmSensorsAddSensor			= new DefaultListModel<String>();
	private JList<String> 			jlSensorsNameAddSensor  	= new JList<String>(dlmSensorsAddSensor);

	//private BasketSensor 					basketAddSensor		= new BasketSensor();
	private DefaultListModel<String>dlmBasketLineAddSensor 		= new DefaultListModel<String>();
	private JList<String>  			jlBasketLineAddSensor		= new JList<String>(dlmBasketLineAddSensor);

	/**
	 * Create the panel.
	 */
	public NeedsTab() {

		northPanel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		cards = new CardLayout(0,0);

		cardPanel = new JPanel();


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

		//NEW HOME PANEL SETTINGS AND CONFIGURATION - PRODUCTS AND BASKET TEMPLATE
		JPnewHome = new JPanel();
		//TODO NEXTLINETODELETE
		cardPanel.add(JPnewHome, "1");
		JPnewHome.setLayout(null);

		//TITLE FOR NEWHOME
		jlTitleNewHome = new JLabel("Création de devis : installation de capteurs sur nouvelle résidence");
		jlTitleNewHome.setFont(new Font("Calibri", Font.BOLD, 15));
		jlTitleNewHome.setBounds(10, 0, 425, 14);
		JPnewHome.add(jlTitleNewHome);

		//PANEL PRODUCT ON LEFT NEWHOME
		productPaneNewHome = new JPanel();
		productPaneNewHome.setBounds(10, 22, 425, 262);
		JPnewHome.add(productPaneNewHome);
		productPaneNewHome.setLayout(null);
		//part's name of NEWHOME
		jlPartsNameNewHome = new JLabel("Ajouter une pièce  -   Entrez le nom de la pièce  :");
		jlPartsNameNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsNameNewHome.setBounds(10, 0, 354, 37);
		productPaneNewHome.add(jlPartsNameNewHome);

		jtfPartsNameNewHome = new JTextField();
		jtfPartsNameNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsNameNewHome.setBounds(313, 4, 102, 28);
		productPaneNewHome.add(jtfPartsNameNewHome);
		jtfPartsNameNewHome.setColumns(10);
		//Area for parts of new home panel
		jlPartsSizeNewHome = new JLabel("Ajouter une pièce  -   Entrez la superficie (en m²) :");
		jlPartsSizeNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsSizeNewHome.setBounds(10, 29, 354, 37);
		productPaneNewHome.add(jlPartsSizeNewHome);

		jtfPartsSizeNewHome = new JTextField();
		jtfPartsSizeNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsSizeNewHome.setColumns(10);
		jtfPartsSizeNewHome.setBounds(323, 34, 62, 28);
		productPaneNewHome.add(jtfPartsSizeNewHome);

		jbNewPartsButtonNewHome = new JButton("Ajouter la pièce");
		jbNewPartsButtonNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbNewPartsButtonNewHome.addActionListener(this);
		jbNewPartsButtonNewHome.setBounds(229, 75, 164, 37);
		productPaneNewHome.add(jbNewPartsButtonNewHome);


		//BASKETPANEL FOR NEW HOME
		basketPanelNewHome = new JPanel();
		basketPanelNewHome.setBounds(445, 11, 473, 273);
		JPnewHome.add(basketPanelNewHome);
		basketPanelNewHome.setLayout(null);
		//TITLE OF SCROLL FOR NEW HOME
		jlListTitleNewHome = new JLabel("Liste des éléments ajoutés à la nouvelle résidence :");
		jlListTitleNewHome.setFont(new Font("Calibri", Font.PLAIN, 13));
		jlListTitleNewHome.setBounds(10, 11, 326, 14);
		basketPanelNewHome.add(jlListTitleNewHome);
		//SCROLL FOR PRODUCTS OF NEW HOME
		jspBasketScrollNewHome = new JScrollPane(this.jlBasketLinesNewHome);
		jspBasketScrollNewHome.setBounds(10, 29, 453, 222);
		basketPanelNewHome.add(jspBasketScrollNewHome);		


		//HomeGrowing PANEL SETTINGS AND CONFIGURATION - PRODUCTS AND BASKET TEMPLATE
		JPhomeGrowing = new JPanel();
		//NextLine to delete
		cardPanel.add(JPhomeGrowing, "2");
		JPhomeGrowing.setLayout(null);

		//TITLE GROWING
		jlTitleGrowing = new JLabel("Création de devis : installation de capteurs pour un agrandissement");
		jlTitleGrowing.setFont(new Font("Calibri", Font.BOLD, 15));
		jlTitleGrowing.setBounds(10, 0, 425, 14);
		JPhomeGrowing.add(jlTitleGrowing);

		//PANEL PRODUCT ON LEFT GROWING
		productPaneGrowing = new JPanel();
		productPaneGrowing.setBounds(10, 22, 425, 262);
		JPhomeGrowing.add(productPaneGrowing);
		productPaneGrowing.setLayout(null);
		//part's name of home growing
		jlPartsNameGrowing = new JLabel("Ajouter une pièce  -   Entrez le nom de la pièce  :");
		jlPartsNameGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsNameGrowing.setBounds(10, 0, 354, 37);
		productPaneGrowing.add(jlPartsNameGrowing);

		jtfPartsNameGrowing = new JTextField();
		jtfPartsNameGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsNameGrowing.setBounds(313, 4, 102, 28);
		productPaneGrowing.add(jtfPartsNameGrowing);
		jtfPartsNameGrowing.setColumns(10);
		//Area for parts of homegrowing panel
		jlPartsSizeGrowing = new JLabel("Ajouter une pièce  -   Entrez la superficie (en m²) :");
		jlPartsSizeGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsSizeGrowing.setBounds(10, 29, 354, 37);
		productPaneGrowing.add(jlPartsSizeGrowing);

		jtfPartsSizeGrowing= new JTextField();
		jtfPartsSizeGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsSizeGrowing.setColumns(10);
		jtfPartsSizeGrowing.setBounds(323, 34, 62, 28);
		productPaneGrowing.add(jtfPartsSizeGrowing);

		jbNewPartsButtonGrowing = new JButton("Ajouter la pièce");
		jbNewPartsButtonGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbNewPartsButtonGrowing.addActionListener(this);
		jbNewPartsButtonGrowing.setBounds(229, 75, 164, 37);
		productPaneGrowing.add(jbNewPartsButtonGrowing);

		//BASKETPANEL FOR GROWING
		basketPaneGrowing = new JPanel();
		basketPaneGrowing.setBounds(445, 11, 473, 273);
		JPhomeGrowing.add(basketPaneGrowing);
		basketPaneGrowing.setLayout(null);
		//TITLE OF SCROLL FOR GROWING
		jlListTitleGrowing = new JLabel("Liste des éléments ajoutés pour l'agrandissement de la résidence :");
		jlListTitleGrowing.setFont(new Font("Calibri", Font.PLAIN, 13));
		jlListTitleGrowing.setBounds(10, 11, 326, 14);
		basketPaneGrowing.add(jlListTitleGrowing);
		//SCROLL FOR PRODUCTS OF GROWING
		jspBasketScrollGrowing = new JScrollPane(this.jlBasketLinesGrowing);
		jspBasketScrollGrowing.setBounds(10, 29, 453, 222);
		basketPaneGrowing.add(jspBasketScrollGrowing);	



		//PANEL ADDSENSOR
		JPaddSensor = new JPanel();
		JPaddSensor.setLayout(null);
		cardPanel.add(JPaddSensor, "3");
		//TITLE
		jlTitleAddSensor = new JLabel("Devis installation de capteurs suppl\u00E9mentaires");
		jlTitleAddSensor.setFont(new Font("Calibri", Font.BOLD, 15));
		jlTitleAddSensor.setBounds(10, 0, 369, 14);
		JPaddSensor.add(jlTitleAddSensor);

		jpProductPanelAddSensor = new JPanel();
		jpProductPanelAddSensor.setLayout(null);
		jpProductPanelAddSensor.setBounds(10, 21, 425, 262);
		JPaddSensor.add(jpProductPanelAddSensor);

		jspProductPanelAddSensor = new JScrollPane(this.jlSensorsNameAddSensor);
		jspProductPanelAddSensor.setBounds(0, 0, 425, 262);
		jpProductPanelAddSensor.add(jspProductPanelAddSensor);

		jpBasketPanelAddSensor = new JPanel();
		jpBasketPanelAddSensor.setLayout(null);
		jpBasketPanelAddSensor.setBounds(511, 21, 425, 262);
		JPaddSensor.add(jpBasketPanelAddSensor);

		jspBasketPanelAddSensor = new JScrollPane(this.jlBasketLineAddSensor);
		jspBasketPanelAddSensor.setBounds(0, 0, 425, 262);
		jpBasketPanelAddSensor.add(jspBasketPanelAddSensor);

		jbAddSensorToBasket = new JButton("Ajouter un capteur");
		jbAddSensorToBasket.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbAddSensorToBasket.setBounds(278, 292, 157, 43);
		JPaddSensor.add(jbAddSensorToBasket);

		lblTotalPriceAddSensor = new JLabel("Prix Total :");
		lblTotalPriceAddSensor.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblTotalPriceAddSensor.setBounds(777, 293, 159, 42);
		JPaddSensor.add(lblTotalPriceAddSensor);

		lblTotalInterviewPriceAddSensor = new JLabel("Cout total de la maintenance à l'année :");
		lblTotalInterviewPriceAddSensor.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblTotalInterviewPriceAddSensor.setBounds(613, 324, 239, 42);
		JPaddSensor.add(lblTotalInterviewPriceAddSensor);

		jbOneMoreAddSensor = new JButton("+");
		jbOneMoreAddSensor.setBounds(946, 91, 89, 23);
		JPaddSensor.add(jbOneMoreAddSensor);

		jbOneLessAddSensor = new JButton("-");
		jbOneLessAddSensor.setBounds(946, 125, 89, 23);
		JPaddSensor.add(jbOneLessAddSensor);

		jbDeleteAddSensor = new JButton("Supprimer");
		jbDeleteAddSensor.setBounds(946, 159, 89, 23);
		JPaddSensor.add(jbDeleteAddSensor);
		dataSensors();

	}

	public void dataSensors()
	{
		/*this.alListSensorsAddSensor.add(new SensorShop(1,"Olympia",SensorType.ACCESS_CONTROL, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 22.97, 70));
		this.alListSensorsAddSensor.add(new SensorShop(2,"Dexlan",SensorType.ACCESS_CONTROL, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 66.28, 35));
		this.alListSensorsAddSensor.add(new SensorShop(3,"FIREANGEL",SensorType.SMOKE, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f,  15.90, 70));
		this.alListSensorsAddSensor.add(new SensorShop(4,"LifeBox",SensorType.SMOKE, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 29.90, 35));
		this.alListSensorsAddSensor.add(new SensorShop(5,"ORNO",SensorType.FLOW, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 12.95, 70));
		this.alListSensorsAddSensor.add(new SensorShop(6,"Led Kia",SensorType.FLOW, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 21.09, 35));*/
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorShop.class, null, null, null,
					null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			// Retrieves all the sensor from the database
			List<SensorShop> shops = (List<SensorShop>)JsonUtil.deserializeObject(response);

			for(SensorShop s : shops) {
				this.alListSensorsAddSensor.add(s);
				System.out.println("==========> " + s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (SensorShop s : this.alListSensorsAddSensor)
		{
			this.dlmSensorsAddSensor.addElement(s.getSensorMark()+" "+s.getSensorType());
		}
	}

	public static void main(String[] args) {
		new NeedsTab().dataSensors();
	}

	public Integer intInside(JTextField jtf)//5
	{
		try
		{
			return new Integer(jtf.getText());
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == JBCreate){
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Création d'une nouvelle résidence"))
			{
				//UNCOMMENT ALL ADD TO TEST AND FINISH TODO
				//				cardPanel.add(JPnewHome, "1");
				cards.show(cardPanel, "1");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Agrandissement"))
			{
				//				cardPanel.add(JPhomeGrowing, "2");
				cards.show(cardPanel, "2");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Devis de capteurs simple"))
			{
				//				cardPanel.add(JPaddSensor, "3");
				cards.show(cardPanel, "3");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
		}

		if(e.getSource() == this.jbNewPartsButtonNewHome)
		{
			if(!jtfPartsSizeNewHome.getText().isEmpty() || jtfPartsNameNewHome.getText().isEmpty() ){
				int c1 = intInside(jtfPartsSizeNewHome);
				basketNewHome.addArc(new ArchitectureBuilder(jtfPartsNameNewHome.getText(), c1));
				this.dlmBasketLinesNewHome.clear();
				this.jtfPartsNameNewHome.setText("");
				this.jtfPartsSizeNewHome.setText("");
				for (CommandLineArc lignecommande : basketNewHome.alCommandLineArc)
				{
					this.dlmBasketLinesNewHome.addElement(lignecommande.getArc().getNom()+" - superficie: "+lignecommande.getArc().getArea()+"m²" );
				}
			}
			else{JOptionPane.showMessageDialog(this, "Veuillez saisir correctement les données");}


		}

		if(e.getSource() == this.jbNewPartsButtonGrowing)
		{
			int c1 = intInside(jtfPartsSizeGrowing);
			basketGrowing.addArc(new ArchitectureBuilder(jtfPartsNameGrowing.getText(), c1));
			this.dlmBasketLinesGrowing.clear();
			this.jtfPartsNameGrowing.setText("");
			this.jtfPartsSizeGrowing.setText("");
			for (CommandLineArc cl : basketGrowing.alCommandLineArc)
			{
				this.dlmBasketLinesGrowing.addElement(cl.getArc().getNom()+" - superficie: "+cl.getArc().getArea()+"m²" );
			}
		}

		if(e.getSource() == this.jbAddSensorToBasket)
		{
			int index = jlSensorsNameAddSensor.getSelectedIndex();
			/*basketAddSensor.addSensor(1, this.alListSensorsAddSensor.get(jlSensorsNameAddSensor.getSelectedIndex()));
					this.dlmBasketLineAddSensor.clear();
					for (CommandLineSensor cl : basketAddSensor.alCommandLineSensor)
					{
						this.dlmBasketLineAddSensor.addElement(cl.getQuantity()+" "+cl.getSensor().getSensorMark());
					}*/
			this.jlBasketLineAddSensor.setSelectedIndex(index);
			this.jlSensorsNameAddSensor.setSelectedIndex(index);
		}
		//		if(ae.getSource() == this.jbAjouterUnProduitAuPanier)
		//		{
		//			int index = jlNomDesProduits.getSelectedIndex();
		//			lePanier.ajouter(1, this.lesProduits.get(jlNomDesProduits.getSelectedIndex()));
		//			this.dlmLesLignesDuPanier.clear();
		//			for (CommandLine lignecommande : lePanier.alLesLignes)
		//			{
		//				this.dlmLesLignesDuPanier.addElement(lignecommande.getQuantité()+" "+lignecommande.getProduit().getNom());
		//			}
		//			this.jlLesLignesDuPanier.setSelectedIndex(index);
		//			this.jlNomDesProduits.setSelectedIndex(index);
		//		}
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

	/**
	 * @return the jtfCorridorSizeNewHome
	 */
	public JTextField getJtfCorridorSizeNewHome() {
		return jtfPartsNameNewHome;
	}

	/**
	 * @param jtfCorridorSizeNewHome the jtfCorridorSizeNewHome to set
	 */
	public void setJtfCorridorSizeNewHome(JTextField jtfCorridorSizeNewHome) {
		this.jtfPartsNameNewHome = jtfCorridorSizeNewHome;
	}


	/**
	 * @return the productPaneGrowing
	 */
	public JPanel getProductPaneGrowing() {
		return productPaneGrowing;
	}

	/**
	 * @param productPaneGrowing the productPaneGrowing to set
	 */
	public void setProductPaneGrowing(JPanel productPaneGrowing) {
		this.productPaneGrowing = productPaneGrowing;
	}

	/**
	 * @return the jlCorridorGrowing
	 */
	public JLabel getJlCorridorGrowing() {
		return jlPartsNameGrowing;
	}

	/**
	 * @param jlCorridorGrowing the jlCorridorGrowing to set
	 */
	public void setJlCorridorGrowing(JLabel jlCorridorGrowing) {
		this.jlPartsNameGrowing = jlCorridorGrowing;
	}


	/**
	 * @return the jtfPartsSizeGrowing
	 */
	public JTextField getJtfPartsSizeGrowing() {
		return jtfPartsSizeGrowing;
	}

	/**
	 * @param jtfPartsSizeGrowing the jtfPartsSizeGrowing to set
	 */
	public void setJtfPartsSizeGrowing(JTextField jtfPartsSizeGrowing) {
		this.jtfPartsSizeGrowing = jtfPartsSizeGrowing;
	}


	/**
	 * @return the basketPaneGrowing
	 */
	public JPanel getBasketPaneGrowing() {
		return basketPaneGrowing;
	}

	/**
	 * @param basketPaneGrowing the basketPaneGrowing to set
	 */
	public void setBasketPaneGrowing(JPanel basketPaneGrowing) {
		this.basketPaneGrowing = basketPaneGrowing;
	}

	/**
	 * @return the jspBasketScrollGrowing
	 */
	public JScrollPane getJspBasketScrollGrowing() {
		return jspBasketScrollGrowing;
	}

	/**
	 * @param jspBasketScrollGrowing the jspBasketScrollGrowing to set
	 */
	public void setJspBasketScrollGrowing(JScrollPane jspBasketScrollGrowing) {
		this.jspBasketScrollGrowing = jspBasketScrollGrowing;
	}

	/**
	 * @return the jlTitleAddSensor
	 */
	public JLabel getJlTitleAddSensor() {
		return jlTitleAddSensor;
	}

	/**
	 * @param jlTitleAddSensor the jlTitleAddSensor to set
	 */
	public void setJlTitleAddSensor(JLabel jlTitleAddSensor) {
		this.jlTitleAddSensor = jlTitleAddSensor;
	}

	/**
	 * @return the jlTitleGrowing
	 */
	public JLabel getJlTitleGrowing() {
		return jlTitleGrowing;
	}

	/**
	 * @param jlTitleGrowing the jlTitleGrowing to set
	 */
	public void setJlTitleGrowing(JLabel jlTitleGrowing) {
		this.jlTitleGrowing = jlTitleGrowing;
	}

	/**
	 * @return the jlTitleNewHome
	 */
	public JLabel getJlTitleNewHome() {
		return jlTitleNewHome;
	}

	/**
	 * @param jlTitleNewHome the jlTitleNewHome to set
	 */
	public void setJlTitleNewHome(JLabel jlTitleNewHome) {
		this.jlTitleNewHome = jlTitleNewHome;
	}

	/**
	 * @return the productPaneNewHome
	 */
	public JPanel getProductPaneNewHome() {
		return productPaneNewHome;
	}

	/**
	 * @param productPaneNewHome the productPaneNewHome to set
	 */
	public void setProductPaneNewHome(JPanel productPaneNewHome) {
		this.productPaneNewHome = productPaneNewHome;
	}

	/**
	 * @return the jlCorridorNewHome
	 */
	public JLabel getJlCorridorNewHome() {
		return jlPartsNameNewHome;
	}

	/**
	 * @param jlCorridorNewHome the jlCorridorNewHome to set
	 */
	public void setJlCorridorNewHome(JLabel jlCorridorNewHome) {
		this.jlPartsNameNewHome = jlCorridorNewHome;
	}

	/**
	 * @return the jbCorridorButtonNewHome
	 */
	public JButton getJbCorridorButtonNewHome() {
		return jbNewPartsButtonNewHome;
	}

	/**
	 * @param jbCorridorButtonNewHome the jbCorridorButtonNewHome to set
	 */
	public void setJbCorridorButtonNewHome(JButton jbCorridorButtonNewHome) {
		this.jbNewPartsButtonNewHome = jbCorridorButtonNewHome;
	}

	/**
	 * @return the basketPanelNewHome
	 */
	public JPanel getBasketPanelNewHome() {
		return basketPanelNewHome;
	}

	/**
	 * @param basketPanelNewHome the basketPanelNewHome to set
	 */
	public void setBasketPanelNewHome(JPanel basketPanelNewHome) {
		this.basketPanelNewHome = basketPanelNewHome;
	}

	/**
	 * @return the jspBasketScrollNewHome
	 */
	public JScrollPane getJspBasketScrollNewHome() {
		return jspBasketScrollNewHome;
	}

	/**
	 * @param jspBasketScrollNewHome the jspBasketScrollNewHome to set
	 */
	public void setJspBasketScrollNewHome(JScrollPane jspBasketScrollNewHome) {
		this.jspBasketScrollNewHome = jspBasketScrollNewHome;
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
}
