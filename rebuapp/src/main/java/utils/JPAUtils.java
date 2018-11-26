package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    private static final EntityManagerFactory EMF = 
            Persistence.createEntityManagerFactory("Context");
    
    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
    
    public static void close() {
        EMF.close();
    }
    
}
