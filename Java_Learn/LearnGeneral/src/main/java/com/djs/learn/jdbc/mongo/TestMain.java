
package com.djs.learn.jdbc.mongo;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestMain
{
	private static final String DB_ADDRESS = "192.168.0.36";
	private static final int DB_PORT = 27017;
	private static final String DB_NAME = "test";
	private static final String DB_USER_NAME = "test";
	private static final String DB_PASSWORD = "test";
	private static final String COLLECTION_NAME = "word_stats";
	private MongoClient mongoClient = null;
	private MongoDatabase mongoDB = null;
	private MongoCollection<Document> mongoCollection = null;

	public void createConnection() throws Exception{
		// mongoClient = new MongoClient(DB_ADDRESS, DB_PORT);

		MongoCredential credential = MongoCredential.createCredential(DB_USER_NAME, DB_NAME, DB_PASSWORD.toCharArray());
		System.out.println("MongoCredential = " + credential);

		mongoClient = new MongoClient(new ServerAddress(DB_ADDRESS, DB_PORT), Arrays.asList(credential));

		System.out.println("Create MongoClient = " + mongoClient);
	}

	public void closeConnection() throws Exception{
		mongoClient.close();

		System.out.println("Close MongoClient = " + mongoClient);
	}

	public void createDB() throws Exception{
		mongoDB = mongoClient.getDatabase(DB_NAME);

		System.out.println("DB = " + mongoDB);
	}

	public void getConnection() throws Exception{
		mongoCollection = mongoDB.getCollection(COLLECTION_NAME);

		System.out.println("Collection = " + mongoCollection);
	}

	public void testFind_1() throws Exception{
		FindIterable<Document> iterable = mongoCollection.find();

		System.out.println("FindIterable = " + iterable);

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document){
				System.out.println("Document = " + document.toJson());
			}
		});

		System.out.println("----------------------------------------");

		MongoCursor<Document> cursor = iterable.iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println("Document = " + cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
	}

	public static void main(String[] args) throws Exception{
		TestMain test = new TestMain();

		test.createConnection();

		test.createDB();
		test.getConnection();

		System.out.println("------------------------------------------------------------");

		test.testFind_1();

		System.out.println("------------------------------------------------------------");

		test.closeConnection();
	}
}
