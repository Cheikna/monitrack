package com.monitrack.gui.frame;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.monitrack.enumeration.Images;
import com.monitrack.gui.dialog.SuperUserModeDialog;
import com.monitrack.gui.panel.HomePage;
import com.monitrack.gui.panel.OpeningPage;
import com.monitrack.listener.MonitrackListener;

public class MonitrackFrame extends JFrame
{
	private static final long serialVersionUID = -1L;
	
	private OpeningPage openingPage;
	private String openingPageName;
	private HomePage homePage;
	private String homePageName;
	private JButton developerModeButton;
	private JButton superUserModeButton;
	private SuperUserModeDialog superUserModeDialog;

	private JButton infiniteRequestButton;

	//Panel which contains the superUserModeButton
	private JPanel northPanel;

	private CardLayout cardLayout;
	//The center panel will keep the cardLayout
	private JPanel centerPanel;

	private MonitrackListener listener;


	public MonitrackFrame()
	{
		listener = new MonitrackListener(this);

		superUserModeDialog = new SuperUserModeDialog(this);

		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.setBackground(Color.WHITE);

		developerModeButton = new JButton(Images.DEVELOPER.getIcon());
		developerModeButton.addActionListener(listener);
		developerModeButton.setToolTipText("Mode développeur");

		superUserModeButton = new JButton(Images.SUPER.getIcon());
		superUserModeButton.addActionListener(listener);
		superUserModeButton.setToolTipText("Mode super utilisateur");

		infiniteRequestButton = new JButton(Images.INFINITE_LOOP.getIcon());
		infiniteRequestButton.addActionListener(listener);
		infiniteRequestButton.setToolTipText("Requête en infini");

		setNorthPanel(false);

		cardLayout = new CardLayout();
		centerPanel = new JPanel(cardLayout);

		openingPage = new OpeningPage(this);
		homePage = new HomePage();		

		openingPageName = "OPENING_PAGE";
		centerPanel.add(openingPage, openingPageName);

		homePageName = "HOME_PAGE";
		centerPanel.add(homePage, homePageName);


		this.setTitle("MONITRACK");
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
			northPanel.add(infiniteRequestButton);
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
	 * @return the superUserModeDialog
	 */
	public SuperUserModeDialog getSuperUserModeDialog() {
		return superUserModeDialog;
	}

	/**
	 * @return the infiniteRequestButton
	 */
	public JButton getInfiniteRequestButton() {
		return infiniteRequestButton;
	}

	/**
	 * @return the developerModeButton
	 */
	public JButton getDeveloperModeButton() {
		return developerModeButton;
	}

}