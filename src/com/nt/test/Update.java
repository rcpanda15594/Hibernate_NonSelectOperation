package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.domain.Product;

public class Update {

	public static void main(String[] args) {
		

		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Product prod=null;
		Transaction tx=null;
		
		//BootStrap hibernate
		cfg=new Configuration();
		
		//load cfgs file
		cfg.configure("/com/nt/cfgs/hibernate.cfgs.xml");
		
		//Build  session factory
		factory=cfg.buildSessionFactory();
		
		//create session
		ses=factory.openSession();
		
		//Update object(Full object modificcation)
		prod=new  Product();
		
		prod.setPid(1001);
		prod.setPname("Gloden Chairs");
		prod.setPrice(8000);
		prod.setQty(20);
		
		
		try {
			tx=ses.beginTransaction();
			ses.update(prod);
			System.out.println("Prod-->"+prod.getPid()+" "+prod.getPname()+" "+prod.getPrice()+" "+prod.getQty());
		tx.commit();
		System.out.println("Object iis inserted/updated");
		}
		catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			System.out.println("object is not inserted/updated");
		}
		
		//close objs
		ses.close();
		factory.close();

		
		
	}

}
