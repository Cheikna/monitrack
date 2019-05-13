package com.monitrack.gui.panel;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.monitrack.entity.SensorShop;
import com.monitrack.enumeration.Energy;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorType;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;
import javax.swing.JCheckBox;


public class NeedsTab extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//COMBOBOX
	private JComboBox<String> actionsCombobox;
	//NORTHPANEL (COMBOBOX + CREATE BUTTON)
	private JPanel northPanel;
	private JLabel create;
	private JButton jbCreate;
	//LAYOUT FOR ACTIONLISTENER
	private CardLayout cards;
	//CENTERPANEL FOR CARDLAYOUT
	private JPanel cardPanel;

	private List<SensorShop> shops ;



	//PANEL NEWHOME
	private JPanel jpNewHome;
	private JLabel jlTitleNewHome;


	//LEFT PANEL PRODUCT NEWHOME
	private JPanel productPaneNewHome;
	//PARTS NEWHOME
	private JLabel jlPartsNameNewHome;
	private JTextField jtfPartsNameNewHome;

	private JLabel jlPartsSizeNewHome;
	private JTextField jtfPartsSizeNewHome;

	private JLabel jlPartsDoorNumberNewHome;
	private JTextField jtfPartsDoorNumberNewHome;

	private JLabel jlPartsWindowNumberNewHome;
	private JTextField jtfPartsWindowNumberNewHome;

	private JButton jbNewPartsButtonNewHome;
	private JButton jbDeletePartsNewHome;

	private JLabel jlPartsAccessControlNewHome;
	private JCheckBox checkBoxAccessControlNewHome;


	//BASKETPANEL OF BUILDER NEWHOME
	private JLabel jlListTitleNewHome;
	private JPanel basketPanelNewHome;
	private JScrollPane jspBasketScrollNewHome;
	//BASKET LIST BUILDER AND INSTANCIATION FOR NEW HOME
	private BasketArc 					basketNewHome					= new BasketArc();
	private DefaultListModel<String>dlmBasketLinesNewHome 		= new DefaultListModel<String>();
	private JList<String>  			jlBasketLinesNewHome			= new JList<String>(dlmBasketLinesNewHome);


	//BUTTON TO INCREMENT SENSORSHOP LIST IN PANEL BASKET SENSORSHOP FROM BUILDER
	private JButton jbAddSensorShopToBasketFromBuilderNewHome;

	//BASKET PANEL OF SENSORSHOP FROM BUILDER
	private JLabel jlbTitleBasketBuilderToSensorShopNewHome;
	private JPanel jpBasketBuilderToSensoShopNewHome;
	private JScrollPane jspBasketBuilderToSensoShopNewHome;
	private JLabel jlbTotalPriceOfSensorShopBasketNewHome;
	private JLabel jlbTotalInterviewCostOfSensorShopBasketNewHome;
	private JLabel jlProvisionnalNewHome;
	private JTextField jtfYearsProvisionnalNewHome;
	private JLabel jlYearProvisionnalNewHome;
	private JButton jbGenerateProvisionnalNewHome;
	private JLabel jlbTotalProvisionnalNewHome;
	private JButton jbClearSensorShopNewHome;
	private JButton jbCapturNewHome;

	//BASKET LIST AND INSTANCIATION
	private BasketSensor basketSensorShopFromBuilderNewHome   						= new BasketSensor();
	private DefaultListModel<String>dlmBasketSensorShopLinesNewHome 		= new DefaultListModel<String>();
	private JList<String>  			jlBasketSensorShopLinesNewHome			= new JList<String>(dlmBasketSensorShopLinesNewHome);
	
	
	
	
	
	//PANEL GROWING
	private JPanel jpGrowing;
	private JLabel jlTitleGrowing;


	//LEFT PANEL PRODUCT GROWING
	private JPanel productPaneGrowing;
	
	//PARTS GROWING
	private JLabel jlPartsNameGrowing;
	private JTextField jtfPartsNameGrowing;

	private JLabel jlPartsSizeGrowing;
	private JTextField jtfPartsSizeGrowing;

	private JLabel jlPartsDoorNumberGrowing;
	private JTextField jtfPartsDoorNumberGrowing;

	private JLabel jlPartsWindowNumberGrowing;
	private JTextField jtfPartsWindowNumberGrowing;

	private JButton jbNewPartsButtonGrowing;
	private JButton jbDeletePartsGrowing;

	private JLabel jlPartsAccessControlGrowing;
	private JCheckBox checkBoxAccessControlGrowing;


	//BASKETPANEL OF BUILDER GROWING PANEL
	private JLabel jlListTitleGrowing;
	private JPanel basketPanelGrowing;
	private JScrollPane jspBasketScrollGrowing;
	//BASKET LIST BUILDER AND INSTANCIATION FOR GROWING PANEL
	private BasketArc 					basketGrowing		= new BasketArc();
	private DefaultListModel<String>dlmBasketLinesGrowing 	= new DefaultListModel<String>();
	private JList<String>  			jlBasketLinesGrowing	= new JList<String>(dlmBasketLinesGrowing);


	//BUTTON TO INCREMENT SENSORSHOP LIST IN PANEL BASKET SENSORSHOP FROM BUILDER GROWING
	private JButton jbAddSensorShopToBasketFromBuilderGrowing;

	//BASKET PANEL OF SENSORSHOP FROM BUILDER GROWING
	private JLabel jlbTitleBasketBuilderToSensorShopGrowing;
	private JPanel jpBasketBuilderToSensoShopGrowing;
	private JScrollPane jspBasketBuilderToSensoShopGrowing;
	private JLabel jlbTotalPriceOfSensorShopBasketGrowing;
	private JLabel jlbTotalInterviewCostOfSensorShopBasketGrowing;
	private JLabel jlProvisionnalGrowing;
	private JTextField jtfYearsProvisionnalGrowing;
	private JLabel jlYearProvisionnalGrowing;
	private JButton jbGenerateProvisionnalGrowing;
	private JLabel jlbTotalProvisionnalGrowing;
	private JButton jbClearSensorShopGrowing;
	private JButton jbCapturGrowing;

	//BASKET LIST AND INSTANCIATION FOR GROWING PANEL
	private BasketSensor basketSensorShopFromBuilderGrowing   			= new BasketSensor();
	private DefaultListModel<String>dlmBasketSensorShopLinesGrowing 	= new DefaultListModel<String>();
	private JList<String>  			jlBasketSensorShopLinesGrowing		= new JList<String>(dlmBasketSensorShopLinesGrowing);



	
	
	//PANEL ADDSENSOR
	private JLabel jlbTitleAddSensor;
	private JPanel jpAddSensor;
	private JPanel jpProductPanelAddSensor;
	private JScrollPane jspProductPanelAddSensor;
	private JPanel jpBasketPanelAddSensor;
	private JScrollPane jspBasketPanelAddSensor;
	private JButton jbAddSensorToBasket;
	private JLabel jlbTotalPriceAddSensor;
	private JLabel jlbTotalInterviewPriceAddSensor;
	private JButton jbOneMoreAddSensor;
	private JButton jbOneLessAddSensor;
	private JButton jbDeleteAddSensor;
	
	//Provisionnal maker
	private JLabel jlbProvisionnalYearAddSensor;
	private JTextField jtfProvisionnalAddSensor;
	private JLabel jlbProvisionnalAddSensor;
	private JButton jbGenerateProvisionnalAddSensor;
	private JLabel jlbTotalProvisionnalAddSensor;
	//Captur
	private JButton jbCapturAddSensor;

	//Product panel list and instanciation for addsensor
	private ArrayList<SensorShop> 		alListSensorsAddSensor	= new ArrayList<SensorShop>();
	private DefaultListModel<String>dlmSensorsAddSensor			= new DefaultListModel<String>();
	private JList<String> 			jlSensorsNameAddSensor  	= new JList<String>(dlmSensorsAddSensor);
	//BasketPanel List and instanciation for addsensor panel
	//Title of Basket Panel
	private JLabel jlbBasketTitleAddSensorPanel;
	private BasketSensor 					basketAddSensor				= new BasketSensor();
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
		create = new JLabel("Créer un devis :");
		jbCreate = new JButton("Créer");
		jbCreate.addActionListener(this);
		actionsCombobox = new JComboBox<String>();
		actionsCombobox.setFont(textAreaFont);
		actionsCombobox.setModel(new DefaultComboBoxModel<String>(new String[] {"Création d'une nouvelle résidence", "Agrandissement", "Devis de capteurs simple"}));

		actionsCombobox.addActionListener(this);
		northPanel.add(actionsCombobox);
		create.setFont(textAreaFont);
		northPanel.add(create);
		northPanel.add(jbCreate);

		//Panel for CardLayout
		cardPanel.setLayout(cards);
		add(cardPanel, BorderLayout.CENTER);

		
		
		//NEW HOME PANEL SETTINGS AND CONFIGURATION - PRODUCTS AND BASKET TEMPLATE
		jpNewHome = new JPanel();
		jpNewHome.setLayout(null);

		//TITLE FOR NEWHOME
		jlTitleNewHome = new JLabel("Création de devis : installation de capteurs sur nouvelle résidence");
		jlTitleNewHome.setFont(new Font("Calibri", Font.BOLD, 15));
		jlTitleNewHome.setBounds(10, 0, 425, 14);
		jpNewHome.add(jlTitleNewHome);

		//PANEL PRODUCT ON LEFT NEWHOME
		productPaneNewHome = new JPanel();
		productPaneNewHome.setBounds(10, 22, 425, 191);
		jpNewHome.add(productPaneNewHome);
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
		jtfPartsSizeNewHome.setBounds(353, 34, 62, 28);
		productPaneNewHome.add(jtfPartsSizeNewHome);

		jlPartsDoorNumberNewHome = new JLabel("Ajouter une pièce  -   Entrez le nombre de portes :");
		jlPartsDoorNumberNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsDoorNumberNewHome.setBounds(10, 59, 354, 37);
		productPaneNewHome.add(jlPartsDoorNumberNewHome);

		jtfPartsDoorNumberNewHome = new JTextField();
		jtfPartsDoorNumberNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsDoorNumberNewHome.setColumns(10);
		jtfPartsDoorNumberNewHome.setBounds(353, 64, 62, 28);
		productPaneNewHome.add(jtfPartsDoorNumberNewHome);

		jlPartsWindowNumberNewHome = new JLabel("Ajouter une pièce  -   Entrez le nombre de fenêtre :");
		jlPartsWindowNumberNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsWindowNumberNewHome.setBounds(10, 90, 354, 37);
		productPaneNewHome.add(jlPartsWindowNumberNewHome);

		jtfPartsWindowNumberNewHome = new JTextField();
		jtfPartsWindowNumberNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsWindowNumberNewHome.setColumns(10);
		jtfPartsWindowNumberNewHome.setBounds(353, 95, 62, 28);
		productPaneNewHome.add(jtfPartsWindowNumberNewHome);

		jbNewPartsButtonNewHome = new JButton("Ajouter la pièce");
		jbNewPartsButtonNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbNewPartsButtonNewHome.addActionListener(this);
		jbNewPartsButtonNewHome.setBounds(251, 154, 164, 37);
		productPaneNewHome.add(jbNewPartsButtonNewHome);

		jlPartsAccessControlNewHome = new JLabel("Ajouter une pièce  -   Contrôle d'accès :");
		jlPartsAccessControlNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsAccessControlNewHome.setBounds(10, 117, 256, 37);
		productPaneNewHome.add(jlPartsAccessControlNewHome);

		checkBoxAccessControlNewHome = new JCheckBox("Cochez si oui");
		checkBoxAccessControlNewHome.setSelected(true);
		checkBoxAccessControlNewHome.setBounds(272, 124, 118, 23);
		productPaneNewHome.add(checkBoxAccessControlNewHome);


		//BASKETPANEL FOR NEW HOME
		basketPanelNewHome = new JPanel();
		basketPanelNewHome.setBounds(462, 11, 898, 201);
		jpNewHome.add(basketPanelNewHome);
		basketPanelNewHome.setLayout(null);
		//TITLE OF SCROLL FOR NEW HOME
		jlListTitleNewHome = new JLabel("Liste des pièces ajoutées à la nouvelle résidence :");
		jlListTitleNewHome.setFont(new Font("Calibri", Font.PLAIN, 13));
		jlListTitleNewHome.setBounds(10, 11, 326, 14);
		basketPanelNewHome.add(jlListTitleNewHome);
		//SCROLL FOR PRODUCTS OF NEW HOME
		jspBasketScrollNewHome = new JScrollPane(this.jlBasketLinesNewHome);
		jspBasketScrollNewHome.setBounds(10, 29, 878, 122);
		basketPanelNewHome.add(jspBasketScrollNewHome);		

		jbDeletePartsNewHome = new JButton("Supprimer la pièce");
		jbDeletePartsNewHome.addActionListener(this);
		jbDeletePartsNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbDeletePartsNewHome.setBounds(10, 153, 164, 37);
		basketPanelNewHome.add(jbDeletePartsNewHome);

		jbAddSensorShopToBasketFromBuilderNewHome = new JButton("Générer une liste de capteurs depuis les pi\u00E8ces ajout\u00E9es au panier");
		jbAddSensorShopToBasketFromBuilderNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbAddSensorShopToBasketFromBuilderNewHome.setBounds(375, 160, 513, 30);
		jbAddSensorShopToBasketFromBuilderNewHome.addActionListener(this);
		basketPanelNewHome.add(jbAddSensorShopToBasketFromBuilderNewHome);

		jpBasketBuilderToSensoShopNewHome = new JPanel();
		jpBasketBuilderToSensoShopNewHome.setBounds(10, 253, 1131, 206);
		jpNewHome.add(jpBasketBuilderToSensoShopNewHome);
		jpBasketBuilderToSensoShopNewHome.setLayout(null);

		jspBasketBuilderToSensoShopNewHome = new JScrollPane(this.jlBasketSensorShopLinesNewHome);
		jspBasketBuilderToSensoShopNewHome.setBounds(0, 0, 1131, 206);
		jpBasketBuilderToSensoShopNewHome.add(jspBasketBuilderToSensoShopNewHome);

		jlbTitleBasketBuilderToSensorShopNewHome = new JLabel("Liste des capteurs générés directement depuis la pièce :");
		jlbTitleBasketBuilderToSensorShopNewHome.setFont(new Font("Calibri", Font.PLAIN, 12));
		jlbTitleBasketBuilderToSensorShopNewHome.setBounds(20, 231, 334, 23);
		jpNewHome.add(jlbTitleBasketBuilderToSensorShopNewHome);

		jlbTotalPriceOfSensorShopBasketNewHome = new JLabel("Prix total des capteurs du panier :");
		jlbTotalPriceOfSensorShopBasketNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalPriceOfSensorShopBasketNewHome.setBounds(152, 470, 383, 31);
		jpNewHome.add(jlbTotalPriceOfSensorShopBasketNewHome);

		jlbTotalInterviewCostOfSensorShopBasketNewHome = new JLabel("Coût total de la maintenance des capteurs du panier :");
		jlbTotalInterviewCostOfSensorShopBasketNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalInterviewCostOfSensorShopBasketNewHome.setBounds(31, 500, 471, 31);
		jpNewHome.add(jlbTotalInterviewCostOfSensorShopBasketNewHome);
		
		jbClearSensorShopNewHome = new JButton("Vider la liste de capteurs");
		jbClearSensorShopNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbClearSensorShopNewHome.setBounds(630, 470, 196, 31);
		jbClearSensorShopNewHome.addActionListener(this);
		jpNewHome.add(jbClearSensorShopNewHome);
		
		jlProvisionnalNewHome = new JLabel("Prévisions des coûts sur ");
		jlProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlProvisionnalNewHome.setBounds(20, 534, 168, 43);
		jpNewHome.add(jlProvisionnalNewHome);
		
		jtfYearsProvisionnalNewHome = new JTextField();
		jtfYearsProvisionnalNewHome.setToolTipText("Entrez un nombre d'années (entier - ex : 5 ans)");
		jtfYearsProvisionnalNewHome.setColumns(10);
		jtfYearsProvisionnalNewHome.setBounds(189, 539, 66, 32);
		jpNewHome.add(jtfYearsProvisionnalNewHome);
		
		jlYearProvisionnalNewHome = new JLabel("ans ");
		jlYearProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlYearProvisionnalNewHome.setBounds(265, 534, 110, 43);
		jpNewHome.add(jlYearProvisionnalNewHome);
		
		jbGenerateProvisionnalNewHome = new JButton("Générer les prévisions");
		jbGenerateProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbGenerateProvisionnalNewHome.setBounds(298, 540, 182, 33);
		jbGenerateProvisionnalNewHome.addActionListener(this);
		jpNewHome.add(jbGenerateProvisionnalNewHome);
		
		jlbTotalProvisionnalNewHome = new JLabel("");
		jlbTotalProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalProvisionnalNewHome.setBounds(77, 566, 387, 32);
		jpNewHome.add(jlbTotalProvisionnalNewHome);
		
		jbCapturNewHome = new JButton("Faire une capture du devis");
		jbCapturNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbCapturNewHome.addActionListener(this);
		jbCapturNewHome.setBounds(1151, 333, 207, 23);
		jpNewHome.add(jbCapturNewHome);


		//HomeGrowing PANEL SETTINGS AND CONFIGURATION - PRODUCTS AND BASKET TEMPLATE
		jpGrowing = new JPanel();
		jpGrowing.setLayout(null);

		//TITLE FOR GROWING PANEL
		jlTitleGrowing = new JLabel("Création de devis : installation de capteurs pour un agrandissement");
		jlTitleGrowing.setFont(new Font("Calibri", Font.BOLD, 15));
		jlTitleGrowing.setBounds(10, 0, 425, 14);
		jpGrowing.add(jlTitleGrowing);

		//PANEL PRODUCT ON LEFT GROWING PANEL
		productPaneGrowing = new JPanel();
		productPaneGrowing.setBounds(10, 22, 425, 191);
		jpGrowing.add(productPaneGrowing);
		productPaneGrowing.setLayout(null);

		//part's name of GROWING PANEL
		jlPartsNameGrowing = new JLabel("Ajouter une pièce  -   Entrez le nom de la pièce  :");
		jlPartsNameGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsNameGrowing.setBounds(10, 0, 354, 37);
		productPaneGrowing.add(jlPartsNameGrowing);

		jtfPartsNameGrowing = new JTextField();
		jtfPartsNameGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsNameGrowing.setBounds(313, 4, 102, 28);
		productPaneGrowing.add(jtfPartsNameGrowing);
		jtfPartsNameGrowing.setColumns(10);
		//Area for parts of GROWING PANEL
		jlPartsSizeGrowing = new JLabel("Ajouter une pièce  -   Entrez la superficie (en m²) :");
		jlPartsSizeGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsSizeGrowing.setBounds(10, 29, 354, 37);
		productPaneGrowing.add(jlPartsSizeGrowing);

		jtfPartsSizeGrowing = new JTextField();
		jtfPartsSizeGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsSizeGrowing.setColumns(10);
		jtfPartsSizeGrowing.setBounds(353, 34, 62, 28);
		productPaneGrowing.add(jtfPartsSizeGrowing);

		jlPartsDoorNumberGrowing = new JLabel("Ajouter une pièce  -   Entrez le nombre de portes :");
		jlPartsDoorNumberGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsDoorNumberGrowing.setBounds(10, 59, 354, 37);
		productPaneGrowing.add(jlPartsDoorNumberGrowing);

		jtfPartsDoorNumberGrowing = new JTextField();
		jtfPartsDoorNumberGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsDoorNumberGrowing.setColumns(10);
		jtfPartsDoorNumberGrowing.setBounds(353, 64, 62, 28);
		productPaneGrowing.add(jtfPartsDoorNumberGrowing);

		jlPartsWindowNumberGrowing = new JLabel("Ajouter une pièce  -   Entrez le nombre de fenêtre :");
		jlPartsWindowNumberGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsWindowNumberGrowing.setBounds(10, 90, 354, 37);
		productPaneGrowing.add(jlPartsWindowNumberGrowing);

		jtfPartsWindowNumberGrowing = new JTextField();
		jtfPartsWindowNumberGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jtfPartsWindowNumberGrowing.setColumns(10);
		jtfPartsWindowNumberGrowing.setBounds(353, 95, 62, 28);
		productPaneGrowing.add(jtfPartsWindowNumberGrowing);

		jbNewPartsButtonGrowing = new JButton("Ajouter la pièce");
		jbNewPartsButtonGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbNewPartsButtonGrowing.addActionListener(this);
		jbNewPartsButtonGrowing.setBounds(251, 154, 164, 37);
		productPaneGrowing.add(jbNewPartsButtonGrowing);

		jlPartsAccessControlGrowing = new JLabel("Ajouter une pièce  -   Contrôle d'accès :");
		jlPartsAccessControlGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlPartsAccessControlGrowing.setBounds(10, 117, 256, 37);
		productPaneGrowing.add(jlPartsAccessControlGrowing);

		checkBoxAccessControlGrowing = new JCheckBox("Cochez si oui");
		checkBoxAccessControlGrowing.setSelected(true);
		checkBoxAccessControlGrowing.setBounds(272, 124, 118, 23);
		productPaneGrowing.add(checkBoxAccessControlGrowing);


		//BASKETPANEL FOR GROWING PANEL
		basketPanelGrowing = new JPanel();
		basketPanelGrowing.setBounds(462, 11, 898, 201);
		jpGrowing.add(basketPanelGrowing);
		basketPanelGrowing.setLayout(null);
		//TITLE OF SCROLL FOR GROWING PANEL
		jlListTitleGrowing = new JLabel("Liste des pièces ajoutées pour l'agrandissement :");
		jlListTitleGrowing.setFont(new Font("Calibri", Font.PLAIN, 13));
		jlListTitleGrowing.setBounds(10, 11, 326, 14);
		basketPanelGrowing.add(jlListTitleGrowing);
		//SCROLL FOR PRODUCTS OF GROWING PANEL
		jspBasketScrollGrowing = new JScrollPane(this.jlBasketLinesGrowing);
		jspBasketScrollGrowing.setBounds(10, 29, 878, 122);
		basketPanelGrowing.add(jspBasketScrollGrowing);		

		jbDeletePartsGrowing = new JButton("Supprimer la pièce");
		jbDeletePartsGrowing.addActionListener(this);
		jbDeletePartsGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbDeletePartsGrowing.setBounds(10, 153, 164, 37);
		basketPanelGrowing.add(jbDeletePartsGrowing);

		jbAddSensorShopToBasketFromBuilderGrowing = new JButton("Générer une liste de capteurs depuis les pièces ajoutées au panier");
		jbAddSensorShopToBasketFromBuilderGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbAddSensorShopToBasketFromBuilderGrowing.setBounds(375, 160, 513, 30);
		jbAddSensorShopToBasketFromBuilderGrowing.addActionListener(this);
		basketPanelGrowing.add(jbAddSensorShopToBasketFromBuilderGrowing);

		jpBasketBuilderToSensoShopGrowing = new JPanel();
		jpBasketBuilderToSensoShopGrowing.setBounds(10, 253, 1131, 206);
		jpGrowing.add(jpBasketBuilderToSensoShopGrowing);
		jpBasketBuilderToSensoShopGrowing.setLayout(null);

		jspBasketBuilderToSensoShopGrowing = new JScrollPane(this.jlBasketSensorShopLinesGrowing);
		jspBasketBuilderToSensoShopGrowing.setBounds(0, 0, 1131, 206);
		jpBasketBuilderToSensoShopGrowing.add(jspBasketBuilderToSensoShopGrowing);

		jlbTitleBasketBuilderToSensorShopGrowing = new JLabel("Liste des capteurs générés directement depuis la pièce :");
		jlbTitleBasketBuilderToSensorShopGrowing.setFont(new Font("Calibri", Font.PLAIN, 12));
		jlbTitleBasketBuilderToSensorShopGrowing.setBounds(20, 231, 334, 23);
		jpGrowing.add(jlbTitleBasketBuilderToSensorShopGrowing);

		jlbTotalPriceOfSensorShopBasketGrowing = new JLabel("Prix total des capteurs du panier :");
		jlbTotalPriceOfSensorShopBasketGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalPriceOfSensorShopBasketGrowing.setBounds(152, 470, 383, 31);
		jpGrowing.add(jlbTotalPriceOfSensorShopBasketGrowing);

		jlbTotalInterviewCostOfSensorShopBasketGrowing = new JLabel("Coût total de la maintenance des capteurs du panier :");
		jlbTotalInterviewCostOfSensorShopBasketGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalInterviewCostOfSensorShopBasketGrowing.setBounds(31, 500, 471, 31);
		jpGrowing.add(jlbTotalInterviewCostOfSensorShopBasketGrowing);
		
		jbClearSensorShopGrowing = new JButton("Vider la liste de capteurs");
		jbClearSensorShopGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbClearSensorShopGrowing.setBounds(630, 470, 196, 31);
		jbClearSensorShopGrowing.addActionListener(this);
		jpGrowing.add(jbClearSensorShopGrowing);
		
		jlProvisionnalGrowing = new JLabel("Prévisions des coûts sur ");
		jlProvisionnalGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlProvisionnalGrowing.setBounds(20, 534, 168, 43);
		jpGrowing.add(jlProvisionnalGrowing);
		
		jtfYearsProvisionnalGrowing = new JTextField();
		jtfYearsProvisionnalGrowing.setToolTipText("Entrez un nombre d'années (entier - ex : 5 ans)");
		jtfYearsProvisionnalGrowing.setColumns(10);
		jtfYearsProvisionnalGrowing.setBounds(189, 539, 66, 32);
		jpGrowing.add(jtfYearsProvisionnalGrowing);
		
		jlYearProvisionnalGrowing = new JLabel("ans ");
		jlYearProvisionnalGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlYearProvisionnalGrowing.setBounds(265, 534, 110, 43);
		jpGrowing.add(jlYearProvisionnalGrowing);
		
		jbGenerateProvisionnalGrowing = new JButton("Générer les prévisions");
		jbGenerateProvisionnalGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbGenerateProvisionnalGrowing.setBounds(298, 540, 182, 33);
		jbGenerateProvisionnalGrowing.addActionListener(this);
		jpGrowing.add(jbGenerateProvisionnalGrowing);
		
		jlbTotalProvisionnalGrowing = new JLabel("");
		jlbTotalProvisionnalGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalProvisionnalGrowing.setBounds(77, 566, 387, 32);
		jpGrowing.add(jlbTotalProvisionnalGrowing);
		
		jbCapturGrowing = new JButton("Faire une capture du devis");
		jbCapturGrowing.setFont(new Font("Calibri", Font.PLAIN, 15));
		jbCapturGrowing.addActionListener(this);
		jbCapturGrowing.setBounds(1151, 333, 207, 23);
		jpGrowing.add(jbCapturGrowing);
		
		


		//PANEL ADDSENSOR
		jpAddSensor = new JPanel();
		jpAddSensor.setLayout(null);
		//TITLE
		jlbTitleAddSensor = new JLabel("Devis installation de capteurs supplémentaires");
		jlbTitleAddSensor.setFont(new Font("Calibri", Font.BOLD, 15));
		jlbTitleAddSensor.setBounds(10, 0, 369, 14);
		jpAddSensor.add(jlbTitleAddSensor);

		jpProductPanelAddSensor = new JPanel();
		jpProductPanelAddSensor.setLayout(null);
		jpProductPanelAddSensor.setBounds(10, 21, 680, 281);
		jpAddSensor.add(jpProductPanelAddSensor);

		jspProductPanelAddSensor = new JScrollPane(this.jlSensorsNameAddSensor);
		jspProductPanelAddSensor.setBounds(0, 0, 680, 280);
		jpProductPanelAddSensor.add(jspProductPanelAddSensor);

		jbAddSensorToBasket = new JButton("Ajouter un capteur");
		jbAddSensorToBasket.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbAddSensorToBasket.setBounds(533, 313, 157, 43);
		jpAddSensor.add(jbAddSensorToBasket);
		jbAddSensorToBasket.addActionListener(this);

		jbOneMoreAddSensor = new JButton("+");
		jbOneMoreAddSensor.setBounds(752, 305, 89, 23);
		jpAddSensor.add(jbOneMoreAddSensor);
		jbOneMoreAddSensor.addActionListener(this);
		jbOneLessAddSensor = new JButton("-");
		jbOneLessAddSensor.setBounds(851, 305, 89, 23);
		jpAddSensor.add(jbOneLessAddSensor);
		jbOneLessAddSensor.addActionListener(this);
		jbDeleteAddSensor = new JButton("Supprimer");
		jbDeleteAddSensor.setBounds(950, 305, 110, 23);
		jbDeleteAddSensor.addActionListener(this);
		jpAddSensor.add(jbDeleteAddSensor);

		jspBasketPanelAddSensor = new JScrollPane();
		jspBasketPanelAddSensor.setBounds(710, 21, 639, 281);
		jpAddSensor.add(jspBasketPanelAddSensor);
		jspBasketPanelAddSensor.setViewportView(jlBasketLineAddSensor);

		jpBasketPanelAddSensor = new JPanel();
		jspBasketPanelAddSensor.setRowHeaderView(jpBasketPanelAddSensor);
		jpBasketPanelAddSensor.setLayout(null);

		jlbBasketTitleAddSensorPanel = new JLabel("Liste des capteurs ajoutés au panier :");
		jlbBasketTitleAddSensorPanel.setFont(new Font("Calibri", Font.BOLD, 15));
		jlbBasketTitleAddSensorPanel.setBounds(737, 1, 323, 13);
		jpAddSensor.add(jlbBasketTitleAddSensorPanel);

		jlbTotalPriceAddSensor = new JLabel("Prix Total : ");
		jlbTotalPriceAddSensor.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalPriceAddSensor.setBounds(1096, 332, 264, 43);
		jpAddSensor.add(jlbTotalPriceAddSensor);

		jlbTotalInterviewPriceAddSensor = new JLabel("Cout total de la maintenance à l'année : ");
		jlbTotalInterviewPriceAddSensor.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalInterviewPriceAddSensor.setBounds(916, 365, 421, 43);
		jpAddSensor.add(jlbTotalInterviewPriceAddSensor);
		
		jlbProvisionnalAddSensor = new JLabel("Prévisions des coûts sur ");
		jlbProvisionnalAddSensor.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbProvisionnalAddSensor.setBounds(892, 401, 168, 43);
		jpAddSensor.add(jlbProvisionnalAddSensor);
		
		jtfProvisionnalAddSensor = new JTextField();
		jtfProvisionnalAddSensor.setToolTipText("Entrez un nombre d'années (entier - ex : 5 ans)");
		jtfProvisionnalAddSensor.setBounds(1061, 406, 66, 32);
		jpAddSensor.add(jtfProvisionnalAddSensor);
		jtfProvisionnalAddSensor.setColumns(10);
		
		jlbProvisionnalYearAddSensor = new JLabel("ans ");
		jlbProvisionnalYearAddSensor.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbProvisionnalYearAddSensor.setBounds(1137, 401, 110, 43);
		jpAddSensor.add(jlbProvisionnalYearAddSensor);
		
		jbGenerateProvisionnalAddSensor = new JButton("Générer les prévisions");
		jbGenerateProvisionnalAddSensor.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbGenerateProvisionnalAddSensor.addActionListener(this);
		jbGenerateProvisionnalAddSensor.setBounds(1170, 407, 168, 33);
		jpAddSensor.add(jbGenerateProvisionnalAddSensor);

		jlbTotalProvisionnalAddSensor = new JLabel("");
		jlbTotalProvisionnalAddSensor.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalProvisionnalAddSensor.setBounds(950, 442, 387, 32);
		jpAddSensor.add(jlbTotalProvisionnalAddSensor);
		
		jbCapturAddSensor = new JButton("Faire une capture du devis");
		jbCapturAddSensor.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbCapturAddSensor.addActionListener(this);
		jbCapturAddSensor.setBounds(1097, 580, 252, 43);
		jpAddSensor.add(jbCapturAddSensor);
		dataSensors();

	}

	@SuppressWarnings("unchecked")
	public void dataSensors()
	{
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorShop.class, null, null, null,
					null, RequestSender.CLIENT);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			// Retrieves all the sensor from the database
			shops = (List<SensorShop>)JsonUtil.deserializeObject(response);
			for(SensorShop s : shops) {
				dlmSensorsAddSensor.addElement(s.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getSmokeSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.SMOKE);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getFlowSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.FLOW);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(30);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getDoorSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.DOOR);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getArc().getDoorsNumber();
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getTemperatureSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.TEMPERATURE);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(150);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getHumiditySensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.HUMIDITY);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getLightSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.LIGHT);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getGasSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.GAS);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	/*public void getGlassBreakageSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.GLASS_BREAKAGE);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getArc().getWindowsNumber();
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}*/
	
	/*public void getAcousticSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.ACOUSTIC);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(150);
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}*/
	
	public void getManualTriggerSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.MANUAL_TRIGGER);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getArc().getDoorsNumber();
		}
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getAccessControlSensorFromBuilderNewHome()
	{
		SensorShop s = getBestSensor(SensorType.ACCESS_CONTROL);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			if(clArc.getArc().isAccessControl() == true)
			{
				quantity += clArc.getArc().getDoorsNumber();
			}
		}
		if(!(quantity == 0))
		{
			basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
			this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
					" - Marque : "+s.getSensorMark()+
					" - Type :"+s.getSensorType().getFrenchLabel()+
					" - Prix : "+arround(s.getSensorPrice(), 2)+
					"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
					"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
					" - Durée de vie moyenne : "+s.getLifeTime());
		}

	}
	
	public void getFloodSensorFromBuilderNewHome()
	{
		//SensorShop s = getBestSensor(SensorType.FLOOD);
		int quantity = 0;
		for(CommandLineArc clArc : basketNewHome.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(150);
		}
		SensorShop s = getBestSensor(SensorType.FLOOD);
		basketSensorShopFromBuilderNewHome.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesNewHome.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getSmokeSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.SMOKE);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getFlowSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.FLOW);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(30);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getDoorSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.DOOR);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getArc().getDoorsNumber();
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getTemperatureSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.TEMPERATURE);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(150);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getHumiditySensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.HUMIDITY);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getLightSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.LIGHT);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getGasSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.GAS);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(40);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	/*public void getGlassBreakageSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.GLASS_BREAKAGE);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getArc().getWindowsNumber();
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}*/
	
	/*public void getAcousticSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.ACOUSTIC);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(150);
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}*/
	
	public void getManualTriggerSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.MANUAL_TRIGGER);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getArc().getDoorsNumber();
		}
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}
	
	public void getAccessControlSensorFromBuilderGrowing()
	{
		SensorShop s = getBestSensor(SensorType.ACCESS_CONTROL);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			if(clArc.getArc().isAccessControl() == true)
			{
				quantity += clArc.getArc().getDoorsNumber();
			}
		}
		if(!(quantity == 0))
		{
			basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
			this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
					" - Marque : "+s.getSensorMark()+
					" - Type :"+s.getSensorType().getFrenchLabel()+
					" - Prix : "+arround(s.getSensorPrice(), 2)+
					"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
					"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
					" - Durée de vie moyenne : "+s.getLifeTime());
		}

	}
	
	public void getFloodSensorFromBuilderGrowing()
	{
		//SensorShop s = getBestSensor(SensorType.FLOOD);
		int quantity = 0;
		for(CommandLineArc clArc : basketGrowing.alCommandLineArc)
		{
			quantity += clArc.getNumberOfSensor(150);
		}
		SensorShop s = getBestSensor(SensorType.FLOOD);
		basketSensorShopFromBuilderGrowing.addSensor(quantity, s);
		this.dlmBasketSensorShopLinesGrowing.addElement("Quantité : "+quantity+
				" - Marque : "+s.getSensorMark()+
				" - Type :"+s.getSensorType().getFrenchLabel()+
				" - Prix : "+arround(s.getSensorPrice(), 2)+
				"€ - Coût de la maintenance à l'année : "+s.getSensorInterviewPrice()+
				"€/an - Classe énergétique : "+s.getEnergy().getLabel()+
				" - Durée de vie moyenne : "+s.getLifeTime());
	}



	private SensorShop getBestSensor(SensorType type) 
	{
		SensorShop bestResult = null;
		for(SensorShop s : shops)
		{
			if(s.getSensorType() == type && s.getEnergy() == Energy.A_PLUS_PLUS && s.getLifeTime() > 4)
			{
				if(bestResult == null || s.getSensorPrice() < bestResult.getSensorPrice())
				{
					bestResult = s;
				}
			}
		}
		return bestResult;
	}

	// Main method to test data retrieving from the database
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

	//to arround double that are showing too many numbers for a float
	public double arround(double number,int nbAfterComma) 
	{
		double power = Math.pow(10.0, nbAfterComma);
		return Math.round(number*power)/power;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//Listener for JcomboBox with cardLayout
		if(e.getSource() == jbCreate){
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Création d'une nouvelle résidence"))
			{
				cardPanel.add(jpNewHome, "1");
				this.jtfPartsNameNewHome.setText("");
				this.jtfPartsSizeNewHome.setText("");
				this.jtfPartsDoorNumberNewHome.setText("");
				this.jtfPartsWindowNumberNewHome.setText("");
				this.dlmBasketSensorShopLinesNewHome.removeAllElements();
				this.dlmBasketLinesNewHome.removeAllElements();
				this.basketSensorShopFromBuilderNewHome.clearBasket();
				this.basketNewHome.clearBasket();
				this.jlbTotalPriceOfSensorShopBasketNewHome.setText("Prix total des capteurs du panier :");
				this.jlbTotalInterviewCostOfSensorShopBasketNewHome.setText("Coût total de la maintenance des capteurs du panier : ");
				this.jlbTotalProvisionnalNewHome.setText("");
				this.jtfYearsProvisionnalNewHome.setText("");
				cards.show(cardPanel, "1");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Agrandissement"))
			{
				cardPanel.add(jpGrowing, "2");
				this.jtfPartsNameGrowing.setText("");
				this.jtfPartsSizeGrowing.setText("");
				this.jtfPartsDoorNumberGrowing.setText("");
				this.jtfPartsWindowNumberGrowing.setText("");
				this.dlmBasketSensorShopLinesGrowing.removeAllElements();
				this.dlmBasketLinesGrowing.removeAllElements();
				this.basketSensorShopFromBuilderGrowing.clearBasket();
				this.basketGrowing.clearBasket();
				this.jlbTotalPriceOfSensorShopBasketGrowing.setText("Prix total des capteurs du panier :");
				this.jlbTotalInterviewCostOfSensorShopBasketGrowing.setText("Coût total de la maintenance des capteurs du panier : ");
				this.jlbTotalProvisionnalGrowing.setText("");
				this.jtfYearsProvisionnalGrowing.setText("");
				cards.show(cardPanel, "2");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Devis de capteurs simple"))
			{
				cardPanel.add(jpAddSensor, "3");
				this.basketAddSensor.clearBasket();
				this.dlmBasketLineAddSensor.removeAllElements();
				this.jtfProvisionnalAddSensor.setText("");
				this.jlbTotalProvisionnalAddSensor.setText("");
				this.jlbTotalPriceAddSensor.setText("Prix total des capteurs du panier :");
				this.jlbTotalInterviewPriceAddSensor.setText("Coût total de la maintenance des capteurs du panier : ");
				cards.show(cardPanel, "3");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
		}
		
		//Listener for newHome panel
		if(e.getSource() == this.jbNewPartsButtonNewHome)
		{
				try 
				{
					int c1 = intInside(jtfPartsSizeNewHome);
					int c2 = intInside(jtfPartsDoorNumberNewHome);
					int c3 = intInside(jtfPartsWindowNumberNewHome);
					basketNewHome.addArc(new ArchitectureBuilder(jtfPartsNameNewHome.getText(), c1, c2, c3,
							checkBoxAccessControlNewHome.isSelected()));
					this.dlmBasketLinesNewHome.clear();
					this.jtfPartsNameNewHome.setText("");
					this.jtfPartsSizeNewHome.setText("");
					this.jtfPartsDoorNumberNewHome.setText("");
					this.jtfPartsWindowNumberNewHome.setText("");
					for (CommandLineArc cl : basketNewHome.alCommandLineArc) 
					{
						this.dlmBasketLinesNewHome.addElement("Nom : " + cl.getArc().getNom() + " - Superficie : "
								+ cl.getArc().getArea() + "m² - Nombre de portes : " + cl.getArc().getDoorsNumber()
								+ " - Nombre de fenêtres : " + cl.getArc().getWindowsNumber() + " - Contrôle d'accès : "
								+ cl.getArc().isAccessControl());
					} 
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(this, "Veuillez saisir correctement les données");
				}
		}
		if(e.getSource() == this.jbDeletePartsNewHome)
		{
			try {
				int index = jlBasketLinesNewHome.getSelectedIndex();
				basketNewHome.alCommandLineArc.remove(index);
				if (index == -1) {
					return;
				}
				this.dlmBasketLinesNewHome.clear();
				for (CommandLineArc cl : basketNewHome.alCommandLineArc) {
					this.dlmBasketLinesNewHome.addElement("Nom : " + cl.getArc().getNom() + " - Superficie : "
							+ cl.getArc().getArea() + "m² - Nombre de portes : " + cl.getArc().getDoorsNumber()
							+ "- Nombre de fenêtres : " + cl.getArc().getWindowsNumber());
				} 
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		if(e.getSource() == this.jbAddSensorShopToBasketFromBuilderNewHome)
		{
			if(!this.dlmBasketLinesNewHome.isEmpty())
			{
				this.dlmBasketSensorShopLinesNewHome.clear();
				this.basketSensorShopFromBuilderNewHome.clearBasket();
				getSmokeSensorFromBuilderNewHome();
				getFlowSensorFromBuilderNewHome();
				getDoorSensorFromBuilderNewHome();
				getTemperatureSensorFromBuilderNewHome();
				getHumiditySensorFromBuilderNewHome();
				getLightSensorFromBuilderNewHome();
				getGasSensorFromBuilderNewHome();
				//getGlassBreakageSensorFromBuilderNewHome();
				//getAcousticSensorFromBuilderNewHome();
				getManualTriggerSensorFromBuilderNewHome();
				getAccessControlSensorFromBuilderNewHome();
				getFloodSensorFromBuilderNewHome();
				this.jlbTotalPriceOfSensorShopBasketNewHome.setText("Prix total des capteurs du panier : "+arround(basketSensorShopFromBuilderNewHome.totalBasketPrice(), 2)+"€");
				this.jlbTotalInterviewCostOfSensorShopBasketNewHome.setText("Coût total de la maintenance des capteurs du panier : "+basketSensorShopFromBuilderNewHome.totalBasketInterviewPrice()+"€/an");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "La liste des pièces est vide");
				this.dlmBasketSensorShopLinesNewHome.clear();
				this.basketSensorShopFromBuilderNewHome.clearBasket();
				this.jlbTotalPriceOfSensorShopBasketNewHome.setText("Prix total des capteurs du panier :");
				this.jlbTotalInterviewCostOfSensorShopBasketNewHome.setText("Coût total de la maintenance des capteurs du panier : ");
				this.jlbTotalProvisionnalNewHome.setText("");
			}

		}
		if(e.getSource() == this.jbClearSensorShopNewHome)
		{
			try {
				this.dlmBasketSensorShopLinesNewHome.clear();
				this.basketSensorShopFromBuilderNewHome.clearBasket();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		if(e.getSource() == jbGenerateProvisionnalNewHome)
		{	
			try {
				int c1 = intInside(jtfYearsProvisionnalNewHome);
				this.jlbTotalProvisionnalNewHome.setText("Prévisions des coûts sur " + c1 + " ans : "
						+ arround(basketSensorShopFromBuilderNewHome.totalProvisionnal(c1), 2) + " €");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Veuillez saisir correctement le nombre d'années sur lesquelles vous voulez baser les prévisions");
			}
		}
		
		if(e.getSource() == this.jbCapturNewHome)
		{
			ScreenShotFactory.screenShot(new Rectangle(0, 0, 1366, 768), new Dimension(1366, 768), "C:\\Users\\gtaoudiat\\Desktop\\devis.png", ScreenShotFactory.IMAGE_TYPE_JPEG);
		}
		
		
		
		//LISTENERS FOR GROWING PANEL
		if(e.getSource() == this.jbNewPartsButtonGrowing)
		{
				try 
				{
					int c1 = intInside(jtfPartsSizeGrowing);
					int c2 = intInside(jtfPartsDoorNumberGrowing);
					int c3 = intInside(jtfPartsWindowNumberGrowing);
					basketGrowing.addArc(new ArchitectureBuilder(jtfPartsNameGrowing.getText(), c1, c2, c3,
							checkBoxAccessControlGrowing.isSelected()));
					this.dlmBasketLinesGrowing.clear();
					this.jtfPartsNameGrowing.setText("");
					this.jtfPartsSizeGrowing.setText("");
					this.jtfPartsDoorNumberGrowing.setText("");
					this.jtfPartsWindowNumberGrowing.setText("");
					for (CommandLineArc cl : basketGrowing.alCommandLineArc) 
					{
						this.dlmBasketLinesGrowing.addElement("Nom : " + cl.getArc().getNom() + " - Superficie : "
								+ cl.getArc().getArea() + "m² - Nombre de portes : " + cl.getArc().getDoorsNumber()
								+ " - Nombre de fenêtres : " + cl.getArc().getWindowsNumber() + " - Contrôle d'accès : "
								+ cl.getArc().isAccessControl());
					} 
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(this, "Veuillez saisir correctement les données");
				}
		}
		if(e.getSource() == this.jbDeletePartsGrowing)
		{
			try {
				int index = jlBasketLinesGrowing.getSelectedIndex();
				basketGrowing.alCommandLineArc.remove(index);
				if (index == -1) {
					return;
				}
				this.dlmBasketLinesGrowing.clear();
				for (CommandLineArc cl : basketGrowing.alCommandLineArc) {
					this.dlmBasketLinesGrowing.addElement("Nom : " + cl.getArc().getNom() + " - Superficie : "
							+ cl.getArc().getArea() + "m² - Nombre de portes : " + cl.getArc().getDoorsNumber()
							+ "- Nombre de fenêtres : " + cl.getArc().getWindowsNumber());
				} 
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		if(e.getSource() == this.jbAddSensorShopToBasketFromBuilderGrowing)
		{
			if(!this.dlmBasketLinesGrowing.isEmpty())
			{
				this.dlmBasketSensorShopLinesGrowing.clear();
				this.basketSensorShopFromBuilderGrowing.clearBasket();
				getSmokeSensorFromBuilderGrowing();
				getFlowSensorFromBuilderGrowing();
				getDoorSensorFromBuilderGrowing();
				getTemperatureSensorFromBuilderGrowing();
				getHumiditySensorFromBuilderGrowing();
				getLightSensorFromBuilderGrowing();
				getGasSensorFromBuilderGrowing();
				//getGlassBreakageSensorFromBuilderGrowing();
				//getAcousticSensorFromBuilderGrowing();
				getManualTriggerSensorFromBuilderGrowing();
				getAccessControlSensorFromBuilderGrowing();
				getFloodSensorFromBuilderGrowing();
				this.jlbTotalPriceOfSensorShopBasketGrowing.setText("Prix total des capteurs du panier : "+arround(basketSensorShopFromBuilderGrowing.totalBasketPrice(), 2)+"€");
				this.jlbTotalInterviewCostOfSensorShopBasketGrowing.setText("Coût total de la maintenance des capteurs du panier : "+basketSensorShopFromBuilderGrowing.totalBasketInterviewPrice()+"€/an");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "La liste des pièces est vide");
				this.dlmBasketSensorShopLinesGrowing.clear();
				this.basketSensorShopFromBuilderGrowing.clearBasket();
				this.jlbTotalPriceOfSensorShopBasketGrowing.setText("Prix total des capteurs du panier :");
				this.jlbTotalInterviewCostOfSensorShopBasketGrowing.setText("Coût total de la maintenance des capteurs du panier : ");
				this.jlbTotalProvisionnalGrowing.setText("");
			}

		}
		if(e.getSource() == this.jbClearSensorShopGrowing)
		{
			try {
				this.dlmBasketSensorShopLinesGrowing.clear();
				this.basketSensorShopFromBuilderGrowing.clearBasket();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		if(e.getSource() == jbGenerateProvisionnalGrowing)
		{	
			try {
				int c1 = intInside(jtfYearsProvisionnalGrowing);
				this.jlbTotalProvisionnalGrowing.setText("Prévisions des coûts sur " + c1 + " ans : "
						+ arround(basketSensorShopFromBuilderGrowing.totalProvisionnal(c1), 2) + " €");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Veuillez saisir correctement le nombre d'années sur lesquelles vous voulez baser les prévisions");
			}
		}
		if(e.getSource() == this.jbCapturGrowing)
		{
			ScreenShotFactory.screenShot(new Rectangle(0, 0, 1366, 768), new Dimension(1366, 768), "C:\\Users\\gtaoudiat\\Desktop\\devis.png", ScreenShotFactory.IMAGE_TYPE_JPEG);
		}


		
		//Listener Panel AddSensor
		if(e.getSource() == this.jbAddSensorToBasket)
		{
			int index = jlSensorsNameAddSensor.getSelectedIndex();
			basketAddSensor.addSensor(1, this.shops.get(jlSensorsNameAddSensor.getSelectedIndex()));
			this.dlmBasketLineAddSensor.clear();
			for (CommandLineSensor cl : basketAddSensor.alCommandLineSensor)
			{
				this.dlmBasketLineAddSensor.addElement("Quantité : "+cl.getQuantity()+
						" - Marque : "+cl.getSensor().getSensorMark()+
						" - Type : "+cl.getSensor().getSensorType()+
						" - Prix : "+arround(cl.getSensor().getSensorPrice(), 2)+
						"€ - Coût de la maintenance à l'année : "+cl.getSensor().getSensorInterviewPrice()+
						"€/an - Classe énergétique : "+cl.getSensor().getEnergy().getLabel()+
						" - Durée de vie moyenne : "+cl.getSensor().getLifeTime());
			}
			this.jlBasketLineAddSensor.setSelectedIndex(index);
			this.jlSensorsNameAddSensor.setSelectedIndex(index);
			this.jlbTotalPriceAddSensor.setText("Prix Total : "+arround(basketAddSensor.totalBasketPrice(), 2)+"€"); 
			this.jlbTotalInterviewPriceAddSensor.setText("Cout total de la maintenance à l'année : "+basketAddSensor.totalBasketInterviewPrice()+"€/an");
		}
		if(e.getSource() == this.jbDeleteAddSensor)
		{
			int index = jlBasketLineAddSensor.getSelectedIndex();
			if(index == -1)
			{
				return;
			}
			basketAddSensor.alCommandLineSensor.remove(index);
			this.dlmBasketLineAddSensor.clear();
			for (CommandLineSensor cl : basketAddSensor.alCommandLineSensor)
			{
				this.dlmBasketLineAddSensor.addElement("Quantité : "+cl.getQuantity()+
						" - Marque : "+cl.getSensor().getSensorMark()+
						" - Type : "+cl.getSensor().getSensorType()+
						" - Prix : "+arround(cl.getSensor().getSensorPrice(), 2)+
						"€ - Coût de la maintenance à l'année : "+cl.getSensor().getSensorInterviewPrice()+
						"€/an - Classe énergétique : "+cl.getSensor().getEnergy().getLabel()+
						" - Durée de vie moyenne : "+cl.getSensor().getLifeTime());
			}
			this.jlbTotalPriceAddSensor.setText("Prix Total : "+arround(basketAddSensor.totalBasketPrice(), 2)+"€");
			this.jlbTotalInterviewPriceAddSensor.setText("Cout total de la maintenance à l'année : "+basketAddSensor.totalBasketInterviewPrice()+"€/an");
		}
		if(e.getSource() == this.jbOneMoreAddSensor)
		{
			int index = jlBasketLineAddSensor.getSelectedIndex();
			if(index == -1)
			{
				return;
			}
			CommandLineSensor cmd = this.basketAddSensor.alCommandLineSensor.get(index);
			cmd.setQuantity(cmd.getQuantity()+1);
			this.dlmBasketLineAddSensor.clear();
			for (CommandLineSensor cl : basketAddSensor.alCommandLineSensor)
			{
				this.dlmBasketLineAddSensor.addElement("Quantité : "+cl.getQuantity()+
						" - Marque : "+cl.getSensor().getSensorMark()+
						" - Type : "+cl.getSensor().getSensorType()+
						" - Prix : "+arround(cl.getSensor().getSensorPrice(), 2)+
						"€ - Coût de la maintenance à l'année : "+cl.getSensor().getSensorInterviewPrice()+
						"€/an - Classe énergétique : "+cl.getSensor().getEnergy().getLabel()+
						" - Durée de vie moyenne : "+cl.getSensor().getLifeTime());
			}
			this.jlBasketLineAddSensor.setSelectedIndex(index);
			this.jlbTotalPriceAddSensor.setText("Prix Total : "+arround(basketAddSensor.totalBasketPrice(), 2)+"€");
			this.jlbTotalInterviewPriceAddSensor.setText("Cout total de la maintenance à l'année : "+basketAddSensor.totalBasketInterviewPrice()+"€/an");
		}
		if(e.getSource() == this.jbOneLessAddSensor)
		{
			int index = jlBasketLineAddSensor.getSelectedIndex();
			if(index == -1)
			{
				return;
			}
			CommandLineSensor cmd = this.basketAddSensor.alCommandLineSensor.get(index);
			cmd.setQuantity(cmd.getQuantity()-1);
			this.dlmBasketLineAddSensor.clear();
			if(cmd.getQuantity() == 0)
			{
				basketAddSensor.alCommandLineSensor.remove(index);
				this.dlmBasketLineAddSensor.clear();
			}
			for (CommandLineSensor cl : basketAddSensor.alCommandLineSensor)
			{
				this.dlmBasketLineAddSensor.addElement("Quantité : "+cl.getQuantity()+
						" - Marque : "+cl.getSensor().getSensorMark()+
						" - Type : "+cl.getSensor().getSensorType()+
						" - Prix : "+arround(cl.getSensor().getSensorPrice(), 2)+
						"€ - Coût de la maintenance à l'année : "+cl.getSensor().getSensorInterviewPrice()+
						"€/an - Classe énergétique : "+cl.getSensor().getEnergy().getLabel()+
						" - Durée de vie moyenne : "+cl.getSensor().getLifeTime());
			}
			this.jlBasketLineAddSensor.setSelectedIndex(index);
			this.jlbTotalPriceAddSensor.setText("Prix Total : "+arround(basketAddSensor.totalBasketPrice(), 2)+"€");
			this.jlbTotalInterviewPriceAddSensor.setText("Cout total de la maintenance à l'année : "+basketAddSensor.totalBasketInterviewPrice()+"€/an");	
		}		
		if(e.getSource() == jbGenerateProvisionnalAddSensor)
		{
			int c1 = intInside(jtfProvisionnalAddSensor);
			this.jlbTotalProvisionnalAddSensor.setText("Prévisions des coûts sur "+c1+" ans : "+arround(basketAddSensor.totalProvisionnal(c1), 2)+" €");
		}		
		if(e.getSource() == this.jbCapturAddSensor)
		{
			ScreenShotFactory.screenShot(new Rectangle(0, 0, 1366, 768), new Dimension(1366, 768), "C:\\Users\\gtaoudiat\\Desktop\\devis.png", ScreenShotFactory.IMAGE_TYPE_JPEG);
		}
	}

	public ArrayList<SensorShop> getAlListSensorsAddSensor() {
		return alListSensorsAddSensor;
	}

	public void setAlListSensorsAddSensor(ArrayList<SensorShop> alListSensorsAddSensor) {
		this.alListSensorsAddSensor = alListSensorsAddSensor;
	}
}
