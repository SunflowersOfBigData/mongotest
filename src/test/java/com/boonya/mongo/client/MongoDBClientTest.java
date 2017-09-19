package com.boonya.mongo.client;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.junit.Test;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
//pom:http://mongodb.github.io/mongo-java-driver/
//参考：https://docs.mongodb.com/v3.0/applications/crud/
//参考：http://mongodb.github.io/mongo-java-driver/3.4/driver/
//参考：http://www.runoob.com/mongodb/mongodb-tutorial.html
public class MongoDBClientTest {

	/**
	 * 数据库对应的集合列表
	 */
	@Test
	public void searchCollections() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient();
		// 获取数据库(Database)
		MongoDatabase database = mongoClient.getDatabase("mongo-test-db");
		// 获取数据库表(Collection)
		for (String name : database.listCollectionNames()) {
			System.out.println(name);
		}
	}
	
	/**
	 * 无密码访问
	 */
	@Test
	public void connectToMongoNoPasswd() throws Exception {
		// 连接到 mongodb 服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		@SuppressWarnings("unused")
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
	}

	/**
	 * 密码访问
	 */
	@Test
	public void connectToMongoByPasswd() throws Exception {
		// ServerAddress()两个参数分别为 服务器地址 和 端口
		ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
		List<ServerAddress> addrs = new ArrayList<ServerAddress>();
		addrs.add(serverAddress);
		// MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
		MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName",
				"password".toCharArray());
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(credential);
		// 通过连接认证获取MongoDB连接
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(addrs, credentials);
		// 连接到数据库
		@SuppressWarnings("unused")
		MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
		System.out.println("Connect to database successfully");
	}

	/**
	 * 创建集合
	 */
	@Test
	public void createCollection() throws Exception {
		// 连接到 mongodb 服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
		//mongoDatabase.createCollection("people");
		//mongoDatabase.createCollection("t_subtable");
		/*int [] arrays=new int []{20170730,20170731,20170801,20170802,20170803,20170804,20170805};
		for (int i = 0,j=arrays.length; i < j; i++) {
			mongoDatabase.createCollection("t_subtable_"+arrays[i]);
		}*/
		int [] arrays=new int []{1,2,3,4,5};
		for (int i = 0,j=arrays.length; i < j; i++) {
			mongoDatabase.createCollection("t_subtable_"+arrays[i]);
		}
		System.out.println("集合创建成功");
	}

	/**
	 *查询集合数据
	 */
	@Test
	public void searchCollectionData() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient();
		// 获取数据库(Database)
		MongoDatabase database = mongoClient.getDatabase("mongo-test-db");
		// 获取数据库表(Collection)
		MongoCollection<Document> collection = database.getCollection("users");
		// 获取结果集
		FindIterable<Document> findIterable = collection.find();
		// 遍历结果集
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}

	}

	/**
	 * 插入文档
	 */
	@Test
	public void addDocument() throws Exception {
		// 连接到 mongodb 服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("t_subtable");
		System.out.println("集合 test 选择成功");
		// 插入文档
		/**
		 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document> 3.
		 * 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用
		 * mongoCollection.insertOne(Document)
		 **/
		Document document = new Document("title", "mongo").append("name", "boonya").append("table", "t_subtable").append("description", "database").append("likes", 100)
				.append("by", "Fly");
		/*List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		System.out.println("文档插入成功");
		collection.insertMany(documents);*/
		collection.insertOne(document);
		
		/*int [] arrays=new int []{20170730,20170731,20170801,20170802,20170803,20170804,20170805};
		for (int i = 0,j=arrays.length; i < j; i++) {
			String table="t_subtable_"+arrays[i];
			Document document2 = new Document("title", "mongo")
					.append("name", "boonya")
					.append("table", table)
					.append("description", "database")
					.append("likes", 100)
					.append("by", "Fly");
			MongoCollection<Document> collection2 = mongoDatabase.getCollection(table);
			collection2.insertOne(document2); 
		}*/
		int [] arrays=new int []{1,2,3,4,5};
		for (int i = 0,j=arrays.length; i < j; i++) {
			String table="t_subtable_"+arrays[i];
			Document document2 = new Document("title", "mongo")
					.append("name", "boonya")
					.append("table", table)
					.append("description", "database")
					.append("likes", 100)
					.append("by", "Fly");
			MongoCollection<Document> collection2 = mongoDatabase.getCollection(table);
			collection2.insertOne(document2); 
		}
		
	}
	
	/**
	 * 插入文档
	 */
	@Test
	public void addDocument2() throws Exception {
		// 连接到 mongodb 服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("10.10.13.251", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("rqwdemo");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("people");
		System.out.println("集合 test 选择成功");
		// 插入文档
		/**
		 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document> 3.
		 * 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用
		 * mongoCollection.insertOne(Document)
		 **/
		List<Document> documents = new ArrayList<Document>();
		
		for (int i = 0; i < 1000; i++) {
		    Document document = new Document("_sex", "male").append("_name", "boonya").append("_table", "people").append("_description", "database");
			documents.add(document);
		}
		
		
//		System.out.println("文档插入成功");
		collection.insertMany(documents);
//		collection.insertOne(document);
		
	}
	
	
	/**
	 * 检索所有文档
	 */
	@Test
	public void searchDocument() throws Exception {
		// 连接到 mongodb服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("t_subtable_20170730");
		System.out.println("集合 t_subtable 选择成功");
		// 检索所有文档
		/**
		 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
		 * 通过游标遍历检索出的文档集合
		 **/
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}
	}

	/**
	 * 检索所有文档
	 */
	public void findDocument() throws Exception {
		// 连接到 mongodb服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		System.out.println("集合 test 选择成功");
		// 检索所有文档
		/**
		 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
		 * 通过游标遍历检索出的文档集合
		 **/
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}
	}

	/**
	 * 更新文档
	 */
	public void updateDocument() throws Exception {
		// 连接到 mongodb服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		System.out.println("集合 test 选择成功");
		// 更新文档 将文档中likes=100的文档修改为likes=200
		collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
		// 检索查看结果
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}
	}

	/**
	 * 删除文档
	 */
	public void delDocument() throws Exception {
		// 连接到 mongodb 服务
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo-test-db");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		System.out.println("集合 test 选择成功");
		// 删除符合条件的第一个文档
		collection.deleteOne(Filters.eq("likes", 200));
		// 删除所有符合条件的文档
		collection.deleteMany(Filters.eq("likes", 200));
		// 检索查看结果
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}
	}

}
