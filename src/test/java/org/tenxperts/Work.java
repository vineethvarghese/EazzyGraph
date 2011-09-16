package org.tenxperts;

import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/16/11
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
interface Work<T> {

     T doWork(GraphDatabaseService graphDatabaseService);

}

