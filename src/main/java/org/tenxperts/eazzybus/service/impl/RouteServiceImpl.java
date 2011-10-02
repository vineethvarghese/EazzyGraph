package org.tenxperts.eazzybus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tenxperts.eazzybus.domain.Route;
import org.tenxperts.eazzybus.domain.impl.RouteImpl;
import org.tenxperts.eazzybus.repository.RouteRepository;
import org.tenxperts.eazzybus.service.RouteService;
import org.tenxperts.eazzybus.service.TxService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 10/2/11
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
@TxService("routeService")
@Path("/routes")
@Produces({"application/json", "application/xml"})
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    @GET
    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<Route>();
        routes.add(new RouteVO(1l, "500"));
        routes.add(new RouteVO(2l, "501"));
        routes.add(new RouteVO(3l, "502"));
        routes.add(new RouteVO(4l, "503"));
        return routes;
    }

    @Override
    @GET
    @Path("/{id}")
    public Route getRoute(@PathParam("id") Long id) {
        return new RouteVO(2l, "344");
    }
}
