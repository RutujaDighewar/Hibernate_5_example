/**
 * 
 */
package com.app.client;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.app.entity.Employee;
import com.app.utility.HibernateUtility;

/**
 * @author Rutuja
 *
 */
public class Test {
	
	Scanner sc=new Scanner(System.in);
	
	public void save() {
		Session session=HibernateUtility.getSessionFactory().openSession();
		System.out.println("How many employee want to add");
		int num=sc.nextInt();
		for(int i=0; i<num;i++) {
			Employee emp=new Employee();
			System.out.println("Enter name of employee");
			emp.setName(sc.next());
			session.save(emp);
		}
		session.beginTransaction().commit();
		System.out.println("Saved successfully");
	}
	
	
	public void findAll() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		CriteriaQuery<Employee> cq=session.getCriteriaBuilder().createQuery(Employee.class);
		cq.from(Employee.class);
		List<Employee> empList=session.createQuery(cq).getResultList();
		empList.forEach(System.out::println);
	}
	
	public Employee findOne() {
		Session session=HibernateUtility.getSessionFactory().openSession();
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=session.getCriteriaBuilder().createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		cq.where(builder.equal(root.get("id"),5));
		Employee emp=session.createQuery(cq).getSingleResult();
		System.out.println(emp);
		return emp;
	}
	
	public void delete() {
		Employee emp=findOne();
		Session session=HibernateUtility.getSessionFactory().openSession();
		System.out.println("Enter id to delete");
		int id=sc.nextInt();
		if(id==emp.getId()) {
			session.delete(emp);
			System.out.println("deleted succesfully");
		}else {
			System.out.println("id not match");
		}
		session.beginTransaction().commit();
	}

	public void update() {
		Session session=HibernateUtility.getSessionFactory().openSession();
	    
	    	Employee emp=session.get(Employee.class, 5);
	    	System.out.println("Enter employee id which want to update");
	    	int id=sc.nextInt();
	    	if(id==emp.getId()) {
	    		System.out.println("Enter name");
	    		emp.setName(sc.next());
	    		session.update(emp);
	    		System.out.println("Updated successfully");
	    	}else {
	    		System.out.println("id not match");
	    	}
	    
	    session.beginTransaction().commit();
	}
	
	
	public static void main(String[] args) {
            Test t=new Test();
           // t.save();
            t.findAll();
           // t.findOne();
            t.delete();
           // t.update();
            
	}

}
