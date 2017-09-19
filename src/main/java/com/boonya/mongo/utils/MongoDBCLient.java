package com.boonya.mongo.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
/**
 * 获取MongoDB客户端
 * 
* @ClassName: BMongoDBCLient
* @Description: TODO(这里用一句话描述这个类的作用)
* @author: pengjl
* @company: 上海势航网络科技有限公司
* @date 2017年7月7日 下午5:44:42
 */
//参考：http://mongodb.github.io/mongo-java-driver/3.4/driver/tutorials/connect-to-mongodb/
public class MongoDBCLient {
	
	/**
	 * 获取默认客户端
	* @Title: getMongoClient
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:29:05
	* @throws
	 */
	public static MongoClient getMongoClient(){
		return new MongoClient();
	}
	
	/**
	 * 根据主机获取客户端
	* @Title: getMongoClient
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:29:05
	* @throws
	 */
	public static MongoClient getMongoClient(String host){
		return new MongoClient(host);
	}
	
	/**
	 * 根据host默认27017访问
	 * 
	* @Title: getMongoClient
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param type
	* @param host
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:25:31
	* @throws
	 */
	public static MongoClient getMongoClient(int type,String host){
		MongoClient mongoClient=null;
		if(type<=0){
			return mongoClient;
		}
		switch (type) {
		case 1:
			mongoClient = new MongoClient(host);
			break;
		case 2:
			mongoClient = new MongoClient(host,27017);
			break;
		case 3:
			mongoClient = new MongoClient(new MongoClientURI("mongodb://"+host+":27017"));
			break;
		default:
			break;
		}
		return mongoClient;
	}
	
	/**
	 * 自定义host:port访问
	 * 
	* @Title: getMongoClient
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param type
	* @param host
	* @param port
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:25:31
	* @throws
	 */
	public static MongoClient getMongoClient(int type,String host,int  port){
		MongoClient mongoClient=null;
		if(type<=0){
			return mongoClient;
		}
		switch (type) {
		case 1:
			mongoClient = new MongoClient(host,port);
			break;
		case 2:
			mongoClient = new MongoClient(new MongoClientURI("mongodb://"+host+":"+port));
			break;
		default:
			break;
		}
		return mongoClient;
	}
	
	/**
	 * 获取多个Mongo副本（分片集合）访问客户端
	 * 
	* @Title: getMongoClientByReplicaSet
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param connectionStr
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:37:47
	* @throws
	 */
	public static MongoClient getMongoClientByReplicaSet(String connectionStr){
		//connectionStr="mongodb://host1:27017,host2:27017,host3:27017";
		//connectionStr="mongodb://host1:27017,host2:27017,host3:27017/?replicaSet=myReplicaSet";
	    return  new MongoClient(new MongoClientURI(connectionStr));
	}
	
	/**
	 * 获取分片集群客户端
	 * 
	* @Title: getMongoClientByShardCluster
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param host
	* @param port
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:42:11
	* @throws
	 */
	public static MongoClient getMongoClientByShardCluster(String host,int port){
	    return  new MongoClient( host , port );
	}

}
