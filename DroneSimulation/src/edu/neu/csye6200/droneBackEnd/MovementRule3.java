package edu.neu.csye6200.droneBackEnd;



public class MovementRule3 {

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
    It will first check the diagonal movement and then the left over horizontal and vertical movement and it step back
    if there is any cell occupied.
     */
    void printSimulation(Drone d, Packages pelement, DroneDeliveryMap Dmr) {
        if(done)return; // return if set true
        s.updateSim(d); //Update the ui with current Drone
        s.updateSim(pelement);

        if(done)return;
        MovementHelper movementRule = new MovementHelper();
        System.out.println("Drone " + d.getDroneName() + " is carrying package " + pelement.getName());

        int x1 = (int) d.getCurPosX();
        int y1 = (int) d.getCurPosY();
        int x2 = pelement.getPickUpX();
        int y2 = pelement.getPickUpY();
        int x3 = pelement.getDropOffX();
        int y3 = pelement.getDropOffY();
        int di = movementRule.calculateDiagonalMove(x1, y1, x2, y2);
        int hr = movementRule.calculateHorizontalMove(x1, y1, x2, y2);
        int vi = movementRule.calculateVerticalMove(x1, y1, x2, y2);
        int weight = pelement.getWeight();
        MovementHelper MR = new MovementHelper();
        double cost = 0;
        int i = x1, j = y1;
        if(done)return;
        System.out.println("Drone is at position x:" + x1 + " y:" + y1);
        while (!(i == x2 && j == y2)) {
            di = movementRule.calculateDiagonalMove(i, j, x2, y2);
            hr = movementRule.calculateHorizontalMove(i, j, x2, y2);
            vi = movementRule.calculateVerticalMove(i, j, x2, y2);
            if (di != 0) {
                if (x1 > x2 && y1 < y2) {
                    i--;
                    j++;
                    for ( ; i >= x2 && j <= y2; i--, j++) {
                        /**
                         * If safe method see if cell is occupied. If it is occupy retrace back in the same direction
                         */
                        if (!Dmr.isSafe(i, j) && (i != x2 && j != y2)){
                            if (Dmr.isSafe(++i, --j))
                                break;
                            else if (Dmr.isSafe(i, ++j))
                                break;
                            else if (Dmr.isSafe(--i, --j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else
                        {
                        sleep();
                        s.updateSim(i,j);
                            if(done)return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");}
                    }
                    i++;
                    j--;
                } else if (x1 < x2 && y1 > y2) {
                    i++;
                    j--;
                    for (; i <= x2 && j >= y2; i++, j--) {
                        if (!Dmr.isSafe(i, j) && (i != x2 && j != y2)) {
                            if (Dmr.isSafe(--i, ++j))
                                break;
                            else if (Dmr.isSafe(i, --j))
                                break;
                            else if (Dmr.isSafe(++i, ++j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else
                         {
                           sleep();
                           s.updateSim(i,j);
                             if(done)return;
                           System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");}
                    }
                    i--;
                    j++;
                } else if (x1 < x2 && y1 < y2) {
                    i ++;
                    j ++;
                    for (; i <= x2 && j <= y2; i++, j++) {
                        if (!Dmr.isSafe(i, j) && (i != x2 && j != y2)) {
                            if (Dmr.isSafe(--i, --j))
                                break;
                            else if (Dmr.isSafe(i, ++j))
                                break;
                            else if (Dmr.isSafe(++i, --j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else
                        {
                            sleep();
                            s.updateSim(i,j);
                            if(done)return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
                    }}
                    i--;
                    j--;
                } else if (x1 > x2 && y1 > y2) {
                    i --;
                    j --;
                    for (; i >= x2 && j >= y2; i--, j--) {
                        if (!Dmr.isSafe(i, j) && (i != x2 && j != y2)) {
                            if (Dmr.isSafe(++i, ++j))
                                break;
                            else if (Dmr.isSafe(i, --j))
                                break;
                            else if (Dmr.isSafe(--i, ++j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else
                        {
                        sleep();
                        s.updateSim(i,j);
                            if(done)return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");}
                    }
                    i++;
                    j++;
                }

            }
            if (vi != 0) {
                if (j < y2) {
                    j++;
                    for (; j <= y2; j++) {
                        if (!Dmr.isSafe(i, j)) {
                            if (Dmr.isSafe(i, --j) && (i != x2 && j != y2))
                                break;
                            else
                                break;
                        } else {

                            sleep();
                            s.updateSim(i, j);
                            if (done) return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
                        }
                    }
                    j--;
                } else if (j > y2) {
                    j--;
                    for (; j >= y2; j--) {
                        if (!Dmr.isSafe(i, j) && (i != x2 && j != y2)) {
                            if (Dmr.isSafe(i, ++j))
                                break;
                            else
                                break;
                        } else {
                            if (done) return;
                            sleep();
                            s.updateSim(i, j);
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
                        }
                    }
                    j++;
                }

            }
            if (hr != 0) {
                if (i < x2) {
                    i++;
                    for (; i <= x2; i++) {
                        if (!Dmr.isSafe(i, j) && (i != x2 && j != y2)) {
                            if (Dmr.isSafe(--i, j))
                                break;
                            else
                                break;
                        } else {


                            sleep();
                            s.updateSim(i, j);
                            if (done) return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
                        }
                    }
                    i--;
                } else if (i > x2) {
                    i--;
                    for (; i >= x2; i--) {
                        if (!Dmr.isSafe(i, j)) {
                            if (Dmr.isSafe(++i, j))
                                break;
                            else
                                break;
                        }
                        {

                            sleep();
                            s.updateSim(i, j);
                            if (done) return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + " awaiting State");
                        }
                    }
                    i++;
                }

            }
        }
        /**
         * Drone Picked up  package
         */
        if(done)return;
        cost = MR.calculateHoveringcost(cost, weight);
        System.out.println("Drone " + d.getDroneName() + " has picked " + pelement.getName() + " current cost: " + cost);

        int di2 = movementRule.calculateDiagonalMove(x2, y2, x3, y3);
        int hr2 = movementRule.calculateHorizontalMove(x2, y2, x3, y3);
        int vi2 = movementRule.calculateVerticalMove(x2, y2, x3, y3);
        // int a = x2, b = y2;
        while(!(i == x3 && j == y3)){
            di2 = movementRule.calculateDiagonalMove(i, j, x3, y3);
            hr2 = movementRule.calculateHorizontalMove(i, j, x3, y3);
            vi2 = movementRule.calculateVerticalMove(i, j, x3, y3);

            if (di2 != 0) {
                if (x2 > x3 && y2 < y3) {
                    i--;
                    j++;
                    for (; i >= x3 && j <= y3; i--, j++) {
                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(++i, --j))
                                break;
                            else if (Dmr.isSafe(i, ++j))
                                break;
                            else if (Dmr.isSafe(--i, --j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else
                        {
                            sleep();
                            s.updateSim(i,j);
                            cost = MR.calculateDiaognalcost(cost, weight);
                            if(done)return;
                        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                    }}
                    i++;
                    j--;
                } else if (x2 < x3 && y2 > y3) {
                    i ++;
                    j --;
                    for (; i <= x3 && j >= y3; i++, j--) {

                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(--i, ++j))
                                break;
                            else if (Dmr.isSafe(i, --j))
                                break;
                            else if (Dmr.isSafe(++i, ++j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else {

                            sleep();
                            s.updateSim(i, j);
                            cost = MR.calculateDiaognalcost(cost, weight);
                            if (done) return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                        }
                    }
                    i--;
                    j++;
                } else if (x2 < x3 && y2 < y3) {

                    i ++;
                    j ++;
                    for (; i <= x3 && j <= y3; i++, j++) {
                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(--i, --j))
                                break;
                            else if (Dmr.isSafe(i, ++j))
                                break;
                            else if (Dmr.isSafe(++i, --j))
                                break;
                            else
                                System.out.println("Drone cant find path");
                        } else
                        {
                        sleep();
                        s.updateSim(i,j);
                            cost = MR.calculateDiaognalcost(cost, weight);
                            if(done)return;
                        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                    }}
                    i--;
                    j--;
                } else if (x2 > x3 && y2> y3){

                    i--;
                    j--;

                    for (; i >= x3 && j >= y3; i--, j--) {


                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(++i, ++j))
                                break;
                            else if (Dmr.isSafe(i, --j))
                                break;
                            else if (Dmr.isSafe(--i, ++j))
                                break;
                        } else {

                            sleep();
                            s.updateSim(i, j);
                            cost = MR.calculateDiaognalcost(cost, weight);
                            if (done) return;
                            System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                        }
                    }
                        i++;
                        j++;


                }
            }
            if (vi2 != 0) {
                if (j < y3) {
                    j++;
                    for (; j <= y3; j++) {
                        if (!Dmr.isSafe(i, j)) {
                            if (Dmr.isSafe(i, --j) && (i != x3 && j != y3))
                                break;
                            else
                                break;
                        } else
                        {
                            sleep();
                            s.updateSim(i,j);
                        cost = MR.calculateVerticlecost(cost, weight);
                            if(done)return;
                        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                    }
                    }
                    j--;
                } else if (j > y3) {
                    j--;
                    for (; j >= y3; j--) {
                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(i, ++j))
                                break;
                            else
                                break;
                        } else{

                            sleep();
                            s.updateSim(i,j);

                        cost = MR.calculateVerticlecost(cost, weight);
                            if(done)return;
                        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                    }}
                    j++;
                }

            }
            if (hr2 != 0) {
                if (i < x3) {
                    i++;
                    for (; i <= x3; i++) {
                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(--i, j))
                                break;
                            else
                                break;
                        } else
                        {if(done)return;
                            sleep();
                            s.updateSim(i,j);

                        cost = MR.calculateHorizontalcost(cost, weight);
                            if(done)return;
                        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                    }}
                    i--;
                }
                if (i > x3) {
                    i--;
                    for (; i >= x3; --i) {
                        if (!Dmr.isSafe(i, j) && (i != x3 && j != y3)) {
                            if (Dmr.isSafe(++i, j))
                                break;
                            else
                                break;
                        } else{

                            if(done)return;
                            sleep();
                            s.updateSim(i,j);
                            if(done)return;
                        cost = MR.calculateHorizontalcost(cost, weight);
                        System.out.println("Drone is at position x:" + i + " y:" + j + "Package " + pelement.getName() + "in-transit State current cost: " + cost);
                    }}
                    i++;
                }

            }
        }

        if(done)return;
            cost = MR.calculateHoveringcost(cost, weight);
        if(done)return;
            System.out.println("Package: " + pelement.getName() + " is Delivered by drone " + d.getDroneName() + " Total cost: $" + cost);



    }
}
