package com.huawei.dao.impl;

import hibernate.utils.HibernateSessionFactory;

import java.util.List;

import model.User;

import org.hibernate.Query;
import org.hibernate.Session;

import com.huawei.dao.DAOException;
import com.huawei.dao.UserDAO;

public class UserDAOImpl implements UserDAO{

	@Override
	public User getUserByUsernameAndPassword(String username,String password) throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from User where username = :username and password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> list = query.list();
		if(list.size() >0){
			return list.get(0);
		}
		return null;
	}

}
