package com.skumarv.tps;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class StoreData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = new Configuration()
		.configure("com/skumarv/tps/hibernate.cfg.xml")
				.buildSessionFactory().openSession();
		Person person = new Person();
		person.setPid(111);
		person.setName("PersonName");
		person.setGender("Male");
		session.persist(person);
		
		Student student = new Student();
		student.setPid(222);
		student.setName("StudentName");
		student.setGender("FeMale");
		student.setYear(1);
		student.setDepartment("CSE");
		session.persist(student);
		
		Teacher teacher = new Teacher();
		teacher.setPid(333);
		teacher.setName("TeacherName");
		teacher.setGender("Male");
		teacher.setSalary(9547.64);
		teacher.setSubjectExpert("Data Structure");
		session.persist(teacher);
		
		session.flush();
		session.close();
	}

}
