package com.abbcc.groovy

import org.junit.Test

class GroovyTest {
	@Test
	void t(){
		def str="str2";
		println("print out ${str}")

		def y = new Person()
		y.setName str;
		println y["name"]

		List<Integer> list=new ArrayList();
		list.add 1;
		list.add('a');
		list << 'b'
		list.each {
			println it;
			y?.out(it);
		}
		
		def range=0..10;
		println(range)
		(0..9).each { print it }

		def closure = { param ->
			param+="sdf";
			println("hello ${param}")
		}
		closure.call("world!")
		def metaMethod = metaClass.getMetaMethod("t2");
		metaMethod.invoke this,closure
		println this.getT3()
		
		def e = new Expandable()
		e["foo"] = "bar"
		println e["foo"]
	}
	def t2(callback){
		callback("closure")
	}
	def getT3(){
		return "t";
	}
	def t3;
	class Person   {
		String name
		String getName(){
			if(name)
				return name;
			return "name";
		}
		void out(e){
		}
	}
	class Expandable {
		def storage = [:]
		def getProperty(String name) { storage[name] }
		void setProperty(String name, value) { storage[name] = value }
	}
	def invokeMethod(){
		println("invoked");
	}
}
