package com.epam.library.dao.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.epam.library.dao.ConnectionInitDAO;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.exception.DAOException;

public class ConnectionInit implements ConnectionInitDAO{

	public void init() throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();

		try {
		    pool.init();
		} 
	    catch(ClassNotFoundException e){
	        throw new DAOException(e);
	    }
	    catch (SQLException e) {
		    throw new DAOException(e);
		} 
	    catch (IOException e) {
	        throw new DAOException(e);

	    }
		
	}

	public void destroy() throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
	        pool.close();
        } 
        catch (SQLException e) {
	        throw new DAOException(e);
	    }
		
	}

}
