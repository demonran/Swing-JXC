package com.huawei.dao;

import internalFrame.guanli.Item;

import java.util.List;

import model.Gysinfo;

public interface GysDAO {
	public boolean add(Gysinfo gys) throws DAOException;
	public List<Gysinfo> findAll()throws DAOException;
	public Gysinfo findByItem(Item item)throws DAOException;
	public boolean update(Gysinfo gys)throws DAOException;
	public boolean  delete(String id)throws DAOException;
	public Gysinfo findByName(String name)throws DAOException;
	public int getMaxId() throws DAOException;
	public List<Item> getGysItems() throws DAOException;
}
