package org.tenxperts;


import org.junit.*;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
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

    public static final String BUSSTOP = "busstop";
    public static final String NAME = "name";
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
//                /**
//                 * Create a traverser that starts searching from the root node for relationship ROUTES
//                 * only till a depth 1
//                 */
//                TraversalDescription traversal = Traversal.description().
//                        breadthFirst().
//                        relationships(ROUTES, Direction.OUTGOING).
//                        evaluator(atDepth(1));
//                Traverser traverser = traversal.traverse(graphDatabaseService.getReferenceNode());
//                for (Path path : traverser) {
//                    return true;
//                }
//                return false;
                Index<Node> busstop = graphDatabaseService.index().forNodes(BUSSTOP);
                Node node = busstop.get(NAME, "Electronic City").getSingle();
                return node != null;
            }
        });
    }

    private void create() {
        if (dataInPlace()) {
            return;
        }
        graphDb.doInTx(new Work<Void>() {
            public Void doWork(GraphDatabaseService graphDatabaseService) {
                Index<Node> busstop = graphDatabaseService.index().forNodes(BUSSTOP);

                Node ecity = createAndIndexNode(graphDatabaseService, busstop, "Electronic City");
                graphDatabaseService.getReferenceNode().createRelationshipTo(ecity, ROUTES);

                Node bommanahalli = createAndIndexNode(graphDatabaseService, busstop, "Bommanahalli");
                ecity.createRelationshipTo(bommanahalli, LINKED_TO);

                Node silkBoard = createAndIndexNode(graphDatabaseService, busstop, "Silk Board");
                bommanahalli.createRelationshipTo(silkBoard, LINKED_TO);


                Node hsr = createAndIndexNode(graphDatabaseService, busstop, "HSR");
                silkBoard.createRelationshipTo(hsr, LINKED_TO);

                Node madiwala = createAndIndexNode(graphDatabaseService, busstop, "Madiwala");
                silkBoard.createRelationshipTo(madiwala, LINKED_TO);

                Node koramangala = createAndIndexNode(graphDatabaseService, busstop, "Koramangala");
                madiwala.createRelationshipTo(koramangala, LINKED_TO);
                hsr.createRelationshipTo(koramangala, LINKED_TO);
                bommanahalli.createRelationshipTo(koramangala, LINKED_TO);
                return null;
            }
        });

    }

    private Node createAndIndexNode(GraphDatabaseService graphDatabaseService, Index<Node> index, String name) {
        Node node = graphDatabaseService.createNode();
        node.setProperty(NAME, name);
        index.add(node, NAME, name);
        return node;
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
                    System.out.println(" ************ Path " + pathCount + " ************");
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



