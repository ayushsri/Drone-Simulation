package edu.neu.csye6200.droneBackEnd;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
It is the Package class contains methods and attributes of packages
 */
public class Packages {

    private String name;//name of the drone
    private int pickUpX;//pick up x position of the drone
    private int pickUpY;//pick up y position of the drone
    private final double VerticalCost=1; // Vertical cost of drone
    private final double hoveringCost=0.75 ;// Hovering cost of drone
    private int priority;//Priority of Package
    private int dropOffX;// Drop position of Drone
    private int dropOffY;//Drop position of Drone
    private int weight;// Package capacity
    private final double horizontalCost=1; //Horizontal cost of movement

    //Constructor of package
    public Packages(String name, int pickUpX, int pickUpY, int priority, int weight,int dropOffX,int dropOffY ) {
        this.name = name;
        this.pickUpX = pickUpX;
        this.pickUpY = pickUpY;
        this.priority = priority;
        this.weight = weight;
        this.dropOffX=dropOffX;
        this.dropOffY=dropOffY;
    }
   // Getter and Setter of packages
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPickUpX() {
        return pickUpX;
    }

    public void setPickUpX(int pickUpX) {
        this.pickUpX = pickUpX;
    }

    public int getPickUpY() {
        return pickUpY;
    }

    public void setPickUpY(int pickUpY) {
        this.pickUpY = pickUpY;
    }

    public int getDropOffX() {
        return dropOffX;
    }

    public void setDropOffX(int dropOffX) {
        this.dropOffX = dropOffX;
    }

    public int getDropOffY() {
        return dropOffY;
    }

    public void setDropOffY(int dropOffY) {
        this.dropOffY = dropOffY;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHorizontalCost() {
        return horizontalCost;
    }

    public double getVerticalCost() {
        return VerticalCost;
    }

    public double getHoveringCost() {
        return hoveringCost;
    }



    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


// Set the priority of Package
   public PriorityQueue<Packages> setPriority( ArrayList<Packages> arr2 )
   {
      PriorityQueue<Packages>p=new PriorityQueue<Packages>(100,new packageComparator());
      // Packages[] arr2;
       for(Packages p1: arr2)
      {
          p.add(p1);
      }
      return p;
   }

}
