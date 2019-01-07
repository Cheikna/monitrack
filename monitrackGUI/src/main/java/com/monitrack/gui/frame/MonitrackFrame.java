package com.monitrack.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
	JTextArea JTArea = new JTextArea();
	public MonitrackFrame()
	{
		jbValidate.addActionListener(this);
		jbOverview.addActionListener(this);
		north.add(jlName);
		north.add(jtfName);
		north.add(jbValidate);
		this.getContentPane().add(north, BorderLayout.NORTH);
		this.getContentPane().add(JTArea, BorderLayout.CENTER);
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
	@Override
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

