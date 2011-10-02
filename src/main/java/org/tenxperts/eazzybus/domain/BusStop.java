package org.tenxperts.eazzybus.domain;

import org.tenxperts.eazzybus.domain.impl.BusStopImpl;
import org.tenxperts.eazzybus.domain.impl.RouteImpl;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 10/3/11
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BusStop<B extends BusStop, R extends Route> {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Set<B> getFromStops();

    void setFromStops(Set<B> fromStops);

    Set<B> getToStops();

    void setToStops(Set<B> toStops);

    Set<R> getServicedByRoutes();

    void setServicedByRoutes(Set<R> servicedByRoutes);
}
