package edu.neu.csye6200.droneBackEnd;

import java.util.Comparator;
/**
 it is the comparator class used by Package Priority to arrange the priority queue according to decreasing order of
 Package priority
 */

public class packageComparator implements Comparator<Packages> {
    public int compare(Packages s1, Packages s2) {
        if (s1.getPriority() < s2.getPriority())
            return 1;
        else if (s1.getPriority() > s2.getPriority())
            return -1;
        return 0;
    }
}
