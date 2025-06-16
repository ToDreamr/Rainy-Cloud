package com.pray.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pray.entity.Result;
import com.pray.exception.CloudServiceException;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author FrozenWatermelon
 * @date 2020/7/16
 */
@Component
public class HttpHandler {

	private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

	@Resource
	private ObjectMapper objectMapper;

	public <T> void printServerResponseToWeb(Result<T> serverResponseEntity) {
		ServletRequest requestAttributes = (ServletRequest) RequestContextHolder
				.getRequestAttributes();
		logger.error("response error: " + serverResponseEntity.getMessage());
		PrintWriter printWriter = null;
		try {
			printWriter.write(objectMapper.writeValueAsString(serverResponseEntity));
		}
		catch (IOException e) {
			throw new CloudServiceException("io 异常", e);
		}
	}

}
