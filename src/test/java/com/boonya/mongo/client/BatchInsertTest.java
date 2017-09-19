package com.boonya.mongo.client;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.junit.Test;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BatchInsertTest {
	
	
	/**
	 * 插入文档
	 */
	@Test
	public void addSubDate() throws Exception {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("10.10.13.251", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("rqwdemo");
		String dates[]={"20170801","20170802","20170803","20170804","20170805"};
		String datetimes[]={"2017-08-01 00:00:00","2017-08-02 00:00:00","2017-08-03 00:00:00","2017-08-04 00:00:00","2017-08-05 00:00:00"};
		System.out.println("Connect to database successfully");
		for (int i = 0; i < datetimes.length; i++) {
			List<Document> documents = new ArrayList<Document>();
			MongoCollection<Document> collection = mongoDatabase.getCollection("T_SUBTABLE_DATE_"+dates[i]);
			for (int j = 0; j < 1000; j++) {
			    Document document = new Document("F_SEX", "male").append("F_NAME", "boonya").append("F_TIME",datetimes[i]).append("F_RANDOM", Math.random());
				documents.add(document);
			}
			collection.insertMany(documents);
		}
		System.out.println("Data has inserted successfully");
		
	}

}
