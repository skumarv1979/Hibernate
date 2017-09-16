package com.skumarv.first;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = new Configuration().configure("com/skumarv/first/hibernate.cfg.xml")
				.buildSessionFactory().openSession();
		//Transaction t = session.beginTransaction();
		First fist = new First();
		fist.setUid(111);
		fist.setName("Saranakumar");
		fist.setSalary(1234.87);
		session.save(fist);
		
		fist = new First();
		fist.setUid(222);
		fist.setName("Kovardhan");
		fist.setSalary(9876.12);
		session.save(fist);
		
		fist = new First();
		fist.setUid(333);
		fist.setName("Kovendhan");
		fist.setSalary(6453.48);
		session.save(fist);
		//t.commit();
		//session.flush();
		session.close();
		System.out.println("success");
	}

}
