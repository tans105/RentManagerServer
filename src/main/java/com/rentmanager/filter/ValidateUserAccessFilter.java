package com.rentmanager.filter;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rentmanager.constants.Constants;
import com.rentmanager.entity.Module;
import com.rentmanager.entity.dto.GenericResponseDTO;
import com.rentmanager.service.FilterService;
import com.rentmanager.service.GenericService;

/**
 * @author : tanmay
 * @created : 27-Jun-2017
 */
public class ValidateUserAccessFilter extends GenericFilterBean {
	private static Logger logger = LoggerFactory.getLogger(ValidateUserAccessFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		Boolean isUserAuthorized = false;
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		if (Constants.OPTIONS.equals(request.getMethod())) {
			chain.doFilter(req, res);
		} else {
			String requestUrl = request.getRequestURL().toString();
			String requestUrlAfterApi = requestUrl.substring(requestUrl.lastIndexOf("/api/") + 5);
			String moduleRequestId = null;
			for (int i = 0; i < requestUrlAfterApi.length(); i++) {
				if (requestUrlAfterApi.charAt(i) == '/') {
					moduleRequestId = requestUrlAfterApi.substring(0, i);
					break;
				}
			}
			logger.debug("Module Request Id :" + moduleRequestId);
			final Claims claims = (Claims) request.getAttribute("claims");
			GenericService service = new GenericService();
			List<Module> moduleList = service.readModuleList(Integer.parseInt(claims.get(Constants.ROLE_ID).toString()));
			if (!Strings.isNullOrEmpty(moduleRequestId)) {
				for (Module module : moduleList) {
					if (moduleRequestId.equals(module.getModuleRequestId())) {
						isUserAuthorized = true;
					}
				}
			}
			if (isUserAuthorized) {
				logger.debug("User authorized to access data");
				chain.doFilter(req, res);
			} else {
				FilterService filterService = new FilterService();
				Gson gson = new Gson();
				GenericResponseDTO genResponse = new GenericResponseDTO();
				logger.error("Unauthorized User Access :" + claims.get(Constants.USER_ID));
				filterService.setHttpResponse(gson, genResponse, response, Constants.UNAUTHORIZED_ACCESS, Boolean.FALSE, HttpStatus.UNAUTHORIZED.value());
			}

		}
	}
}
