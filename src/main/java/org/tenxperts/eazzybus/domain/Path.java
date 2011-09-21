package org.tenxperts.eazzybus.domain;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/21/11
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Path {

    private float distance;

    private String name;

    private BusStop startingStop;

    private BusStop endingStop;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BusStop getStartingStop() {
        return startingStop;
    }

    public void setStartingStop(BusStop startingStop) {
        this.startingStop = startingStop;
    }

    public BusStop getEndingStop() {
        return endingStop;
    }

    public void setEndingStop(BusStop endingStop) {
        this.endingStop = endingStop;
    }
}
