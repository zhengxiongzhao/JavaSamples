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
public class PagedQueryCallback implements HibernateCallback {

	private int offset = 0;
	
	private int num = 0;
	
	private String hql;
	
	private String[] lockAlias;
	
	public PagedQueryCallback(String hql, int offset, int num) {
		this.hql = hql;
		this.offset = offset;
		this.num = num;
	}
	
	public PagedQueryCallback(String hql, Object[] param, int offset, int num,String[] lockAlias) {
		this(hql, offset, num);
		this.param = param;
		this.lockAlias = lockAlias;
	}
	
	private Object[] param = null;
	
	public PagedQueryCallback(String hql, Object[] param, int offset, int num) {
		this(hql, offset, num);
		this.param = param;
	}
	
	private Map<String, Object> paramMap = null;
	
	public PagedQueryCallback(String hql, Map<String, Object> paramMap, int offset, int num) {
		this(hql, offset, num);
		this.paramMap = paramMap;
	}
	
	public Object doInHibernate(Session session) throws HibernateException, SQLException {
		 Query query = session.createQuery(hql);
	     query.setFirstResult(offset);
	     query.setMaxResults(num);
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
