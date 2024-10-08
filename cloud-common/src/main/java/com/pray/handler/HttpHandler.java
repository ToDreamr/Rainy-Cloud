package com.pray.handler;

import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pray.entity.Result;
import com.pray.exception.CloudServiceException;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
		if (serverResponseEntity == null) {
			logger.info("print obj is null");
			return;
		}

		ServletRequest requestAttributes = (ServletRequest) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes == null) {
			logger.error("requestAttributes is null, can not print to web");
			return;
		}
		HttpServletResponse response = (HttpServletResponse) requestAttributes.getAsyncContext().getResponse();
		if (response == null) {
			logger.error("httpServletResponse is null, can not print to web");
			return;
		}
		logger.error("response error: " + serverResponseEntity.getMessage());
		response.setCharacterEncoding(CharsetUtil.UTF_8);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.write(objectMapper.writeValueAsString(serverResponseEntity));
		}
		catch (IOException e) {
			throw new CloudServiceException("io 异常", e);
		}
	}

}
