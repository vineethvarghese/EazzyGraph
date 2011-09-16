package org.tenxperts;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;

import static org.neo4j.graphdb.traversal.Evaluators.atDepth;
import static org.tenxperts.Neo4jTryOutsTest.Edges.LINKED_TO;
import static org.tenxperts.Neo4jTryOutsTest.Edges.ROUTES;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 9/16/11
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Neo4jTryOutsTest {

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
        if (!dataInPlace()) {
            create();
        }
        query();
    }

    private boolean dataInPlace() {
        return graphDb.doInTx(new Work<Boolean>() {
            public Boolean doWork(GraphDatabaseService graphDatabaseService) {
                /**
                 * Create a traverser that starts searching from the root node for relationship ROUTES
                 * only till a depth 1
                 */
                TraversalDescription traversal = Traversal.description().
                        breadthFirst().
                        relationships(ROUTES, Direction.OUTGOING).
                        evaluator(atDepth(1));
                Traverser traverser = traversal.traverse(graphDatabaseService.getReferenceNode());
                for (Path path : traverser) {
                    return true;
                }
                return false;
            }
        });
    }

    private void create() {
        graphDb.doInTx(new Work<Void>() {
            public Void doWork(GraphDatabaseService graphDatabaseService) {
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
                bommanahalli.createRelationshipTo(koramangala,LINKED_TO);
                return null;
            }
        });

    }

    private void query() {
        graphDb.doInTx(new Work<Void>() {
            public Void doWork(GraphDatabaseService graphDatabaseService) {
                Node referenceNode = graphDatabaseService.getReferenceNode();
                org.neo4j.graphdb.Traverser traverser = referenceNode.traverse(org.neo4j.graphdb.Traverser.Order.BREADTH_FIRST,
                        StopEvaluator.END_OF_GRAPH, ReturnableEvaluator.ALL,
                        ROUTES, Direction.OUTGOING);
                for (Node node : traverser) {
                    if (node.hasProperty("name")) {
                        System.out.println(node.getProperty("name"));
                    } else {
                        System.out.println("No name for Node : " + node);
                    }
                }
                return null;
            }
        });
    }

    enum Edges implements RelationshipType {
        LINKED_TO,
        ROUTES;
    }




}



