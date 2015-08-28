package order.db;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.jdbc.Connection;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Connection getConnection(){
    	Context c;
		try {
			c = new InitialContext();
			DataSource ds = (DataSource)c.lookup("java:comp/env/jdbc/books");
	    	Connection con=  (Connection)ds.getConnection();
	    	return con;
		} catch (Exception e) {
			//  Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    	
    }

}