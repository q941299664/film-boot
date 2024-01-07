package com.tao.demo.utils;

import lombok.Data;

import java.lang.reflect.ParameterizedType;

@Data
public class GenerosityOperationSuper<T> {
 
	/** 泛型的类型 */
	private Class<T> entityClass;
	
	
	public GenerosityOperationSuper(){
		BaseHibernateEntityDao();
	}
	
	@SuppressWarnings("unchecked")
	public void BaseHibernateEntityDao() {
		//System.out.println("classList +++++++++" + ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments());
		entityClass =(Class<T>) ((ParameterizedType) getClass()
	                                .getGenericSuperclass()).getActualTypeArguments()[0];
	}
 
}