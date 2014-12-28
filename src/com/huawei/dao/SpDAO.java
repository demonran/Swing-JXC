package com.huawei.dao;

import internalFrame.guanli.Item;

import java.util.List;

import model.Spinfo;

public interface SpDAO {
	
	public List<Item> getSpItems() throws DAOException;
	public boolean add(Spinfo spinfo) throws DAOException;
	public List<Spinfo> findAll()throws DAOException;
	public Spinfo findByItem(Item item)throws DAOException;
	public List<Spinfo> findByGysname(String gysname)throws DAOException;
	public boolean update(Spinfo spinfo)throws DAOException;
	public boolean  delete(String id)throws DAOException;
	public Spinfo findByName(String name)throws DAOException;
	//public int getMaxId() throws DAOException;
}
