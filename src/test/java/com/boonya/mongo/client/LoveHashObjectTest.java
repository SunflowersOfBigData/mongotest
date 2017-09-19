package com.boonya.mongo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoveHashObjectTest {
	
	public static void main(String[] args) {
		List<Person> list=new ArrayList<Person>();
		
		Person p=new Person("1", "boonya", 20);
		Person p2=new Person("2", "boonya", 20);
		Person p3=new Person("3", "boonya", 21);
		Person p4=new Person("4", "boonya", 20);
		
		list.add(p);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		
		Map<String,Object> map=new HashMap<String, Object>();
		for (Person person : list) {
			if(!map.containsKey(""+person.toString().hashCode())){
				map.put(person.toString().hashCode()+"", person);
			}
		}
		
		for (Object  person : map.values()) {
			Person uniquePerson=(Person) person;
			System.out.println(uniquePerson.getId()+" "+uniquePerson);
		}
	}
	

}
class Person{
	private String id;
	private String name;
	private int age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [" + (name != null ? "name=" + name + ", " : "")
				+ "age=" + age + "]";
	}
	
	
}