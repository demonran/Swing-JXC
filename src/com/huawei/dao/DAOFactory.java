package com.huawei.dao;

import com.huawei.dao.impl.GysDAOImpl;
import com.huawei.dao.impl.RukuMainDAOImpl;
import com.huawei.dao.impl.SpDAOImpl;
import com.huawei.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static GysDAO gysDAO = new GysDAOImpl();
	private static UserDAO userDAO = new UserDAOImpl();
	private static SpDAO spDAO = new SpDAOImpl();
	private static RukuMainDAO rukuMainDAO = new RukuMainDAOImpl();
	
	
	public static RukuMainDAO getRukuMainDAO(){
		return rukuMainDAO;
	}
	
	public static GysDAO getGysDAO(){
		return gysDAO;
	}
	
	public static UserDAO getUserDAO(){
		return userDAO;
	}
	
	public static SpDAO getSpDAO(){
		return spDAO;
	}
}
