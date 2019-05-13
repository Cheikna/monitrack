package com.monitrack.gui.panel;


	


















import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.monitrack.entity.Location;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestType;
import com.monitrack.shared.MonitrackGuiUtil;
//import com.monitrack.dao.implementation.MapDAO;
import com.monitrack.util.JsonUtil;

public class MapPage extends JPanel implements MouseListener {

	private Image planImage;
	private static final Rectangle poly1 = new Rectangle(55, 268, 310, 340);
	private static final Rectangle poly2 = new Rectangle(430, 268, 340, 170);
	JOptionPane jop1;
	ArrayList<String> mylist = new ArrayList<String>();
	
	
	
	private final String[] buildingWinds = {"NORD", "SUD", "OUEST", "EST"};
	private JPanel createLocationPopupPanel;
	private JTextField newLocationNameTextField;
	private JTextField newFloorTextField;
	private JTextField newLocationSizeTextField;
	private JComboBox<String> newBuildingWingCombobox;
	

	public MapPage() {
		
		planImage = Images.MAP.getImage();
		this.addMouseListener(this);
		
		

		try {
		String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null);
	    String response = MonitrackGuiUtil.sendRequest(jsonRequest);
	    List<Location> locations;
     	locations = (List<Location>) JsonUtil.deserializeObject(response);
      for (Location location : locations) {
      mylist.add(location.getIdLocation() + " - " + location.getNameLocation().toString());
     
     	
	}
		}catch(Exception e){
		e.printStackTrace();
	}
	
	}
	private static final long serialVersionUID = 7378770185814371929L;
	private BufferedImage buffer = null;
	Point p = null;

	public MapPage(BufferedImage image) {
		this.addMouseListener(this);
		this.buffer = image;
	}

	private void testLocation(Point mouse, Rectangle commonArea, String text) {
		// if the mouse if here
		if (commonArea.contains(mouse))
			System.out.println(text + " - image");
		else
			System.out.println(text + " - !image");
	}

	private boolean location(Point mouse, Rectangle commonArea) {
		if (commonArea.contains(mouse))
			return true;
		else
			return false;
	}

	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent e) {
		// récupération de la position de la souri
		p = e.getPoint();
		testLocation(p, poly1, "mouseClicked - data 1");
		testLocation(p, poly2, "mouseClicked - data 2");
		
		if (location(p, poly1) == true) {
			//new SurfacePolygon(polygon1).setVisible(true);
			System.out.println("Polygon1");
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "<HTML><BR>capteurs<BR><HTML>"+ mylist+"Etat:", "Salle de repos_1", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int planWidth = planImage.getWidth(null);
		int panelWidth = this.getWidth();
		int leftOffset = 3 * ((panelWidth - planWidth) / 4);

		g2.drawImage(planImage, 50, 0, leftOffset, this.getHeight(), this);

		this.revalidate();
		// g2.drawImage(buffer, 0, 0, buffer.getWidth(), buffer.getHeight(), this);
		g2.setColor(Color.red);
		g2.drawRect(poly1.x, poly1.y, poly1.width, poly1.height);
		g2.drawRect(poly2.x, poly2.y, poly2.width, poly2.height);

	}
}











//
//// JComboBox<String> cbE = new JComboBox();
//// private Image planImage= Images.MAP.getImage();
//// /**
//// *
//// */
////
////
//
//// public MapPage() {
////
//// //this.buffer = planImage;
//// String[] values = {"Elem1", "Elem2", "Elem3"} ;
//// cbE = new JComboBox(values) ;
////
////
//// cbE.setBounds(400, 200, 490, 120);
//// add(cbE);
////
////
//// cbE.addActionListener(new ActionListener() {
////
//// @Override
//// public void actionPerformed(ActionEvent arg0) {
////
////
//// String result = (String) cbE.getSelectedItem();
//// if(result.equals("Elem1")) {
//// JPanel pan1= new JPanel();
//// pan1.setBackground(Color.BLACK);
//// add(pan1);
////
////
////
//// System.out.println("Salle 1");
//// }else if (result.equals("Elem2")) {
//// System.out.println("Salle 2");
//// }
////
////
//// }
////
//// });
////
//// }
//// @Override
//// public void mouseClicked(MouseEvent arg0) {
//// // TODO Auto-generated method stub
////
//// }
//// @Override
//// public void mouseEntered(MouseEvent arg0) {
//// // TODO Auto-generated method stub
////
//// }
//// @Override
//// public void mouseExited(MouseEvent arg0) {
//// // TODO Auto-generated method stub
////
//// }
//// @Override
//// public void mousePressed(MouseEvent arg0) {
//// // TODO Auto-generated method stub
////
//// }
//// @Override
//// public void mouseReleased(MouseEvent arg0) {
//// // TODO Auto-generated method stub
////
//// }
////
////
////
////
//// }
////
////
//
//// private Image planImage;
//// private JPanel pan = new JPanel();
////
//// final JLabel label = new JLabel();
////
//// ArrayList<String> mylist = new ArrayList<String>();
//// final JComboBox<String> cb = new JComboBox();
////
//// private JLabel lblImage = new JLabel();
//// private JTextField NameLocation;
////
//// public MapPage() {
////
//// setLayout(new BorderLayout());
//// setBackground(new Color(220, 220, 220));
//// // planImage= Images.MAP.getImage();
////
//// label.setHorizontalAlignment(JLabel.CENTER);
//// label.setSize(400, 100);
////
//// try {
////
//// String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT,
//// Location.class, null, null, null);
//// String response = MonitrackGuiUtil.sendRequest(jsonRequest);
//// List<Location> locations;
//// locations = (List<Location>) JsonUtil.deserializeObject(response);
////
//// for (Location location : locations) {
//// mylist.add(location.getIdLocation() + " - " +
//// location.getNameLocation().toString());
//// cb.addItem(location.getIdLocation() + " - " +
//// location.getNameLocation().toString());
//// }
////
//// } catch (Exception e) {
//// e.printStackTrace();
//// }
////
//// cb.setBounds(400, 200, 490, 120);
//// add(cb);
//// add(label);
////
//// String item = "" + cb.getSelectedIndex();
////
//// // cb.addItemListener(new ItemListener() {
//// // public void itemStateChanged (ItemEvent arg0) {
//// // if (arg0.getStateChange()== ItemEvent.SELECTED) {
//// // lblImage.setText("salle1");
//// // }
//// // }
//// // });
////
//// cb.addActionListener(new ActionListener() {
////
//// @Override
//// public void actionPerformed(ActionEvent arg0) {
//// System.out.println(item);
//// if (item.equals("0") ) {
////
//// JPanel panel = new JPanel();
//// add(panel);
//// NameLocation = new JTextField("ok");
//// panel.add(NameLocation);
//// NameLocation.setColumns(10);
//// panel.setVisible(true);
//// panel.setBounds(100, 50, 490, 120);
////
////
//// } else {
////
//// }
////
//// }
//// });
////
////
////
//// }
////
//// /*
//// * public void paintComponent(Graphics g){ super.paintComponent(g); Graphics2D
//// * g2 = (Graphics2D)g;
//// *
//// * int planWidth = planImage.getWidth(null); int panelWidth = this.getWidth();
//// * int leftOffset = (panelWidth - planWidth) / 2;
//// *
//// *
//// * g2.drawImage(planImage, leftOffset, 20, null); this.revalidate();
//// *
//// * }
//// */
