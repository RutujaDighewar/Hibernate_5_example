package com.app.utility;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.app.entity.Employee;

public class HibernateUtility {

	public static SessionFactory getSessionFactory() {
		
		Properties properties=new Properties();
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hiber5");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.connection.password", "root");
		properties.setProperty("hibernate.dialect;", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.id.new_generator_mappings", "false");
		
		StandardServiceRegistryBuilder registryBuilder=new StandardServiceRegistryBuilder();
		registryBuilder.applySettings(properties);
		
		ServiceRegistry serviceRegistry = registryBuilder.build();
		
		MetadataSources sources=new MetadataSources(serviceRegistry).addAnnotatedClass(Employee.class);
		
		Metadata metadata=sources.getMetadataBuilder().build();
		
		SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
		
		return sessionFactory;
		
	}
}
