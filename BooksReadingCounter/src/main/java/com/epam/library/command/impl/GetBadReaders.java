package com.epam.library.command.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Employee;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.StatisticService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class GetBadReaders implements Command{
	private final static String GETTING_BAD_READERS_ERROR = "Getting bad readers error";
	public final static Logger logger = Logger.getLogger(GetBadReaders.class);
	
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		StatisticService statisticService = factory.getStatisticService();
		Map<Employee, Integer> badReaders = null;
		Response response=new Response();
		
		try {
			badReaders = statisticService.getBooksReadLessThenTwo();
			response.setBadReaders(badReaders);
		}
		catch (ServiceException e) {
			logger.error(GETTING_BAD_READERS_ERROR);
			response.setErrorMessage(GETTING_BAD_READERS_ERROR);
		}

        return response;
	}

}
