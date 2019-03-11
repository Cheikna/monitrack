package com.monitrack.gui.frame;


import java.awt.CardLayout;

import javax.swing.JFrame;
import com.monitrack.gui.panel.HomePage;
import com.monitrack.gui.panel.OpeningPage;
import com.monitrack.listener.MonitrackListener;

public class MonitrackFrame extends JFrame
{
	private OpeningPage openingPage;
	private String openingPageName;
	
	private HomePage homePage;
	private String homePageName;
	
	private CardLayout cardLayout;
	
	private MonitrackListener listener;

	
	public MonitrackFrame()
	{
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		openingPage = new OpeningPage(this);
		homePage = new HomePage();		

		openingPageName = "OPENING_PAGE";
	    add(openingPage, openingPageName);
	    
	    homePageName = "HOME_PAGE";
	    add(homePage, homePageName);
	    
	    listener = new MonitrackListener(this);
		
		this.setTitle("MONITRACK");
		cardLayout.show(this.getContentPane(), openingPageName);
		this.setSize(800, 450);
		setLocationRelativeTo(null);
		this.addWindowListener(listener);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void changePage(String pageName) {
	    cardLayout.show(this.getContentPane(), pageName);
	  }

	/**
	 * @return the homePageName
	 */
	public String getHomePageName() {
		return homePageName;
	}
	
	
}