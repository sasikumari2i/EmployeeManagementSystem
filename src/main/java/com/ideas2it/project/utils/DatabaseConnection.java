package com.ideas2it.project.utils;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.HibernateException;

public class DatabaseConnection {

    private DatabaseConnection() {
    }
    
    private static SessionFactory factory = null;

    /**
     * Open the Database Connection
     *
     * @return, Database connection
     */
    public static SessionFactory getSessionFactory() {
        try {
           // Configuration configuration = new Configuration().configure();
            //configuration.addResource("resources/Employee.hbm.xml");
            //configuration.addResource("resources/Address.hbm.xml");
            //ServiceRegistry servReg = new StandardServiceRegistryBuilder()
              //                    .applySettings(configuration.getProperties())
                //                  .build();
            factory = new Configuration().configure().buildSessionFactory();
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        return factory;
    }    
}
