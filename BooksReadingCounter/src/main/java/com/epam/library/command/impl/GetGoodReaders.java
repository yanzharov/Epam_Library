package com.epam.library.command.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.StatisticService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class GetGoodReaders implements Command{
	private final static String GETTING_GOOD_READERS_ERROR = "Getting good readers error";
	public final static Logger logger = Logger.getLogger(GetGoodReaders.class);
	
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		StatisticService statisticService = factory.getStatisticService();
		Map<String, Integer> goodReaders = null;
        Response response=new Response();
        
		try {
			goodReaders = statisticService.getBooksReadMoreThenOne();
			response.setGoodReaders(goodReaders);
		}
		catch (ServiceException e) {
			logger.error(GETTING_GOOD_READERS_ERROR);
			response.setErrorMessage(GETTING_GOOD_READERS_ERROR);
		}
        
		return response;
	}
    
}
