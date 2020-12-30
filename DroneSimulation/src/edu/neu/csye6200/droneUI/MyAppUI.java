package edu.neu.csye6200.droneUI;

import edu.neu.csye6200.droneBackEnd.Simulation;
import edu.neu.csye6200.droneBackEnd.Test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A sample Swing-based UI that leverages an abstract base class
 * @author mgmunson
 */

public class MyAppUI extends DDApp  {

	//private JFrame frame = null; // This definition is now in DDApp
	private JPanel buttonPanel = null;
	public JButton startBtn ; //Start Button is used to start the process
	public JButton stopBtn ;
	//private JButton pauseBtn = null;
	private MyCanvas canvas ;
	
	private Simulation simulation;
	private JComboBox<String> ruleSelectCB;
	Test test=Test.instance();
	
	/**
	 * MyApp Constructor
	 * DDApp has already been called, so the simulation UI should be constructed
	 */
	public MyAppUI() {
	  // The GUI is mostly built, so let's create the simulation 	
	  simulation = Simulation.instance(); // Ensure that the simulation exists/
		//new MyAppUI();
	  // Turn on the UI and make it visible
	  customizeGUI(); // Final GUI adjustments
	  showUI(); // Launch the GUI and make it visible
      simulation.addObserver(canvas);

	}
	
	/*
	 * Misc. UI adjustments are implemented here
	 */
	private void customizeGUI() {
		frame.setSize(600, 500); // Width, height
		frame.setTitle("MyAppUI");
	}
	
    /*
    * Make a control panel and add buttons and/or input entry fields
    */
	private JPanel makeButtonPanel() {
		buttonPanel = new JPanel(); // Build the button panel
		buttonPanel.setLayout(new FlowLayout()); // Buttons will flow from the left
		
		buttonPanel.setBackground(Color.BLUE);

		startBtn = new JButton("Start");
		//startBtn.addActionListener(this); // Add my class as a listener of the button events
		/**
		 * StartBtn is used to start the process
		 * actionlistnener set stopbutton enable ,start and combobox disable.
		 */
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				ruleSelectCB.setEnabled(false);
				simulation.startSim();

			}
		});
		/**
		 * StopBtn is used to stop the process
		 * actionlistnener set stopbutton disable , and combobox enable.
		 */


		 stopBtn = new JButton("Stop");
		stopBtn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			ruleSelectCB.setEnabled(true);
			stopBtn.setEnabled(false);
			simulation.stopSim();

		}
	});// Add my class as a listener of the button events
		startBtn.setEnabled(false);
		stopBtn.setEnabled(false);
        // startBtn.setEnabled(true);
		ruleSelectCB=new JComboBox<>();
		ruleSelectCB.addItem("MovementRule1");
		ruleSelectCB.addItem("MovementRule2");
		ruleSelectCB.addItem("MovementRule3");

		buttonPanel.add(startBtn);
	//	buttonPanel.add(pauseBtn);
		buttonPanel.add(stopBtn);

		buttonPanel.add(new JLabel("Select Rule"));
		buttonPanel.add(ruleSelectCB);
		ruleSelectCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) ruleSelectCB.getSelectedItem();

				//	startBtn.setEnabled(true);
				switch (s) {//check for a match
					case "MovementRule1":
						startBtn.setEnabled(true);
						test.count=1;
						break;
					case "MovementRule2":
						startBtn.setEnabled(true);
						test.count=2;
						break;
					case "MovementRule3":
						startBtn.setEnabled(true);
						test.count=3;
						break;
					default:
						final boolean enabled = ruleSelectCB.getSelectedIndex() == 1;

						test.count=1;
						break;
				}
			}
		});
		return buttonPanel;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {


		/*
		Show the canvas
		 */
		new MyAppUI();
		//Main.main(null);
		System.out.println("MyAppUI is exiting!!!!");

	}

	/**
	 * DDApp Defines an ActionListener interface, so we'll
	 * implement it here. Use if needed to handle subscribed events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("We received an ActionEvent " + e);


	}

	@Override
	public JPanel getNorthPanel() {
		return makeButtonPanel();
	}

	@Override
	public JPanel getMainPanel() {
		canvas = new MyCanvas();
		return canvas;
	}

}
