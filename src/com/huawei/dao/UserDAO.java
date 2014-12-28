package com.huawei.dao;

import model.User;

public interface UserDAO {
	public User getUserByUsernameAndPassword(String username,String password) throws DAOException;
}
