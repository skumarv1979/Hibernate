package com.skumarv.o2m;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateOneToManyMain {

	public static void main(String[] args) {
		
		Cart cart = buildDemoCart();
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		//Get Session
		sessionFactory = new Configuration().configure("com/skumarv/o2m/hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Session created");
		//start transaction
		tx = session.beginTransaction();
		//Save the Model object
		session.persist(cart);
		//Commit transaction
		tx.commit();
		System.out.println("Transaction ID="+cart.getCart_id());
		//Get Saved Trasaction Data
		printTransactionData(cart.getCart_id(), sessionFactory);
		
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
			sessionFactory = new Configuration().configure("com/skumarv/o2m/hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
			//start transaction
			tx = session.beginTransaction();
			//Save the Model object
			Cart cart = (Cart) session.get(Cart.class, id);
			//Commit transaction
			tx.commit();
			System.out.println("Transaction Details=\n"+cart);
			
			}catch(Exception e){
				System.out.println("Exception occured. "+e.getMessage());
				e.printStackTrace();
			}
	}
	private static Cart buildDemoCart() {
		Cart cart = new Cart();
		//txn.setId(222);
		Set<Items> set =new HashSet<Items>();
		cart.setName("My Cart");
		Items items1= new Items();
		items1.setItemId("I1");
		items1.setQuantity(1);
		items1.setItemTotal(123.87);
		items1.setCart(cart);
		set.add(items1);
		
		items1= new Items();
		items1.setItemId("I2");
		items1.setQuantity(2);
		items1.setItemTotal(785.48);
		items1.setCart(cart);
		set.add(items1);
		
		cart.setTotal(1*123.87+2*785.48);
		cart.setItems(set);
		
		return cart;
	}

}
