package edu.neu.csye6200.droneBackEnd;

import java.util.ArrayList;
import java.util.HashMap;
/**
Class DroneDeliverMap is used to decorate the
 */
public class DroneDeliveryMap {
   /**
   Matrix to figure out if there is any hurdle
    */
    private ArrayList<ArrayList<Integer>> Arr=new ArrayList<ArrayList<Integer>>();

   /**
   SetPath is Used to initiate the matrix
    */
   public void setPath()
   {
       for(int i=0;i<25;i++) {
           ArrayList<Integer> list = new ArrayList<Integer>();
           Arr.add(list);
           for (int j = 0; j < 25; j++) {
               list.add(0);
           }
       }
   }
   /**
   Recharging Station is a type of Barrier for the Drone
    */
   public void setRechergingStation(int x,int y)
   {
       Arr.get(x).set(y,1);
   }
   /**
   Occupy Cell is used to Set the intial parameter
    */
   public void occupyCell(int x, int y)
   {
       Arr.get(x).set(y,1);
   }
   /**
   It is used to release any cell which is not used.
    */
   public void releaseCell(int x,int y)
   {
       Arr.get(x).set(y,0);
   }
   /**
   It is used to check if cell is safe or not or there is any drone or Recharging station
    */
   boolean isSafe(int x,int y)
   {
       if(Arr.get(x).get(y)==0)
       {
          return true;
       }
       return false;
   }
    /**
    It is used to print the Array when needed.
     */
   public void printArray()
   {
       for(int i = 0; i < Arr.size(); i++){
           for(int j = 0; j < Arr.get(i).size(); j++){
               System.out.print(Arr.get(i).get(j)+" ");
           }

           System.out.println();
       }
   }
  /**
   *appointment function Maps the nearest Drone to the Priority of the Package
   *@Params ArrayList of drones which need to be Mapped with Packages
   *@Params ArrayList of Packages
   */
   public HashMap<Packages,Drone>appointment(ArrayList<Drone>D,ArrayList<Packages>P)
   {HashMap<Packages, Drone> HM=new HashMap<>();
       int weight=0;
       for(Packages P1:P)
       {    weight= P1.getWeight();
           Drone d=appointmentOfDrone(D,P1.getPickUpX(),P1.getPickUpY(),weight);
           HM.put(P1,d);
           int weight1= (int) d.getWeightRestriction();
           weight1=P1.getWeight()-weight1;
           d.setWeightRestriction(weight1);
       }
       return  HM;
   }
  /**
  This is helper function of appointment which appoint Drone to package  and return it to appointment
  @Params Drone Array List
  @Params x-x coordinate of a package
  @Params y-y coordinate of the package
  @weight of the package
   */
   Drone appointmentOfDrone(ArrayList<Drone>D,int x,int y ,int weight)
   {int min1=Integer.MAX_VALUE,x1,y1;
   int s=0;
   Drone p=D.get(0);
       for(Drone d :D)
   {
       if(weight>d.getWeightRestriction())
       {
           continue;
       }
         x1= Math.abs((int) (d.getCurPosX()-x));
          y1= Math.abs((int) (d.getCurPosY()-y));
         s=(int)Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2));
        if(min1>s)
        { min1= s;
          p=d;
        }
   }
       return p;
}

}
