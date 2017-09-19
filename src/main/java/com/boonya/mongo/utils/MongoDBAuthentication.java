package com.boonya.mongo.utils;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
/**
 * 用户授权的MongoDB客户端封装
 * 
* @ClassName: MongoDBAuthentication
* @Description: TODO(这里用一句话描述这个类的作用)
* @author: pengjl
* @company: 上海势航网络科技有限公司
* @date 2017年7月7日 下午5:56:23
 */
//参考:http://mongodb.github.io/mongo-java-driver/3.4/driver/tutorials/authentication/
public class MongoDBAuthentication {
	
	/**
	 * 获取授权对象
	 * 
	* @Title: getMongoCredential
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param user
	* @param database
	* @param password
	* @return    设定文件
	* @return MongoCredential    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:51:23
	* @throws
	 */
	public MongoCredential getMongoCredential(String user,String database,char[] password){
		 return MongoCredential.createCredential(user, database, password);
	}
	
	/**
	 * 获取授权的MongoDB客户端
	 * 
	* @Title: getMongoClient
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param host
	* @param port
	* @param credential
	* @return    设定文件
	* @return MongoClient    返回类型
	* @author: pengjl
	* @date 2017年7月7日 下午5:53:50
	* @throws
	 */
	public MongoClient getMongoClient(String host,int port,MongoCredential credential){
		 return  new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
	}

}
