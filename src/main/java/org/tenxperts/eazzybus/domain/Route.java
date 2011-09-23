package org.tenxperts.eazzybus.domain;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.core.NodeBacked;

@NodeEntity
public class Route implements NodeBacked {

    @Indexed
    private Long id;

    @Indexed
    private String name;

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
}
