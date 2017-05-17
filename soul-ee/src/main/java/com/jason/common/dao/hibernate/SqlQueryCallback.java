package com.jason.common.dao.hibernate;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
@SuppressWarnings("all")
public class SqlQueryCallback implements HibernateCallback{

	String sql = null;
	
	Map<String, Object> entities = new LinkedHashMap<String, Object> ();
	
	Map<String, Type> scalars = new LinkedHashMap<String, Type> ();
	
	public SqlQueryCallback(String sql, Object[] param) {
		this.sql = sql;
		this.param = param;
	}
	
	public SqlQueryCallback(String sql, Map<String, Type> scalar,Map<String, Object> entity, Object[] param) {
		this.sql = sql;
		if(scalar != null)
			scalars.putAll(scalar);
		if(entity != null)
			entities.putAll(entity);
		this.param = param;
	}
	
	public SqlQueryCallback(String sql, Class entity, Object[] param) {
		this.sql = sql;
		entities.put(entity.getName(), entity.getName());
		this.param = param;
	}
	
	public SqlQueryCallback(String sql, Class[] entity, Object[] param) {
		this.sql = sql;
		this.param = param;
		for (int i = 0; i < entity.length; i++)
			entities.put(entity[i].getName(), entity[i].getName());
	}
	
	public SqlQueryCallback(String sql, String entity, Object[] param) {
		this.sql = sql;
		entities.put(entity, entity);
		this.param = param;
	}
	
	public SqlQueryCallback(String sql, String[] entity, Object[] param) {
		this.sql = sql;
		this.param = param;
		for (int i = 0; i < entity.length; i++)
			entities.put(entity[i], entity[i]);
	}
	
	public SqlQueryCallback(String sql, Map<String, Object> entity, Object[] param) {
		this.sql = sql;
		entities.putAll(entity);
		this.param = param;
	}
	
	private Object[] param = null;
	
	public Object doInHibernate(Session session) throws HibernateException, SQLException {
		SQLQuery query = session.createSQLQuery(sql);
		for (Map.Entry<String, Object> entry : entities.entrySet()) {
			if (entry.getValue() instanceof String)
				query.addEntity(entry.getKey(), (String) entry.getValue());
			else if (entry.getValue() instanceof Class)
				query.addEntity(entry.getKey(), (Class) entry.getValue());
		}
		for (Map.Entry<String, Type> scalar : scalars.entrySet()) {
			if (scalar.getValue() instanceof Type)
				query.addScalar(scalar.getKey(), (Type) scalar.getValue());
		}
		if (param != null) {
	    	 for (int i = 0; i < param.length; i++) {
	    		 query.setParameter(i, param[i]);
	    	 }
	     }
		return query.list();
	}

}
