package org.tenxperts.eazzybus.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.tenxperts.eazzybus.repository.BusStopRepository;

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

    @Test
    public void testBusStopCreation() {
        BusStop busStop = new BusStop();
        busStop.setId(1l);
        busStop.setName("Madiwala");
        busStop.persist();

        BusStop value = busStopRepository.findByPropertyValue("bus_stop_name", "name", "Madiwala");
    }
}
