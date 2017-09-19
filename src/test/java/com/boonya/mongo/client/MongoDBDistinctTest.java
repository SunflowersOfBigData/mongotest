package com.boonya.mongo.client;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBDistinctTest {
	
	@Test
	public void addDocument() throws Exception {
		// 连接到 mongodb 服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongodb-first");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("people");
		Document document = new Document("_sex", "male").append("_name", "boonya").append("_table", "people").append("_description", "database");
		collection.insertOne(document);
		System.out.println("插入文档成功");
	}
	
	@Test
	public void searchFindCollection() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient();
		// 获取数据库(Database)
		MongoDatabase database = mongoClient.getDatabase("mongodb-first");
		// 获取数据库表(Collection)
		MongoCollection<Document> collection = database.getCollection("people");
		// 获取结果集
		FindIterable<Document> findIterable = collection.find();
		// 遍历结果集
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}

	}


}
