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
import com.monitrack.enumeration.Energy;
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

	//BASKET LIST AND INSTANCIATION
	private BasketSensor basketSensorShopFromBuilderNewHome   						= new BasketSensor();
	private DefaultListModel<String>dlmBasketSensorShopLinesNewHome 		= new DefaultListModel<String>();
	private JList<String>  			jlBasketSensorShopLinesNewHome			= new JList<String>(dlmBasketSensorShopLinesNewHome);



	//TODO COPY PANEL NEW HOME AND PASTE INSIDE PANEL GROWING
	//PANEL GROWING
	private JPanel jphomeGrowing;

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
	//BASKET LIST AND INSTANCIATION FOR Growing
	private BasketArc 					basketGrowing					= new BasketArc();
	private DefaultListModel<String>dlmBasketLinesGrowing  			= new DefaultListModel<String>();
	private JList<String>  			jlBasketLinesGrowing			= new JList<String>(dlmBasketLinesGrowing);




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

		//CardPanel.panels are generate by actionPerformed

		//TESTFORPANELS

		//NEW HOME PANEL SETTINGS AND CONFIGURATION - PRODUCTS AND BASKET TEMPLATE
		jpNewHome = new JPanel();
		//TODO NEXTLINETODELETE
		cardPanel.add(jpNewHome, "1");
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
		jbAddSensorShopToBasketFromBuilderNewHome.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbAddSensorShopToBasketFromBuilderNewHome.setBounds(485, 160, 403, 30);
		jbAddSensorShopToBasketFromBuilderNewHome.addActionListener(this);
		basketPanelNewHome.add(jbAddSensorShopToBasketFromBuilderNewHome);

		jpBasketBuilderToSensoShopNewHome = new JPanel();
		jpBasketBuilderToSensoShopNewHome.setBounds(10, 253, 816, 206);
		jpNewHome.add(jpBasketBuilderToSensoShopNewHome);
		jpBasketBuilderToSensoShopNewHome.setLayout(null);

		jspBasketBuilderToSensoShopNewHome = new JScrollPane(this.jlBasketSensorShopLinesNewHome);
		jspBasketBuilderToSensoShopNewHome.setBounds(0, 0, 816, 206);
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
		
		jbClearSensorShopNewHome = new JButton("Vider la liste de capteur");
		jbClearSensorShopNewHome.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbClearSensorShopNewHome.setBounds(644, 470, 182, 23);
		jbClearSensorShopNewHome.addActionListener(this);
		jpNewHome.add(jbClearSensorShopNewHome);
		
		jlProvisionnalNewHome = new JLabel("Pr\u00E9visions des co\u00FBts sur ");
		jlProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlProvisionnalNewHome.setBounds(20, 534, 168, 43);
		jpNewHome.add(jlProvisionnalNewHome);
		
		jtfYearsProvisionnalNewHome = new JTextField();
		jtfYearsProvisionnalNewHome.setToolTipText("Entrez un nombre d'ann\u00E9es entier svp");
		jtfYearsProvisionnalNewHome.setColumns(10);
		jtfYearsProvisionnalNewHome.setBounds(189, 539, 66, 32);
		jpNewHome.add(jtfYearsProvisionnalNewHome);
		
		jlYearProvisionnalNewHome = new JLabel("ans ");
		jlYearProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlYearProvisionnalNewHome.setBounds(265, 534, 110, 43);
		jpNewHome.add(jlYearProvisionnalNewHome);
		
		jbGenerateProvisionnalNewHome = new JButton("Générer les prévisions");
		jbGenerateProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 12));
		jbGenerateProvisionnalNewHome.setBounds(298, 540, 168, 33);
		jbGenerateProvisionnalNewHome.addActionListener(this);
		jpNewHome.add(jbGenerateProvisionnalNewHome);
		
		jlbTotalProvisionnalNewHome = new JLabel("");
		jlbTotalProvisionnalNewHome.setFont(new Font("Calibri", Font.PLAIN, 15));
		jlbTotalProvisionnalNewHome.setBounds(77, 566, 387, 32);
		jpNewHome.add(jlbTotalProvisionnalNewHome);


		//HomeGrowing PANEL SETTINGS AND CONFIGURATION - PRODUCTS AND BASKET TEMPLATE
		jphomeGrowing = new JPanel();
		//NextLine to delete
		cardPanel.add(jphomeGrowing, "2");
		jphomeGrowing.setLayout(null);

		//TITLE GROWING
		jlTitleGrowing = new JLabel("Création de devis : installation de capteurs pour un agrandissement");
		jlTitleGrowing.setFont(new Font("Calibri", Font.BOLD, 15));
		jlTitleGrowing.setBounds(10, 0, 425, 14);
		jphomeGrowing.add(jlTitleGrowing);

		//PANEL PRODUCT ON LEFT GROWING
		productPaneGrowing = new JPanel();
		productPaneGrowing.setBounds(10, 22, 425, 262);
		jphomeGrowing.add(productPaneGrowing);
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
		jphomeGrowing.add(basketPaneGrowing);
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
		jpAddSensor = new JPanel();
		jpAddSensor.setLayout(null);
		cardPanel.add(jpAddSensor, "3");
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
		jspBasketPanelAddSensor.setBounds(737, 21, 612, 281);
		jpAddSensor.add(jspBasketPanelAddSensor);
		jspBasketPanelAddSensor.setViewportView(jlBasketLineAddSensor);

		jpBasketPanelAddSensor = new JPanel();
		jspBasketPanelAddSensor.setRowHeaderView(jpBasketPanelAddSensor);
		jpBasketPanelAddSensor.setLayout(null);

		jlbBasketTitleAddSensorPanel = new JLabel("Liste des capteurs ajoutés au panier :");
		jlbBasketTitleAddSensorPanel.setFont(new Font("Calibri", Font.PLAIN, 12));
		jlbBasketTitleAddSensorPanel.setBounds(820, 1, 219, 14);
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
		jtfProvisionnalAddSensor.setToolTipText("Entrez un nombre d'années entier svp");
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
		dataSensors();

	}

	@SuppressWarnings("unchecked")
	public void dataSensors()
	{
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorShop.class, null, null, null,
					null);
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

	public void getSmokeSensorFromBuilder()
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
	
	public void getFlowSensorFromBuilder()
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
	
	public void getDoorSensorFromBuilder()
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
	
	public void getTemperatureSensorFromBuilder()
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
	
	public void getHumiditySensorFromBuilder()
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
	
	public void getLightSensorFromBuilder()
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
	
	public void getGasSensorFromBuilder()
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
	
	public void getGlassBreakageSensorFromBuilder()
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
	}
	
	public void getAcousticSensorFromBuilder()
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
	}
	
	public void getManualTriggerSensorFromBuilder()
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
	
	public void getAccessControlSensorFromBuilder()
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
	
	public void getFloodSensorFromBuilder()
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
				//UNCOMMENT ALL ADD TO TEST AND FINISH TODO
				//				cardPanel.add(JPnewHome, "1");
				//TODO RemoveAllElements inside lists and jtf
				//FIXME IF MISSING JTF OR LIST
				this.jtfPartsNameNewHome.setText("");
				this.jtfPartsSizeNewHome.setText("");
				this.jtfPartsDoorNumberNewHome.setText("");
				this.jtfPartsWindowNumberNewHome.setText("");
				this.dlmBasketSensorShopLinesNewHome.removeAllElements();
				this.dlmBasketLinesNewHome.removeAllElements();
				this.basketSensorShopFromBuilderNewHome.clearBasket();
				this.basketNewHome.clearBasket();
				cards.show(cardPanel, "1");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Agrandissement"))
			{
				//				cardPanel.add(JPhomeGrowing, "2");
				//TODO RemoveAllElements inside lists
				cards.show(cardPanel, "2");
				cardPanel.repaint();
				cardPanel.revalidate();
			}
			if(actionsCombobox.getSelectedItem().toString().equalsIgnoreCase("Devis de capteurs simple"))
			{
				//				cardPanel.add(JPaddSensor, "3");
				//TODO RemoveAllElements inside lists
				this.dlmBasketLineAddSensor.removeAllElements();
				this.jtfProvisionnalAddSensor.setText("");
				this.jlbTotalProvisionnalAddSensor.setText("");
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
			if(!this.dlmBasketSensorShopLinesNewHome.isEmpty())
			{
				this.dlmBasketSensorShopLinesNewHome.clear();
				this.basketSensorShopFromBuilderNewHome.clearBasket();
				getSmokeSensorFromBuilder();
				getFlowSensorFromBuilder();
				getDoorSensorFromBuilder();
				getTemperatureSensorFromBuilder();
				getHumiditySensorFromBuilder();
				getLightSensorFromBuilder();
				getGasSensorFromBuilder();
				getGlassBreakageSensorFromBuilder();
				getAcousticSensorFromBuilder();
				getManualTriggerSensorFromBuilder();
				getAccessControlSensorFromBuilder();
				getFloodSensorFromBuilder();
				this.jlbTotalPriceOfSensorShopBasketNewHome.setText("Prix Total : "+arround(basketSensorShopFromBuilderNewHome.totalBasketPrice(), 2)+"€");
				this.jlbTotalInterviewCostOfSensorShopBasketNewHome.setText("Cout total de la maintenance à l'année : "+basketSensorShopFromBuilderNewHome.totalBasketInterviewPrice()+"€/an");
			}
			else{JOptionPane.showMessageDialog(this, "La liste des pièces est vide");}

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
		//TODO NEW LISTENER TO CREATE XCEL FILE FROM SENSORSHOPBUILDER
		//TODO LISTENERS FOR PANEL GROWING


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
		return jpNewHome;
	}

	/**
	 * @param jPnewHome the jPnewHome to set
	 */
	public void setJPnewHome(JPanel jPnewHome) {
		jpNewHome = jPnewHome;
	}


	/**
	 * @return the jBCreate
	 */
	public JButton getJBCreate() {
		return jbCreate;
	}

	/**
	 * @param jBCreate the jBCreate to set
	 */
	public void setJBCreate(JButton jBCreate) {
		jbCreate = jBCreate;
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
		return jlbTitleAddSensor;
	}

	/**
	 * @param jlTitleAddSensor the jlTitleAddSensor to set
	 */
	public void setJlTitleAddSensor(JLabel jlTitleAddSensor) {
		this.jlbTitleAddSensor = jlTitleAddSensor;
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
		return jphomeGrowing;
	}

	/**
	 * @param jPhomeGrowing the jPhomeGrowing to set
	 */
	public void setJPhomeGrowing(JPanel jPhomeGrowing) {
		jphomeGrowing = jPhomeGrowing;
	}

	/**
	 * @return the jPaddSensor
	 */
	public JPanel getJPaddSensor() {
		return jpAddSensor;
	}

	/**
	 * @param jPaddSensor the jPaddSensor to set
	 */
	public void setJPaddSensor(JPanel jPaddSensor) {
		jpAddSensor = jPaddSensor;
	}

	/**
	 * @return the shops
	 */
	public List<SensorShop> getShops() {
		return shops;
	}

	/**
	 * @param shops the shops to set
	 */
	public void setShops(List<SensorShop> shops) {
		this.shops = shops;
	}

	/**
	 * @return the jlPartsNameNewHome
	 */
	public JLabel getJlPartsNameNewHome() {
		return jlPartsNameNewHome;
	}

	/**
	 * @param jlPartsNameNewHome the jlPartsNameNewHome to set
	 */
	public void setJlPartsNameNewHome(JLabel jlPartsNameNewHome) {
		this.jlPartsNameNewHome = jlPartsNameNewHome;
	}

	/**
	 * @return the jtfPartsNameNewHome
	 */
	public JTextField getJtfPartsNameNewHome() {
		return jtfPartsNameNewHome;
	}

	/**
	 * @param jtfPartsNameNewHome the jtfPartsNameNewHome to set
	 */
	public void setJtfPartsNameNewHome(JTextField jtfPartsNameNewHome) {
		this.jtfPartsNameNewHome = jtfPartsNameNewHome;
	}

	/**
	 * @return the jlPartsSizeNewHome
	 */
	public JLabel getJlPartsSizeNewHome() {
		return jlPartsSizeNewHome;
	}

	/**
	 * @param jlPartsSizeNewHome the jlPartsSizeNewHome to set
	 */
	public void setJlPartsSizeNewHome(JLabel jlPartsSizeNewHome) {
		this.jlPartsSizeNewHome = jlPartsSizeNewHome;
	}

	/**
	 * @return the jtfPartsSizeNewHome
	 */
	public JTextField getJtfPartsSizeNewHome() {
		return jtfPartsSizeNewHome;
	}

	/**
	 * @param jtfPartsSizeNewHome the jtfPartsSizeNewHome to set
	 */
	public void setJtfPartsSizeNewHome(JTextField jtfPartsSizeNewHome) {
		this.jtfPartsSizeNewHome = jtfPartsSizeNewHome;
	}

	/**
	 * @return the jbNewPartsButtonNewHome
	 */
	public JButton getJbNewPartsButtonNewHome() {
		return jbNewPartsButtonNewHome;
	}

	/**
	 * @param jbNewPartsButtonNewHome the jbNewPartsButtonNewHome to set
	 */
	public void setJbNewPartsButtonNewHome(JButton jbNewPartsButtonNewHome) {
		this.jbNewPartsButtonNewHome = jbNewPartsButtonNewHome;
	}

	/**
	 * @return the jlListTitleNewHome
	 */
	public JLabel getJlListTitleNewHome() {
		return jlListTitleNewHome;
	}

	/**
	 * @param jlListTitleNewHome the jlListTitleNewHome to set
	 */
	public void setJlListTitleNewHome(JLabel jlListTitleNewHome) {
		this.jlListTitleNewHome = jlListTitleNewHome;
	}

	/**
	 * @return the basketNewHome
	 */
	public BasketArc getBasketNewHome() {
		return basketNewHome;
	}

	/**
	 * @param basketNewHome the basketNewHome to set
	 */
	public void setBasketNewHome(BasketArc basketNewHome) {
		this.basketNewHome = basketNewHome;
	}

	/**
	 * @return the dlmBasketLinesNewHome
	 */
	public DefaultListModel<String> getDlmBasketLinesNewHome() {
		return dlmBasketLinesNewHome;
	}

	/**
	 * @param dlmBasketLinesNewHome the dlmBasketLinesNewHome to set
	 */
	public void setDlmBasketLinesNewHome(DefaultListModel<String> dlmBasketLinesNewHome) {
		this.dlmBasketLinesNewHome = dlmBasketLinesNewHome;
	}

	/**
	 * @return the jlBasketLinesNewHome
	 */
	public JList<String> getJlBasketLinesNewHome() {
		return jlBasketLinesNewHome;
	}

	/**
	 * @param jlBasketLinesNewHome the jlBasketLinesNewHome to set
	 */
	public void setJlBasketLinesNewHome(JList<String> jlBasketLinesNewHome) {
		this.jlBasketLinesNewHome = jlBasketLinesNewHome;
	}

	/**
	 * @return the jlPartsNameGrowing
	 */
	public JLabel getJlPartsNameGrowing() {
		return jlPartsNameGrowing;
	}

	/**
	 * @param jlPartsNameGrowing the jlPartsNameGrowing to set
	 */
	public void setJlPartsNameGrowing(JLabel jlPartsNameGrowing) {
		this.jlPartsNameGrowing = jlPartsNameGrowing;
	}

	/**
	 * @return the jtfPartsNameGrowing
	 */
	public JTextField getJtfPartsNameGrowing() {
		return jtfPartsNameGrowing;
	}

	/**
	 * @param jtfPartsNameGrowing the jtfPartsNameGrowing to set
	 */
	public void setJtfPartsNameGrowing(JTextField jtfPartsNameGrowing) {
		this.jtfPartsNameGrowing = jtfPartsNameGrowing;
	}

	/**
	 * @return the jlPartsSizeGrowing
	 */
	public JLabel getJlPartsSizeGrowing() {
		return jlPartsSizeGrowing;
	}

	/**
	 * @param jlPartsSizeGrowing the jlPartsSizeGrowing to set
	 */
	public void setJlPartsSizeGrowing(JLabel jlPartsSizeGrowing) {
		this.jlPartsSizeGrowing = jlPartsSizeGrowing;
	}

	/**
	 * @return the jbNewPartsButtonGrowing
	 */
	public JButton getJbNewPartsButtonGrowing() {
		return jbNewPartsButtonGrowing;
	}

	/**
	 * @param jbNewPartsButtonGrowing the jbNewPartsButtonGrowing to set
	 */
	public void setJbNewPartsButtonGrowing(JButton jbNewPartsButtonGrowing) {
		this.jbNewPartsButtonGrowing = jbNewPartsButtonGrowing;
	}

	/**
	 * @return the jlListTitleGrowing
	 */
	public JLabel getJlListTitleGrowing() {
		return jlListTitleGrowing;
	}

	/**
	 * @param jlListTitleGrowing the jlListTitleGrowing to set
	 */
	public void setJlListTitleGrowing(JLabel jlListTitleGrowing) {
		this.jlListTitleGrowing = jlListTitleGrowing;
	}

	/**
	 * @return the basketGrowing
	 */
	public BasketArc getBasketGrowing() {
		return basketGrowing;
	}

	/**
	 * @param basketGrowing the basketGrowing to set
	 */
	public void setBasketGrowing(BasketArc basketGrowing) {
		this.basketGrowing = basketGrowing;
	}

	/**
	 * @return the dlmBasketLinesGrowing
	 */
	public DefaultListModel<String> getDlmBasketLinesGrowing() {
		return dlmBasketLinesGrowing;
	}

	/**
	 * @param dlmBasketLinesGrowing the dlmBasketLinesGrowing to set
	 */
	public void setDlmBasketLinesGrowing(DefaultListModel<String> dlmBasketLinesGrowing) {
		this.dlmBasketLinesGrowing = dlmBasketLinesGrowing;
	}

	/**
	 * @return the jlBasketLinesGrowing
	 */
	public JList<String> getJlBasketLinesGrowing() {
		return jlBasketLinesGrowing;
	}

	/**
	 * @param jlBasketLinesGrowing the jlBasketLinesGrowing to set
	 */
	public void setJlBasketLinesGrowing(JList<String> jlBasketLinesGrowing) {
		this.jlBasketLinesGrowing = jlBasketLinesGrowing;
	}

	/**
	 * @return the jpProductPanelAddSensor
	 */
	public JPanel getJpProductPanelAddSensor() {
		return jpProductPanelAddSensor;
	}

	/**
	 * @param jpProductPanelAddSensor the jpProductPanelAddSensor to set
	 */
	public void setJpProductPanelAddSensor(JPanel jpProductPanelAddSensor) {
		this.jpProductPanelAddSensor = jpProductPanelAddSensor;
	}

	/**
	 * @return the jspProductPanelAddSensor
	 */
	public JScrollPane getJspProductPanelAddSensor() {
		return jspProductPanelAddSensor;
	}

	/**
	 * @param jspProductPanelAddSensor the jspProductPanelAddSensor to set
	 */
	public void setJspProductPanelAddSensor(JScrollPane jspProductPanelAddSensor) {
		this.jspProductPanelAddSensor = jspProductPanelAddSensor;
	}

	/**
	 * @return the jpBasketPanelAddSensor
	 */
	public JPanel getJpBasketPanelAddSensor() {
		return jpBasketPanelAddSensor;
	}

	/**
	 * @param jpBasketPanelAddSensor the jpBasketPanelAddSensor to set
	 */
	public void setJpBasketPanelAddSensor(JPanel jpBasketPanelAddSensor) {
		this.jpBasketPanelAddSensor = jpBasketPanelAddSensor;
	}

	/**
	 * @return the jspBasketPanelAddSensor
	 */
	public JScrollPane getJspBasketPanelAddSensor() {
		return jspBasketPanelAddSensor;
	}

	/**
	 * @param jspBasketPanelAddSensor the jspBasketPanelAddSensor to set
	 */
	public void setJspBasketPanelAddSensor(JScrollPane jspBasketPanelAddSensor) {
		this.jspBasketPanelAddSensor = jspBasketPanelAddSensor;
	}

	/**
	 * @return the jbAddSensorToBasket
	 */
	public JButton getJbAddSensorToBasket() {
		return jbAddSensorToBasket;
	}

	/**
	 * @param jbAddSensorToBasket the jbAddSensorToBasket to set
	 */
	public void setJbAddSensorToBasket(JButton jbAddSensorToBasket) {
		this.jbAddSensorToBasket = jbAddSensorToBasket;
	}

	/**
	 * @return the lblTotalPriceAddSensor
	 */
	public JLabel getLblTotalPriceAddSensor() {
		return jlbTotalPriceAddSensor;
	}

	/**
	 * @param lblTotalPriceAddSensor the lblTotalPriceAddSensor to set
	 */
	public void setLblTotalPriceAddSensor(JLabel lblTotalPriceAddSensor) {
		this.jlbTotalPriceAddSensor = lblTotalPriceAddSensor;
	}

	/**
	 * @return the lblTotalInterviewPriceAddSensor
	 */
	public JLabel getLblTotalInterviewPriceAddSensor() {
		return jlbTotalInterviewPriceAddSensor;
	}

	/**
	 * @param lblTotalInterviewPriceAddSensor the lblTotalInterviewPriceAddSensor to set
	 */
	public void setLblTotalInterviewPriceAddSensor(JLabel lblTotalInterviewPriceAddSensor) {
		this.jlbTotalInterviewPriceAddSensor = lblTotalInterviewPriceAddSensor;
	}

	/**
	 * @return the jbOneMoreAddSensor
	 */
	public JButton getJbOneMoreAddSensor() {
		return jbOneMoreAddSensor;
	}

	/**
	 * @param jbOneMoreAddSensor the jbOneMoreAddSensor to set
	 */
	public void setJbOneMoreAddSensor(JButton jbOneMoreAddSensor) {
		this.jbOneMoreAddSensor = jbOneMoreAddSensor;
	}

	/**
	 * @return the jbOneLessAddSensor
	 */
	public JButton getJbOneLessAddSensor() {
		return jbOneLessAddSensor;
	}

	/**
	 * @param jbOneLessAddSensor the jbOneLessAddSensor to set
	 */
	public void setJbOneLessAddSensor(JButton jbOneLessAddSensor) {
		this.jbOneLessAddSensor = jbOneLessAddSensor;
	}

	/**
	 * @return the jbDeleteAddSensor
	 */
	public JButton getJbDeleteAddSensor() {
		return jbDeleteAddSensor;
	}

	/**
	 * @param jbDeleteAddSensor the jbDeleteAddSensor to set
	 */
	public void setJbDeleteAddSensor(JButton jbDeleteAddSensor) {
		this.jbDeleteAddSensor = jbDeleteAddSensor;
	}

	/**
	 * @return the lblBasketTitleAddSensorPanel
	 */
	public JLabel getLblBasketTitleAddSensorPanel() {
		return jlbBasketTitleAddSensorPanel;
	}

	/**
	 * @param lblBasketTitleAddSensorPanel the lblBasketTitleAddSensorPanel to set
	 */
	public void setLblBasketTitleAddSensorPanel(JLabel lblBasketTitleAddSensorPanel) {
		this.jlbBasketTitleAddSensorPanel = lblBasketTitleAddSensorPanel;
	}

	/**
	 * @return the alListSensorsAddSensor
	 */
	public ArrayList<SensorShop> getAlListSensorsAddSensor() {
		return alListSensorsAddSensor;
	}

	/**
	 * @param alListSensorsAddSensor the alListSensorsAddSensor to set
	 */
	public void setAlListSensorsAddSensor(ArrayList<SensorShop> alListSensorsAddSensor) {
		this.alListSensorsAddSensor = alListSensorsAddSensor;
	}

	/**
	 * @return the dlmSensorsAddSensor
	 */
	public DefaultListModel<String> getDlmSensorsAddSensor() {
		return dlmSensorsAddSensor;
	}

	/**
	 * @param dlmSensorsAddSensor the dlmSensorsAddSensor to set
	 */
	public void setDlmSensorsAddSensor(DefaultListModel<String> dlmSensorsAddSensor) {
		this.dlmSensorsAddSensor = dlmSensorsAddSensor;
	}

	/**
	 * @return the jlSensorsNameAddSensor
	 */
	public JList<String> getJlSensorsNameAddSensor() {
		return jlSensorsNameAddSensor;
	}

	/**
	 * @param jlSensorsNameAddSensor the jlSensorsNameAddSensor to set
	 */
	public void setJlSensorsNameAddSensor(JList<String> jlSensorsNameAddSensor) {
		this.jlSensorsNameAddSensor = jlSensorsNameAddSensor;
	}

	/**
	 * @return the basketAddSensor
	 */
	public BasketSensor getBasketAddSensor() {
		return basketAddSensor;
	}

	/**
	 * @param basketAddSensor the basketAddSensor to set
	 */
	public void setBasketAddSensor(BasketSensor basketAddSensor) {
		this.basketAddSensor = basketAddSensor;
	}

	/**
	 * @return the dlmBasketLineAddSensor
	 */
	public DefaultListModel<String> getDlmBasketLineAddSensor() {
		return dlmBasketLineAddSensor;
	}

	/**
	 * @param dlmBasketLineAddSensor the dlmBasketLineAddSensor to set
	 */
	public void setDlmBasketLineAddSensor(DefaultListModel<String> dlmBasketLineAddSensor) {
		this.dlmBasketLineAddSensor = dlmBasketLineAddSensor;
	}

	/**
	 * @return the jlBasketLineAddSensor
	 */
	public JList<String> getJlBasketLineAddSensor() {
		return jlBasketLineAddSensor;
	}

	/**
	 * @param jlBasketLineAddSensor the jlBasketLineAddSensor to set
	 */
	public void setJlBasketLineAddSensor(JList<String> jlBasketLineAddSensor) {
		this.jlBasketLineAddSensor = jlBasketLineAddSensor;
	}

	/**
	 * @return the create
	 */
	public JLabel getCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public void setCreate(JLabel create) {
		this.create = create;
	}

	/**
	 * @return the jlPartsDoorNumberNewHome
	 */
	public JLabel getJlPartsDoorNumberNewHome() {
		return jlPartsDoorNumberNewHome;
	}

	/**
	 * @param jlPartsDoorNumberNewHome the jlPartsDoorNumberNewHome to set
	 */
	public void setJlPartsDoorNumberNewHome(JLabel jlPartsDoorNumberNewHome) {
		this.jlPartsDoorNumberNewHome = jlPartsDoorNumberNewHome;
	}

	/**
	 * @return the jtfPartsDoorNumberNewHome
	 */
	public JTextField getJtfPartsDoorNumberNewHome() {
		return jtfPartsDoorNumberNewHome;
	}

	/**
	 * @param jtfPartsDoorNumberNewHome the jtfPartsDoorNumberNewHome to set
	 */
	public void setJtfPartsDoorNumberNewHome(JTextField jtfPartsDoorNumberNewHome) {
		this.jtfPartsDoorNumberNewHome = jtfPartsDoorNumberNewHome;
	}

	/**
	 * @return the jlPartsWindowNumberNewHome
	 */
	public JLabel getJlPartsWindowNumberNewHome() {
		return jlPartsWindowNumberNewHome;
	}

	/**
	 * @param jlPartsWindowNumberNewHome the jlPartsWindowNumberNewHome to set
	 */
	public void setJlPartsWindowNumberNewHome(JLabel jlPartsWindowNumberNewHome) {
		this.jlPartsWindowNumberNewHome = jlPartsWindowNumberNewHome;
	}

	/**
	 * @return the jtfPartsWindowNumberNewHome
	 */
	public JTextField getJtfPartsWindowNumberNewHome() {
		return jtfPartsWindowNumberNewHome;
	}

	/**
	 * @param jtfPartsWindowNumberNewHome the jtfPartsWindowNumberNewHome to set
	 */
	public void setJtfPartsWindowNumberNewHome(JTextField jtfPartsWindowNumberNewHome) {
		this.jtfPartsWindowNumberNewHome = jtfPartsWindowNumberNewHome;
	}

	/**
	 * @return the jbDeletePartsNewHome
	 */
	public JButton getJbDeletePartsNewHome() {
		return jbDeletePartsNewHome;
	}

	/**
	 * @param jbDeletePartsNewHome the jbDeletePartsNewHome to set
	 */
	public void setJbDeletePartsNewHome(JButton jbDeletePartsNewHome) {
		this.jbDeletePartsNewHome = jbDeletePartsNewHome;
	}

	/**
	 * @return the jlPartsAccessControlNewHome
	 */
	public JLabel getJlPartsAccessControlNewHome() {
		return jlPartsAccessControlNewHome;
	}

	/**
	 * @param jlPartsAccessControlNewHome the jlPartsAccessControlNewHome to set
	 */
	public void setJlPartsAccessControlNewHome(JLabel jlPartsAccessControlNewHome) {
		this.jlPartsAccessControlNewHome = jlPartsAccessControlNewHome;
	}

	/**
	 * @return the checkBoxAccessControlNewHome
	 */
	public JCheckBox getCheckBoxAccessControlNewHome() {
		return checkBoxAccessControlNewHome;
	}

	/**
	 * @param checkBoxAccessControlNewHome the checkBoxAccessControlNewHome to set
	 */
	public void setCheckBoxAccessControlNewHome(JCheckBox checkBoxAccessControlNewHome) {
		this.checkBoxAccessControlNewHome = checkBoxAccessControlNewHome;
	}

	/**
	 * @return the jbAddSensorShopToBasketFromBuilderNewHome
	 */
	public JButton getJbAddSensorShopToBasketFromBuilderNewHome() {
		return jbAddSensorShopToBasketFromBuilderNewHome;
	}

	/**
	 * @param jbAddSensorShopToBasketFromBuilderNewHome the jbAddSensorShopToBasketFromBuilderNewHome to set
	 */
	public void setJbAddSensorShopToBasketFromBuilderNewHome(JButton jbAddSensorShopToBasketFromBuilderNewHome) {
		this.jbAddSensorShopToBasketFromBuilderNewHome = jbAddSensorShopToBasketFromBuilderNewHome;
	}

	/**
	 * @return the jlbTitleBasketBuilderToSensorShopNewHome
	 */
	public JLabel getJlbTitleBasketBuilderToSensorShopNewHome() {
		return jlbTitleBasketBuilderToSensorShopNewHome;
	}

	/**
	 * @param jlbTitleBasketBuilderToSensorShopNewHome the jlbTitleBasketBuilderToSensorShopNewHome to set
	 */
	public void setJlbTitleBasketBuilderToSensorShopNewHome(JLabel jlbTitleBasketBuilderToSensorShopNewHome) {
		this.jlbTitleBasketBuilderToSensorShopNewHome = jlbTitleBasketBuilderToSensorShopNewHome;
	}

	/**
	 * @return the jpBasketBuilderToSensoShopNewHome
	 */
	public JPanel getJpBasketBuilderToSensoShopNewHome() {
		return jpBasketBuilderToSensoShopNewHome;
	}

	/**
	 * @param jpBasketBuilderToSensoShopNewHome the jpBasketBuilderToSensoShopNewHome to set
	 */
	public void setJpBasketBuilderToSensoShopNewHome(JPanel jpBasketBuilderToSensoShopNewHome) {
		this.jpBasketBuilderToSensoShopNewHome = jpBasketBuilderToSensoShopNewHome;
	}

	/**
	 * @return the jspBasketBuilderToSensoShopNewHome
	 */
	public JScrollPane getJspBasketBuilderToSensoShopNewHome() {
		return jspBasketBuilderToSensoShopNewHome;
	}

	/**
	 * @param jspBasketBuilderToSensoShopNewHome the jspBasketBuilderToSensoShopNewHome to set
	 */
	public void setJspBasketBuilderToSensoShopNewHome(JScrollPane jspBasketBuilderToSensoShopNewHome) {
		this.jspBasketBuilderToSensoShopNewHome = jspBasketBuilderToSensoShopNewHome;
	}

	/**
	 * @return the jlbTotalPriceOfSensorShopBasketNewHome
	 */
	public JLabel getJlbTotalPriceOfSensorShopBasketNewHome() {
		return jlbTotalPriceOfSensorShopBasketNewHome;
	}

	/**
	 * @param jlbTotalPriceOfSensorShopBasketNewHome the jlbTotalPriceOfSensorShopBasketNewHome to set
	 */
	public void setJlbTotalPriceOfSensorShopBasketNewHome(JLabel jlbTotalPriceOfSensorShopBasketNewHome) {
		this.jlbTotalPriceOfSensorShopBasketNewHome = jlbTotalPriceOfSensorShopBasketNewHome;
	}

	/**
	 * @return the jlbTotalInterviewCostOfSensorShopBasketNewHome
	 */
	public JLabel getJlbTotalInterviewCostOfSensorShopBasketNewHome() {
		return jlbTotalInterviewCostOfSensorShopBasketNewHome;
	}

	/**
	 * @param jlbTotalInterviewCostOfSensorShopBasketNewHome the jlbTotalInterviewCostOfSensorShopBasketNewHome to set
	 */
	public void setJlbTotalInterviewCostOfSensorShopBasketNewHome(JLabel jlbTotalInterviewCostOfSensorShopBasketNewHome) {
		this.jlbTotalInterviewCostOfSensorShopBasketNewHome = jlbTotalInterviewCostOfSensorShopBasketNewHome;
	}

	/**
	 * @return the basketSensorShopFromBuilder
	 */
	public BasketSensor getBasketSensorShopFromBuilder() {
		return basketSensorShopFromBuilderNewHome;
	}

	/**
	 * @param basketSensorShopFromBuilder the basketSensorShopFromBuilder to set
	 */
	public void setBasketSensorShopFromBuilder(BasketSensor basketSensorShopFromBuilder) {
		basketSensorShopFromBuilderNewHome = basketSensorShopFromBuilder;
	}

	/**
	 * @return the dlmBasketSensorShopLinesNewHome
	 */
	public DefaultListModel<String> getDlmBasketSensorShopLinesNewHome() {
		return dlmBasketSensorShopLinesNewHome;
	}

	/**
	 * @param dlmBasketSensorShopLinesNewHome the dlmBasketSensorShopLinesNewHome to set
	 */
	public void setDlmBasketSensorShopLinesNewHome(DefaultListModel<String> dlmBasketSensorShopLinesNewHome) {
		this.dlmBasketSensorShopLinesNewHome = dlmBasketSensorShopLinesNewHome;
	}

	/**
	 * @return the jlBasketSensorShopLinesNewHome
	 */
	public JList<String> getJlBasketSensorShopLinesNewHome() {
		return jlBasketSensorShopLinesNewHome;
	}

	/**
	 * @param jlBasketSensorShopLinesNewHome the jlBasketSensorShopLinesNewHome to set
	 */
	public void setJlBasketSensorShopLinesNewHome(JList<String> jlBasketSensorShopLinesNewHome) {
		this.jlBasketSensorShopLinesNewHome = jlBasketSensorShopLinesNewHome;
	}
}
