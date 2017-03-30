package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;

public interface ConnectionInitDAO {
	
	public void init() throws DAOException;

    public void destroy() throws DAOException;
    
}
