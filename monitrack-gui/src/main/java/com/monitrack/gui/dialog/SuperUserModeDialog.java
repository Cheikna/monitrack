package com.monitrack.gui.dialog;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.monitrack.enumeration.Images;
import com.monitrack.listener.SuperUserModeDialogListener;

@SuppressWarnings("serial")
public class SuperUserModeDialog extends JDialog {

	private SuperUserModeDialogListener listener;
	
	//private JLabel entityLabel;
	//private 
	
	
	public SuperUserModeDialog(JFrame owner) {
		super(owner, true);
		
		setTitle("Super mode");
		setIconImage(Images.SUPER.getImage());
		setSize(600, 350);
		setLocationRelativeTo(owner);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		listener = new SuperUserModeDialogListener(this);
		
		
	}
}
