package org.tenxperts;


import org.junit.*;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;
import scala.Int;

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
        create();
    }

    @After
    public void destroy() {
        graphDb.close();
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
        if (dataInPlace()) {
            return;
        }
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
                bommanahalli.createRelationshipTo(koramangala, LINKED_TO);
                return null;
            }
        });

    }

    @Test
    public void query() {
        graphDb.doInTx(new Work<Void>() {
            public Void doWork(GraphDatabaseService graphDatabaseService) {
                TraversalDescription traversalDescription = Traversal.description().
                        depthFirst().
                        relationships(LINKED_TO, Direction.OUTGOING).
                        relationships(ROUTES, Direction.OUTGOING).
                        evaluator(Evaluators.all());
                Traverser traverse = traversalDescription.traverse(graphDatabaseService.getReferenceNode());
                int pathCount = 1;
                for (Path path : traverse) {
                    System.out.println(" ************ Path " + pathCount + " ************" );
                    Iterable<Node> nodes = path.nodes();
                    for (Node node : nodes) {
                        System.out.println(node.getProperty("name", "Root NodeR"));
                    }
                    pathCount++;
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



