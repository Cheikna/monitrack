package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.monitrack.entity.Location;
import com.monitrack.entity.LocationSensors;
import com.monitrack.entity.Sensor;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.shared.MonitrackGuiUtil;
//import com.monitrack.dao.implementation.MapDAO;
import com.monitrack.util.JsonUtil;
import com.monitrack.util.Util;

public class MapPage extends JPanel implements MouseListener {

	private Image planImage;
	private static final Rectangle poly1 = new Rectangle(55, 268, 310, 340);
	private static final Rectangle poly2 = new Rectangle(430, 268, 340, 170);
	JOptionPane jop1;
	ArrayList<String> mylist = new ArrayList<String>();

	private final String[] buildingWinds = { "NORD", "SUD", "OUEST", "EST" };
	private JPanel createLocationPopupPanel;
	private JTextField newLocationNameTextField;
	private JTextField newFloorTextField;
	private JTextField newLocationSizeTextField;
	private JComboBox<String> newBuildingWingCombobox;
	List<LocationSensors> locationSensors;
	List<Location> locations;

	public void drawLocations(Graphics2D g2) {
		for(Location location : locations) {
			drawLocation(location, g2);
		}
	}

	public void drawLocation(Location location, Graphics2D g2) {

		//Rectangle poly1 = new Rectangle(55, 268, 310, 340);
		Float x = location.getPositionX();
		Float y = location.getPositionY();
		Float width = location.getWidth();
		Float height = location.getHeight();
		//g2.drawRect(x.intValue() + 50, y.intValue() + 80, width.intValue(),height.intValue() );
		g2.drawRect(80, 395, 440, 490);
		g2.drawRect(660, 395, 500, 250);
	}

	public MapPage() {

		planImage = Images.MAP.getImage();
		this.addMouseListener(this);

		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null, null, RequestSender.CLIENT);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);

			locations = (List<Location>) JsonUtil.deserializeObject(response);
			List<SensorConfiguration> sensors = null;

			try {
				//FIXME Cheikna : do not forget to remove this
				jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, null,
						null, null, RequestSender.CLIENT);
				response = MonitrackGuiUtil.sendRequest(jsonRequest);
				sensors = (List<SensorConfiguration>) JsonUtil.deserializeObject(response);
				for (SensorConfiguration s : sensors) {
					System.out.println("===========> " + s);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}


			/*for (Location location : locations) {
				mylist.add(location.getIdLocation() + " - " + location.getNameLocation().toString());

			}*/


			// récuperer toute les location avec les champs sensors remplis
			jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, LocationSensors.class, null, null, null, null, RequestSender.CLIENT);
			response = MonitrackGuiUtil.sendRequest(jsonRequest);
			locationSensors = (List<LocationSensors>) JsonUtil.deserializeObject(response);

			List<Integer> sensorIds = new ArrayList();
			List<Sensor> mySensors;
			int[][] poses = {{55, 268, 310, 340}, {430, 268, 340, 170}};
			/*Location l1 = locations.get(0);
			Location l2 = locations.get(1);
			locations.clear();
			locations.add(l1);
			locations.add(l2);*/
			int i = 0;
			// on va affecter les sensorLocation aux locations
			for(Location location : locations) {
				mySensors = new ArrayList<>();
				location.setSensors(mySensors);
				
				location.setPositionX(poses[i][0]);
				location.setPositionY(poses[i][1]);
				location.setWidth(poses[i][2]);
				location.setHeight(poses[i][3]);
				// parcourir tous les sensor_location et trouver le meme id location
				//for(LocationSensors l : locationSensors) {
				
				int j,k ;
				Random r =new Random();
				for(SensorConfiguration sensor : sensors) {
					if(sensor.getLocationId() == location.getIdLocation()) {
						System.out.println("====> " + sensor);
						mySensors.add(sensor);
						j = r.nextInt(SensorType.values().length);
						k = r.nextInt(SensorState.values().length);
						sensor.setSensorType(SensorType.values()[j]);
						sensor.setState(SensorState.values()[k]);
					}
				}
				i++;
			}


			//Rectangle poly1 = new Rectangle(55, 268, 310, 340);
			//Rectangle poly2 = new Rectangle(430, 268, 340, 170);
			// TODO à supprimer

