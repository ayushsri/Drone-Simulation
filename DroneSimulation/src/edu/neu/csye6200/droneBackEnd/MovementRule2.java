package edu.neu.csye6200.droneBackEnd;

/**
        MovementRule2 class do  vertical and horizontal movement.
        It Ignores if any barrrier is in its path.It flies over it.
 */

public class MovementRule2 {

    public static boolean done =false;//done is used to return if stop button is clicked on UI
    Simulation s= Simulation.instance();//It is used to update the Canvas
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
    /**
This is the print method which print the Drone Movement and calculate cost as well display on UI.
It will first check the diagonal movement and then the left over horizontal and vertical movement.
 */
    void printSimulation(Drone d, Packages pelement) {
        if(done)return;
        s.updateSim(d); //Update the ui with current Drone
        s.updateSim(pelement);
        MovementHelper movementRule = new MovementHelper();
        if(done)return;
        System.out.println("Drone " + d.getDroneName() + " is carrying package " + pelement.getName());
        int x1 = (int) d.getCurPosX();
        int y1 = (int) d.getCurPosY();
        int x2 = pelement.getPickUpX();
        int y2 = pelement.getPickUpY();
        int x3 = pelement.getDropOffX();
        int y3 = pelement.getDropOffY();

        int hr = movementRule.calculateOnlyHorizontalMove(x1,x2);//horizontal movement without considering the diagonal
        int vi = movementRule.calculateOnlyVerticalMove(y1,y2);//virtical movement without considering the diagonal
        int weight=pelement.getWeight();
        MovementHelper MR=new MovementHelper();
        double cost=0;
        int i = x1, j = y1;
        /**
         * Drone only go horizontally and vertically
         */
        if (vi !=0) {
            if (j < y2)
            { for (; j <= y2; j++) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }j--;}
            else if (j > y2)
            { for (; j >= y2; j--) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }j++;}

        }
        if (hr != 0) {
            if (i < x2)
            { for (; i <= x2; i++) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i--;}
            else if (i > x2)
            {for (; i >= x2; i--) {
                if(done)return;
                sleep();
                s.updateSim(i,j);
                if(done)return;
                System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
            }i++;}

        }
        /**
        Drone pick up the package
         */


        if(done)return;
        cost=MR.calculateHoveringcost(cost,weight);
        System.out.println("Drone "+ d.getDroneName() +" has picked "+ pelement.getName()+ " current cost: "+cost );


        int hr2 = movementRule.calculateOnlyHorizontalMove(x2,x3);
        int vi2 = movementRule.calculateOnlyVerticalMove(y2,y3);
        // int a = x2, b = y2;

        if (vi2 != 0) {
            if (j < y3)
            {j++;
                for(; j <= y3; j++) {
                    if(done)return;
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    cost=MR.calculateVerticlecost(cost,weight);
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }j--;}
            else if (j > y3)
            {j--;
                for(; j >= y3; j--) {
                    if(done)return;
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    cost=MR.calculateVerticlecost(cost,weight);
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }j++;}

        }
        if (hr2 != 0) {
            if (i < x3)
            { i++;
                for (; i <= x3; i++) {
                    if(done)return;
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    cost=MR.calculateHorizontalcost(cost,weight);
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }i--;}
            if (i > x3)
            {i--;
                for (; i >= x3; --i) {
                    if(done)return;
                    sleep();
                    s.updateSim(i,j);
                    if(done)return;
                    cost=MR.calculateHorizontalcost(cost,weight);
                    System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: "+cost );
                }i++;}

        }
         /**
         After Delivering of the Package
          */
        if(done)return;
        cost=MR.calculateHoveringcost(cost,weight);
        System.out.println("Package: "+pelement.getName()+ " is Delivered by drone "+ d.getDroneName() + " Total cost: $"+cost  );


    }
}
