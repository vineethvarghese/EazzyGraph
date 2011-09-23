package org.tenxperts.eazzybus.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.tenxperts.eazzybus.domain.Route;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/23/11
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RouteRepository extends GraphRepository<Route>, NamedIndexRepository<Route> {
}
