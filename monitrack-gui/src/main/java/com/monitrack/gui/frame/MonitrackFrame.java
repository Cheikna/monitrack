package com.monitrack.gui.frame;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.monitrack.enumeration.Images;
import com.monitrack.gui.panel.HomePage;
import com.monitrack.gui.panel.OpeningPage;
import com.monitrack.listener.MonitrackListener;
import com.monitrack.shared.MonitrackGUIFactory;

public class MonitrackFrame extends JFrame
{
	private static final long serialVersionUID = -1L;
	
	private OpeningPage openingPage;
	private String openingPageName;
	private HomePage homePage;
	private String homePageName;
	private JButton developerModeButton;
	
	//Button to generate some random values according to the entity selected
	private JButton superUserModeButton;

	//Button to reserve a connection
	private JButton reserveConectionButton;
	//Label to indication the remaining time before the reserved connection is released
	private JLabel timerLabel;
	
	//Button to open the .log file
	private JButton openLogFileButton;

	//Panel which contains the superUserModeButton
	private JPanel northPanel;

	private CardLayout cardLayout;
	//The center panel will keep the cardLayout
	private JPanel centerPanel;

	private MonitrackListener listener;


	public MonitrackFrame()
	{
		listener = new MonitrackListener(this);

		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.setBackground(Color.WHITE);

		developerModeButton = new JButton(Images.DEVELOPER.getIcon());
		developerModeButton.addActionListener(listener);
		developerModeButton.setToolTipText("Mode d�veloppeur");

		superUserModeButton = new JButton(Images.SUPER.getIcon());
		superUserModeButton.addActionListener(listener);
		superUserModeButton.setToolTipText("G�n�rer des valeurs al�atoires");

		reserveConectionButton = new JButton(Images.INFINITE_LOOP.getIcon());
		reserveConectionButton.addActionListener(listener);
		reserveConectionButton.setToolTipText("R�server une connexion");
		
		openLogFileButton = new JButton(Images.LOG_FILE.getIcon());
		openLogFileButton.addActionListener(listener);
		openLogFileButton.setToolTipText("Ouvrir le fichier monitrack.log");		

		timerLabel = new JLabel("");
		timerLabel.setIcon(Images.CLOCK.getIcon());
		timerLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		timerLabel.setVisible(false);

		setNorthPanel(false);

		cardLayout = new CardLayout();
		centerPanel = new JPanel(cardLayout);

		openingPage = new OpeningPage(this);
		homePage = new HomePage();		

		openingPageName = "OPENING_PAGE";
		centerPanel.add(openingPage, openingPageName);

		homePageName = "HOME_PAGE";
		centerPanel.add(homePage, homePageName);


		this.setTitle("MONITRACK version " + MonitrackGUIFactory.getApplicationVersion());
		cardLayout.show(centerPanel, openingPageName);

		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		this.setSize(1000, 600);
		setLocationRelativeTo(null);
		this.addWindowListener(listener);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void changePage(String pageName) {
		cardLayout.show(centerPanel, pageName);
	}
	
	public void setNorthPanel(boolean isDeveloperModeActive)
	{
		northPanel.removeAll();
		
		if(isDeveloperModeActive)
		{
			northPanel.add(superUserModeButton);
			northPanel.add(reserveConectionButton);
			northPanel.add(openLogFileButton);
			northPanel.add(timerLabel);
		}
		else
		{
			northPanel.add(developerModeButton);			
		}
		
		northPanel.repaint();
		northPanel.revalidate();
	}

	/**
	 * @return the homePageName
	 */
	public String getHomePageName() {
		return homePageName;
	}

	/**
	 * @return the superUserModeButton
	 */
	public JButton getSuperUserModeButton() {
		return superUserModeButton;
	}

	/**
	 * @return the reserveConnectionButton
	 */
	public JButton getReserveConnectionButton() {
		return reserveConectionButton;
	}

	/**
	 * @return the developerModeButton
	 */
	public JButton getDeveloperModeButton() {
		return developerModeButton;
	}

	/**
	 * @return the openLogFileButton
	 */
	public JButton getOpenLogFileButton() {
		return openLogFileButton;
	}

	/**
	 * @return the timerLabel
	 */
	public JLabel getTimerLabel() {
		return timerLabel;
	}
	
}