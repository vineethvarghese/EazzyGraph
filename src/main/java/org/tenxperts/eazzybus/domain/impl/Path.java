package org.tenxperts.eazzybus.domain.impl;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.core.RelationshipBacked;
import org.tenxperts.eazzybus.domain.impl.BusStopImpl;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/21/11
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
@RelationshipEntity
public class Path implements RelationshipBacked {

    private float distance;

    private String name;

    @StartNode private BusStopImpl startingStop;

    @EndNode private BusStopImpl endingStop;

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

    public BusStopImpl getStartingStop() {
        return startingStop;
    }

    public void setStartingStop(BusStopImpl startingStop) {
        this.startingStop = startingStop;
    }

    public BusStopImpl getEndingStop() {
        return endingStop;
    }

    public void setEndingStop(BusStopImpl endingStop) {
        this.endingStop = endingStop;
    }
}
