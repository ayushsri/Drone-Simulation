package edu.neu.csye6200.droneBackEnd;



/**
Movement Rule 1 class do diagonal as well as vertical, horizontal movement. It tries to do maximum number of Diagonal movement.
It Ignores if any barrrier is in its path.
 */
public class MovementRule1 {
    public static boolean done =false;//done is used to return if stop button is clicked on UI
    Simulation s= Simulation.instance(); //It is used to update the Canvas
    /**
    sleep method is used to control slow down the movement of Drone.
     */

    void sleep()
    {
        try {
            Thread.sleep(800L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
  //
/**
This is the print method which print the Drone Movement and calculate cost as well display on UI.
It will first check the diagonal movement and then the left over horizontal and vertical movement.
 */
    void printSimulation(Drone d, Packages pelement) {
       if(done)return;// Return if Stop button is Pressed
       // System.out.println("pama"+Thread.currentThread().getName());
        s.updateSim(d); //Update the ui with current Drone
        s.updateSim(pelement);// Update the current Package detail;
        MovementHelper movementRule = new MovementHelper();
        if(done)return;
        System.out.println("Drone " + d.getDroneName() + " is carrying package " + pelement.getName());
        int x1 = (int) d.getCurPosX();//drone initial x coordinate
        int y1 = (int) d.getCurPosY();//drone initial y coordinate
        int x2 = pelement.getPickUpX(); //x coordinate of pickup position of Package
        int y2 = pelement.getPickUpY();//y coordinate of pickup position of package
        int x3 = pelement.getDropOffX();//x coordinate of drop location of Package
        int y3 = pelement.getDropOffY();//Y coordinate of drop location of package
        int di = movementRule.calculateDiagonalMove(x1, y1, x2, y2);//calculate diagonal distance
        int hr = movementRule.calculateHorizontalMove(x1, y1, x2, y2);//calculate horizontal distance, left after calculating diagonal Distance
        int vi = movementRule.calculateVerticalMove(x1, y1, x2, y2);//calculate virtical distance, left after calculating diagonal Distance
        int weight=pelement.getWeight();
        MovementHelper MR=new MovementHelper();// functions of Movement Helper is used to calculate distance and cost;
        double cost=0;
        int i = x1, j = y1;
        s.updateSim("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
        if(done)return;
        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
        /**
         * This function first go as far as diagonal it could go and then it cover remaining distance horizontally and vertically
         * meanwhile it calculate the cost.
         */

        if (di != 0) {
            if (x1 > x2 && y1 < y2)
            {
                for (i = x1, j = y1; i >= x2 && j <= y2; i--, j++) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                    if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i++;j--;}
            else if (x1 < x2 && y1 > y2)
            { for (i = x1, j = y1; i <= x2 && j >= y2; i++, j--) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i--;j++;}
            else if  (x1 < x2 && y1 < y2) {
                for (i = x1, j = y1; i <= x2 && j <= y2; i++, j++) {
                    if(done)return;
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
                }i--;j--;}
            else if (x1 > x2 && y1 > y2)
            { for (i = x1, j = y1; i >= x2 && j >= y2; i--, j--) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i++;j++;}

        }
        if (hr == 0) {
            if (j < y2)
            { j++;
                for (; j <= y2; j++) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                    if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }j--;}
            else if (j > y2)
            { j--;
                for (; j >= y2; j--) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                    if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }j++;}

        }
        if (vi == 0) {
            if (i < x2)
            { i++;
                for (; i <= x2; i++) {
              if(done)return;
              sleep();
              s.updateSim(i,j);
                    if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i--;}
            else if (i > x2)
            {i--;
                for (; i >= x2; i--) {
                if(done)return;
                sleep();
                    s.updateSim(i,j);
                    if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i++;}

        }
        //   if(i==x2 && j==y2)
     //   c.refresh(i,j);
        cost=MR.calculateHoveringcost(cost,weight);
        if(done)return;
        sleep();
        s.updateSim(i,j);
        if(done)return;
        System.out.println("Drone "+ d.getDroneName() +" has picked "+ pelement.getName()+ " current cost: "+cost );

        int di2 = movementRule.calculateDiagonalMove(x2, y2, x3, y3);
        int hr2 = movementRule.calculateHorizontalMove(x2, y2, x3, y3);
        int vi2 = movementRule.calculateVerticalMove(x2, y2, x3, y3);
        // int a = x2, b = y2;
        if (di2 != 0) {
            if (x2 > x3 && y2 < y3){
                i=x2-1;j=y2+1;
                for (; i >= x3 && j <= y3; i--, j++) {
                   if(done)return;
                   sleep();
                    s.updateSim(i,j);
                    cost=MR.calculateDiaognalcost(cost,weight);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }
                i++;j--;
            }
            else if (x2 < x3 && y2 > y3){i=x2+1;j=y2-1;
                for (; i <= x3 && j >= y3; i++, j--) {
                 if(done)return;
                 sleep();
                 s.updateSim(i,j);
                 cost=MR.calculateDiaognalcost(cost,weight);
                    if(done)return;
                 System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }i--;j++;}
            else  if (x2 < x3 && y2 < y3){i=x2+1;j=y2+1;
                for (; i <= x3 && j <=y3; i++, j++) {
                   if(done)return;
                   sleep();
                   s.updateSim(i,j);
                    cost=MR.calculateDiaognalcost(cost,weight);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }i--;j--;}
            else
            { i--;j--;
            for (; i >= x3 && j >=y3; i--, j--) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                cost=MR.calculateDiaognalcost(cost,weight);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
            }i++;j++;}

        }
        if (hr2 == 0) {
            if (j < y3)
            {j++;
                for (; j <= y3; j++) {
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    cost=MR.calculateVerticlecost(cost,weight);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }j--;}
            else if (j > y3)
            {j--;
                for (; j >= y3; j--) {
                    s.updateSim(i,j);
                    if(done)return;
                    sleep();
                    cost=MR.calculateVerticlecost(cost,weight);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }j++;}

        }
        if (vi2 == 0) {
            if (i < x3)
            { i++;
                for (; i <= x3; i++) {
                    s.updateSim(i,j);
                    if(done)return;
                    sleep();
                    cost=MR.calculateHorizontalcost(cost,weight);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }i--;}
            if (i > x3)
            {i--;
                for (; i >= x3; --i) {
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    sleep();
                    cost=MR.calculateHorizontalcost(cost,weight);
                    if(done)return;
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }i++;}

        }

        cost=MR.calculateHoveringcost(cost,weight);
        sleep();
        if(done)return;
        //Total cost
        System.out.println("Package: "+pelement.getName()+ " is Delivered by drone "+ d.getDroneName() + " Total cost: $"+cost  );


    }
}
