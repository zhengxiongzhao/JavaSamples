package com.jason.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.jason.common.dao.hibernate.HqlQueryCallback;
import com.jason.common.dao.hibernate.PagedQueryCallback;
import com.jason.common.dao.hibernate.SqlQueryCallback;

/**
 * @ClassName: BaseDAO
 * @Description: TODO(..)
 * @author Jason Chiu:CIHUnKnown@Gmail.com
 * @date 2013-8-17 下午04:53:27
 * 
 */
@SuppressWarnings("all")
public class BaseDAO
{

	private HibernateTemplate hibernateTemplate;
	private JdbcTemplate jdbcTemplate;

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public void delete(Object entity)
	{
		hibernateTemplate.delete(entity);
	}

	public Object get(Class entityClass, Serializable id)
	{
		return hibernateTemplate.get(entityClass, id);
	}

	public Object load(String entityName, Serializable id)
	{
		return hibernateTemplate.load(entityName, id);
	}

	public List hqlFind(String hql)
	{
		return (List) hibernateTemplate.execute(new HqlQueryCallback(hql));
	}

	public List hqlFind(String hql, Object[] params)
	{
		return hibernateTemplate.execute(new HqlQueryCallback(hql, params));
	}

	public List hqlFind(String hql, Object[] params, String[] lockAlias)
	{
		return hibernateTemplate.execute(new HqlQueryCallback(hql, params,
				lockAlias));
	}

	public List hqlFind(String hql, Object[] param, int offset, int num,
			String[] lockAlias)
	{
		return hibernateTemplate.execute(new PagedQueryCallback(hql, param,
				offset, num, lockAlias));
	}

	public List hqlFind(String hql, int offset, int num)
	{
		return hibernateTemplate.execute(new PagedQueryCallback(hql, offset,
				num));
	}

	public List hqlFind(String hql, Object[] param, int offset, int num)
	{
		return hibernateTemplate.execute(new PagedQueryCallback(hql, param,
				offset, num));
	}

	public List hqlFind(String hql, Map<String, Object> paramMap, int offset,
			int num)
	{
		return hibernateTemplate.execute(new PagedQueryCallback(hql, paramMap,
				offset, num));
	}

	public List sqlFind(String sql, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, param));
	}

	public List sqlFind(String sql, Map<String, Type> scalar,
			Map<String, Object> entity, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, scalar,
				entity, param));
	}

	public List sqlFind(String sql, Class entity, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, entity,
				param));
	}

	public List sqlFind(String sql, Class[] entity, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, entity,
				param));
	}

	public List sqlFind(String sql, String entity, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, entity,
				param));
	}

	public List sqlFind(String sql, String[] entity, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, entity,
				param));
	}

	public List sqlFind(String sql, Map<String, Object> entity, Object[] param)
	{
		return hibernateTemplate.execute(new SqlQueryCallback(sql, entity,
				param));
	}

	public List<Map<String, Object>> queryForList(String sql)
	{
		return jdbcTemplate.queryForList(sql);
	}

	public List queryForList(String sql, Class elementType)
	{
		return jdbcTemplate.queryForList(sql, elementType);
	}

	public List<Map<String, Object>> queryForList(String sql, Object... args)
	{
		return jdbcTemplate.queryForList(sql, args);
	}

	public List queryForList(String sql, Class elementType, Object... args)
	{
		return jdbcTemplate.queryForList(sql, elementType, args);
	}

	public List queryForList(String sql, Object[] args, Class elementType)
	{
		return jdbcTemplate.queryForList(sql, args, elementType);
	}

	public List<Map<String, Object>> queryForList(String sql, Object[] args,
			int[] argTypes)
	{
		return jdbcTemplate.queryForList(sql, args, argTypes);
	}

	public List<Map<String, Object>> queryForList(String sql, Object[] args,
			int[] argTypes, Class elementType)
	{
		return jdbcTemplate.queryForList(sql, args, argTypes, elementType);
	}

	public int queryForInt(String sql)
	{
		return jdbcTemplate.queryForInt(sql);
	}

	public int queryForInt(String sql, Object... args)
	{
		return jdbcTemplate.queryForInt(sql, args);
	}

	public int queryForInt(String sql, Object[] args, int[] argTypes)
	{
		return jdbcTemplate.queryForInt(sql, args, argTypes);
	}

	/**
	 * 测试jdbc的事务
	 * 
	 * @throws Exception
	 */
	public void test() throws Exception
	{
		String sql = "insert into test (id,name) values(7,'5')";
		String sql1 = "insert into test (id) values(8)";
		jdbcTemplate.execute(sql);
		jdbcTemplate.execute(sql1);
		System.out.println("OVER!");
	}

	/**
	 * 测试hibernate的事务
	 * 
	 * @throws Exception
	 */
	public void test1()
	{
		/*
		 * TFloor t1 = new TFloor(); t1.setfFloorId("1"); TFloor t2 = new
		 * TFloor(); hibernateTemplate.save(t1); hibernateTemplate.save(t2);
		 * System.out.println("OVER!");
		 */
	}

	public static void main(String[] args) throws ClassNotFoundException
	{

	}

}