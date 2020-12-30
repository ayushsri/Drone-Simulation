package edu.neu.csye6200.droneBackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Test {
   public boolean done=false;//done is used to stop the thread
   public int count=0;//count is used to
    /**
    Test is made singleton so that every class can use same object
     */
    private static Test instance=null;
    private Test(){};
    public static Test instance(){
        if(instance==null)instance=new Test();
        return instance;
    }
    /**
    @return populated arrayList
     */
    public ArrayList<Drone> getArrDrones() {
        return arrDrones;
    }
    /**
     @return populated PackageList
     */
    public ArrayList<Packages> getArrPackages() {
        return arrPackages;
    }

    ArrayList<Drone>arrDrones=new ArrayList<>();//Drone ArrayList

     /**
      use to set the drones
      */
    public void setArrDrones(ArrayList<Drone> arrDrones) {
        this.arrDrones = arrDrones;
    }

    ArrayList<Packages>arrPackages=new ArrayList<>();
    public void setDone( boolean done)
    {
        this.done =done;
    }

    /**
     * this method sets Drone, packages, Perform operations on Drone and packages.
     */
    public void doWork() {
        DroneDeliveryMap drM=new DroneDeliveryMap();
        drM.setPath();
        /**
         * Making 5 Drones
         * Setting attributes of Drones.
         */
        Drone D1 = new Drone("Drone1", "DXUV1", 1, 1, 0.5, 4);
        Drone D2 = new Drone("Drone2", "DXUV2", 5, 14, 0.5, 3);
        Drone D3 = new Drone("Drone3", "DXUV3", 15, 16, 0.5, 6);
        Drone D4 = new Drone("Drone4", "DXUV4", 12, 12, 0.5, 7);
        Drone D5 = new Drone("Drone5", "DXUV5", 16, 18, 0.5, 2);
        /**
         * Setting the Drones in map.
         */

        drM.occupyCell((int)D1.getCurPosX(),(int)D1.getCurPosY());
        drM.occupyCell((int)D2.getCurPosX(),(int)D2.getCurPosY());
        drM.occupyCell((int)D3.getCurPosX(),(int)D3.getCurPosY());
        drM.occupyCell((int)D4.getCurPosX(),(int)D4.getCurPosY());
        drM.occupyCell((int)D5.getCurPosX(),(int)D5.getCurPosY());
        /**
        Making 5 Packages
         */
        Packages P1 = new Packages("Pack1", 5, 6, 1, 2,24,5);
        Packages P2 = new Packages("Pack2", 7, 12, 2, 3,2,7);
        Packages P3 = new Packages("Pack3", 18, 19, 3, 4,3,7);
        Packages P4 = new Packages("Pack4", 13, 11, 5, 3,0,0);
        Packages P5 = new Packages("Pack5", 18, 18, 4 , 1,6,5);

        if(!arrDrones.isEmpty())
            arrDrones.clear();
        /**
         * adding Drones to ArrayList
         */
        arrDrones.add(D1);
        arrDrones.add(D2);
        arrDrones.add(D3);
        arrDrones.add(D4);
        arrDrones.add(D5);
        /**
         * Adding Packages to ArrayList
         */
        if(!arrPackages.isEmpty())
            arrPackages.clear();
        arrPackages.add(P1);
        arrPackages.add(P2);
        arrPackages.add(P3);
        arrPackages.add(P4);
        arrPackages.add(P5);

        /**
         * Priority queue used to Arrange the Drone to a Priority
         */

        PackagePriority PP=new PackagePriority();
        PriorityQueue<Packages> PQ=PP.setPriority(arrPackages);
        arrPackages.clear();
        Iterator itr = PQ.iterator();
        while (itr.hasNext())
        {

            arrPackages.add((PQ.peek()));
            PQ.remove(PQ.peek());
        }
        /**
        Delivery Map to Map the nearest drone to package
         */
        DroneDeliveryMap DDM=new DroneDeliveryMap();
        HashMap<Packages,Drone> HM =new HashMap<>();
        HM=DDM.appointment(arrDrones,arrPackages);
        /**
         * Recharging status is set to make hurdles in Drone Path
         */

        drM.setRechergingStation(2,6);


        int k=0;
        WorkerClass sm=new WorkerClass();

       //this is used to stop the thread to do any action and wait for the start button
        while(!done)
        {  k++;
           System.out.println(k);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        /**
         * call the run method of worker class which will asssign the correct Movement class.
         */

        sm.run1(arrPackages,HM,drM,count);
    }


}