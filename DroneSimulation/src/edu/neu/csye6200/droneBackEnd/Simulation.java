package edu.neu.csye6200.droneBackEnd;


import edu.neu.csye6200.droneUI.MyCanvas;

import java.util.ArrayList;
import java.util.Observable;
/**
Simulation class act as a pipe between backend and UI
 */
public class Simulation extends Observable implements Runnable{
    MyCanvas c=new MyCanvas();
    ArrayList<Integer>a=new ArrayList<>();
	private Thread thread = null;
	private Thread thread1 = null;// the thread the runs my simulation
	private boolean done = false; 
	private boolean paused = false; // Set true to pause the simulation loop
	private int ctr = 1;
	MovementRule1 M1;//Movement Rule 1 object
	MovementRule2 M2;//Movement Rule 2 Object
	MovementRule3 M3;//Movement Rule 3 Object
	private static Simulation instance =null;
	Test test=Test.instance();

	private Simulation() {
	}
    public static Simulation instance(){
		if(instance==null) instance =new Simulation();
		return  instance;
	}
	/**
	 * Start the simulation
	 */
	public void startSim() {
	  System.out.println("Starting the simulation");

      if(thread != null ) return;

	  thread = new Thread(this); // We are the 'runnable'
	  done = false;

	  ctr = 0;
	  paused = false;
	  c.flag1=false;
		{   if (thread1 != null ) return;
			M1.done=false;
			M2.done=false;
			M3.done=false;
			Thread thread1 = new Thread () {
				public void run () {
					BackendInitiator.initiate(null);
				}
			};

			test.setDone(true);
			thread1.start();

		}


	  thread.start();


	}
	

	public boolean isPaused() {
		return paused;
	}

	/**
	 * Stop the simulation
	 */
	public void stopSim() {
		M1.done=true;
		M2.done=true;
		M3.done=true;
		System.out.println("Stopping the simulation");
		 if (thread == null ) return;
		done = true;

		paused = false;
	}

	
	@Override
	public void run() {

		thread = null; // Indicate that our thread isn't needed anymore
	}


	/**
	 * Perform an update to our simulation
	 */
	public void updateSim(int i, int j) {

		a.add(i);
		a.add(j);
		setChanged();

		notifyObservers(a);
		a.clear();
	}
	public void updateSim(Drone d) {

		setChanged();

		notifyObservers(d);

	}
	public void updateSim(Packages p) {

		setChanged();
		notifyObservers(p);

	}
	public void updateSim(String S) {

		setChanged();
		notifyObservers(S);

	}

	
}
