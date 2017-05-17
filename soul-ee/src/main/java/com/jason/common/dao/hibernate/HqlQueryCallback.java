package com.jason.common.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

@SuppressWarnings("all")
public class HqlQueryCallback implements HibernateCallback {

	private String hql;
	
	private String[] lockAlias;
	public HqlQueryCallback(String hql) {
		this.hql = hql;
	}
	
	public HqlQueryCallback(String hql, Object[] param,String[] lockAlias) {
		this.hql = hql;
		this.param = param;
		this.lockAlias = lockAlias;
	}
	
	private Object[] param = null;
	
	public HqlQueryCallback(String hql, Object[] param) {
		this(hql);
		this.param = param;
	}
	private Map<String, Object> paramMap = null;
	
	public HqlQueryCallback(String hql, Map<String, Object> paramMap) {
		this(hql);
		this.paramMap = paramMap;
	}
	public Object doInHibernate(Session session) throws HibernateException, SQLException {
		 Query query = session.createQuery(hql);
	     if (param != null) {
	    	 for (int i = 0; i < param.length; i++) {
	    		 query.setParameter(i, param[i]);
	    	 }
	     }
	     if (paramMap != null) {
	    	 for (String key : paramMap.keySet()) {
	    		 query.setParameter(key, paramMap.get(key));
	    	 }
	     }
	     if(lockAlias != null){
	    	 for(String alias : lockAlias)
	    		 query.setLockMode(alias, LockMode.UPGRADE_NOWAIT);
	     }
	     List list = query.list();
	     return list;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public String getHql() {
		return hql;
	}

}
