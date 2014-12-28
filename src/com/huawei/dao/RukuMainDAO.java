package com.huawei.dao;

import model.RukuMain;

public interface RukuMainDAO {
	public void save(RukuMain rukuMain)throws DAOException;
	public String getMaxId()throws DAOException;
}
