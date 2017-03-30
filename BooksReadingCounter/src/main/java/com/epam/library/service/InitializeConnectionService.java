package com.epam.library.service;

import com.epam.library.service.exception.ServiceException;

public interface InitializeConnectionService {
	
	void init() throws ServiceException;

    void destroy() throws ServiceException;
    
}
