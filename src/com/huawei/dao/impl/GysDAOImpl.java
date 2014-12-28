package com.huawei.dao.impl;

import hibernate.utils.HibernateSessionFactory;
import internalFrame.guanli.Item;

import java.util.ArrayList;
import java.util.List;

import model.Gysinfo;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.huawei.dao.DAOException;
import com.huawei.dao.GysDAO;

public class GysDAOImpl implements GysDAO{
	
	@Override
	public boolean add(Gysinfo gys) throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction =  session.beginTransaction();
		session.save(gys);
		transaction.commit();
		HibernateSessionFactory.closeSession();
		return true;
	}

	@Override
	public List<Gysinfo> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gysinfo findByItem(Item item) throws DAOException {
		Gysinfo gys = null;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Gysinfo where name = :name and id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("name",item.getName());
		query.setParameter("id",item.getId());
		List<Gysinfo> list = query.list();
		if(list.size()>0){
			gys = list.get(0);
		}
		HibernateSessionFactory.closeSession();
		return gys;
	}

	@Override
	public boolean update(Gysinfo gys) throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(gys);
		transaction.commit();
		HibernateSessionFactory.closeSession();
		
		return true;
	}

	@Override
	public boolean delete(String id) throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Gysinfo gysinfo = new Gysinfo(id);
		session.delete(gysinfo);
		transaction.commit();
		HibernateSessionFactory.closeSession();
		return true;
	}

	@Override
	public Gysinfo findByName(String name) throws DAOException {
		Gysinfo gys = null;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Gysinfo where name = :name";
		Query query = session.createQuery(hql).setParameter("name", name);
		List<Gysinfo> list = query.list();
		if(list.size()>0){
			gys = list.get(0);
		}
		HibernateSessionFactory.closeSession();
		return gys;
	}

	@Override
	public int getMaxId() throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String sql = "select max(id) from tb_gysinfo";
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		HibernateSessionFactory.closeSession();
		String sid = list.get(0);
		if(sid == null){
			return 0;
		}
		
		return Integer.parseInt(sid.substring(3));
	}

	@Override
	public List<Item> getGysItems() throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String hql = "select new Gysinfo(id,name) from Gysinfo";
		Query query = session.createQuery(hql);
		List<Gysinfo>  list = query.list();
		HibernateSessionFactory.closeSession();
		List<Item> items = new ArrayList<Item>();
		for(Gysinfo gys : list){
			Item item = new Item();
			item.setId(gys.getId());
			item.setName(gys.getName());
			items.add(item);
		}
		return items;
	}

}
