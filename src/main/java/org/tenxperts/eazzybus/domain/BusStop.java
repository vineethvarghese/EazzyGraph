package org.tenxperts.eazzybus.domain;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/21/11
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class BusStop {

    private String name;

    private Set<Path> fromPaths;

    private Set<Path> toPaths;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Path> getFromPaths() {
        return fromPaths;
    }

    public void setFromPaths(Set<Path> fromPaths) {
        this.fromPaths = fromPaths;
    }

    public Set<Path> getToPaths() {
        return toPaths;
    }

    public void setToPaths(Set<Path> toPaths) {
        this.toPaths = toPaths;
    }
}
