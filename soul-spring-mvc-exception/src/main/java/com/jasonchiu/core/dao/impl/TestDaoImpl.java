package com.jasonchiu.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.jasonchiu.core.dao.TestDao;
import com.jasonchiu.core.exception.BusinessException;
import com.jasonchiu.core.exception.ParameterException;

@Repository("testDao")
public class TestDaoImpl implements TestDao {
	public void exception(Integer id) throws Exception {
		switch (id) {
		case 1:
			throw new BusinessException("12", "dao12");
		case 2:
			throw new BusinessException("22", "dao22");
		case 3:
			throw new BusinessException("32", "dao32");
		case 4:
			throw new BusinessException("42", "dao42");
		case 5:
			throw new BusinessException("52", "dao52");
		default:
			throw new ParameterException("Dao Parameter Error");
		}
	}
}
