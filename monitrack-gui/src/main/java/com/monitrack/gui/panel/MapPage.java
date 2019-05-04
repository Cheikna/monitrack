package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.monitrack.entity.Location;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestType;
import com.monitrack.shared.MonitrackGuiUtil;
//import com.monitrack.dao.implementation.MapDAO;
import com.monitrack.util.JsonUtil;

public class MapPage extends JPanel {
	private Image planImage;
	private JPanel pan = new JPanel();

	final JLabel label = new JLabel();

	ArrayList<String> mylist = new ArrayList<String>();
	final JComboBox<String> cb = new JComboBox();

	private JLabel lblImage = new JLabel();

	public MapPage() {

		setLayout(new BorderLayout());
		setBackground(new Color(220, 220, 220));
		// planImage= Images.MAP.getImage();

		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(400, 100);

		try {

			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			List<Location> locations;
			locations = (List<Location>) JsonUtil.deserializeObject(response);

			for (Location location : locations) {
				mylist.add(location.getIdLocation() + " - " + location.getNameLocation().toString());
				cb.addItem(location.getIdLocation() + " - " + location.getNameLocation().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		cb.setBounds(400, 200, 490, 120);
		add(cb);
		add(label);

		String item = cb.getSelectedItem().toString();

		// cb.addItemListener(new ItemListener() {
		// public void itemStateChanged (ItemEvent arg0) {
		// if (arg0.getStateChange()== ItemEvent.SELECTED) {
		// lblImage.setText("salle1");
		// }
		// }
		// });

		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (item == "0") {
					// lblImage.setText("salle1");
					System.out.println("salle1");

				} else {

				}
				lblImage.setText("salle2");

			}
		});

		/*
		 * b.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { String data =
		 * "Programming language Selected: " + cb.getItemAt(cb.getSelectedIndex());
		 * label.setText(data); } });
		 */

	}

	/*
	 * public void paintComponent(Graphics g){ super.paintComponent(g); Graphics2D
	 * g2 = (Graphics2D)g;
	 * 
	 * int planWidth = planImage.getWidth(null); int panelWidth = this.getWidth();
	 * int leftOffset = (panelWidth - planWidth) / 2;
	 * 
	 * 
	 * g2.drawImage(planImage, leftOffset, 20, null); this.revalidate();
	 * 
	 * }
	 */

}