//			Location location = locations.get(0);
//			locations.clear();
//			locations.add(location);
//			location.setX(55);
//			location.setY(268);
//			location.setWidth(310);
//			location.setHeight(340);
//			List<Sensor> ss = new ArrayList<>();
//			sensors.get(0).setSensorType(SensorType.ACCESS_CONTROL);
//			sensors.get(1).setSensorType(SensorType.DOOR);
//			ss.add(sensors.get(0));
//			ss.add(sensors.get(1));
//			locations.get(0).setSensors(ss);



		} catch (Exception e) {
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
	
	public void showSensor(Location location, SensorConfiguration sc) {
		String text;
		Float x = location.getPositionX();
		Float y = location.getPositionY();
		JPanel panelSensor;
		panelSensor = new JPanel();
		
		text = sc.getSensorType().getFrenchLabel();
		JLabel label = new JLabel(text);
		panelSensor.add(label);
		
		text = sc.getState().name();
		label = new JLabel(text);
		panelSensor.add(label);
		System.out.println("Position capteur : " + sc.getPositionX().intValue() + ", " + sc.getPositionY().intValue() );
		panelSensor.setBounds(x.intValue() + sc.getPositionX().intValue(), y.intValue()+ sc.getPositionY().intValue(), 100, 100);

		add(panelSensor);
		revalidate();
	}
	
	public void showSensors(Location location) {
		String text;

		SensorConfiguration sc;
		JPanel panelSensor;
		// dessiner les senseur
		for(Sensor sensor : location.getSensors()) {
			sc = (SensorConfiguration) sensor;
			showSensor(location, sc);
			revalidate();
		}
	}

	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent e) {
		// récupération de la position de la souri
		p = e.getPoint();
		//testLocation(p, poly1, "mouseClicked - data 1");
		//testLocation(p, poly2, "mouseClicked - data 2");
		Rectangle rect;
		
		Location loc = new Location(0, "", "", Util.getCurrentTimestamp(), 0, 1, "", 255, 80f,395f, 440,490f);
		loc.setSensors(new ArrayList<>());
		locations.add(loc);
		Location loc2 = new Location(0, "", "", Util.getCurrentTimestamp(), 0, 1, "", 255,660, 395, 500, 250);;
		loc2.setSensors(new ArrayList<>());
		locations.add(loc2);
		
		for(Location location : locations) {
			Float x = location.getPositionX();
			Float y = location.getPositionY();
			Float width = location.getWidth();
			Float height = location.getHeight();
			rect = new Rectangle(x.intValue(), y.intValue(), width.intValue(), height.intValue());
			if (location(p, rect) == true) {				
				showSensors(location);
				
				JButton jButton = new JButton("Ajouter");
				jButton.setBounds(0, 0, 100, 50);
				add(jButton);
				final MapPage mapPage = this;
				// on a cliquer le bouton ajouter du sensor
				jButton.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									SensorForm window = new SensorForm(mapPage, location);
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});
				revalidate();
			}
		}
		/*if (location(p, poly2) == true) {
			// new SurfacePolygon(polygon1).setVisible(true);
			System.out.println("Polygon2");
			jop1 = new JOptionPane();
			jop1.showMessageDialog(null, "Capteurs  " + "<br>" + mylist.get(1) + "Etat:", "Salle de repos_1",
					JOptionPane.INFORMATION_MESSAGE);

		}*/
		//	}
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
		setLayout(null);
		//drawLocations(g2);
		g2.drawRect(80, 395, 440, 490);
		g2.drawRect(660, 395, 500, 250);
		//g2.drawRect(poly1.x, poly1.y, poly1.width, poly1.height);
		//g2.drawRect(poly2.x, poly2.y, poly2.width, poly2.height);

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
//// * public void paintComponent(Graphics g){ super.paintComponent(g);
// Graphics2D
//// * g2 = (Graphics2D)g;
//// *
//// * int planWidth = planImage.getWidth(null); int panelWidth =
// this.getWidth();
//// * int leftOffset = (panelWidth - planWidth) / 2;
//// *
//// *
//// * g2.drawImage(planImage, leftOffset, 20, null); this.revalidate();
//// *
//// * }
//// */
