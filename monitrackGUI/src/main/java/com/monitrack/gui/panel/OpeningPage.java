package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.monitrack.enumeration.Images;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.listener.OpeningPageListener;

public class OpeningPage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Image projectNameImage;
	private Image groupLogoImage;
	private OpeningPageListener listener;
	private MonitrackFrame parentFrame;
	private int parentFrameWidth;
	private int parentFrameHeight;
	private int logoWidth;
	private int logoHeight;
	private int logoX;
	private int logoY;
	private JLabel connectionStateLabel;
	
	private JButton accessToServerButton;
	
	public OpeningPage(MonitrackFrame parentFrame)
	{
		setLayout(new BorderLayout());
		connectionStateLabel = new JLabel("", SwingConstants.CENTER);
		this.parentFrame = parentFrame;
		parentFrameWidth = parentFrame.getWidth();
		parentFrameHeight = parentFrame.getHeight();
		setBackground(new Color(255,255,255));
		
		listener = new OpeningPageListener(parentFrame, this);
		
		projectNameImage = Images.PROJECT_LOGO.getImage();
		groupLogoImage = Images.GROUP_LOGO.getImage();
		
		logoWidth = groupLogoImage.getWidth(null);
		logoHeight = groupLogoImage.getHeight(null);
		
		logoX = parentFrameWidth - logoWidth;
		logoY = parentFrameHeight - logoHeight - 15;
		
		accessToServerButton = new JButton("Accéder aux serveurs");
		accessToServerButton.addActionListener(listener);
		connectionStateLabel.setFont(new Font("Arial", Font.BOLD, 22));
		add(connectionStateLabel, BorderLayout.CENTER);
		add(accessToServerButton, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(projectNameImage, 90, 20, null);
		//g2.drawImage(groupLogoImage, logoX, logoY, null);
		this.revalidate();
	}

	/**
	 * @return the accessToServerButton
	 */
	public JButton getAccessToServerButton() {
		return accessToServerButton;
	}

	/**
	 * @return the connectionStateLabel
	 */
	public JLabel getConnectionStateLabel() {
		return connectionStateLabel;
	}
	
	
	
	
	
}
