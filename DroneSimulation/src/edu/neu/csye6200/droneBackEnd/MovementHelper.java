package edu.neu.csye6200.droneBackEnd;

import static java.lang.Math.min;
/**
MovementHelper Class Help Movement Rule Classes . It provide various methods to run the program.
 */
public class MovementHelper {
    /**
    * calculate number of diagonals movement Required  from (CurPosX,curPosY) to ( destX, destY)
    * */
    int calculateDiagonalMove(int curPosX, int curPosY,int destX, int destY)
    {
        return min(Math.abs(destX-curPosX),Math.abs(destY-curPosY));
    }
    /**
     * calculate number of horozontal movement Required  from (CurPosX,curPosY) to ( destX, destY) after performing Diaognal Movement
     * */
    int calculateHorizontalMove(int curPosX, int curPosY,int destX, int destY)
    {
        return Math.abs(Math.abs(destX-curPosX)-calculateDiagonalMove(curPosX,curPosY,destX,destY));
    }
    /**
     * calculate number of Vertical movement Required  from (CurPosX,curPosY) to ( destX, destY) after performing Diaognal Movement
     * */
    int calculateVerticalMove(int curPosX, int curPosY,int destX, int destY)
    {
        return Math.abs(Math.abs(destY-curPosY)-calculateDiagonalMove(curPosX,curPosY,destX,destY));
    }
    /**
    Calculate only horizantal movement Required
     */
    int calculateOnlyHorizontalMove(int curPosX, int destX)
    {
        return Math.abs(Math.abs(destX-curPosX));
    }
    /**
   Calculate only Vertical movement Required
    */
    int calculateOnlyVerticalMove( int curPosY, int destY)
    {
        return Math.abs(Math.abs(destY-curPosY));
    }
    /**
   Calculate Horizontal cost
    */

    double calculateHorizontalcost(double h,int weight) {
         h =  h + (1 * weight);
         return h;
    }
     /*
   Calculate Vertical cost
    */

    double calculateVerticlecost(double v,int weight) {
         v = v + (1 * weight);
         return v;
    }
    /*
   Calculate Diagonal cost
    */
    double calculateDiaognalcost(double d,int weight) {
        d = d + (1.5 * weight);
        return d;
    }
    /*
 Calculate Hovering cost
  */
    double calculateHoveringcost(double h,int weight) {
        h =  h + (0.75 * weight);
        return h;
    }

}