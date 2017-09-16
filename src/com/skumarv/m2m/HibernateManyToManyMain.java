package com.skumarv.m2m;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateManyToManyMain {

	public static void main(String[] args) {
		
		List<Cart> cartList = buildDemoCart();
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		//Get Session
		sessionFactory = new Configuration().configure("com/skumarv/m2m/hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Session created");
		//start transaction
		tx = session.beginTransaction();
		//Save the Model object
		for (Cart cart : cartList) {
			session.persist(cart);			
		}
		//Commit transaction
		tx.commit();
		//System.out.println("Transaction ID="+cart.getId());
		//Get Saved Trasaction Data
		//printTransactionData(cart.getId(), sessionFactory);
		
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
			sessionFactory = new Configuration().configure("com/skumarv/m2m/hibernate.cfg.xml").buildSessionFactory();
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
	private static List<Cart> buildDemoCart() {
		//txn.setId(222);
		List<Cart> lst = new ArrayList<Cart>();
		Set<Items> items =new HashSet<Items>();
		Items items1= new Items();
		items1.setDescription("I1");
		items1.setPrice(123.76);;
		items.add(items1);
		
		Items items2= new Items();
		items2.setDescription("I2");
		items2.setPrice(785.48);
		items.add(items2);
		
		Cart cart1 = new Cart();
		cart1.setTotal(7865.76);
		cart1.setItems(items);
		
		Cart cart2 = new Cart();
		cart2.setTotal(123.98);
		cart2.setItems(items);
		
		Set<Cart> carts = new HashSet<Cart>();
		carts.add(cart1);
		carts.add(cart2);
		
		items1.setCarts(carts);
		items2.setCarts(carts);
		lst.add(cart1);
		lst.add(cart2);
		return lst;
	}

}
