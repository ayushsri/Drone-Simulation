package edu.neu.csye6200.droneBackEnd;
/**
Drone class contains Methods and attributes of a Drone
 */
public class Drone {
    private String droneName;//Drone name
    private String droneId; //Drone Id
    private double curPosX; //Initial x coordinate of drone
    private double curPosY; //Initial y coordinate of Drone
    private double speed;  //Speed of Drone
    private double loadCapacity; // loadCapacity of Drone
    private double battery; //Battery percentage of the Drone
    private double destX; //X coordinate of Drone
    private double destY;  //Y coordinates of Drone
    private double weightRestriction;// Weight Restriction of Drone

    //Constructor
    public Drone(String droneName, String droneId, double curPosX, double curPosY, double speed, double weightRestriction) {
        this.droneName = droneName;
        this.droneId = droneId;
        this.curPosX = curPosX;
        this.curPosY = curPosY;
        this.speed = speed;
        this.weightRestriction = weightRestriction;


    }

    /**
    Getter and Setter of above Attributes
    * */


    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public double getCurPosX() {
        return curPosX;
    }

    public void setCurPosX(double curPosX) {
        this.curPosX = curPosX;
    }

    public double getCurPosY() {
        return curPosY;
    }

    public void setCurPosY(double curPosY) {
        this.curPosY = curPosY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public double getDestX() {
        return destX;
    }

    public void setDestX(double destX) {
        this.destX = destX;
    }

    public double getDestY() {
        return destY;
    }

    public void setDestY(double destY) {
        this.destY = destY;
    }

    public double getWeightRestriction() {
        return weightRestriction;
    }

    public void setWeightRestriction(double weightRestriction) {
        this.weightRestriction = weightRestriction;
    }


}
