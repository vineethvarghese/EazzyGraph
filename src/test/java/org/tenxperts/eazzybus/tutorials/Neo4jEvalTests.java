package org.tenxperts.eazzybus.tutorials;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class Neo4jEvalTests {

	private static final String GRAPH_DATA = "/home/selva/projects/tenXperts/EazzyGraph/data";
	GraphDatabaseService graphDB;
	long id;
	
	@Before
	public void setup() {
//		deleteRecursively(new File(GRAPH_DATA));
		graphDB  =  new EmbeddedGraphDatabase(GRAPH_DATA);
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("Shutting down...");
				graphDB.shutdown();
				System.out.printf("Removing database...");
			}
		});
		createDB();
    }
	
	public void removeDB(){
		File file = new File(GRAPH_DATA);
		deleteRecursively(file);
	}
	
	public void deleteRecursively(File file) {
		if ( !file.exists()){
			return;
		}
		if ( file.isDirectory()){
			for(File f:file.listFiles()){
				deleteRecursively(f);
			}
		}
		if(!file.delete()){
			throw new RuntimeException("Couldn't clean up the db offending file"+file.getName());
		}
	}

	public void createDB() {
		
		Transaction transaction = graphDB.beginTx();
		try {
			Node referenceNode = graphDB.getReferenceNode();
			
			Node anderson = graphDB.createNode();
			anderson.setProperty("name", "Thomas Anderson");	
			anderson.setProperty("age", 29);
			referenceNode.createRelationshipTo(anderson, Relations.NEO_NODE);
			
			Node trinity = graphDB.createNode();
			trinity.setProperty("name", "Trinity");	
			Relationship relAndeTri = anderson.createRelationshipTo(trinity, Relations.KNOWS);
			relAndeTri.setProperty("age", "3 days");

			Node morpheus = graphDB.createNode();
			morpheus.setProperty("name", "Morpheus");
			morpheus.setProperty("occupation","Total badass");
			morpheus.setProperty("rank", "Captain");
			Relationship relTriAndMor= anderson.createRelationshipTo(morpheus, Relations.KNOWS);
			
			Relationship relMorAndTri = morpheus.createRelationshipTo(trinity, Relations.KNOWS);
			relMorAndTri.setProperty("age", "12 years");
			
			Node cypher = graphDB.createNode();
			cypher.setProperty("name", "Cypher");
			cypher.setProperty("last name","Reagan");
			Relationship relMorAndRea= morpheus.createRelationshipTo(cypher, Relations.KNOWS);
			relMorAndRea.setProperty("disclosure", "public");
			
			Node agentSmith = graphDB.createNode();
			id = agentSmith.getId();
			agentSmith.setProperty("name", "Agent Smith");
			agentSmith.setProperty("language","C++");
			agentSmith.setProperty("version","1.0b");
			Relationship relCypAndSmi= cypher.createRelationshipTo(agentSmith, Relations.KNOWS);
			relCypAndSmi.setProperty("disclosure", "secret");
			relCypAndSmi.setProperty("age", "6 months");

			Node  theArchitect = graphDB.createNode();
			theArchitect.setProperty("name", "The Architect");
			agentSmith.createRelationshipTo(theArchitect, Relations.CODED_BY);
			
			

			transaction.success();
		} catch (Exception e) {

		}finally{
			transaction.finish();
		}
	}

	@Test
	public void read(){
		Transaction transaction = graphDB.beginTx();
		System.out.println("------------------------------------");
		try {
			for(Node node:graphDB.getAllNodes()){
				System.out.printf("Node Name:%s\n",node.getProperty("name",""));
			}
			transaction.success();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			transaction.finish();
		}
		System.out.println("------------------------------------");
		
	}
	
	
	public void getFrinds(){
//		Transaction transaction = graphDB.beginTx();
		Node node = graphDB.getNodeById(id);
		System.out.printf("Node Name:%s\n",node.getProperty("name",""));
//		transaction.success();
//		transaction.finish();
	}
	
	enum Relations implements RelationshipType{
		NEO_NODE,
		KNOWS,
		CODED_BY;
	}
}
