package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
private static final  SessionFactory sessionFactory = buldSessionFactory();
    private static SessionFactory buldSessionFactory(){

        try {
            return  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutdown(){
        getSessionFactory().close();
    }
}
