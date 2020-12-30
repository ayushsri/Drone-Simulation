package edu.neu.csye6200.droneBackEnd;

import java.util.ArrayList;
import java.util.HashMap;
/**
WorkerClass set the right MovementRule and Print Drone path According to choosed Movement Rule.
 */
public class WorkerClass {
    MovementRule1 M1= new MovementRule1();//Object of Movement Rule1
    MovementRule2 M2=new MovementRule2();//Object of Movement Rule2
    MovementRule3 M3=new MovementRule3();//Object of Movement Rule3


    ArrayList<Packages>p;//ArrayList of Packages
    private Thread thread1=null ;
    HashMap<Packages, Drone> HD;// Drones Assigned to packages
    DroneDeliveryMap Dmr;//The matrix
    /**
    Run method assign the correct movement Rule according to user input(through ui)
    @Param p- ArrayList of Packages.
    @Param Hd- HashMap of packages and Drone.
    @Param count- The user choosed option.
     */

    public void run1(ArrayList<Packages> p,HashMap<Packages, Drone> HD,DroneDeliveryMap Dmr,int  count) {
        this.p=p;
        this.HD=HD;
        this.Dmr=Dmr;
        switch (count)

        {  case 1:
                  { for (Packages pelement : p) {
                      Drone d = HD.get(pelement);
                      M1.printSimulation(d, pelement);}
                      break;
                  }
            case 2:
                { for (Packages pelement : p) {
                Drone d = HD.get(pelement);
                M2.printSimulation(d, pelement);}
                break;
                 }
            case 3:
            { for (Packages pelement : p) {
                Drone d = HD.get(pelement);
                M3.printSimulation(d, pelement,Dmr);
                }
                break;
            }
        }
    }








}


