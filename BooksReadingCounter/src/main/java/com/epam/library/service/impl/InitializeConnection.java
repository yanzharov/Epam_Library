package com.epam.library.service.impl;

import com.epam.library.dao.ConnectionInitDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.service.InitializeConnectionService;
import com.epam.library.service.exception.ServiceException;

public class InitializeConnection implements InitializeConnectionService{

	public void init() throws ServiceException {
		DAOFactory factory=DAOFactory.getINSTANCE();
        ConnectionInitDAO conInitDAO=(ConnectionInitDAO)factory.getConnectionInitDAO();

        try{
            conInitDAO.init();
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
		
	}

	public void destroy() throws ServiceException {
		DAOFactory factory=DAOFactory.getINSTANCE();
        ConnectionInitDAO conInitDAO=(ConnectionInitDAO)factory.getConnectionInitDAO();
        
		try{
            conInitDAO.destroy();
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
		
	}

}
