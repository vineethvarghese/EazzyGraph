package org.tenxperts.eazzybus.domain;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/21/11
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */

@NodeEntity
public class BusStop {

    @Indexed
    private Long id;

    @Indexed
    private String name;

    @RelatedTo
    private Set<BusStop> fromStops;

    private Set<BusStop> toStops;

    private Set<Route> servicedByRoutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BusStop> getFromStops() {
        return fromStops;
    }

    public void setFromStops(Set<BusStop> fromStops) {
        this.fromStops = fromStops;
    }

    public Set<BusStop> getToStops() {
        return toStops;
    }

    public void setToStops(Set<BusStop> toStops) {
        this.toStops = toStops;
    }

    public Set<Route> getServicedByRoutes() {
        return servicedByRoutes;
    }

    public void setServicedByRoutes(Set<Route> servicedByRoutes) {
        this.servicedByRoutes = servicedByRoutes;
    }
}
