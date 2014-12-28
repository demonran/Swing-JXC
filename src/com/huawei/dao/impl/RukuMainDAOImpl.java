package com.huawei.dao.impl;

import hibernate.utils.HibernateSessionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.RukuMain;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.huawei.dao.DAOException;
import com.huawei.dao.RukuMainDAO;

public class RukuMainDAOImpl implements RukuMainDAO{
	
	@Override
	public void save(RukuMain rukuMain) throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(rukuMain);
		transaction.commit();
		HibernateSessionFactory.closeSession();
	}

	@Override
	public String getMaxId() throws DAOException {
		Session session = HibernateSessionFactory.getSession();
		String sql = "select max(RKID) from tb_ruku_main where RKID like :rkid";
		SQLQuery query = session.createSQLQuery(sql);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		String id = "RK"+date;
		query.setParameter("rkid", id+"%");
		List list = query.list();
		String baseId = null;
		if(list !=null && list.size()>0){
			baseId = (String)list.get(0);
		}
		baseId =  baseId == null? "000":baseId.substring(baseId.length()-3);
		int idNum = Integer.parseInt(baseId)+1;
		return id+String.format("%03d", idNum);
	}
}
