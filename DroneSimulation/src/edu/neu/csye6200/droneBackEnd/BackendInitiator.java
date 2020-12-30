package edu.neu.csye6200.droneBackEnd;

public class BackendInitiator {

    public static void initiate(String[] args) {
	/**
     * This is the main initiator function used to run the Backend.
     * */
        Test t=Test.instance();
        t.doWork();
        t.done=false;

    }
}
