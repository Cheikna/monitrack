package com.monitrack.gui.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MonitrackFrame extends JFrame implements ActionListener
{
	// déclaration des composants graphiques
	JPanel north = new JPanel(new FlowLayout());
	//Etiquettes ou JLABEL
	JLabel jlName = new JLabel("Nom :");
	//Zone de saisie de texte
	JTextField jtfName  = new JTextField(10);
	//Boutons ou JButtons
	JButton jbValidate     = new JButton("Valider");
	JButton jbOverview = new JButton("Tout voir");
	//Zone de texte ou JTextArea
	JTextArea jTArea = new JTextArea();
	public MonitrackFrame()
	{
		jbValidate.addActionListener(this);
		jbOverview.addActionListener(this);
		north.add(jlName);
		north.add(jtfName);
		north.add(jbValidate);
		jTArea.setEditable(false);
		this.getContentPane().add(north, BorderLayout.NORTH);
		this.getContentPane().add(jTArea, BorderLayout.CENTER);
		this.getContentPane().add(jbOverview, BorderLayout.SOUTH);
		this.setTitle("MONITRACK");
		this.setSize(600, 300);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
		//PLACEMENT DES COMPOSANTS GRAPHIQUES
	public static void main(String[] args)
	{
		MonitrackFrame monitrack = new MonitrackFrame();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbValidate)
		{
			System.out.println("JBVALIDATE");
		}
		if(e.getSource()==jbOverview)
		{
			System.out.println("JBOVERVIEW");
		}
	}
}

