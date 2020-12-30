package edu.neu.csye6200.droneBackEnd;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
    Package Priority arrange the Packages to priority..
     */
public class PackagePriority {

    public PriorityQueue<Packages> setPriority(ArrayList<Packages> arr2 )
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
