package org.tenxperts.eazzybus.domain.impl;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.core.Direction;
import org.springframework.data.neo4j.core.NodeBacked;
import org.tenxperts.eazzybus.domain.BusStop;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/21/11
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */

@NodeEntity
public class BusStopImpl implements NodeBacked, BusStop<BusStopImpl, RouteImpl> {

    @Indexed
    private Long id;

    @Indexed(fulltext = true, indexName = "bus_stop_name")
    private String name;

    @RelatedTo(elementClass = BusStopImpl.class, type = "ROAD", direction = Direction.INCOMING)
    private Set<BusStopImpl> fromStops;

    @RelatedTo(elementClass = BusStopImpl.class, type = "ROAD", direction = Direction.OUTGOING)
    private Set<BusStopImpl> toStops;

    @RelatedTo(elementClass = RouteImpl.class, type = "SERVICED_BY", direction = Direction.BOTH)
    private Set<RouteImpl> servicedByRoutes;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<BusStopImpl> getFromStops() {
        return fromStops;
    }

    @Override
    public void setFromStops(Set<BusStopImpl> fromStops) {
        this.fromStops = fromStops;
    }

    @Override
    public Set<BusStopImpl> getToStops() {
        return toStops;
    }

    @Override
    public void setToStops(Set<BusStopImpl> toStops) {
        this.toStops = toStops;
    }

    @Override
    public Set<RouteImpl> getServicedByRoutes() {
        return servicedByRoutes;
    }

    @Override
    public void setServicedByRoutes(Set<RouteImpl> servicedByRoutes) {
        this.servicedByRoutes = servicedByRoutes;
    }
}
