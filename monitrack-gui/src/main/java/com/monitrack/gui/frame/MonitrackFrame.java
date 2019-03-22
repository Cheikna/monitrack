package com.monitrack.gui.frame;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.monitrack.enumeration.Images;
import com.monitrack.gui.panel.HomePage;
import com.monitrack.gui.panel.OpeningPage;
import com.monitrack.listener.MonitrackListener;

public class MonitrackFrame extends JFrame
{
	private OpeningPage openingPage;
	private String openingPageName;
	private HomePage homePage;
	private String homePageName;
	private JButton superUserModeButton;
	
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
		superUserModeButton = new JButton(Images.SUPER.getIcon());
		superUserModeButton.addActionListener(listener);
		superUserModeButton.setToolTipText("Mode super utilisateur");
		northPanel.add(superUserModeButton);
		
		cardLayout = new CardLayout();
		centerPanel = new JPanel(cardLayout);
		//setLayout(cardLayout);
				
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
	
	
	
	
}