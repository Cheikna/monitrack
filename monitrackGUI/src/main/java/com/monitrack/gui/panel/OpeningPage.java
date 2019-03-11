package com.monitrack.gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.monitrack.enumeration.Images;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.listener.OpeningPageListener;

public class OpeningPage extends JPanel {
	
	private Timer blinkTimer;
	private Image projectNameImage;
	private Image groupLogoImage;
	private boolean isImageVisible;
	private OpeningPageListener listener;
	
	public OpeningPage(MonitrackFrame parentFrame)
	{
		setBackground(new Color(255,255,255));
		
		listener = new OpeningPageListener(parentFrame);
		this.setFocusable(true);
		addKeyListener(listener);
		projectNameImage = Images.PROJECT_LOGO.getImage();
		groupLogoImage = Images.GROUP_LOGO.getImage();
		isImageVisible = true;
		blinkTimer = new Timer(850, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				/*
				 * When the time is reached, the state changes to its opposite.
				 * For example : if the image was visible, it is now not visible and if it was not visible it is now visible 
				 */
				isImageVisible = !isImageVisible;	
				repaint();
			}
		});
		blinkTimer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(projectNameImage, 90, 50, null);
		
		if(isImageVisible)
		{
			Font font = new Font("Arial", Font.BOLD, 25);
			g2.setFont(font);
			
			/*if(DataSource.isConnectionPoolFilled())
				g2.drawString("Création des connexions terminées, appuyez sur une touche...", 12, 200);
			else
				g2.drawString("Création des connexions pour le pool de connexions en cours...", 12, 200);*/
			g2.drawImage(groupLogoImage, 300, 220, null);		
		}

		this.revalidate();
	}

}
