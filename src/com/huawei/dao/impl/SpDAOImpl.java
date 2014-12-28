package com.huawei.dao.impl;

import hibernate.utils.HibernateSessionFactory;
import internalFrame.guanli.Item;

import java.util.ArrayList;
import java.util.List;

import model.Gysinfo;
import model.Spinfo;

import org.hibernate.Query;
import org.hibernate.Session;

import com.huawei.dao.DAOException;
import com.huawei.dao.SpDAO;

public class SpDAOImpl implements SpDAO{

	@Override
	public List<Item> getSpItems() throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String hql = "select new Spinfo(id,spname) from Spinfo";
		Query query = session.createQuery(hql);
		List<Spinfo> list = query.list();
		HibernateSessionFactory.closeSession();
		List<Item> items = new ArrayList<Item>();
		for(Spinfo spinfo : list){
			Item item = new Item();
			item.setId(spinfo.getId());
			item.setName(spinfo.getSpname());
			items.add(item);
		}
		return items;
	}

	@Override
	public boolean add(Spinfo spinfo) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Spinfo> findAll() throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Spinfo";
		Query query = session.createQuery(hql);
		List<Spinfo> list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public Spinfo findByItem(Item item) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Spinfo spinfo) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Spinfo findByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spinfo> findByGysname(String gysname) throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Spinfo where gysname=:gysname";
		Query query = session.createQuery(hql);
		query.setParameter("gysname", gysname);
		List<Spinfo> list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

}
