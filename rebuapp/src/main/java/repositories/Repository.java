package repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import utils.JPAUtils;

@SuppressWarnings("unchecked")
public class Repository<TEntity> {
    
    protected EntityManager manager;
    private Class<TEntity> persistentClass;
    
	public Repository() {
        this.persistentClass = (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        manager = JPAUtils.getEntityManager();
    }
    
    public void open() {
        manager = JPAUtils.getEntityManager();
    }
    
    public void close() {
    	JPAUtils.close();
    }
    
    public void insert(TEntity entity) {
        
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
    }
    
    public void update(TEntity entity) {
        
        manager.getTransaction().begin();
        manager.merge(entity);
        manager.getTransaction().commit();
        
    }
    
    public void delete(TEntity entity) {
        
        manager.getTransaction().begin();
        manager.remove(entity);
        manager.getTransaction().commit();
        
    }
    
    public TEntity find(int id) {
        
        TEntity entity = manager.find(this.persistentClass, id);
        
        return entity;
    }

    public List<TEntity> getAll() {
        
        Query query = manager.createQuery("from " + this.persistentClass.getName());
		List<TEntity> entities = query.getResultList();
        
        return entities;
    }
    
}

