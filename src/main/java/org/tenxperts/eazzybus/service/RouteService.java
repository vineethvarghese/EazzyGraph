package org.tenxperts.eazzybus.service;

import org.tenxperts.eazzybus.domain.Route;
import org.tenxperts.eazzybus.domain.impl.RouteImpl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 10/2/11
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RouteService {

    List<Route> getAllRoutes();

    Route getRoute(Long id);
}
