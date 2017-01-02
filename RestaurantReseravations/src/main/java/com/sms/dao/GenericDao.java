package com.sms.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class GenericDao<T>{

	@Autowired
	private SessionFactory sessionFactory;
	//@Autowired
	//private EntityManager manager;
    
	@SuppressWarnings("unchecked")
	@Transactional
	public T save(T entity){
      return (T) sessionFactory.getCurrentSession().save(entity);
	//	return (T) manager.merge(entity);
    }
	@Transactional
    public void delete(T entity){
      sessionFactory.getCurrentSession().delete(entity);
    }
	@Transactional
    public T get(Class<T> entityClass, Integer id){
      return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }
	@Transactional
    public void saveOrUpdate(T entity){
      sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Transactional
    public List<T> getAll(Class<T> entityClass) {
      return sessionFactory.getCurrentSession().createCriteria(entityClass).list();
    }
}
