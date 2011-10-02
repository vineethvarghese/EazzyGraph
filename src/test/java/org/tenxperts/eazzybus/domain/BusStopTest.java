package org.tenxperts.eazzybus.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tenxperts.eazzybus.domain.impl.BusStopImpl;
import org.tenxperts.eazzybus.repository.BusStopRepository;
import org.tenxperts.eazzybus.service.RouteService;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/23/11
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml"})
@Transactional
public class BusStopTest {

    @Autowired BusStopRepository busStopRepository;

    @Autowired
    RouteService routeService;

    @Test
    public void testBusStopCreation() {
        BusStopImpl busStop = new BusStopImpl();
        busStop.setId(1l);
        busStop.setName("Madiwala");
        busStop.persist();

        BusStopImpl value = busStopRepository.findByPropertyValue("bus_stop_name", "name", "Madiwala");
        ClosableIterable<BusStopImpl> byQuery = busStopRepository.findAllByQuery("bus_stop_name", "name", "M*");
        for (BusStopImpl b : byQuery) {
            System.out.println(b.getName());

        }

    }
}
