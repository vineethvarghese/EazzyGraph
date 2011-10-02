package org.tenxperts.eazzybus.domain.impl;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.core.NodeBacked;
import org.tenxperts.eazzybus.domain.Route;

import javax.xml.bind.annotation.XmlRootElement;

@NodeEntity
public class RouteImpl implements NodeBacked, Route {

    @Indexed
    private Long id;

    @Indexed
    private String name;

    public RouteImpl() {
    }

    public RouteImpl(Long id, String name) {
        this.name = name;
        this.id = id;
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
