package com.skumarv.hql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class HibernateHqlMain {

	public static void main(String[] args) {
		
		//List<Employee> employeeList = buildDemoEmployee();
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		//Get Session
		sessionFactory = new Configuration().configure("com/skumarv/hql/hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Session created");
		//start transaction
		tx = session.beginTransaction();
		//Save the Model object
		/*for (Employee employee2 : employeeList) {
			session.persist(employee2);			
		}*/
		Query query = session.createQuery("from Employee");
		List<Employee> lst = (List<Employee>)query.list();
		for (Employee employee : lst) {
			System.out.println(employee);
		}
		
		
		query = session.createQuery("from Employee where id= :id");
		query.setLong("id", 3);
		Employee emp = (Employee) query.uniqueResult();
		Iterator<Address> itr = emp.getAddresses().iterator();
		Address  addr = null;
		while(itr.hasNext()) {
			addr = itr.next();
			break;
		}
		System.out.println("Employee Name="+emp.getName()+", City="+addr.getCity());
		//Commit transaction
		
		query = session.createQuery("from Employee");
		query.setFirstResult(0); //starts with 0
		query.setFetchSize(2);
		lst = query.list();
		for(Employee emp4 : lst){
			itr = emp.getAddresses().iterator();
			addr = null;
			while(itr.hasNext()) {
				addr = itr.next();
				break;
			}
			System.out.println("Paginated Employees::"+emp4.getId()+","+addr.getCity());
		}
		
		query = session.createQuery("update Employee set name= :name where id= :id");
		query.setParameter("name", "SkumarV");
		query.setLong("id", 1);
		int result = query.executeUpdate();
		System.out.println("Employee Update Status="+result);
		
		query = session.createQuery("select sum(salary) from Employee");
		double sumSalary = (Double) query.uniqueResult();
		System.out.println("Sum of all Salaries= "+sumSalary);
		
		query = session.createQuery("select e.name, a.city from Employee e "
				+ "INNER JOIN e.addresses a");
		List<Object[]> list = query.list();
		for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}
		
		
		//HQL group by and like example
				query = session.createQuery("select e.name, sum(e.salary), count(e)"
						+ " from Employee e where e.name like '%i%' group by e.name");
				List<Object[]> groupList = query.list();
				for(Object[] arr : groupList){
					System.out.println(Arrays.toString(arr));
				}
				
				//HQL order by example
				query = session.createQuery("from Employee e order by e.id desc");
				lst = query.list();
				for(Employee emp3 : lst){
					itr = emp.getAddresses().iterator();
					addr = null;
					while(itr.hasNext()) {
						addr = itr.next();
						break;
					}
					System.out.println("ID Desc Order Employee::"+emp3.getId()+","+addr.getCity());
				}
				
				SQLQuery sqlquery = session.createSQLQuery("select {e.*}, {a.*} from Employee e join Address a ON e.emp_id=a.emp_id")
						.addEntity("e",Employee.class)
						.addJoin("a","e.addresses");
				List<Employee> rows = query.list();
				for (Employee row : rows) {
					Employee e = (Employee) row;
					System.out.println("Employee Info::"+e);
					/*Address a = (Address) row.;
					System.out.println("Address Info::"+a);*/
				}
				
				// Get with ID, creating new Criteria to remove all the settings
				Criteria criteria = session.createCriteria(Employee.class).add(Restrictions.eq("id", new Long(3)));
				emp = (Employee) criteria.uniqueResult();
				System.out.println("Employeeee=" + emp );
				
				//Projections example
				long count = (Integer) session.createCriteria(Employee.class)
						.setProjection(Projections.rowCount())
						.add(Restrictions.like("name", "%i%"))
						.uniqueResult();
				System.out.println("Number of employees with 'i' in name="+count);

				//using Projections for sum, min, max aggregation functions
				sumSalary = (Double) session.createCriteria(Employee.class)
					.setProjection(Projections.sum("salary"))
					.uniqueResult();
				System.out.println("Sum of Salaries="+sumSalary);
				
				criteria = session.createCriteria(Employee.class);
				criteria.setFetchMode("addresses", FetchMode.JOIN);
				criteria.createAlias("addresses", "address"); // inner join by default

				ProjectionList columns = Projections.projectionList()
								.add(Projections.property("name"))
								.add(Projections.property("address.city"));
				criteria.setProjection(columns);

				List<Object[]> listO = criteria.list();
				for(Object[] arr : listO){
					System.out.println(Arrays.toString(arr));
				}
				
				
				List<Object[]> cats = session.createCriteria(Employee.class)
					     .setProjection( Projections.projectionList()
					         .add( Projections.rowCount() )
					         .add( Projections.avg("salary") )
					         .add( Projections.max("salary") )
					         .add( Projections.min("salary") )
					         .add( Projections.groupProperty("id") )
					     )
					     .addOrder( Order.asc("id") )
					     .list();
				for (Object[] employee : cats) {
					System.out.println(Arrays.asList(employee));
				}
				tx.commit();
		
		}catch(Exception e){
			System.out.println("Exception occured. "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

	private static void printTransactionData(long id, SessionFactory sessionFactory) {
		Session session = null;
		Transaction tx = null;
		try{
			//Get Session
			sessionFactory = new Configuration().configure("com/skumarv/hql/hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			//start transaction
			tx = session.beginTransaction();
			//Save the Model object
			Employee cart = (Employee) session.get(Employee.class, id);
			//Commit transaction
			tx.commit();
			System.out.println("Transaction Details=\n"+cart);
			
			}catch(Exception e){
				System.out.println("Exception occured. "+e.getMessage());
				e.printStackTrace();
			}
	}
	private static List<Employee> buildDemoEmployee() {
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = new Employee();
		employee.setName("Saravanakumar");
		employee.setSalary(123.56);
		//txn.setId(222);
		Set<Address> set =new HashSet<Address>();
		Address address= new Address();
		address.setAddessLine("Address Line 1");
		address.setCity("Chennai");
		address.setZipcode("12397");
		address.setEmployee(employee);
		set.add(address);
		employee.setAddresses(set);
		employeeList.add(employee);
		
		set =new HashSet<Address>();
		employee = new Employee();
		employee.setName("Kovardhan");
		employee.setSalary(5674.43);
		address= new Address();
		address.setAddessLine("Address Line 2");
		address.setCity("Chennai");
		address.setZipcode("42345");
		address.setEmployee(employee);
		set.add(address);
		employee.setAddresses(set);
		employeeList.add(employee);
		
		set =new HashSet<Address>();
		employee = new Employee();
		employee.setName("Kovendhan");
		employee.setSalary(3222.45);
		address= new Address();
		address.setAddessLine("Address Line 3");
		address.setCity("Chennai");
		address.setZipcode("54677");
		address.setEmployee(employee);
		set.add(address);
		employee.setAddresses(set);
		employeeList.add(employee);

		set =new HashSet<Address>();
		employee = new Employee();
		employee.setName("Sachin");
		employee.setSalary(7644.44);
		address= new Address();
		address.setAddessLine("Address Line 4");
		address.setCity("Chennai");
		address.setZipcode("4444");
		address.setEmployee(employee);
		set.add(address);
		employee.setAddresses(set);
		employeeList.add(employee);

		set =new HashSet<Address>();
		employee = new Employee();
		employee.setName("Dhoni");
		employee.setSalary(7644.44);
		address= new Address();
		address.setAddessLine("Address Line 5");
		address.setCity("Chennai");
		address.setZipcode("5555");
		address.setEmployee(employee);
		set.add(address);
		employee.setAddresses(set);
		employeeList.add(employee);
		return employeeList;
	}

}
