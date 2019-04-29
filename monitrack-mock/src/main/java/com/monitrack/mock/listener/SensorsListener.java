package com.monitrack.mock.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.monitrack.mock.panel.SensorsPage;

public class SensorsListener implements ListSelectionListener {

	private SensorsPage page;
	
	public SensorsListener(SensorsPage page) {
		this.page = page;
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			String str = page.getList().getSelectedValue();
			System.out.println(str);
		}

	}

}
