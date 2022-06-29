package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.domain.Product;

public class MergeTest {

	public static void main(String[] args) {
		
		
		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Product prod,prod1=null;
		Transaction tx=null;
		
		//BootStrap hibernate
		cfg=new Configuration();
		
		//load cfgs file
		cfg.configure("/com/nt/cfgs/hibernate.cfgs.xml");
		
		//Build  session factory
		factory=cfg.buildSessionFactory();
		
		//create session
		ses=factory.openSession();
		
		//Save or update object
		prod=new  Product();
		
		prod.setPid(561);
		prod.setPname("Plastic Chairs");
		prod.setPrice(5000);
		prod.setQty(10);
		
		
		try {
			tx=ses.beginTransaction();
			prod1=(Product) ses.merge(prod);
			System.out.println("Prod1-->"+prod1.getPid()+" "+prod1.getPname()+" "+prod1.getPrice()+" "+prod1.getQty());
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
