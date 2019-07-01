package sample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnect {

    private Configuration cfg = null;
    private SessionFactory factory = null;
    private Session session = null;

    DatabaseConnect() {

        cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    public void startSession() {
        session = factory.openSession();
    }

    public void closeSession() {
        session.close();
    }

    public Session getSession() {
        return session;
    }
}
