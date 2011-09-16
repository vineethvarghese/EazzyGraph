package org.tenxperts;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class GraphDb {
    GraphDatabaseService graphDb;

    public GraphDb() {
        graphDb = new EmbeddedGraphDatabase("/home/vineeth/Workspace/Neo4jDB/EazzyBus");
    }

    public <T> T doInTx(Work<T> work) {
        Transaction transaction = graphDb.beginTx();
        try {
            T obj = work.doWork(graphDb);
            transaction.success();
            return obj;
        } catch (Exception e) {
            transaction.failure();
            throw new RuntimeException(e);
        } finally {
            transaction.finish();
        }
    }

    public void close() {
        graphDb.shutdown();
    }
}