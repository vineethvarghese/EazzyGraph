package org.tenxperts.eazzybus.service.impl;

import org.tenxperts.eazzybus.domain.Route;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 10/3/11
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "route")
public class RouteVO implements Route {

    private Long id;
    private String name;

    public RouteVO() {
    }

    public RouteVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
