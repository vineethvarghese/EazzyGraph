package org.tenxperts;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;

import static org.tenxperts.Neo4jTryOuts.Edges.LINKED_TO;
import static org.tenxperts.Neo4jTryOuts.Edges.ROUTES;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/16/11
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Neo4jTryOuts {

    GraphDb graphDb;

    @Before
    public void init() {
        graphDb = new GraphDb();
    }

    @After
    public void destroy() {
        graphDb.close();
    }

    @Test
    public void createAndQuery() {
        create();
        query();
    }
    private void create() {
        graphDb.doInTx(new Work() {
            public void doWork(GraphDatabaseService graphDatabaseService) {
                Node ecity = graphDatabaseService.createNode();
                ecity.setProperty("name", "Electronic City");
                graphDatabaseService.getReferenceNode().createRelationshipTo(ecity, ROUTES);

                Node bommanahalli = graphDatabaseService.createNode();
                bommanahalli.setProperty("name", "Bommanahalli");
                ecity.createRelationshipTo(bommanahalli, LINKED_TO);

                Node silkBoard = graphDatabaseService.createNode();
                silkBoard.setProperty("name", "Silk Board");
                bommanahalli.createRelationshipTo(silkBoard, LINKED_TO);

                Node hsr = graphDatabaseService.createNode();
                hsr.setProperty("name", "HSR");
                silkBoard.createRelationshipTo(hsr, LINKED_TO);

                Node madiwala = graphDatabaseService.createNode();
                madiwala.setProperty("name", "Madiwala");
                silkBoard.createRelationshipTo(madiwala, LINKED_TO);

                Node koramangala = graphDatabaseService.createNode();
                koramangala.setProperty("name", "Koramangala");
                madiwala.createRelationshipTo(koramangala, LINKED_TO);
                hsr.createRelationshipTo(koramangala, LINKED_TO);
            }
        });

    }

    private void query() {
        graphDb.doInTx(new Work() {
            public void doWork(GraphDatabaseService graphDatabaseService) {
                Node referenceNode = graphDatabaseService.getReferenceNode();
            }
        });
    }

    enum Edges implements RelationshipType {
        LINKED_TO,
        ROUTES;
    }




}



